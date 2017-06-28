/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.uberfood.entity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reverside.uberfood.connection.Utility;
import com.reverside.uberfood.essentials.Constants;
import com.reverside.uberfood.essentials.Constants.RestResponseCodes;
import com.reverside.uberfood.essentials.Self;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by reversidesoftwaresolutions on 2017/06/05.
 */
public class Address {

    private int addressId;
    private String unitNo;
    private String streetname;
    private String suburb;
    private String city;
    private String code;
    private String gpsCoord;
    private Integer personId;
    
    private String type;

    public Address() {
        setAddressId(0);
        setStreetname("");
        setSuburb("");
        setCity("");
        setCode("");
        setGpsCoord("");
        setPersonId(0);
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
            obj.put("streetname", streetname);
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
            address.setStreetname(obj.getString("streetname"));
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

    public static List<Address> invokeGetAddressesWS(RequestParams params, final Self.ServComBuilder servComBuilder) {
        final List<Address> addresses = new ArrayList<>();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://" +
                        Constants.GlassfishConnProps.URL + ":" +
                        Constants.GlassfishConnProps.PORT + "/" +
                        Constants.GlassfishConnProps.WEB_APP + "/" +
                        Constants.GlassfishConnProps.REST_HOME + "/" +
                        "findEntity/addressesByPerson",
                params,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
                            String line = "", response = "";
                            while ((line = reader.readLine()) != null) {
                                response += line;
                            }
                            reader.close();

                            JSONArray objArr = new JSONArray(response);
                            for(int x = 0; x < objArr.length(); x++) {
                                JSONObject obj = objArr.getJSONObject(x);

                                addresses.add(new Address().fromString(obj.toString()));
                            }

                            servComBuilder.mServResponse.onResponse(RestResponseCodes.SUCCESS, "Successful getAddresses()");
                            servComBuilder.mServResponse.onResponse(addresses);
                        } catch (IOException ex){
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            servComBuilder.mServResponse.onResponse(RestResponseCodes.FAULT, "Error Occured [Server's JSON response might be invalid]!");
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Utility.getFailureMsg(i, bytes, servComBuilder);
                    }
                });

        return addresses;
    }
    
}
