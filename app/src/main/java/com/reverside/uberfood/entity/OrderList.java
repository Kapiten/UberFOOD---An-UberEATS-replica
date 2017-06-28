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
public class OrderList {

    private Integer orderListId;
    private String orderNo;
    private String date;
    private String cuisineId;
    private String extra;
    private String specialInstructions;
    private Integer quantity;

    public OrderList() {
    }

    public OrderList(Integer orderListId) {
        this.orderListId = orderListId;
    }

    public Integer getOrderListId() {
        return orderListId;
    }

    public void setOrderListId(Integer orderListId) {
        this.orderListId = orderListId;
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
            obj.put("orderListId", orderListId);
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

    public OrderList fromString(String JSONOrderList) {
        OrderList orderList = new OrderList();

        try {
            JSONObject obj = new JSONObject(JSONOrderList);
            orderList.setOrderListId(obj.getInt("orderListId"));
            orderList.setOrderNo(obj.getString("orderNo"));
            orderList.setCuisineId(obj.getString("cuisineId"));
            orderList.setExtra(obj.getString("extra"));
            orderList.setSpecialInstructions(obj.getString("specialInstructions"));
            orderList.setQuantity(obj.getInt("quantity"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return orderList;
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
