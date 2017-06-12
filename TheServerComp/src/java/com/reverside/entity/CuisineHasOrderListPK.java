/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author StrettO
 */
@Embeddable
public class CuisineHasOrderListPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "cuisine_cuisine_id")
    private int cuisineCuisineId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_list_order_list_id")
    private int orderListOrderListId;

    public CuisineHasOrderListPK() {
    }

    public CuisineHasOrderListPK(int cuisineCuisineId, int orderListOrderListId) {
        this.cuisineCuisineId = cuisineCuisineId;
        this.orderListOrderListId = orderListOrderListId;
    }

    public int getCuisineCuisineId() {
        return cuisineCuisineId;
    }

    public void setCuisineCuisineId(int cuisineCuisineId) {
        this.cuisineCuisineId = cuisineCuisineId;
    }

    public int getOrderListOrderListId() {
        return orderListOrderListId;
    }

    public void setOrderListOrderListId(int orderListOrderListId) {
        this.orderListOrderListId = orderListOrderListId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cuisineCuisineId;
        hash += (int) orderListOrderListId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuisineHasOrderListPK)) {
            return false;
        }
        CuisineHasOrderListPK other = (CuisineHasOrderListPK) object;
        if (this.cuisineCuisineId != other.cuisineCuisineId) {
            return false;
        }
        if (this.orderListOrderListId != other.orderListOrderListId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.CuisineHasOrderListPK[ cuisineCuisineId=" + cuisineCuisineId + ", orderListOrderListId=" + orderListOrderListId + " ]";
    }
    
}
