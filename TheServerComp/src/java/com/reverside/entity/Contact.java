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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author StrettO
 */
@Entity
@Table(name = "contact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c")
    , @NamedQuery(name = "Contact.findByContactId", query = "SELECT c FROM Contact c WHERE c.contactId = :contactId")
    , @NamedQuery(name = "Contact.findByCell1", query = "SELECT c FROM Contact c WHERE c.cell1 = :cell1")
    , @NamedQuery(name = "Contact.findByCell2", query = "SELECT c FROM Contact c WHERE c.cell2 = :cell2")
    , @NamedQuery(name = "Contact.findByTel1", query = "SELECT c FROM Contact c WHERE c.tel1 = :tel1")
    , @NamedQuery(name = "Contact.findByTel2", query = "SELECT c FROM Contact c WHERE c.tel2 = :tel2")
    , @NamedQuery(name = "Contact.findByEmail", query = "SELECT c FROM Contact c WHERE c.email = :email")
    , @NamedQuery(name = "Contact.findByEmail2", query = "SELECT c FROM Contact c WHERE c.email2 = :email2")
    , @NamedQuery(name = "Contact.findByFax", query = "SELECT c FROM Contact c WHERE c.fax = :fax")
    , @NamedQuery(name = "Contact.findByPersonId", query = "SELECT c FROM Contact c WHERE c.personId = :personId")})
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "contact_id")
    private Integer contactId;
    @Size(max = 45)
    @Column(name = "cell1")
    private String cell1;
    @Size(max = 45)
    @Column(name = "cell2")
    private String cell2;
    @Size(max = 45)
    @Column(name = "tel1")
    private String tel1;
    @Size(max = 45)
    @Column(name = "tel2")
    private String tel2;
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
    @Size(max = 45)
    @Column(name = "person_id")
    private String personId;

    public Contact() {
    }

    public Contact(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactId != null ? contactId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.contactId == null && other.contactId != null) || (this.contactId != null && !this.contactId.equals(other.contactId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("contactId", contactId);
            obj.put("cell1", cell1);
            obj.put("tel1", tel1);
            obj.put("email", email);
            obj.put("fax", fax);
            obj.put("personId", personId);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public Contact fromString(String JSONContact) {
        Contact contact = new Contact();

        try {
            JSONObject obj = new JSONObject(JSONContact);
            contact.setContactId(obj.getInt("contactId"));
            contact.setCell1(obj.getString("cell1"));
            contact.setTel1(obj.getString("tel1"));
            contact.setEmail(obj.getString("email"));
            contact.setFax(obj.getString("fax"));
            contact.setPersonId(obj.getString("personId"));

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return contact;
    }
    
}
