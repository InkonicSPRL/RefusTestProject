/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apimethods;

import com.inkonic.refus.annotations.RAPIEnable;
import com.inkonic.refus.annotations.RGet;
import com.inkonic.refus.annotations.RModel;
import com.inkonic.refus.annotations.RParameter;
import com.inkonic.refus.annotations.RPost;
import com.inkonic.refus.annotations.RReturns;
import com.inkonic.refus.arch.RAPIMethod;
import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author benoit.simonis
 */
@RAPIEnable(name = "Sales", desc = "Manages sales and accounting", url = "/API/Sales/")
public class Sales extends RAPIMethod {

    @Override
    public Object process(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RGet(parameters = {
        @RParameter(name = "client_id", desc = "The client number", type = Integer.class)},
            returns = @RReturns(desc = "Le client", type = String.class),
            description = "Retrieve all transactions for a given client")
    public Object doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("PASSAGE2");
        return "bonjour";

    }

    @Override
    @RPost(parameters = {
        @RParameter(name = "amount", desc = "Adds a transaction for the client", type = String.class),
        @RParameter(name = "client_id", desc = "The client number", type = Integer.class)},
            returns = @RReturns(desc = "A client instance", type = Object.class), description = "Adds a client to the database")
    public Object doPost(HttpServletRequest request, HttpServletResponse response) {
        return null;

    }

}
