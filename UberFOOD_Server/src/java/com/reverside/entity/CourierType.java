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
@Table(name = "courier_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CourierType.findAll", query = "SELECT c FROM CourierType c")
    , @NamedQuery(name = "CourierType.findByCourierTypeId", query = "SELECT c FROM CourierType c WHERE c.courierTypeId = :courierTypeId")
    , @NamedQuery(name = "CourierType.findByName", query = "SELECT c FROM CourierType c WHERE c.name = :name")
    , @NamedQuery(name = "CourierType.findByDescription", query = "SELECT c FROM CourierType c WHERE c.description = :description")})
public class CourierType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "courier_type_id")
    private Integer courierTypeId;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 300)
    @Column(name = "description")
    private String description;

    public CourierType() {
    }

    public CourierType(Integer courierTypeId) {
        this.courierTypeId = courierTypeId;
    }

    public Integer getCourierTypeId() {
        return courierTypeId;
    }

    public void setCourierTypeId(Integer courierTypeId) {
        this.courierTypeId = courierTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (courierTypeId != null ? courierTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourierType)) {
            return false;
        }
        CourierType other = (CourierType) object;
        if ((this.courierTypeId == null && other.courierTypeId != null) || (this.courierTypeId != null && !this.courierTypeId.equals(other.courierTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.CourierType[ courierTypeId=" + courierTypeId + " ]";
    }
    
}
