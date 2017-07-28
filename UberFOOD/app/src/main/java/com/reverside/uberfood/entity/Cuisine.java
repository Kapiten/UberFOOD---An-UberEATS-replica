package com.reverside.uberfood.entity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reverside.uberfood.connection.Utility;
import com.reverside.uberfood.essentials.Constants;
import com.reverside.uberfood.essentials.Constants.RestResponseCodes;
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

    private static int sqlResult = 0;

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

    public static Cuisine invokeGetCuisineWS(RequestParams params, final Self.ServComBuilder servComBuilder) {
        final Cuisine cuisine = new Cuisine();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://" +
                        Constants.GlassfishConnProps.URL + ":" +
                        Constants.GlassfishConnProps.PORT + "/" +
                        Constants.GlassfishConnProps.WEB_APP + "/" +
                        Constants.GlassfishConnProps.REST_HOME + "/" +
                        "findEntity/cuisine",
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
                            cuisine.setCuisineId(obj.getInt("cuisineId"));
                            cuisine.setName(obj.getString("name"));
                            cuisine.setDescription(obj.getString("description"));
                            cuisine.setPrice(obj.getString("price"));
                            cuisine.setProfilePic(obj.getString("profilePic"));
                            //cuisine.setIngredients(obj.getString("ingredients"));
                            //cuisine.setPreparation(obj.getString("preparation"));
                            //cuisine.setDateAdded(obj.getString("dateAdded"));

                            servComBuilder.mServResponse.onResponse(RestResponseCodes.SUCCESS, "Successful getCuisine(int)");
                            servComBuilder.mServResponse.onResponse(cuisine);
                        } catch (IOException ex){
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            servComBuilder.mServResponse.onResponse(RestResponseCodes.FAULT, "Error Occured [Server's JSON response might be invalid]!");
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        servComBuilder.mServResponse.onResponse(RestResponseCodes.FAULT,
                                Utility.getFailureMsg(i, bytes, servComBuilder));
                    }
                });

        return cuisine;
    }

    public static List<Cuisine> invokeGetCuisinesWS(RequestParams params, final Self.ServComBuilder servComBuilder) {
        final List<Cuisine> cuisines = new ArrayList<>();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://" +
                        Constants.GlassfishConnProps.URL + ":" +
                        Constants.GlassfishConnProps.PORT + "/" +
                        Constants.GlassfishConnProps.WEB_APP + "/" +
                        Constants.GlassfishConnProps.REST_HOME + "/" +
                        "findEntity/cuisineAll",
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

                            JSONArray objArr = new JSONArray(response);
                            for(int x = 0; x < objArr.length(); x++) {
                                JSONObject obj = objArr.getJSONObject(x);

                                Cuisine cuisine = new Cuisine();
                                cuisine.setCuisineId(obj.getInt("cuisineId"));
                                cuisine.setName(obj.getString("name"));
                                cuisine.setDescription(obj.getString("description"));
                                cuisine.setPrice(obj.getString("price"));
                                cuisine.setProfilePic(obj.getString("profilePic"));
                                //cuisine.setIngredients(obj.getString("ingredients"));
                                //cuisine.setPreparation(obj.getString("preparation"));
                                //cuisine.setDateAdded(obj.getString("dateAdded"));

                                cuisines.add(cuisine);
                            }

                            sqlResult = 1;
                            servComBuilder.mServResponse.onResponse(sqlResult, "Successful getCuisine(int)");
                            servComBuilder.mServResponse.onResponse(cuisines);
                        } catch (IOException ex){
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            sqlResult = 3;
                            servComBuilder.mServResponse.onResponse(sqlResult, "Error Occured [Server's JSON response might be invalid]!");
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Utility.getFailureMsg(i, bytes, servComBuilder);
                    }
                });

        return cuisines;
    }

}
