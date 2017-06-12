package com.strettocorp.uberfood.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by reversidesoftwaresolutions on 2017/06/05.
 */

public class Restaurant {
    private Integer restaurantId;
    private String name;
    private String owners;
    private String description;
    private String operatingHours;
    private String type;
    private String addressId;
    private String profilePic;
    private String userId;

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
}
