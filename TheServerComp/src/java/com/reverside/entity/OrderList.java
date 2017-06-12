/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.entity;

import java.io.Serializable;
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
    , @NamedQuery(name = "OrderList.findBySpecialInstructions", query = "SELECT o FROM OrderList o WHERE o.specialInstructions = :specialInstructions")})
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
        return "com.reverside.entity.OrderList[ orderListId=" + orderListId + " ]";
    }
    
}
