/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author StrettO
 */
@Entity
@Table(name = "store_address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreAddress.findAll", query = "SELECT s FROM StoreAddress s")
    , @NamedQuery(name = "StoreAddress.findByStoreAddressId", query = "SELECT s FROM StoreAddress s WHERE s.storeAddressPK.storeAddressId = :storeAddressId")
    , @NamedQuery(name = "StoreAddress.findByUnitNo", query = "SELECT s FROM StoreAddress s WHERE s.unitNo = :unitNo")
    , @NamedQuery(name = "StoreAddress.findByStreetname", query = "SELECT s FROM StoreAddress s WHERE s.streetname = :streetname")
    , @NamedQuery(name = "StoreAddress.findBySuburb", query = "SELECT s FROM StoreAddress s WHERE s.suburb = :suburb")
    , @NamedQuery(name = "StoreAddress.findByCity", query = "SELECT s FROM StoreAddress s WHERE s.city = :city")
    , @NamedQuery(name = "StoreAddress.findByCode", query = "SELECT s FROM StoreAddress s WHERE s.code = :code")
    , @NamedQuery(name = "StoreAddress.findByGpsCoord", query = "SELECT s FROM StoreAddress s WHERE s.gpsCoord = :gpsCoord")
    , @NamedQuery(name = "StoreAddress.findByType", query = "SELECT s FROM StoreAddress s WHERE s.type = :type")
    , @NamedQuery(name = "StoreAddress.findByRestaurantRestaurantId", query = "SELECT s FROM StoreAddress s WHERE s.storeAddressPK.restaurantRestaurantId = :restaurantRestaurantId")})
public class StoreAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StoreAddressPK storeAddressPK;
    @Column(name = "store_address_id")
    private Integer storeAddressId;
    @Size(max = 45)
    @Column(name = "unit_no")
    private String unitNo;
    @Size(max = 45)
    @Column(name = "streetname")
    private String streetname;
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

    public StoreAddress() {
    }

    public StoreAddress(StoreAddressPK storeAddressPK) {
        this.storeAddressPK = storeAddressPK;
    }

    public StoreAddress(int storeAddressId, int restaurantRestaurantId) {
        this.storeAddressPK = new StoreAddressPK(storeAddressId, restaurantRestaurantId);
    }

    public StoreAddressPK getStoreAddressPK() {
        return storeAddressPK;
    }

    public void setStoreAddressPK(StoreAddressPK storeAddressPK) {
        this.storeAddressPK = storeAddressPK;
    }

    public Integer getStoreAddressId() {
        return storeAddressId;
    }

    public void setStoreAddressId(Integer storeAddressId) {
        this.storeAddressId = storeAddressId;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeAddressPK != null ? storeAddressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreAddress)) {
            return false;
        }
        StoreAddress other = (StoreAddress) object;
        if ((this.storeAddressPK == null && other.storeAddressPK != null) || (this.storeAddressPK != null && !this.storeAddressPK.equals(other.storeAddressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("storeAddressId", storeAddressId);
            obj.put("unitNo", unitNo);
            obj.put("streetname", streetname);
            obj.put("suburb", suburb);
            obj.put("city", city);
            obj.put("code", code);
            obj.put("gpsCoord", gpsCoord);
            obj.put("type", type);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public StoreAddress fromString(String JSONStoreAddress) {
        StoreAddress storeAddress = new StoreAddress();

        try {
            JSONObject obj = new JSONObject(JSONStoreAddress);
            storeAddress.setStoreAddressId(obj.getInt("storeAddressId"));
            storeAddress.setUnitNo(obj.getString("unitNo"));
            storeAddress.setStreetname(obj.getString("streetname"));
            storeAddress.setSuburb(obj.getString("suburb"));
            storeAddress.setCity(obj.getString("city"));
            storeAddress.setCode(obj.getString("code"));
            storeAddress.setGpsCoord(obj.getString("gpsCoord"));
            storeAddress.setType(obj.getString("type"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return storeAddress;
    }
    
}
