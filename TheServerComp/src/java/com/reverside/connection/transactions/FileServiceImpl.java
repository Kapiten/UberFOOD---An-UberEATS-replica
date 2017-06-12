/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.connection.transactions;

import com.reverside.entity.Restaurant;
import java.io.File;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import javax.ws.rs.core.MediaType;

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
        File file = new File(path);
        
        ResponseBuilder builder = Response.ok((Object) file);
        builder.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        return builder.build();
    }
    
    @GET
    @Path("/download/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String downloadTest() {
        File file = new File("/Users/reversidesoftwaresolutions/NetBeansProjects/TheServerComp/web/resources/images/salata.jpg");
        
        return file.list().length + "";
    }
    
}
