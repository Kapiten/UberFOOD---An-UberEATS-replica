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
@Table(name = "extra_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExtraType.findAll", query = "SELECT e FROM ExtraType e")
    , @NamedQuery(name = "ExtraType.findByExtraTypeId", query = "SELECT e FROM ExtraType e WHERE e.extraTypeId = :extraTypeId")
    , @NamedQuery(name = "ExtraType.findByName", query = "SELECT e FROM ExtraType e WHERE e.name = :name")
    , @NamedQuery(name = "ExtraType.findByDescription", query = "SELECT e FROM ExtraType e WHERE e.description = :description")})
public class ExtraType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "extra_type_id")
    private Integer extraTypeId;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "description")
    private String description;

    public ExtraType() {
    }

    public ExtraType(Integer extraTypeId) {
        this.extraTypeId = extraTypeId;
    }

    public Integer getExtraTypeId() {
        return extraTypeId;
    }

    public void setExtraTypeId(Integer extraTypeId) {
        this.extraTypeId = extraTypeId;
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
        hash += (extraTypeId != null ? extraTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExtraType)) {
            return false;
        }
        ExtraType other = (ExtraType) object;
        if ((this.extraTypeId == null && other.extraTypeId != null) || (this.extraTypeId != null && !this.extraTypeId.equals(other.extraTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.ExtraType[ extraTypeId=" + extraTypeId + " ]";
    }
    
}
