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
public class StoreAddressPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "store_address_id")
    private int storeAddressId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "restaurant_restaurant_id")
    private int restaurantRestaurantId;

    public StoreAddressPK() {
    }

    public StoreAddressPK(int storeAddressId, int restaurantRestaurantId) {
        this.storeAddressId = storeAddressId;
        this.restaurantRestaurantId = restaurantRestaurantId;
    }

    public int getStoreAddressId() {
        return storeAddressId;
    }

    public void setStoreAddressId(int storeAddressId) {
        this.storeAddressId = storeAddressId;
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
        hash += (int) storeAddressId;
        hash += (int) restaurantRestaurantId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreAddressPK)) {
            return false;
        }
        StoreAddressPK other = (StoreAddressPK) object;
        if (this.storeAddressId != other.storeAddressId) {
            return false;
        }
        if (this.restaurantRestaurantId != other.restaurantRestaurantId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.StoreAddressPK[ storeAddressId=" + storeAddressId + ", restaurantRestaurantId=" + restaurantRestaurantId + " ]";
    }
    
}
