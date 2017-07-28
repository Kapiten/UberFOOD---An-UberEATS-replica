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
public class StoreContactPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "store_contact_id")
    private int storeContactId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "restaurant_restaurant_id")
    private int restaurantRestaurantId;

    public StoreContactPK() {
    }

    public StoreContactPK(int storeContactId, int restaurantRestaurantId) {
        this.storeContactId = storeContactId;
        this.restaurantRestaurantId = restaurantRestaurantId;
    }

    public int getStoreContactId() {
        return storeContactId;
    }

    public void setStoreContactId(int storeContactId) {
        this.storeContactId = storeContactId;
    }

    public int getRestaurantRestaurantId() {
        return restaurantRestaurantId;
    }

    public void setRestaurantRestaurantId(int restaurantRestaurantId) {
        this.restaurantRestaurantId = restaurantRestaurantId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) storeContactId;
        hash += (int) restaurantRestaurantId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreContactPK)) {
            return false;
        }
        StoreContactPK other = (StoreContactPK) object;
        if (this.storeContactId != other.storeContactId) {
            return false;
        }
        if (this.restaurantRestaurantId != other.restaurantRestaurantId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.StoreContactPK[ storeContactId=" + storeContactId + ", restaurantRestaurantId=" + restaurantRestaurantId + " ]";
    }
    
}
