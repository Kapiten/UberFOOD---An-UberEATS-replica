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
public class PromotionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "promotion_id")
    private int promotionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "restaurant_restaurant_id")
    private int restaurantRestaurantId;

    public PromotionPK() {
    }

    public PromotionPK(int promotionId, int restaurantRestaurantId) {
        this.promotionId = promotionId;
        this.restaurantRestaurantId = restaurantRestaurantId;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
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
        hash += (int) promotionId;
        hash += (int) restaurantRestaurantId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PromotionPK)) {
            return false;
        }
        PromotionPK other = (PromotionPK) object;
        if (this.promotionId != other.promotionId) {
            return false;
        }
        if (this.restaurantRestaurantId != other.restaurantRestaurantId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.PromotionPK[ promotionId=" + promotionId + ", restaurantRestaurantId=" + restaurantRestaurantId + " ]";
    }
    
}
