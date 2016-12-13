/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import apimethods.Sales;
import apimethods.Users;
import com.inkonic.refus.arch.RErrorImpl;
import com.inkonic.refus.arch.RSerializeImpl;
import com.inkonic.refus.core.RAPI;
import com.inkonic.refus.core.RAPIHandler;
import com.inkonic.refus.core.RDocumentation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author benoit.simonis
 */
public class API extends HttpServlet {

    /**
     *
     */
    public static final RAPI rapi;

    static {
        rapi = new RAPI("api_demo", "inkonic");

        begin();

    }

    public static void begin() {

        rapi.addRModel("Users", new Users());
        rapi.addRModel("Sales", new Sales());

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //System.out.println("PASSAGE");
        begin();
        try {
            rapi.setRserializer(new RSerializeImpl() {

                @Override
                public Object serialize(HttpServletRequest request, HttpServletResponse response, Object data) {
                    PrintWriter out = null;
                    try {
                        out = response.getWriter();
                        out.write("" + data);
                    } catch (IOException ex) {
                        Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        out.close();
                    }
                    return "";
                }

            });

            rapi.setRerrorhandler(new RErrorImpl() {

                @Override
                public Object processError(HttpServletRequest request, HttpServletResponse response, Object data) {
                   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                    PrintWriter out = null;
                    try {
                        out = response.getWriter();
                        out.write("ERROR OCCURED" + data);
                    } catch (IOException ex) {
                        Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        out.close();
                    }
                    return "";

                }
            });

            RAPIHandler api_handler = new RAPIHandler(rapi);
            String lapage = request.getRequestURI();
            lapage = lapage.replaceFirst(request.getContextPath(), "");
            lapage = lapage.replaceFirst("/API/", "");

            System.out.println("LA PAGE " + lapage);
            if (lapage.equalsIgnoreCase("documentation")) {
                RDocumentation rdoc = new RDocumentation();

                PrintWriter out = null;
                try {
                    out = response.getWriter();
                    out.write(rdoc.getDocumentation(rapi));
                } catch (IOException ex) {
                    Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    out.close();
                }

            }

            if (lapage.equalsIgnoreCase("Users")) {
                api_handler.processPage("Users", request, response);
            }

        } finally {

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
