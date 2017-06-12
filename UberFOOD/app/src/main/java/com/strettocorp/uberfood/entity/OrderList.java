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
public class OrderList {

    private Integer orderListId;
    private String orderNo;
    private String date;
    private String cuisineId;
    private String extra;
    private String specialInstructions;

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

    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("orderListId", orderListId);
            obj.put("orderNo", orderNo);
            obj.put("date", date);
            obj.put("cuisineId", cuisineId);
            obj.put("extra", extra);
            obj.put("specialInstructions", specialInstructions);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public OrderList fromString(String JSONOrderList) {
        OrderList orderList = new OrderList();

        try {
            JSONObject obj = new JSONObject(JSONOrderList);
            orderList.setOrderListId(obj.getInt("orderList"));
            orderList.setOrderNo(obj.getString("orderNo"));
            orderList.setDate(obj.getString("date"));
            orderList.setCuisineId(obj.getString("cuisineId"));
            orderList.setExtra(obj.getString("extra"));
            orderList.setSpecialInstructions(obj.getString("specialInstructions"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return orderList;
    }
    
}
