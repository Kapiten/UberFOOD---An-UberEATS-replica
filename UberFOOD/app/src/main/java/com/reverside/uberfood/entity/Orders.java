/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.uberfood.entity;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reverside.uberfood.connection.Utility;
import com.reverside.uberfood.essentials.Constants;
import com.reverside.uberfood.essentials.Constants.RestResponseCodes;
import com.reverside.uberfood.essentials.Self;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by reversidesoftwaresolutions on 2017/06/08.
 */

public class Orders {

    private Integer orderId;
    private String orderNo;
    private String date;
    private String restaurantId;
    private String orderType;
    private String extrasNote;
    private String deliveryNote;
    private String recipient;
    private String intendedRecipient;
    private String completionTypeId;
    private String courierId;
    private String subtotal;
    private String tax;
    private String deliveryFee;
    private int status;

    private static int sqlResult = 0;

    public Orders() {
        setOrderId(0);
        setOrderNo("");
        setDate("");
        setRestaurantId("");
        setOrderType("");
        setExtrasNote("");
        setDeliveryNote("");
        setRecipient("");
        setIntendedRecipient("");
        setCompletionTypeId("");
        setCourierId("");
        setSubtotal("");
        setTax("");
        setDeliveryFee("");
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getExtrasNote() {
        return extrasNote;
    }

    public void setExtrasNote(String extrasNote) {
        this.extrasNote = extrasNote;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getIntendedRecipient() {
        return intendedRecipient;
    }

    public void setIntendedRecipient(String intendedRecipient) {
        this.intendedRecipient = intendedRecipient;
    }

    public String getCompletionTypeId() {
        return completionTypeId;
    }

    public void setCompletionTypeId(String completionTypeId) {
        this.completionTypeId = completionTypeId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("orderId", orderId);
            obj.put("orderNo", orderNo);
            obj.put("date", date);
            obj.put("restaurantId", restaurantId);
            obj.put("orderType", orderType);
            obj.put("extrasNote", extrasNote);
            obj.put("deliveryNote", deliveryNote);
            obj.put("recipient", recipient);
            obj.put("intendedRecipient", intendedRecipient);
            obj.put("completionTypeId", completionTypeId);
            obj.put("courierId", courierId);
            obj.put("subtotal", subtotal);
            obj.put("tax", tax);
            obj.put("deliveryFee", deliveryFee);
            obj.put("status", status);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public Orders fromString(String JSONOrder) {
        Orders orders = new Orders();

        try {
            JSONObject obj = new JSONObject(JSONOrder);
            orders.setOrderId(obj.getInt("orderId"));
            orders.setOrderNo(obj.getString("orderNo"));
            orders.setDate(obj.getString("date"));
            orders.setRestaurantId(obj.getString("restaurantId"));
            orders.setOrderType(obj.getString("orderType"));
            orders.setExtrasNote(obj.getString("extrasNote"));
            orders.setDeliveryNote(obj.getString("deliveryNote"));
            orders.setRecipient(obj.getString("recipient"));
            orders.setIntendedRecipient(obj.getString("intendedRecipient"));
            orders.setCompletionTypeId(obj.getString("completionTypeId"));
            orders.setCourierId(obj.getString("courierId"));
            orders.setSubtotal(obj.getString("subtotal"));
            orders.setTax(obj.getString("tax"));
            orders.setDeliveryFee(obj.getString("deliveryFee"));
            orders.setStatus(obj.getInt("status"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return orders;
    }

    public static void invokeAddOrder(RequestParams params, final Self.ServComBuilder servComBuilder) {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://" +
                        Constants.GlassfishConnProps.URL + ":" +
                        Constants.GlassfishConnProps.PORT + "/" +
                        Constants.GlassfishConnProps.WEB_APP + "/" +
                        Constants.GlassfishConnProps.REST_HOME + "/" +
                        "addEntity/addOrder",
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

                            JSONObject obj = new JSONObject(response);
                            if(obj.getBoolean("status")) {
                                servComBuilder.mServResponse.onResponse(RestResponseCodes.SUCCESS, "Successfully placed Orders");
                            } else {
                                servComBuilder.mServResponse.onResponse(RestResponseCodes.FAULT, "Something went wrong while placing order");
                            }
                        } catch (IOException ex){
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            servComBuilder.mServResponse.onResponse(RestResponseCodes.FAULT, "Error Occurred - Server's JSON response might be invalid");
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        servComBuilder.mServResponse.onResponse(RestResponseCodes.FAULT,
                                Utility.getFailureMsg(i, bytes, servComBuilder));
                    }
                });
    }

    public static void invokeAddOrder(Context context, StringEntity entity, final Self.ServComBuilder servComBuilder) {
        AsyncHttpClient client = new AsyncHttpClient();
        Log.i(Constants.UNI_TAG, "invokeAddOrder = " + entity);
        client.post(context,
                "http://" +
                        Constants.GlassfishConnProps.URL + ":" +
                        Constants.GlassfishConnProps.PORT + "/" +
                        Constants.GlassfishConnProps.WEB_APP + "/" +
                        Constants.GlassfishConnProps.REST_HOME + "/" +
                        "addEntity/addOrder",
                entity,
                "application/json",
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

                            JSONObject obj = new JSONObject(response);
                            if(obj.getBoolean("status")) {
                                servComBuilder.mServResponse.onResponse(RestResponseCodes.SUCCESS, "Successfully placed Orders");
                            } else {
                                servComBuilder.mServResponse.onResponse(RestResponseCodes.FAULT, response);
                            }
                        } catch (IOException ex){
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            servComBuilder.mServResponse.onResponse(RestResponseCodes.FAULT, "Error Occurred - Server's JSON response might be invalid");
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        servComBuilder.mServResponse.onResponse(RestResponseCodes.FAULT,
                                Utility.getFailureMsg(i, bytes, servComBuilder));
                    }
                });
    }



}
