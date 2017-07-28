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
public class CourierPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "courier_id")
    private int courierId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vehicle_vehicle_id")
    private int vehicleVehicleId;

    public CourierPK() {
    }

    public CourierPK(int courierId, int vehicleVehicleId) {
        this.courierId = courierId;
        this.vehicleVehicleId = vehicleVehicleId;
    }

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }

    public int getVehicleVehicleId() {
        return vehicleVehicleId;
    }

    public void setVehicleVehicleId(int vehicleVehicleId) {
        this.vehicleVehicleId = vehicleVehicleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) courierId;
        hash += (int) vehicleVehicleId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourierPK)) {
            return false;
        }
        CourierPK other = (CourierPK) object;
        if (this.courierId != other.courierId) {
            return false;
        }
        if (this.vehicleVehicleId != other.vehicleVehicleId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.CourierPK[ courierId=" + courierId + ", vehicleVehicleId=" + vehicleVehicleId + " ]";
    }

}
