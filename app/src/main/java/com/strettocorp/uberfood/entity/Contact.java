/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strettocorp.uberfood.entity;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by reversidesoftwaresolutions on 2017/06/05.
 */
public class Contact {

    private Integer contactId;
    private String cell1;
    private String cell2;
    private String tel1;
    private String tel2;
    private String email;
    private String email2;
    private String fax;
    private String personId;

    public Contact() {
    }

    public Contact(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getCell1() {
        return cell1;
    }

    public void setCell1(String cell1) {
        this.cell1 = cell1;
    }

    public String getCell2() {
        return cell2;
    }

    public void setCell2(String cell2) {
        this.cell2 = cell2;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("contactId", contactId);
            obj.put("cell1", cell1);
            obj.put("tel1", tel1);
            obj.put("email", email);
            obj.put("fax", fax);
            obj.put("personId", personId);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public Contact fromString(String JSONContact) {
        Contact contact = new Contact();

        try {
            JSONObject obj = new JSONObject(JSONContact);
            contact.setContactId(obj.getInt("contactId"));
            contact.setCell1(obj.getString("cell1"));
            contact.setTel1(obj.getString("tel1"));
            contact.setEmail(obj.getString("email"));
            contact.setFax(obj.getString("fax"));
            contact.setPersonId(obj.getString("personId"));

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return contact;
    }
    
}
