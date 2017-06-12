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
public class RestaurantHasCuisinePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "restaurant_id")
    private int restaurantId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cuisine_id")
    private int cuisineId;

    public RestaurantHasCuisinePK() {
    }

    public RestaurantHasCuisinePK(int restaurantId, int cuisineId) {
        this.restaurantId = restaurantId;
        this.cuisineId = cuisineId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(int cuisineId) {
        this.cuisineId = cuisineId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) restaurantId;
        hash += (int) cuisineId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RestaurantHasCuisinePK)) {
            return false;
        }
        RestaurantHasCuisinePK other = (RestaurantHasCuisinePK) object;
        if (this.restaurantId != other.restaurantId) {
            return false;
        }
        if (this.cuisineId != other.cuisineId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.RestaurantHasCuisinePK[ restaurantId=" + restaurantId + ", cuisineId=" + cuisineId + " ]";
    }
    
}
