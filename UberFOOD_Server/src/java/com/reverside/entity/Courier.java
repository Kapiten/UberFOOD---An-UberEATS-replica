/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author StrettO
 */
@Entity
@Table(name = "courier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courier.findAll", query = "SELECT c FROM Courier c")
    , @NamedQuery(name = "Courier.findByCourierId", query = "SELECT c FROM Courier c WHERE c.courierPK.courierId = :courierId")
    , @NamedQuery(name = "Courier.findByPersonId", query = "SELECT c FROM Courier c WHERE c.personId = :personId")
    , @NamedQuery(name = "Courier.findByCourierTypeId", query = "SELECT c FROM Courier c WHERE c.courierTypeId = :courierTypeId")
    , @NamedQuery(name = "Courier.findByRating", query = "SELECT c FROM Courier c WHERE c.rating = :rating")
    , @NamedQuery(name = "Courier.findByCouriercol", query = "SELECT c FROM Courier c WHERE c.couriercol = :couriercol")
    , @NamedQuery(name = "Courier.findByVehicleVehicleId", query = "SELECT c FROM Courier c WHERE c.courierPK.vehicleVehicleId = :vehicleVehicleId")})
public class Courier implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CourierPK courierPK;
    @Size(max = 45)
    @Column(name = "person_id")
    private String personId;
    @Column(name = "courier_type_id")
    private Integer courierTypeId;
    @Size(max = 45)
    @Column(name = "rating")
    private String rating;
    @Size(max = 45)
    @Column(name = "couriercol")
    private String couriercol;

    public Courier() {
    }

    public Courier(CourierPK courierPK) {
        this.courierPK = courierPK;
    }

    public Courier(int courierId, int vehicleVehicleId) {
        this.courierPK = new CourierPK(courierId, vehicleVehicleId);
    }

    public CourierPK getCourierPK() {
        return courierPK;
    }

    public void setCourierPK(CourierPK courierPK) {
        this.courierPK = courierPK;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Integer getCourierTypeId() {
        return courierTypeId;
    }

    public void setCourierTypeId(Integer courierTypeId) {
        this.courierTypeId = courierTypeId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCouriercol() {
        return couriercol;
    }

    public void setCouriercol(String couriercol) {
        this.couriercol = couriercol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courierPK != null ? courierPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Courier)) {
            return false;
        }
        Courier other = (Courier) object;
        if ((this.courierPK == null && other.courierPK != null) || (this.courierPK != null && !this.courierPK.equals(other.courierPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.Courier[ courierPK=" + courierPK + " ]";
    }
    
}
