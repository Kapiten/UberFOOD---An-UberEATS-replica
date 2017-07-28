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
@Table(name = "store_contact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreContact.findAll", query = "SELECT s FROM StoreContact s")
    , @NamedQuery(name = "StoreContact.findByStoreContactId", query = "SELECT s FROM StoreContact s WHERE s.storeContactPK.storeContactId = :storeContactId")
    , @NamedQuery(name = "StoreContact.findByTel1", query = "SELECT s FROM StoreContact s WHERE s.tel1 = :tel1")
    , @NamedQuery(name = "StoreContact.findByTel2", query = "SELECT s FROM StoreContact s WHERE s.tel2 = :tel2")
    , @NamedQuery(name = "StoreContact.findByCell1", query = "SELECT s FROM StoreContact s WHERE s.cell1 = :cell1")
    , @NamedQuery(name = "StoreContact.findByCell2", query = "SELECT s FROM StoreContact s WHERE s.cell2 = :cell2")
    , @NamedQuery(name = "StoreContact.findByEmail", query = "SELECT s FROM StoreContact s WHERE s.email = :email")
    , @NamedQuery(name = "StoreContact.findByEmail2", query = "SELECT s FROM StoreContact s WHERE s.email2 = :email2")
    , @NamedQuery(name = "StoreContact.findByFax", query = "SELECT s FROM StoreContact s WHERE s.fax = :fax")
    , @NamedQuery(name = "StoreContact.findByRestaurantRestaurantId", query = "SELECT s FROM StoreContact s WHERE s.storeContactPK.restaurantRestaurantId = :restaurantRestaurantId")})
public class StoreContact implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StoreContactPK storeContactPK;
    @Column(name = "store_contact_id")
    private Integer storeContactId;
    @Size(max = 45)
    @Column(name = "tel1")
    private String tel1;
    @Size(max = 45)
    @Column(name = "tel2")
    private String tel2;
    @Size(max = 45)
    @Column(name = "cell1")
    private String cell1;
    @Size(max = 45)
    @Column(name = "cell2")
    private String cell2;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "email2")
    private String email2;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "fax")
    private String fax;

    public StoreContact() {
    }

    public StoreContact(StoreContactPK storeContactPK) {
        this.storeContactPK = storeContactPK;
    }

    public StoreContact(int storeContactId, int restaurantRestaurantId) {
        this.storeContactPK = new StoreContactPK(storeContactId, restaurantRestaurantId);
    }

    public StoreContactPK getStoreContactPK() {
        return storeContactPK;
    }

    public void setStoreContactPK(StoreContactPK storeContactPK) {
        this.storeContactPK = storeContactPK;
    }

    public Integer getStoreContactId() {
        return storeContactId;
    }

    public void setStoreContactId(Integer storeContactId) {
        this.storeContactId = storeContactId;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getCell1() {
        return cell1;
    }

    public void setCell1(String cell1) {
        this.cell1 = cell1;
    }

    public String getCell2() {
        return cell2;
    }

    public void setCell2(String cell2) {
        this.cell2 = cell2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeContactPK != null ? storeContactPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreContact)) {
            return false;
        }
        StoreContact other = (StoreContact) object;
        if ((this.storeContactPK == null && other.storeContactPK != null) || (this.storeContactPK != null && !this.storeContactPK.equals(other.storeContactPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("storeContactId", storeContactId);
            obj.put("tel1", tel1);
            obj.put("cell1", cell1);
            obj.put("email", email);
            obj.put("fax", fax);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public StoreContact fromString(String JSONStoreContact) {
        StoreContact storeContact = new StoreContact();

        try {
            JSONObject obj = new JSONObject(JSONStoreContact);
            storeContact.setStoreContactId(obj.getInt("storeContactId"));
            storeContact.setTel1(obj.getString("tel1"));
            storeContact.setCell1(obj.getString("cell1"));
            storeContact.setEmail(obj.getString("email"));
            storeContact.setFax(obj.getString("fax"));

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return storeContact;
    }
    
}
