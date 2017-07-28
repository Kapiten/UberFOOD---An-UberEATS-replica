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
@Table(name = "promotion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotion.findAll", query = "SELECT p FROM Promotion p")
    , @NamedQuery(name = "Promotion.findByPromotionId", query = "SELECT p FROM Promotion p WHERE p.promotionId = :promotionId")
    , @NamedQuery(name = "Promotion.findByTitle", query = "SELECT p FROM Promotion p WHERE p.title = :title")
    , @NamedQuery(name = "Promotion.findByDescription", query = "SELECT p FROM Promotion p WHERE p.description = :description")
    , @NamedQuery(name = "Promotion.findByPromoCode", query = "SELECT p FROM Promotion p WHERE p.promoCode = :promoCode")
    , @NamedQuery(name = "Promotion.findByDateFrom", query = "SELECT p FROM Promotion p WHERE p.dateFrom = :dateFrom")
    , @NamedQuery(name = "Promotion.findByDateTo", query = "SELECT p FROM Promotion p WHERE p.dateTo = :dateTo")
    , @NamedQuery(name = "Promotion.findByPromoType", query = "SELECT p FROM Promotion p WHERE p.promoType = :promoType")
    , @NamedQuery(name = "Promotion.findByRequirements", query = "SELECT p FROM Promotion p WHERE p.requirements = :requirements")
    , @NamedQuery(name = "Promotion.findByRestaurantId", query = "SELECT p FROM Promotion p WHERE p.restaurantId = :restaurantId")})
public class Promotion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "promotion_id")
    private Integer promotionId;
    @Size(max = 45)
    @Column(name = "title")
    private String title;
    @Size(max = 300)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "promo_code")
    private String promoCode;
    @Size(max = 45)
    @Column(name = "date_from")
    private String dateFrom;
    @Size(max = 45)
    @Column(name = "date_to")
    private String dateTo;
    @Size(max = 45)
    @Column(name = "promo_type")
    private String promoType;
    @Size(max = 45)
    @Column(name = "requirements")
    private String requirements;
    @Column(name = "restaurant_id")
    private Integer restaurantId;

    public Promotion() {
    }

    public Promotion(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getPromoType() {
        return promoType;
    }

    public void setPromoType(String promoType) {
        this.promoType = promoType;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promotionId != null ? promotionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotion)) {
            return false;
        }
        Promotion other = (Promotion) object;
        if ((this.promotionId == null && other.promotionId != null) || (this.promotionId != null && !this.promotionId.equals(other.promotionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.Promotion[ promotionId=" + promotionId + " ]";
    }
    
}
