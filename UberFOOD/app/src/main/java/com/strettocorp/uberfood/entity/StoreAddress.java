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
public class StoreAddress {

    private Integer storeAddressId;
    private String unitNo;
    private String streetname;
    private String suburb;
    private String city;
    private String code;
    private String gpsCoord;
    private String type;

    public StoreAddress() {
    }

    public Integer getStoreAddressId() {
        return storeAddressId;
    }

    public void setStoreAddressId(Integer storeAddressId) {
        this.storeAddressId = storeAddressId;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGpsCoord() {
        return gpsCoord;
    }

    public void setGpsCoord(String gpsCoord) {
        this.gpsCoord = gpsCoord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("storeAddressId", storeAddressId);
            obj.put("unitNo", unitNo);
            obj.put("streetname", streetname);
            obj.put("suburb", suburb);
            obj.put("city", city);
            obj.put("code", code);
            obj.put("gpsCoord", gpsCoord);
            obj.put("type", type);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public StoreAddress fromString(String JSONStoreAddress) {
        StoreAddress storeAddress = new StoreAddress();

        try {
            JSONObject obj = new JSONObject(JSONStoreAddress);
            storeAddress.setStoreAddressId(obj.getInt("storeAddressId"));
            storeAddress.setUnitNo(obj.getString("unitNo"));
            storeAddress.setStreetname(obj.getString("streetname"));
            storeAddress.setSuburb(obj.getString("suburb"));
            storeAddress.setCity(obj.getString("city"));
            storeAddress.setCode(obj.getString("code"));
            storeAddress.setGpsCoord(obj.getString("gpsCoord"));
            storeAddress.setType(obj.getString("type"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return storeAddress;
    }
    
}
