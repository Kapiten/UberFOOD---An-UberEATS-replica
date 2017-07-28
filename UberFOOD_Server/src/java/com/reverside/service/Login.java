/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.service;

import com.reverside.connection.transactions.Transactions;
import com.reverside.connection.transactions.Utility;
import com.reverside.entity.User;
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
            @QueryParam("jsonUser") String jsonUser) {
        String response = "";
        
        User user = User.fromString(jsonUser);
        User loggedUser = User.fromString(checkCredentials(user));
        if(loggedUser.getUserId() > 0) {
            response = loggedUser.toString();
        } else {
            response = Utility.constructJSON("login", false, "Incorrect Username | Password combo");
        }
        
        return response;
    }

public String checkCredentials(User user){
    System.out.println("Inside checkCredentials");
    String result = "";
    if(Utility.isNotNull(user.getUsername()) && Utility.isNotNull(user.getPassword())){
    try {
        result = Transactions.checkLogin(user);
    } catch (Exception e) {
    result = "";
    }
    }else{
    result = "";
    }
    return result;
    }
}