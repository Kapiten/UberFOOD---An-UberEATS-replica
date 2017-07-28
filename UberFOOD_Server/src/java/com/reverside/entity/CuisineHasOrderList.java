/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author StrettO
 */
@Entity
@Table(name = "cuisine_has_order_list")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuisineHasOrderList.findAll", query = "SELECT c FROM CuisineHasOrderList c")
    , @NamedQuery(name = "CuisineHasOrderList.findByCuisineCuisineId", query = "SELECT c FROM CuisineHasOrderList c WHERE c.cuisineHasOrderListPK.cuisineCuisineId = :cuisineCuisineId")
    , @NamedQuery(name = "CuisineHasOrderList.findByOrderListOrderListId", query = "SELECT c FROM CuisineHasOrderList c WHERE c.cuisineHasOrderListPK.orderListOrderListId = :orderListOrderListId")})
public class CuisineHasOrderList implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CuisineHasOrderListPK cuisineHasOrderListPK;

    public CuisineHasOrderList() {
    }

    public CuisineHasOrderList(CuisineHasOrderListPK cuisineHasOrderListPK) {
        this.cuisineHasOrderListPK = cuisineHasOrderListPK;
    }

    public CuisineHasOrderList(int cuisineCuisineId, int orderListOrderListId) {
        this.cuisineHasOrderListPK = new CuisineHasOrderListPK(cuisineCuisineId, orderListOrderListId);
    }

    public CuisineHasOrderListPK getCuisineHasOrderListPK() {
        return cuisineHasOrderListPK;
    }

    public void setCuisineHasOrderListPK(CuisineHasOrderListPK cuisineHasOrderListPK) {
        this.cuisineHasOrderListPK = cuisineHasOrderListPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuisineHasOrderListPK != null ? cuisineHasOrderListPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuisineHasOrderList)) {
            return false;
        }
        CuisineHasOrderList other = (CuisineHasOrderList) object;
        if ((this.cuisineHasOrderListPK == null && other.cuisineHasOrderListPK != null) || (this.cuisineHasOrderListPK != null && !this.cuisineHasOrderListPK.equals(other.cuisineHasOrderListPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.CuisineHasOrderList[ cuisineHasOrderListPK=" + cuisineHasOrderListPK + " ]";
    }
    
}
