/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.service;

import com.reverside.connection.DBConnection;
import com.reverside.entity.Order1;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author reversidesoftwaresolutions
 */
@Path("/orders")
public class Orders {
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public String downloadTest() {
        try {
            //pushFCMNotification(DEVICE_ID.trim(), "TestMsg", "Hey man, This is that msg you sent from TheServerComp server");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Hey";
    }
}
