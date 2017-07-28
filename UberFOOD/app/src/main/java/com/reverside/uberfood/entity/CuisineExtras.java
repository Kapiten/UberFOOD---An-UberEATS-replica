package com.reverside.uberfood.entity;

import org.json.JSONObject;

/**
 * Created by reversidesoftwaresolutions on 2017/06/08.
 */

public class CuisineExtras {
    public Integer mCuisineExtrasId;
    public String mPrice;
    public String mName;
    public String mTypeId;
    public String mRequired;

    public Integer getCuisineExtrasId() {
        return mCuisineExtrasId;
    }

    public void setCuisineExtrasId(Integer extrasId) {
        mCuisineExtrasId = extrasId;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getTypeId() {
        return mTypeId;
    }

    public void setTypeId(String typeId) {
        mTypeId = typeId;
    }

    public String getRequired() {
        return mRequired;
    }

    public void setRequired(String required) {
        mRequired = required;
    }



    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("cuisineExtrasId", getCuisineExtrasId());
            obj.put("name", getName());
            obj.put("price", getPrice());
            obj.put("typeId", getTypeId());
        } catch (Exception ex) {

        }

        return obj.toString();
    }

    public static CuisineExtras fromString(String jsonExtras) {
        CuisineExtras extras = new CuisineExtras();

        try {
            JSONObject obj = new JSONObject(jsonExtras);
            extras.setCuisineExtrasId(obj.getInt("cuisineExtrasId"));
            extras.setName(obj.getString("name"));
            extras.setPrice(obj.getString("price"));
            extras.setTypeId(obj.getString("typeId"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return extras;
    }
}
