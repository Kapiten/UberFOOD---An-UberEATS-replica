/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strettocorp.uberfood.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by reversidesoftwaresolutions on 2017/06/08.
 */

public class User {

    private Integer userId;
    private String username;
    private String password;
    private String personId;
    private String tokenId;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("userId", userId);
            obj.put("username", username);
            obj.put("password", password);
            obj.put("personId", personId);
            obj.put("tokenId", tokenId);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public User fromString(String JSONUser) {
        User user = new User();

        try {
            JSONObject obj = new JSONObject(JSONUser);
            user.setUserId(obj.getInt("userId"));
            user.setUsername(obj.getString("username"));
            user.setPassword(obj.getString("password"));
            user.setPersonId(obj.getString("personId"));
            user.setTokenId(obj.getString("tokenId"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return user;
    }
    
}
