/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.service;

import com.reverside.connection.transactions.IFileService;
import com.reverside.entity.Restaurant;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author reversidesoftwaresolutions
 */
@Path("/fileservice")
public class FileServiceImpl implements IFileService {

    //public static final String UPLOAD_FILE_SERVER
    @GET
    @Path("/download/image")
    @Produces({"image/png", "image/jpg", "image/gif"})
    public Response downloadImageFile(@QueryParam("path") String path) {
        System.out.println("Inside downloadImageFile & \npath = " + path);

        String home = System.getProperty("user.home");
        if (home.contains("/")) {
            path = path.replace("\\", "/");
        }
        File file = new File(System.getProperty("user.home") + path);

        ResponseBuilder builder = Response.ok((Object) file);
        builder.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        return builder.build();
    }

    public static int val = 0;
    public int val2 = 0;

    @GET
    @Path("/download/test")
    @Produces(MediaType.TEXT_HTML)
    public String downloadTest() {
        try {
            //pushFCMNotification(DEVICE_ID.trim(), "TestMsg", "Hey man, This is that msg you sent from TheServerComp server");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Hey";
    }

    @GET
    @Path("/download/test2")
    @Produces(MediaType.TEXT_PLAIN)
    public String downloadTest2() {
        try {
            //pushFCMNotification(DEVICE_ID.trim(), "Test2Msg", "Hey man, This is that msg you sent from TheServerComp server");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "Hi";
    }

}
