/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.service;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author reversidesoftwaresolutions
 */
@Path("/fcm")
public class FirebaseCMREST {
    
    
    @GET
    @Path("/orderAccept")
    @Produces(MediaType.TEXT_HTML)
    public String downloadOrderAccept() {
        try {
            pushFCMNotification(DEVICE_ID.trim(), "Order", "Order is accepted.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Hey";
    }

    @GET
    @Path("/foodPrep")
    @Produces(MediaType.TEXT_PLAIN)
    public String downloadFoodPrep() {
        try {
            pushFCMNotification(DEVICE_ID.trim(), "Food", "Food is prepared.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "Hi";
    }
    
    @GET
    @Path("/courierComing")
    @Produces(MediaType.TEXT_PLAIN)
    public String downloadCourierComing() {
        try {
            pushFCMNotification(DEVICE_ID.trim(), "Courier", "Courier has arrived.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "Hi";
    }

    public final static String AUTH_KEY_FCM = "AIzaSyB2AZerUfCF4vMp5hJ4FbpAlooN5JPV5Fk";

    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
    public final static String DEVICE_ID
            = "ekCujlmnEvk:APA91bHpNtEylQ9k5_GV3qf93hcSrGG1fJTtd6ohihwcFgJydcha1bdwLb7c3y1ELHD3UbdcDYuJO60p5BVgxRl1NOPCcJ2Rp37A5WXthT1ZtSUS6jigKrFSrz2c1l6-F2WbPdU6tcqS";

    // userDeviceIdKey is the device id you will query from your database
    public void pushFCMNotification(
            String deviceKey, 
            String title,
            String msg) throws Exception {

        String authKey = AUTH_KEY_FCM;   // You FCM AUTH key
        String FMCurl = API_URL_FCM;

        URL url = new URL(FMCurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + authKey);
        conn.setRequestProperty("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        json.put("to", deviceKey);
        JSONObject info = new JSONObject();
        info.put("title", title); // Notification title
        info.put("body", msg); // Notification body
        //info.put("image", "https://lh6.googleusercontent.com/-sYITU_cFMVg/AAAAAAAAAAI/AAAAAAAAABM/JmQNdKRPSBg/photo.jpg");
        info.put("type", "message");
        json.put("data", info);
        System.out.println(json.toString());

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(json.toString());
        wr.flush();
        conn.getInputStream();
    }
}
