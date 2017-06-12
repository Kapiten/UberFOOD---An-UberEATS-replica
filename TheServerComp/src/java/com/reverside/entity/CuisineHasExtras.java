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
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author StrettO
 */
@Entity
@Table(name = "cuisine_has_extras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuisineHasExtras.findAll", query = "SELECT c FROM CuisineHasExtras c")
    , @NamedQuery(name = "CuisineHasExtras.findByCuisineCuisineId", query = "SELECT c FROM CuisineHasExtras c WHERE c.cuisineCuisineId = :cuisineCuisineId")
    , @NamedQuery(name = "CuisineHasExtras.findByExtrasExtrasId", query = "SELECT c FROM CuisineHasExtras c WHERE c.extrasExtrasId = :extrasExtrasId")
    , @NamedQuery(name = "CuisineHasExtras.findByExtraTypeId", query = "SELECT c FROM CuisineHasExtras c WHERE c.extraTypeId = :extraTypeId")})
public class CuisineHasExtras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cuisine_cuisine_id")
    private Integer cuisineCuisineId;
    @Column(name = "extras_extras_id")
    private Integer extrasExtrasId;
    @Column(name = "extra_type_id")
    private Integer extraTypeId;

    public CuisineHasExtras() {
    }

    public CuisineHasExtras(Integer cuisineCuisineId) {
        this.cuisineCuisineId = cuisineCuisineId;
    }

    public Integer getCuisineCuisineId() {
        return cuisineCuisineId;
    }

    public void setCuisineCuisineId(Integer cuisineCuisineId) {
        this.cuisineCuisineId = cuisineCuisineId;
    }

    public Integer getExtrasExtrasId() {
        return extrasExtrasId;
    }

    public void setExtrasExtrasId(Integer extrasExtrasId) {
        this.extrasExtrasId = extrasExtrasId;
    }

    public Integer getExtraTypeId() {
        return extraTypeId;
    }

    public void setExtraTypeId(Integer extaTypeId) {
        this.extraTypeId = extaTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuisineCuisineId != null ? cuisineCuisineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuisineHasExtras)) {
            return false;
        }
        CuisineHasExtras other = (CuisineHasExtras) object;
        if ((this.cuisineCuisineId == null && other.cuisineCuisineId != null) || (this.cuisineCuisineId != null && !this.cuisineCuisineId.equals(other.cuisineCuisineId))) {
            return false;
        }
        return true;
    }

    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("cuisineCuisineId", cuisineCuisineId);
            obj.put("extrasExtrasId", extrasExtrasId);
            obj.put("extraTypeId", extraTypeId);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public CuisineHasExtras fromString(String JSONCuisineHasExtras) {
        CuisineHasExtras cuisineHasExtras = new CuisineHasExtras();

        try {
            JSONObject obj = new JSONObject(JSONCuisineHasExtras);
            cuisineHasExtras.setCuisineCuisineId(obj.getInt("cuisineCuisineId"));
            cuisineHasExtras.setExtrasExtrasId(obj.getInt("extrasExtrasId"));
            cuisineHasExtras.setExtraTypeId(obj.getInt("extraTypeId"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return cuisineHasExtras;
    }
    
}
