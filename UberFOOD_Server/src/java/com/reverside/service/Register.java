/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.service;

import com.reverside.connection.transactions.Transactions;
import com.reverside.connection.transactions.Utility;
import com.reverside.entity.Contact;
import com.reverside.entity.Person;
import com.reverside.entity.User;
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

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
            @QueryParam("jsonUser") String jsonUser,
            @QueryParam("jsonPerson") String jsonPerson,
            @QueryParam("jsonContact") String jsonContact) {
        String response = "";

        String retCode = registerUser(
                User.fromString(jsonUser),
                Person.fromString(jsonPerson),
                Contact.fromString(jsonContact));

        try {
            JSONObject obj = new JSONObject(retCode);
            if (!obj.getString("username").isEmpty()) {
                JSONArray jsona = new JSONArray();
                User user = User.getUserByTokenId(obj.getString("tokenId"));

                if (user != null && user.getUserId() > 0) {
                    Person person = Person.getPersonByUserId(user.getUserId() + "");

                    if (person != null && person.getPersonId() > 0) {
                        Contact contact = Contact.getContactByPersonId(person.getPersonId() + "");

                        jsona.put(user.toString());
                        jsona.put(person.toString());

                        if (contact != null && contact.getContactId() > 0) {
                            jsona.put(contact.toString());
                        }
                    }

                    response = jsona.toString();
                }
            } else {
                response = Utility.constructJSON("register", false, "Error occured");
            }
        } catch (SQLException | JSONException ex) {
            ex.printStackTrace();
        }

        return response;
    }

    /**
     * 
     * @param user
     * @param person
     * @param contact
     * @return String jsonUser
     */
    private String registerUser(User user, Person person, Contact contact) {
        String result = "";
        if (Utility.isNotNull(user.getUsername()) && Utility.isNotNull(user.getPassword())) {
            try {
                return Transactions.insertUser(user, person, contact);
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1062) {
                    result = "0";
                } else if (ex.getErrorCode() == 1064) {
                    result = "2";
                }
                ex.printStackTrace();
            } catch (Exception ex) {
                result = "3";
                ex.printStackTrace();
            }
        } else {
            result = "3";
        }

        return result;
    }
}
