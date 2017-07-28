/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.entity;

import com.reverside.connection.DBConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
 * @author reversidesoftwaresolutions
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
    , @NamedQuery(name = "Order1.findByCourierId", query = "SELECT o FROM Order1 o WHERE o.courierId = :courierId")
    , @NamedQuery(name = "Order1.findBySubtotal", query = "SELECT o FROM Order1 o WHERE o.subtotal = :subtotal")
    , @NamedQuery(name = "Order1.findByTax", query = "SELECT o FROM Order1 o WHERE o.tax = :tax")
    , @NamedQuery(name = "Order1.findByDeliveryFee", query = "SELECT o FROM Order1 o WHERE o.deliveryFee = :deliveryFee")})
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
    @Size(max = 45)
    @Column(name = "subtotal")
    private String subtotal;
    @Size(max = 45)
    @Column(name = "tax")
    private String tax;
    @Size(max = 45)
    @Column(name = "delivery_fee")
    private String deliveryFee;
    @Column(name = "status")
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
            order.setSubtotal(obj.getString("subtotal"));
            order.setTax(obj.getString("tax"));
            order.setDeliveryFee(obj.getString("deliveryFee"));
            order.setStatus(obj.getInt("status"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return order;
    }
    
    public static boolean insertOrder(Order1 order)
            throws SQLException {
        Connection dbConn = null;
        boolean insStatus = false;

        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Statement stmt = dbConn.createStatement();
            String sqlInsOrder = "INSERT INTO uberfood.order ("
                    + "order_no,"
                    + "date,"
                    + "restaurant_id,"
                    + "order_type,"
                    + "extras_note,"
                    + "delivery_note,"
                    + "recipient,"
                    + "intended_recipient,"
                    + "completion_type_id,"
                    + "courier_id,"
                    + "subtotal,"
                    + "tax,"
                    + "delivery_fee) values ("
                    + "'" + order.getOrderNo() + "', "
                    + "'" + order.getDate() + "', "
                    + "'" + order.getRestaurantId() + "', "
                    + "'" + order.getOrderType() + "', "
                    + "'" + order.getExtrasNote() + "', "
                    + "'" + order.getDeliveryNote() + "', "
                    + "'" + order.getRecipient() + "', "
                    + "'" + order.getIntendedRecipient() + "', "
                    + "'" + order.getCompletionTypeId() + "', "
                    + "'" + order.getCourierId() + "', "
                    + "'" + order.getSubtotal() + "', "
                    + "'" + order.getTax() + "', "
                    + "'" + order.getDeliveryFee() + "')";
            
            insStatus = stmt.executeUpdate(sqlInsOrder) > 0;
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception e) {
            if (dbConn != null) {
                dbConn.close();
            }

            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }

        return insStatus;
    }

    public static Order1 getOrderById(int id) throws SQLException {
        Connection dbConn = null;
        Order1 order = new Order1();

        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Statement st = dbConn.createStatement();
            String sqlSelOrder = "SELECT * FROM uberfood.order WHERE order_id = " + id;

            order = getOrder(sqlSelOrder, st);

        } catch (SQLException ex) {
            throw ex;
        } catch (Exception e) {
            if (dbConn != null) {
                dbConn.close();
            }

            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }

        return order;
    }

    public static Order1 getOrderByOrderNo(int ordrNo) throws SQLException {
        Connection dbConn = null;
        Order1 order = new Order1();

        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Statement st = dbConn.createStatement();
            String sqlSelOrder = "SELECT * FROM uberfood.order "
                    + "WHERE order_no = " + ordrNo;

            order = getOrder(sqlSelOrder, st);

        } catch (SQLException ex) {
            throw ex;
        } catch (Exception e) {
            if (dbConn != null) {
                dbConn.close();
            }

            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }

        return order;
    }
    
    public static List<Order1> getAllOrdersByRestaurantId(String restuarantId) throws SQLException {
        Connection dbConn = null;
        List<Order1> orders = new ArrayList<>();

        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Statement st = dbConn.createStatement();
            String sqlSelOrder = "SELECT * FROM uberfood.order "
                    + "WHERE restaurant_id = '" + restuarantId + "'";

            orders = getAllOrders(sqlSelOrder, st);

        } catch (SQLException ex) {
            throw ex;
        } catch (Exception e) {
            if (dbConn != null) {
                dbConn.close();
            }

            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }

        return orders;
    }
    
    public static List<Order1> getAllOrders() throws SQLException {
        Connection dbConn = null;
        List<Order1> orders = new ArrayList<>();

        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Statement st = dbConn.createStatement();
            String sqlSelOrder = "SELECT * FROM uberfood.order";

            orders = getAllOrders(sqlSelOrder, st);

        } catch (SQLException ex) {
            throw ex;
        } catch (Exception e) {
            if (dbConn != null) {
                dbConn.close();
            }

            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }

        return orders;
    }

    private static Order1 getOrder(String sql, Statement st)
            throws SQLException {
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
             return fillOrder(rs);
        }

        return new Order1();
    }
    
    private static List<Order1> getAllOrders(String sql, Statement st)
            throws SQLException {
        List<Order1> orders = new ArrayList<>();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
             orders.add(fillOrder(rs));
        }

        return orders;
    }

    private static Order1 fillOrder(ResultSet rs)
        throws SQLException {
        Order1 order = new Order1();
        order.setOrderId(rs.getInt("order_id"));
        order.setOrderNo(rs.getString("order_no"));
        order.setDate(rs.getString("date"));
        order.setRestaurantId(rs.getString("restaurant_id"));
        order.setOrderType(rs.getString("order_type"));
        order.setExtrasNote(rs.getString("extras_note"));
        order.setDeliveryNote(rs.getString("delivery_note"));
        order.setRecipient(rs.getString("recipient"));
        order.setIntendedRecipient(rs.getString("intended_recipient"));
        order.setCompletionTypeId(rs.getString("completion_type_id"));
        order.setCourierId(rs.getString("courier_id"));

        return order;
    }

    public static boolean updateOrder(Order1 order)
            throws SQLException {
        Connection dbConn = null;
        boolean updStatus = false;

        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Statement st = dbConn.createStatement();
            String sqlSelOrder = "UPDATE uberfood.order SET "
                    + "order_no = '" + order.getOrderNo() + "', "
                    + "date = '" + order.getDate() + "', "
                    + "restaurant_id = '" + order.getRestaurantId() + "', "
                    + "order_type = '" + order.getOrderType() + "', "
                    + "extras_note = '" + order.getExtrasNote() + "', "
                    + "delivery_note = '" + order.getDeliveryNote() + "', "
                    + "recipient = '" + order.getRecipient() + "', "
                    + "intended_recipient = '" + order.getIntendedRecipient() + "', "
                    + "completion_type_id = '" + order.getCompletionTypeId() + "', "
                    + "courier_id = '" + order.getCourierId() + "' "
                    + "WHERE order_id = " + order.getOrderNo();
            updStatus = st.executeUpdate(sqlSelOrder) > 0;

        } catch (SQLException ex) {
            throw ex;
        } catch (Exception e) {
            if (dbConn != null) {
                dbConn.close();
            }

            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }

        return updStatus;
    }
    
}
