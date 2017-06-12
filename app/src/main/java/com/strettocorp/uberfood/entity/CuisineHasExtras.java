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

public class CuisineHasExtras {

    private int cuisineId;
    private int extraId;
    private int extraTypeId;

    public int getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(int cuisineId) {
        this.cuisineId = cuisineId;
    }

    public int getExtraId() {
        return extraId;
    }

    public void setExtraId(int extraId) {
        this.extraId = extraId;
    }

    public int getExtraTypeId() {
        return extraTypeId;
    }

    public void setExtraTypeId(int extraTypeId) {
        this.extraTypeId = extraTypeId;
    }

    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("cuisineCuisineId", cuisineId);
            obj.put("extrasExtrasId", extraId);
            obj.put("extraTypeId", extraTypeId);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public CuisineHasExtras fromString(String JSONCuisineHasExtras) {
        CuisineHasExtras cuisineHasExtras = new CuisineHasExtras();

        try {
            JSONObject obj = new JSONObject(JSONCuisineHasExtras);
            cuisineHasExtras.setCuisineId(obj.getInt("cuisineCuisineId"));
            cuisineHasExtras.setExtraId(obj.getInt("extrasExtrasId"));
            cuisineHasExtras.setExtraTypeId(obj.getInt("extraTypeId"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return cuisineHasExtras;
    }
    
}
