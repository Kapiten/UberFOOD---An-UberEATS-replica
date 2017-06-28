/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.uberfood.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by reversidesoftwaresolutions on 2017/06/08.
 */

public class User implements Serializable {

    private Integer userId;
    private String username;
    private String password;
    private String tokenId;
    private String sessionToken;
    private String profilePic;
    private String picPath;

    public User() {
        setUserId(0);
        setUsername("");
        setPassword("");
        setTokenId("");
        setSessionToken("");
        setProfilePic("");
        setPicPath("");
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("userId", userId);
            obj.put("username", username);
            obj.put("password", password);
            obj.put("tokenId", tokenId);
            obj.put("sessionToken", sessionToken);
            obj.put("profilePic", profilePic);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public static User fromString(String JSONUser) {
        User user = new User();

        try {
            JSONObject obj = new JSONObject(JSONUser);
            user.setUserId(obj.getInt("userId"));
            user.setUsername(obj.getString("username"));
            user.setPassword(obj.getString("password"));
            user.setTokenId(obj.getString("tokenId"));
            user.setSessionToken(obj.getString("sessionToken"));
            user.setProfilePic(obj.getString("profilePic"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return user;
    }
    
}
