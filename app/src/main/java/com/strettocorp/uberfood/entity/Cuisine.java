package com.strettocorp.uberfood.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by reversidesoftwaresolutions on 2017/06/08.
 */

public class Cuisine {
    private Integer cuisineId;
    private String price;
    private String profilePic;
    private String name;
    private String description;
    private String ingredients;
    private String extras;
    private String type;
    private String dateAdded;
    private String preparation;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
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

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
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
