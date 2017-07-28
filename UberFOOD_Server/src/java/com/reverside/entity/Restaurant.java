/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.entity;

import com.reverside.connection.DBConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "restaurant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restaurant.findAll", query = "SELECT r FROM Restaurant r")
    , @NamedQuery(name = "Restaurant.findByRestaurantId", query = "SELECT r FROM Restaurant r WHERE r.restaurantId = :restaurantId")
    , @NamedQuery(name = "Restaurant.findByName", query = "SELECT r FROM Restaurant r WHERE r.name = :name")
    , @NamedQuery(name = "Restaurant.findByOwners", query = "SELECT r FROM Restaurant r WHERE r.owners = :owners")
    , @NamedQuery(name = "Restaurant.findByDescription", query = "SELECT r FROM Restaurant r WHERE r.description = :description")
    , @NamedQuery(name = "Restaurant.findByOperatingHours", query = "SELECT r FROM Restaurant r WHERE r.operatingHours = :operatingHours")
    , @NamedQuery(name = "Restaurant.findByType", query = "SELECT r FROM Restaurant r WHERE r.type = :type")
    , @NamedQuery(name = "Restaurant.findByAddressId", query = "SELECT r FROM Restaurant r WHERE r.addressId = :addressId")
    , @NamedQuery(name = "Restaurant.findByProfilePic", query = "SELECT r FROM Restaurant r WHERE r.profilePic = :profilePic")
    , @NamedQuery(name = "Restaurant.findByUserId", query = "SELECT r FROM Restaurant r WHERE r.userId = :userId")
    , @NamedQuery(name = "Restaurant.findByRestaurantNo", query = "SELECT r FROM Restaurant r WHERE r.restaurantNo = :restaurantNo")
    , @NamedQuery(name = "Restaurant.findByTokenId", query = "SELECT r FROM Restaurant r WHERE r.tokenId = :tokenId")
    , @NamedQuery(name = "Restaurant.findBySessionToken", query = "SELECT r FROM Restaurant r WHERE r.sessionToken = :sessionToken")})
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "restaurant_id")
    private Integer restaurantId;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "owners")
    private String owners;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @Size(max = 100)
    @Column(name = "operating_hours")
    private String operatingHours;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @Size(max = 45)
    @Column(name = "address_id")
    private String addressId;
    @Size(max = 200)
    @Column(name = "profile_pic")
    private String profilePic;
    @Size(max = 45)
    @Column(name = "user_id")
    private String userId;
    @Size(max = 45)
    @Column(name = "restaurant_no")
    private String restaurantNo;
    @Size(max = 100)
    @Column(name = "token_id")
    private String tokenId;
    @Size(max = 45)
    @Column(name = "session_token")
    private String sessionToken;

    public Restaurant() {
    }

    public Restaurant(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwners() {
        return owners;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRestaurantNo() {
        return restaurantNo;
    }

    public void setRestaurantNo(String restaurantNo) {
        this.restaurantNo = restaurantNo;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (restaurantId != null ? restaurantId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restaurant)) {
            return false;
        }
        Restaurant other = (Restaurant) object;
        if ((this.restaurantId == null && other.restaurantId != null) || (this.restaurantId != null && !this.restaurantId.equals(other.restaurantId))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("restaurantId", restaurantId);
            obj.put("name", name);
            obj.put("description", description);
            obj.put("operatingHours", operatingHours);
            obj.put("profilePic", profilePic);
            obj.put("type", type);
            obj.put("addressId", addressId);
            obj.put("userId", userId);
        } catch(JSONException ex) {

        }

        return obj.toString();
    }

    public static Restaurant fromString(String jsonString) {
        Restaurant restaurant = new Restaurant();

        try {
            JSONObject obj = new JSONObject(jsonString);
            restaurant.setRestaurantId(obj.getInt("restaurantId"));
            restaurant.setName(obj.getString("name"));
            restaurant.setDescription(obj.getString("description"));
            restaurant.setOperatingHours(obj.getString("operatingHours"));
            restaurant.setProfilePic(obj.getString("profilePic"));
            restaurant.setType("type");
            restaurant.setAddressId("addressId");
            restaurant.setUserId("userId");

        } catch (JSONException ex) {

        }

        return restaurant;
    }

    public static Restaurant getRestaurant(int id) throws Exception {
        Connection dbConn = null;
        Restaurant restaurant = new Restaurant();
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String sqlRest = "SELECT * FROM restaurant WHERE restaurant_id = " + id;
            ResultSet rs = stmt.executeQuery(sqlRest);
            while (rs.next()) {
                restaurant.setRestaurantId(rs.getInt("restaurant_id"));
                restaurant.setName(rs.getString("name"));
                restaurant.setDescription(rs.getString("description"));
                restaurant.setOperatingHours(rs.getString("operating_hours"));
                restaurant.setProfilePic(rs.getString("profile_pic"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            if (dbConn != null) {
                dbConn.close();
            }
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return restaurant;
    }

    public static List<Restaurant> getRestaurantAll() throws Exception {
        Connection dbConn = null;
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String sqlRest = "SELECT * FROM uberfood.restaurant";
            ResultSet rs = stmt.executeQuery(sqlRest);
            while (rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantId(rs.getInt("restaurant_id"));
                restaurant.setName(rs.getString("name"));
                restaurant.setDescription(rs.getString("description"));
                restaurant.setOperatingHours(rs.getString("operating_hours"));
                restaurant.setProfilePic(rs.getString("profile_pic"));
                restaurants.add(restaurant);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            if (dbConn != null) {
                dbConn.close();
            }
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return restaurants;
    }

    public static List<Restaurant> getRestaurantAllBySearch(String search) throws Exception {
        Connection dbConn = null;
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String sqlRest = "SELECT * FROM uberfood.restaurant "
                    + "WHERE name LIKE '%" + search + "%'";
            ResultSet rs = stmt.executeQuery(sqlRest);
            while (rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantId(rs.getInt("restaurant_id"));
                restaurant.setName(rs.getString("name"));
                restaurant.setDescription(rs.getString("description"));
                restaurant.setOperatingHours(rs.getString("operating_hours"));
                restaurant.setProfilePic(rs.getString("profile_pic"));
                restaurants.add(restaurant);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            if (dbConn != null) {
                dbConn.close();
            }
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return restaurants;
    }
    
}
