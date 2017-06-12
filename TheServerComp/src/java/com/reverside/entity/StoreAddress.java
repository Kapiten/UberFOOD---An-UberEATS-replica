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
@Table(name = "store_address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreAddress.findAll", query = "SELECT s FROM StoreAddress s")
    , @NamedQuery(name = "StoreAddress.findByStoreAddressId", query = "SELECT s FROM StoreAddress s WHERE s.storeAddressPK.storeAddressId = :storeAddressId")
    , @NamedQuery(name = "StoreAddress.findByUnitNo", query = "SELECT s FROM StoreAddress s WHERE s.unitNo = :unitNo")
    , @NamedQuery(name = "StoreAddress.findByStreetame", query = "SELECT s FROM StoreAddress s WHERE s.streetame = :streetame")
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
        return "com.reverside.entity.StoreAddress[ storeAddressPK=" + storeAddressPK + " ]";
    }
    
}
