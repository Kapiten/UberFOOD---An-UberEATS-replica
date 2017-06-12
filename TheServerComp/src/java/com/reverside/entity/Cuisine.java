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
@Table(name = "cuisine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuisine.findAll", query = "SELECT c FROM Cuisine c")
    , @NamedQuery(name = "Cuisine.findByCuisineId", query = "SELECT c FROM Cuisine c WHERE c.cuisineId = :cuisineId")
    , @NamedQuery(name = "Cuisine.findByName", query = "SELECT c FROM Cuisine c WHERE c.name = :name")
    , @NamedQuery(name = "Cuisine.findByDescription", query = "SELECT c FROM Cuisine c WHERE c.description = :description")
    , @NamedQuery(name = "Cuisine.findByIngredients", query = "SELECT c FROM Cuisine c WHERE c.ingredients = :ingredients")
    , @NamedQuery(name = "Cuisine.findByPreparation", query = "SELECT c FROM Cuisine c WHERE c.preparation = :preparation")
    , @NamedQuery(name = "Cuisine.findByExtras", query = "SELECT c FROM Cuisine c WHERE c.extras = :extras")
    , @NamedQuery(name = "Cuisine.findByPrice", query = "SELECT c FROM Cuisine c WHERE c.price = :price")
    , @NamedQuery(name = "Cuisine.findByDateAdded", query = "SELECT c FROM Cuisine c WHERE c.dateAdded = :dateAdded")
    , @NamedQuery(name = "Cuisine.findByType", query = "SELECT c FROM Cuisine c WHERE c.type = :type")
    , @NamedQuery(name = "Cuisine.findByProfilePic", query = "SELECT c FROM Cuisine c WHERE c.profilePic = :profilePic")})
public class Cuisine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cuisine_id")
    private Integer cuisineId;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @Size(max = 300)
    @Column(name = "ingredients")
    private String ingredients;
    @Size(max = 300)
    @Column(name = "preparation")
    private String preparation;
    @Size(max = 100)
    @Column(name = "extras")
    private String extras;
    @Size(max = 45)
    @Column(name = "price")
    private String price;
    @Size(max = 45)
    @Column(name = "date_added")
    private String dateAdded;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @Size(max = 300)
    @Column(name = "profile_pic")
    private String profilePic;

    public Cuisine() {
    }

    public Cuisine(Integer cuisineId) {
        this.cuisineId = cuisineId;
    }

    public Integer getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(Integer cuisineId) {
        this.cuisineId = cuisineId;
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuisineId != null ? cuisineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuisine)) {
            return false;
        }
        Cuisine other = (Cuisine) object;
        if ((this.cuisineId == null && other.cuisineId != null) || (this.cuisineId != null && !this.cuisineId.equals(other.cuisineId))) {
            return false;
        }
        return true;
    }

    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("cuisineId", cuisineId);
            obj.put("price", price);
            obj.put("profilePic", profilePic);
            obj.put("name", name);
            obj.put("description", description);
            obj.put("ingredients", ingredients);
            obj.put("extras", extras);
            obj.put("type", type);
            obj.put("dateAdded", dateAdded);
            obj.put("preparation", preparation);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public Cuisine fromString(String JSONCuisine) {
        Cuisine cuisine = new Cuisine();

        try {
            JSONObject obj = new JSONObject(JSONCuisine);
            cuisine.setCuisineId(obj.getInt("cuisineId"));
            cuisine.setPrice(obj.getString("price"));
            cuisine.setProfilePic(obj.getString("profilePic"));
            cuisine.setName(obj.getString("name"));
            cuisine.setDescription(obj.getString("description"));
            cuisine.setIngredients(obj.getString("ingredients"));
            cuisine.setExtras(obj.getString("extras"));
            cuisine.setType(obj.getString("type"));
            cuisine.setDateAdded(obj.getString("dateAdded"));
            cuisine.setPreparation(obj.getString("preparation"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return cuisine;
    }
    
}
