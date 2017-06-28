/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.uberfood.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by reversidesoftwaresolutions on 2017/06/08.
 */

public class ExtraType {

    private Integer extraTypeId;
    private String name;
    private String description;

    public ExtraType() {
    }

    public ExtraType(Integer extraTypeId) {
        this.extraTypeId = extraTypeId;
    }

    public Integer getExtraTypeId() {
        return extraTypeId;
    }

    public void setExtraTypeId(Integer extraTypeId) {
        this.extraTypeId = extraTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("extraTypeId", extraTypeId);
            obj.put("name", name);
            obj.put("description", description);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public ExtraType fromString(String JSONExtraType) {
        ExtraType extraType = new ExtraType();

        try {
            JSONObject obj = new JSONObject(JSONExtraType);
            extraType.setExtraTypeId(obj.getInt("extraTypeId"));
            extraType.setName(obj.getString("name"));
            extraType.setDescription(obj.getString("description"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return extraType;
    }
    
}
