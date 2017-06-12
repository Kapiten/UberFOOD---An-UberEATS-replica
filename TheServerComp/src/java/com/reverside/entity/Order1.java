/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.entity;

import com.reverside.connection.DBConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author StrettO
 */
@Entity
@Table(name = "order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order1.findAll", query = "SELECT o FROM Order1 o")
    , @NamedQuery(name = "Order1.findByOrderId", query = "SELECT o FROM Order1 o WHERE o.orderId = :orderId")
    , @NamedQuery(name = "Order1.findByOrderNo", query = "SELECT o FROM Order1 o WHERE o.orderNo = :orderNo")
    , @NamedQuery(name = "Order1.findByDate", query = "SELECT o FROM Order1 o WHERE o.date = :date")
    , @NamedQuery(name = "Order1.findByOrderType", query = "SELECT o FROM Order1 o WHERE o.orderType = :orderType")
    , @NamedQuery(name = "Order1.findByExtrasNote", query = "SELECT o FROM Order1 o WHERE o.extrasNote = :extrasNote")
    , @NamedQuery(name = "Order1.findByDeliveryNote", query = "SELECT o FROM Order1 o WHERE o.deliveryNote = :deliveryNote")
    , @NamedQuery(name = "Order1.findByRecipient", query = "SELECT o FROM Order1 o WHERE o.recipient = :recipient")
    , @NamedQuery(name = "Order1.findByIntendedRecipient", query = "SELECT o FROM Order1 o WHERE o.intendedRecipient = :intendedRecipient")
    , @NamedQuery(name = "Order1.findByCompletionTypeId", query = "SELECT o FROM Order1 o WHERE o.completionTypeId = :completionTypeId")
    , @NamedQuery(name = "Order1.findByRestaurantId", query = "SELECT o FROM Order1 o WHERE o.restaurantId = :restaurantId")
    , @NamedQuery(name = "Order1.findByCourierId", query = "SELECT o FROM Order1 o WHERE o.courierId = :courierId")})
public class Order1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_id")
    private Integer orderId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "order_no")
    private String orderNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "date")
    private String date;
    @Size(max = 45)
    @Column(name = "order_type")
    private String orderType;
    @Size(max = 500)
    @Column(name = "extras_note")
    private String extrasNote;
    @Size(max = 500)
    @Column(name = "delivery_note")
    private String deliveryNote;
    @Size(max = 45)
    @Column(name = "recipient")
    private String recipient;
    @Size(max = 45)
    @Column(name = "intended_recipient")
    private String intendedRecipient;
    @Size(max = 45)
    @Column(name = "completion_type_id")
    private String completionTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "restaurant_id")
    private String restaurantId;
    @Size(max = 45)
    @Column(name = "courier_id")
    private String courierId;

    public Order1() {
    }

    public Order1(Integer orderId) {
        this.orderId = orderId;
    }

    public Order1(Integer orderId, String orderNo, String date, String restaurantId) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.date = date;
        this.restaurantId = restaurantId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1)) {
            return false;
        }
        Order1 other = (Order1) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
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

    public Order1 fromString(String JSONOrder) {
        Order1 order = new Order1();

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
    
    public void insertOrder(Order1 order)
        throws SQLException {
        Connection dbConn = null;
        
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            Statement stmt = dbConn.createStatement();
            String sqlInsOrder = "";
        } catch(SQLException ex) {
            throw ex;
        } catch(Exception e) {
            if(dbConn != null) {
                dbConn.close();
            }
            
            throw e;
        } finally {
            if(dbConn != null) {
                dbConn.close();
            }
        }
    }
    
}
