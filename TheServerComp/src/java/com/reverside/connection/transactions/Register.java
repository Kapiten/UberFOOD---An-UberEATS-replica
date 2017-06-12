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
@Path("/register")
public class Register {
    @GET
    @Path("/doRegister")
    @Produces(MediaType.APPLICATION_JSON)
    public String doRegister(
            @QueryParam("username") String uname, 
            @QueryParam("password") String passw,
            @QueryParam("names") String names,
            @QueryParam("surname") String surname,
            @QueryParam("email") String email,
            @QueryParam("cell1") String mobile) {
        String response = "";
        int retCode = registerUser(
                uname, 
                passw, 
                names, 
                surname,
                email,
                mobile);
        if(retCode == 0) {
            response = Utility.constructJSON("register", true);
        } else if (retCode == 1) {
            response = Utility.constructJSON("register", false, "You are already logged in.");
        } else if(retCode == 2) {
            response = Utility.constructJSON("register", false, "Special Characters are not allowed.");
        } else if(retCode == 3) {
            response = Utility.constructJSON("register", false, "Error occured");
        }

        return response;
    }
    
    private int registerUser(
            String uname, 
            String passw,
            String names,
            String surname,
            String email,
            String mobile) {
        int result = 3;
        if(Utility.isNotNull(uname) && Utility.isNotNull(passw)) {
            try {
                if (Transactions.insertUser(
                        uname, 
                        passw,
                        names,
                        surname,
                        email,
                        mobile)) {
                    result = 0;
                }
            } catch(SQLException ex) {
                if(ex.getErrorCode() == 1062) {
                    result = 0;
                } else if(ex.getErrorCode() == 1064) {
                    result = 2;
                }
                ex.printStackTrace();
            } catch (Exception ex) {
                result = 3;
                ex.printStackTrace();
            }
        } else {
            result = 3;
        }
        
        return result;
    }
}
