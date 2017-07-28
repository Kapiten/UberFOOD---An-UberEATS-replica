package com.reverside.uberfood.entity;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reverside.uberfood.connection.Utility;
import com.reverside.uberfood.essentials.Constants;
import com.reverside.uberfood.essentials.Self;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by reversidesoftwaresolutions on 2017/06/05.
 */

public class Restaurant {
    private Integer restaurantId;
    private String name = "";
    private String owners = "";
    private String description = "";
    private String operatingHours = "";
    private String type = "";
    private String addressId = "";
    private String profilePic = "";
    private String userId = "";

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

    public static Restaurant invokeGetRestaurantWS(RequestParams params, final Self.ServComBuilder servComBuilder) {
        final Restaurant restaurant = new Restaurant();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://" +
                        Constants.GlassfishConnProps.URL + ":" +
                        Constants.GlassfishConnProps.PORT + "/" +
                        Constants.GlassfishConnProps.WEB_APP + "/" +
                        Constants.GlassfishConnProps.REST_HOME + "/" +
                        "findEntity/restaurant",
                params,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
                            String line = "", response = "";
                            while ((line = reader.readLine()) != null) {
                                response += line;
                            }
                            reader.close();


                            JSONObject obj = new JSONObject(response);

                            if(obj.getInt("restaurant_id") > 0) {
                                restaurant.setRestaurantId(obj.getInt("restaurant_id"));
                                restaurant.setName(obj.getString("name"));
                                restaurant.setDescription(obj.getString("description"));
                                restaurant.setProfilePic(obj.getString("profile_pic"));
                                restaurant.setOperatingHours(obj.getString("operatingHours"));
                                Log.i("Self", "Good: " + obj.getInt("restaurant_id"));

                                servComBuilder.mServResponse.onResponse(Constants.RestResponseCodes.SUCCESS, "Successfully getRestaurant(int)");
                                servComBuilder.mServResponse.onResponse(restaurant);
                            } else {
                                servComBuilder.mServResponse.onResponse(Constants.RestResponseCodes.FAULT, "Something went wrong while saving your information");
                            }
                        } catch (IOException ex){
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            servComBuilder.mServResponse.onResponse(Constants.RestResponseCodes.FAULT, "Error Occured [Server's JSON response might be invalid]!");
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Utility.getFailureMsg(i, bytes, servComBuilder);
                    }
                });

        return restaurant;
    }

    public static List<Restaurant> invokeGetRestaurantsWS(final Self.ServComBuilder servComBuilder) {
        final List<Restaurant> restaurants = new ArrayList<>();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://" +
                        Constants.GlassfishConnProps.URL + ":" +
                        Constants.GlassfishConnProps.PORT + "/" +
                        Constants.GlassfishConnProps.WEB_APP + "/" +
                        Constants.GlassfishConnProps.REST_HOME + "/" +
                        "findEntity/restaurantAll",
                null,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        //mProgressDialog.hide();

                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
                            String line = "", response = "";
                            while ((line = reader.readLine()) != null) {
                                response += line;
                            }
                            reader.close();

                            JSONArray objArr = new JSONArray(response);
                            for(int x = 0; x < objArr.length(); x++) {
                                JSONObject obj = objArr.getJSONObject(x);

                                Restaurant restaurant = new Restaurant();
                                restaurant.setRestaurantId(obj.getInt("restaurantId"));
                                restaurant.setName(obj.getString("name"));
                                restaurant.setDescription(obj.getString("description"));
                                restaurant.setProfilePic(obj.getString("profilePic"));
                                restaurant.setOperatingHours(obj.getString("operatingHours"));

                                restaurants.add(restaurant);
                            }

                            servComBuilder.mServResponse.onResponse(Constants.RestResponseCodes.SUCCESS, "Successful getRestaurants()");
                            servComBuilder.mServResponse.onResponse(restaurants);
                        } catch (IOException ex){
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            servComBuilder.mServResponse.onResponse(Constants.RestResponseCodes.FAULT, "Error Occured [Server's JSON response might be invalid]!");
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Utility.getFailureMsg(i, bytes, servComBuilder);
                    }
                });

        return restaurants;
    }
}
