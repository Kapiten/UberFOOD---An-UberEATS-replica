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

public class StoreContact {

    protected Integer storeContactId;
    private String tel1;
    //private String tel2;
    private String cell1;
    //private String cell2;
    private String email;
    //private String email2;
    private String fax;

    public StoreContact() {
    }

    public Integer getStoreContactId() {
        return storeContactId;
    }

    public void setStoreContactId(Integer storeContactId) {
        this.storeContactId = storeContactId;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getCell1() {
        return cell1;
    }

    public void setCell1(String cell1) {
        this.cell1 = cell1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("storeContactId", storeContactId);
            obj.put("tel1", tel1);
            obj.put("cell1", cell1);
            obj.put("email", email);
            obj.put("fax", fax);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public StoreContact fromString(String JSONStoreContact) {
        StoreContact storeContact = new StoreContact();

        try {
            JSONObject obj = new JSONObject(JSONStoreContact);
            storeContact.setStoreContactId(obj.getInt("storeContactId"));
            storeContact.setTel1(obj.getString("tel1"));
            storeContact.setCell1(obj.getString("cell1"));
            storeContact.setEmail(obj.getString("email"));
            storeContact.setFax(obj.getString("fax"));

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return storeContact;
    }
    
}
