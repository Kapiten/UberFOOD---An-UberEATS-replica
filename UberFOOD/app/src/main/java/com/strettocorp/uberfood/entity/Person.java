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
public class Person {

    private Integer personId;
    private String names;
    private String surname;
    private String nationalId;
    private String dateOfBirth;
    private String gender;
    private String age;
    private String profilePic;
    private String tokenId;

    public Person() {
    }

    public Person(Integer personId) {
        this.personId = personId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
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
            obj.put("personId", personId);
            obj.put("names", names);
            obj.put("surname", surname);
            obj.put("nationalId", nationalId);
            obj.put("dateOfBirth", dateOfBirth);
            obj.put("gender", gender);
            obj.put("age", age);
            obj.put("profilePic", profilePic);
            obj.put("tokenId", tokenId);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public Person fromString(String JSONPerson) {
        Person person = new Person();

        try {
            JSONObject obj = new JSONObject(JSONPerson);
            person.setPersonId(obj.getInt("personId"));
            person.setNames(obj.getString("names"));
            person.setSurname(obj.getString("surname"));
            person.setNationalId(obj.getString("nationalId"));
            person.setDateOfBirth(obj.getString("dateOfBirth"));
            person.setGender(obj.getString("gender"));
            person.setAge(obj.getString("age"));
            person.setProfilePic(obj.getString("profilePic"));
            person.setTokenId(obj.getString("tokenId"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return person;
    }
    
}
