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

public class Order {

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

    public Order() {
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
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public Order fromString(String JSONOrder) {
        Order order = new Order();

        try {
            JSONObject obj = new JSONObject(JSONOrder);
            order.setOrderId(obj.getInt("orderId"));
            order.setOrderNo(obj.getString("orderNo"));
            order.setDate(obj.getString("date"));
            order.setRestaurantId(obj.getString("restaurantId"));
            order.setOrderType(obj.getString("orderType"));
            order.setExtrasNote(obj.getString("extrasNote"));
            order.setDeliveryNote(obj.getString("deliveryNote"));
            order.setRecipient(obj.getString("recipient"));
            order.setIntendedRecipient(obj.getString("intendedRecipient"));
            order.setCompletionTypeId(obj.getString("completionTypeId"));
            order.setCourierId(obj.getString("courierId"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return order;
    }
    
}
