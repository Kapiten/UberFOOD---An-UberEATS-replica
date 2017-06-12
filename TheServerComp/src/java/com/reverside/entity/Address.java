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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author StrettO
 */
@Entity
@Table(name = "address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
    , @NamedQuery(name = "Address.findByAddressId", query = "SELECT a FROM Address a WHERE a.addressId = :addressId")
    , @NamedQuery(name = "Address.findByUnitNo", query = "SELECT a FROM Address a WHERE a.unitNo = :unitNo")
    , @NamedQuery(name = "Address.findByStreetame", query = "SELECT a FROM Address a WHERE a.streetame = :streetame")
    , @NamedQuery(name = "Address.findBySuburb", query = "SELECT a FROM Address a WHERE a.suburb = :suburb")
    , @NamedQuery(name = "Address.findByCity", query = "SELECT a FROM Address a WHERE a.city = :city")
    , @NamedQuery(name = "Address.findByCode", query = "SELECT a FROM Address a WHERE a.code = :code")
    , @NamedQuery(name = "Address.findByGpsCoord", query = "SELECT a FROM Address a WHERE a.gpsCoord = :gpsCoord")
    , @NamedQuery(name = "Address.findByType", query = "SELECT a FROM Address a WHERE a.type = :type")
    , @NamedQuery(name = "Address.findByPersonId", query = "SELECT a FROM Address a WHERE a.personId = :personId")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "address_id")
    private Integer addressId;
    @Size(max = 45)
    @Column(name = "unit_no")
    private String unitNo;
    @Size(max = 45)
    @Column(name = "streetame")
    private String streetame;
    @Size(max = 45)
    @Column(name = "suburb")
    private String suburb;
    @Size(max = 45)
    @Column(name = "city")
    private String city;
    @Size(max = 45)
    @Column(name = "code")
    private String code;
    @Size(max = 45)
    @Column(name = "gps_coord")
    private String gpsCoord;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "person_id")
    private int personId;

    public Address() {
    }

    public Address(Integer addressId) {
        this.addressId = addressId;
    }

    public Address(Integer addressId, int personId) {
        this.addressId = addressId;
        this.personId = personId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getStreetame() {
        return streetame;
    }

    public void setStreetame(String streetame) {
        this.streetame = streetame;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGpsCoord() {
        return gpsCoord;
    }

    public void setGpsCoord(String gpsCoord) {
        this.gpsCoord = gpsCoord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addressId != null ? addressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.addressId == null && other.addressId != null) || (this.addressId != null && !this.addressId.equals(other.addressId))) {
            return false;
        }
        return true;
    }

    
    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("addressId", addressId);
            obj.put("unitNo", unitNo);
            obj.put("streetame", streetame);
            obj.put("suburb", suburb);
            obj.put("city", city);
            obj.put("code", code);
            obj.put("gpsCoord", gpsCoord);
            obj.put("personId", personId);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public Address fromString(String JSONAddress) {
        Address address = new Address();

        try {
            JSONObject obj = new JSONObject(JSONAddress);
            address.setAddressId(obj.getInt("addressId"));
            address.setUnitNo(obj.getString("unitNo"));
            address.setStreetame(obj.getString("streetame"));
            address.setSuburb(obj.getString("suburb"));
            address.setCity(obj.getString("city"));
            address.setCode(obj.getString("code"));
            address.setGpsCoord(obj.getString("gpsCoord"));
            address.setPersonId(obj.getInt("personId"));

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return address;
    }
    
}
