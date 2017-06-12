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
public class Payment{

    private Integer paymentId;
    private String cardNo;
    private String cardType;
    private String cvv;
    private String expireDate;
    private String paypalId;

    public Payment() {
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getPaypalId() {
        return paypalId;
    }

    public void setPaypalId(String paypalId) {
        this.paypalId = paypalId;
    }

    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("paymentId", paymentId);
            obj.put("cardNo", cardNo);
            obj.put("cardType", cardType);
            obj.put("cvv", cvv);
            obj.put("expireDate", expireDate);
            obj.put("paypalId", paypalId);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public Payment fromString(String JSONPayment) {
        Payment payment = new Payment();

        try {
            JSONObject obj = new JSONObject(JSONPayment);
            payment.setPaymentId(obj.getInt("paymentId"));
            payment.setCardNo(obj.getString("cardNo"));
            payment.setCardType(obj.getString("cardType"));
            payment.setCvv(obj.getString("cvv"));
            payment.setExpireDate(obj.getString("expireDate"));
            payment.setPaypalId(obj.getString("paypalId"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return payment;
    }
    
}
