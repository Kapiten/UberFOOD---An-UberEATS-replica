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
@Table(name = "restaurant_has_cuisine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RestaurantHasCuisine.findAll", query = "SELECT r FROM RestaurantHasCuisine r")
    , @NamedQuery(name = "RestaurantHasCuisine.findByRestaurantId", query = "SELECT r FROM RestaurantHasCuisine r WHERE r.restaurantHasCuisinePK.restaurantId = :restaurantId")
    , @NamedQuery(name = "RestaurantHasCuisine.findByCuisineId", query = "SELECT r FROM RestaurantHasCuisine r WHERE r.restaurantHasCuisinePK.cuisineId = :cuisineId")})
public class RestaurantHasCuisine implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RestaurantHasCuisinePK restaurantHasCuisinePK;

    public RestaurantHasCuisine() {
    }

    public RestaurantHasCuisine(RestaurantHasCuisinePK restaurantHasCuisinePK) {
        this.restaurantHasCuisinePK = restaurantHasCuisinePK;
    }

    public RestaurantHasCuisine(int restaurantId, int cuisineId) {
        this.restaurantHasCuisinePK = new RestaurantHasCuisinePK(restaurantId, cuisineId);
    }

    public RestaurantHasCuisinePK getRestaurantHasCuisinePK() {
        return restaurantHasCuisinePK;
    }

    public void setRestaurantHasCuisinePK(RestaurantHasCuisinePK restaurantHasCuisinePK) {
        this.restaurantHasCuisinePK = restaurantHasCuisinePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (restaurantHasCuisinePK != null ? restaurantHasCuisinePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RestaurantHasCuisine)) {
            return false;
        }
        RestaurantHasCuisine other = (RestaurantHasCuisine) object;
        if ((this.restaurantHasCuisinePK == null && other.restaurantHasCuisinePK != null) || (this.restaurantHasCuisinePK != null && !this.restaurantHasCuisinePK.equals(other.restaurantHasCuisinePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.RestaurantHasCuisine[ restaurantHasCuisinePK=" + restaurantHasCuisinePK + " ]";
    }
    
}
