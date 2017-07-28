/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.connection.transactions;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author RSS
 */
public class Utility {
    public static boolean isNotNull(String txt) {
        return txt != null && txt.trim().length() >= 0;
    }
    
    public static String constructJSON(String tag, boolean status) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", tag);
            obj.put("status", new Boolean(status));
        } catch (JSONException ex) {
            
        }
        
        return obj.toString();
    }
    
    public static String constructJSON(String tag, boolean status,String err_msg) {
        JSONObject obj = new JSONObject();
        
        try {
            obj.put("tag", tag);
            obj.put("status", new Boolean(status));
            obj.put("error_msg", err_msg);
        } catch(JSONException ex) {
            
        }
        
        return obj.toString();
    }
    
    public static String constructJSON(String tag, String value) {
        JSONObject obj = new JSONObject();
        
        try {
            obj.put("tag", tag);
            obj.put("value", value);
        } catch(JSONException ex) {
            
        }
        
        return obj.toString();
    }
}
