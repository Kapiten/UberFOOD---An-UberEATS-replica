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
 * @author StrettO
 */
@Entity
@Table(name = "order_list")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderList.findAll", query = "SELECT o FROM OrderList o")
    , @NamedQuery(name = "OrderList.findByOrderListId", query = "SELECT o FROM OrderList o WHERE o.orderListId = :orderListId")
    , @NamedQuery(name = "OrderList.findByOrderNo", query = "SELECT o FROM OrderList o WHERE o.orderNo = :orderNo")
    , @NamedQuery(name = "OrderList.findByCuisineId", query = "SELECT o FROM OrderList o WHERE o.cuisineId = :cuisineId")
    , @NamedQuery(name = "OrderList.findByExtra", query = "SELECT o FROM OrderList o WHERE o.extra = :extra")
    , @NamedQuery(name = "OrderList.findBySpecialInstructions", query = "SELECT o FROM OrderList o WHERE o.specialInstructions = :specialInstructions")
    , @NamedQuery(name = "OrderList.findByQuantity", query = "SELECT o FROM OrderList o WHERE o.quantity = :quantity")})
public class OrderList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_list_id")
    private Integer orderListId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "order_no")
    private String orderNo;
    @Size(max = 45)
    @Column(name = "cuisine_id")
    private String cuisineId;
    @Size(max = 45)
    @Column(name = "extra")
    private String extra;
    @Size(max = 200)
    @Column(name = "special_instructions")
    private String specialInstructions;
    @Column(name = "quantity")
    private Integer quantity;

    public OrderList() {
    }

    public OrderList(Integer orderListId) {
        this.orderListId = orderListId;
    }

    public OrderList(Integer orderListId, String orderNo) {
        this.orderListId = orderListId;
        this.orderNo = orderNo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderListId != null ? orderListId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderList)) {
            return false;
        }
        OrderList other = (OrderList) object;
        if ((this.orderListId == null && other.orderListId != null) || (this.orderListId != null && !this.orderListId.equals(other.orderListId))) {
            return false;
        }
        return true;
    }

    @Override
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
    
    public static boolean insertOrderList(OrderList orderList) 
        throws SQLException {
        Connection dbConn = null;
        boolean insStatus = false;
        
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            Statement st = dbConn.createStatement();
            String sqlInsOrderLst = "INSERT INTO order_list ("
                    + "order_no,"
                    + "cuisine_id,"
                    + "extra,"
                    + "special_instructions,"
                    + "quantity) values ("
                    + "'" + orderList.getOrderNo() + "', "
                    + "'" + orderList.getCuisineId() + "', "
                    + "'" + orderList.getExtra() + "', "
                    + "'" + orderList.getSpecialInstructions() + "', "
                    + orderList.getQuantity() + ")";
            
            insStatus = st.executeUpdate(sqlInsOrderLst) > 0;
        }catch(SQLException ex) {
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
        
        return insStatus;
    }
    
    public static List<OrderList> getOrderListByOrderNo(String ordrNo)  
        throws SQLException {
        Connection dbConn = null;
        List<OrderList> orderList = new ArrayList<>();
        
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            Statement st = dbConn.createStatement();
            String sqlSelOrderLst = "SELECT * FROM order_list "
                    + "WHERE order_no = '" + ordrNo + "'";
            
            ResultSet rs = st.executeQuery(sqlSelOrderLst);
            while(rs.next()) {
                OrderList orderListItem = new OrderList();
                orderListItem.setOrderListId(rs.getInt("order_list_id"));
                orderListItem.setOrderNo(rs.getString("order_no"));
                orderListItem.setCuisineId(rs.getString("cuisine_id"));
                orderListItem.setExtra(rs.getString("special_instructions"));
                orderListItem.setQuantity(rs.getInt("quantity"));
                
                orderList.add(orderListItem);
            }
        }catch(SQLException ex) {
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
        
        return orderList;
    }
    
}
