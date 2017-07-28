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
import com.reverside.uberfood.essentials.Self;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.Header;

/**
 * Created by reversidesoftwaresolutions on 2017/06/08.
 */
public class CuisineList {

    private Integer cuisineListId;
    private String orderNo;
    private String date;
    private String cuisineId;
    private String extra;
    private String specialInstructions;
    private Integer quantity;

    public CuisineList() {
    }

    public CuisineList(Integer cuisineListId) {
        this.cuisineListId = cuisineListId;
    }

    public Integer getCuisineListId() {
        return cuisineListId;
    }

    public void setCuisineListId(Integer cuisineListId) {
        this.cuisineListId = cuisineListId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(String cuisineId) {
        this.cuisineId = cuisineId;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("cuisineListId", cuisineListId);
            obj.put("orderNo", orderNo);
            obj.put("cuisineId", cuisineId);
            obj.put("extra", extra);
            obj.put("specialInstructions", specialInstructions);
            obj.put("quantity", quantity);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public CuisineList fromString(String JSONOrderList) {
        CuisineList cuisineList = new CuisineList();

        try {
            JSONObject obj = new JSONObject(JSONOrderList);
            cuisineList.setCuisineListId(obj.getInt("cuisineListId"));
            cuisineList.setOrderNo(obj.getString("orderNo"));
            cuisineList.setCuisineId(obj.getString("cuisineId"));
            cuisineList.setExtra(obj.getString("extra"));
            cuisineList.setSpecialInstructions(obj.getString("specialInstructions"));
            cuisineList.setQuantity(obj.getInt("quantity"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return cuisineList;
    }

    public static void invokeResponse(RequestParams params, final Self.ServComBuilder servComBuilder) {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://" +
                        Constants.GlassfishConnProps.URL + ":" +
                        Constants.GlassfishConnProps.PORT + "/" +
                        Constants.GlassfishConnProps.WEB_APP + "/" +
                        Constants.GlassfishConnProps.REST_HOME + "/" +
                        "fileservice/download/test",
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

                            if(!response.isEmpty()) {
                                servComBuilder.mServResponse.onResponse(1, response);
                            } else {
                                servComBuilder.mServResponse.onResponse(2, "Something went wrong while placing order");
                            }
                        } catch (IOException ex){
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        servComBuilder.mServResponse.onResponse(1,
                                Utility.getFailureMsg(i, bytes, servComBuilder));
                    }
                });
    }
    
}
