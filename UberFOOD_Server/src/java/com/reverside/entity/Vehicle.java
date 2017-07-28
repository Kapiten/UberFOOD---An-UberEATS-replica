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
@Table(name = "vehicle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehicle.findAll", query = "SELECT v FROM Vehicle v")
    , @NamedQuery(name = "Vehicle.findByVehicleId", query = "SELECT v FROM Vehicle v WHERE v.vehicleId = :vehicleId")
    , @NamedQuery(name = "Vehicle.findByVehicleTypeId", query = "SELECT v FROM Vehicle v WHERE v.vehicleTypeId = :vehicleTypeId")
    , @NamedQuery(name = "Vehicle.findByTitle", query = "SELECT v FROM Vehicle v WHERE v.title = :title")
    , @NamedQuery(name = "Vehicle.findByBrand", query = "SELECT v FROM Vehicle v WHERE v.brand = :brand")
    , @NamedQuery(name = "Vehicle.findByModel", query = "SELECT v FROM Vehicle v WHERE v.model = :model")
    , @NamedQuery(name = "Vehicle.findByColor", query = "SELECT v FROM Vehicle v WHERE v.color = :color")
    , @NamedQuery(name = "Vehicle.findByOwner", query = "SELECT v FROM Vehicle v WHERE v.owner = :owner")
    , @NamedQuery(name = "Vehicle.findByRegistrationNo", query = "SELECT v FROM Vehicle v WHERE v.registrationNo = :registrationNo")
    , @NamedQuery(name = "Vehicle.findByDescription", query = "SELECT v FROM Vehicle v WHERE v.description = :description")})
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "vehicle_id")
    private Integer vehicleId;
    @Size(max = 45)
    @Column(name = "vehicle_type_id")
    private String vehicleTypeId;
    @Size(max = 45)
    @Column(name = "title")
    private String title;
    @Size(max = 45)
    @Column(name = "brand")
    private String brand;
    @Size(max = 45)
    @Column(name = "model")
    private String model;
    @Size(max = 45)
    @Column(name = "color")
    private String color;
    @Size(max = 45)
    @Column(name = "owner")
    private String owner;
    @Size(max = 45)
    @Column(name = "registration_no")
    private String registrationNo;
    @Size(max = 300)
    @Column(name = "description")
    private String description;

    public Vehicle() {
    }

    public Vehicle(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vehicleId != null ? vehicleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehicle)) {
            return false;
        }
        Vehicle other = (Vehicle) object;
        if ((this.vehicleId == null && other.vehicleId != null) || (this.vehicleId != null && !this.vehicleId.equals(other.vehicleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.Vehicle[ vehicleId=" + vehicleId + " ]";
    }
    
}
