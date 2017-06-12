/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.connection.transactions;

import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author RSS
 */
@Path("/login")
public class Login {
    @GET
    @Path("/doLogin")
    @Produces(MediaType.APPLICATION_JSON)
    public String doLogin(
            @QueryParam("username") String uname, 
            @QueryParam("password") String passw) {
        String response = "";
        if(checkCredentials(uname, passw)) {
            response = Utility.constructJSON("login", true);
        } else {
            response = Utility.constructJSON("login", false, "Incorrect Username | Password combo");
        }
        
        return response;
    }

public boolean checkCredentials(String uname, String passw){
    System.out.println("Inside checkCredentials");
    boolean result = false;
    if(Utility.isNotNull(uname) && Utility.isNotNull(passw)){
    try {
    result = Transactions.checkLogin(uname, passw);
    } catch (Exception e) {
    result = false;
    }
    }else{
    result = false;
    }
    return result;
    }
}