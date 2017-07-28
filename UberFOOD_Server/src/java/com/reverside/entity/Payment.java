/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author StrettO
 */
@Entity
@Table(name = "payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p")
    , @NamedQuery(name = "Payment.findByPaymentId", query = "SELECT p FROM Payment p WHERE p.paymentPK.paymentId = :paymentId")
    , @NamedQuery(name = "Payment.findByCardNo", query = "SELECT p FROM Payment p WHERE p.cardNo = :cardNo")
    , @NamedQuery(name = "Payment.findByCardType", query = "SELECT p FROM Payment p WHERE p.cardType = :cardType")
    , @NamedQuery(name = "Payment.findByCvv", query = "SELECT p FROM Payment p WHERE p.cvv = :cvv")
    , @NamedQuery(name = "Payment.findByExpireDate", query = "SELECT p FROM Payment p WHERE p.expireDate = :expireDate")
    , @NamedQuery(name = "Payment.findByPaypalId", query = "SELECT p FROM Payment p WHERE p.paypalId = :paypalId")
    , @NamedQuery(name = "Payment.findByPersonPersonId", query = "SELECT p FROM Payment p WHERE p.paymentPK.personPersonId = :personPersonId")})
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "payment_id")
    private Integer paymentId;
    @Size(max = 45)
    @Column(name = "card_no")
    private String cardNo;
    @Size(max = 45)
    @Column(name = "card_type")
    private String cardType;
    @Size(max = 3)
    @Column(name = "cvv")
    private String cvv;
    @Size(max = 5)
    @Column(name = "expire_date")
    private String expireDate;
    @Size(max = 45)
    @Column(name = "paypal_id")
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

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if (this.toString().equals(other.toString())) {
            return false;
        }
        return true;
    }

    @Override
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
            //payment.setPaymentId(obj.getInt("paymentId"));
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
