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
@Table(name = "extras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Extras.findAll", query = "SELECT e FROM Extras e")
    , @NamedQuery(name = "Extras.findByExtrasId", query = "SELECT e FROM Extras e WHERE e.extrasId = :extrasId")
    , @NamedQuery(name = "Extras.findByName", query = "SELECT e FROM Extras e WHERE e.name = :name")
    , @NamedQuery(name = "Extras.findByPrice", query = "SELECT e FROM Extras e WHERE e.price = :price")
    , @NamedQuery(name = "Extras.findByTypeId", query = "SELECT e FROM Extras e WHERE e.typeId = :typeId")
    , @NamedQuery(name = "Extras.findByRequired", query = "SELECT e FROM Extras e WHERE e.required = :required")})
public class Extras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "extras_id")
    private Integer extrasId;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "price")
    private String price;
    @Size(max = 45)
    @Column(name = "type_id")
    private String typeId;
    @Size(max = 45)
    @Column(name = "required")
    private String required;

    public Extras() {
    }

    public Extras(Integer extrasId) {
        this.extrasId = extrasId;
    }

    public Integer getExtrasId() {
        return extrasId;
    }

    public void setExtrasId(Integer extrasId) {
        this.extrasId = extrasId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (extrasId != null ? extrasId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Extras)) {
            return false;
        }
        Extras other = (Extras) object;
        if ((this.extrasId == null && other.extrasId != null) || (this.extrasId != null && !this.extrasId.equals(other.extrasId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.Extras[ extrasId=" + extrasId + " ]";
    }
    
}
