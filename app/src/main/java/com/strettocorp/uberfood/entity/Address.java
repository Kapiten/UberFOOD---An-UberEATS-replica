/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strettocorp.uberfood.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by reversidesoftwaresolutions on 2017/06/05.
 */
public class Address {

    private int addressId;
    private String unitNo;
    private String streetame;
    private String suburb;
    private String city;
    private String code;
    private String gpsCoord;
    private Integer personId;
    
    private String type;

    public Address() {
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getStreetame() {
        return streetame;
    }

    public void setStreetame(String streetame) {
        this.streetame = streetame;
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

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("addressId", addressId);
            obj.put("unitNo", unitNo);
            obj.put("streetame", streetame);
            obj.put("suburb", suburb);
            obj.put("city", city);
            obj.put("code", code);
            obj.put("gpsCoord", gpsCoord);
            obj.put("personId", personId);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public Address fromString(String JSONAddress) {
        Address address = new Address();

        try {
            JSONObject obj = new JSONObject(JSONAddress);
            address.setAddressId(obj.getInt("addressId"));
            address.setUnitNo(obj.getString("unitNo"));
            address.setStreetame(obj.getString("streetame"));
            address.setSuburb(obj.getString("suburb"));
            address.setCity(obj.getString("city"));
            address.setCode(obj.getString("code"));
            address.setGpsCoord(obj.getString("gpsCoord"));
            address.setPersonId(obj.getInt("personId"));

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return address;
    }
    
}
