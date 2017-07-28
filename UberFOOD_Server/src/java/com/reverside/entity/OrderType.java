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
@Table(name = "order_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderType.findAll", query = "SELECT o FROM OrderType o")
    , @NamedQuery(name = "OrderType.findByOrderTypeId", query = "SELECT o FROM OrderType o WHERE o.orderTypeId = :orderTypeId")
    , @NamedQuery(name = "OrderType.findByName", query = "SELECT o FROM OrderType o WHERE o.name = :name")
    , @NamedQuery(name = "OrderType.findByDescription", query = "SELECT o FROM OrderType o WHERE o.description = :description")})
public class OrderType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_type_id")
    private Integer orderTypeId;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "description")
    private String description;

    public OrderType() {
    }

    public OrderType(Integer orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public Integer getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(Integer orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderTypeId != null ? orderTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderType)) {
            return false;
        }
        OrderType other = (OrderType) object;
        if ((this.orderTypeId == null && other.orderTypeId != null) || (this.orderTypeId != null && !this.orderTypeId.equals(other.orderTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.OrderType[ orderTypeId=" + orderTypeId + " ]";
    }
    
}
