package com.strettocorp.uberfood.essentials;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Log;
import android.webkit.WebResourceResponse;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.strettocorp.uberfood.R;
import com.strettocorp.uberfood.connection.Utility;
import com.strettocorp.uberfood.entity.Cuisine;
import com.strettocorp.uberfood.entity.OrderList;
import com.strettocorp.uberfood.entity.Restaurant;
import com.strettocorp.uberfood.essentials.Constants.GlassfishConnProps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by StrettO on 2017/06/04.
 */

public class Self {

    private static Self mSelf;

    private static int sqlResult;
    private static ServResponse mResponse;

    private static Bitmap bitmapImg;

    public static Typeface mUberFont;

    private Context mContext;

    public Restaurant mRestaurant;
    public Cuisine mCuisine;

    private List<OrderList> mOrderList = new ArrayList<>();

    private Self(Context context) {
        mContext = context;
    }

    public static Self get(Context context) {
        if(mSelf == null) {
            mSelf = new Self(context);
            mUberFont = Typeface.createFromAsset(context.getAssets(), "font/clanpro_news.ttf");
        }

        return mSelf;
    }

    public List<OrderList> getOrderList() {
        return mOrderList;
    }

    public void setOrderList(List<OrderList> orderList) {
        mOrderList = orderList;
    }

    public interface ServResponse {
        void onResponse(int responseCode);
    }

    public void setServResponse(ServResponse servResponse) {
        mResponse = servResponse;
    }

    public String getOperatingHrsString(String formattedString) {
        if(formattedString != null) {
            return mContext.getString(R.string.restuarant_oper_hrs,
                    formattedString.substring(
                            formattedString.lastIndexOf("[") + 1,
                            formattedString.lastIndexOf("/")),
                    formattedString.substring(
                            formattedString.lastIndexOf("/") + 1,
                            formattedString.lastIndexOf("]")));
        } else {
            return "";
        }
    }

    public static void loginUser(String email, String password) {
        RequestParams params = new RequestParams();
        if(Utility.isNotNull(email) && Utility.isNotNull(password)) {
            params.put("username", email);
            params.put("password", password);
            invokeLoginWS(params);
        }
    }

    private static void invokeLoginWS(RequestParams params) {
        //mProgressDialog.show();
        //1 = ok, 2 = ok - sum, 3 - fault
       sqlResult = 0;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://" +
                GlassfishConnProps.URL + ":" +
                GlassfishConnProps.PORT + "/" +
                GlassfishConnProps.WEB_APP + "/" +
                GlassfishConnProps.REST_HOME + "/" +
                "login/doLogin",
                params,
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

                            JSONObject obj = new JSONObject(response);
                            if(obj.getBoolean("status")) {
                                sqlResult = 1;
                                mResponse.onResponse(sqlResult);
                                //Toast.makeText(getActivity(), "You are successfully logged in!", Toast.LENGTH_SHORT).show();
                            } else {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                            }
                        } catch (IOException ex){
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            //Toast.makeText(getActivity(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_SHORT).show();
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        //mProgressDialog.hide();

                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
                            String line = "", response = "";
                            while ((line = reader.readLine()) != null) {
                                response += line;
                            }

                            reader.close();

                            if (i == 404) {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                               // Toast.makeText(getActivity(), "Requested resource not found", Toast.LENGTH_SHORT).show();
                            }
// When Http response code is '500'
                            else if (i == 500) {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                               // Toast.makeText(getActivity(), "Something went wrong at server end", Toast.LENGTH_SHORT).show();
                            }
// When Http response code other than 404, 500
                            else {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                                //Toast.makeText(getActivity(), "Unexpected Error occcured! [Most common Error.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            ex.printStackTrace();
                        } catch (Exception ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            //Toast.makeText(getActivity(), "Unexpected Error occcured!", Toast.LENGTH_SHORT).show();
                            ex.printStackTrace();
                        }
                    }


                });
    }

    public static void registerUser(
            String email,
            String password,
            String names,
            String surname,
            String mobile) {
        RequestParams params = new RequestParams();
        if(Utility.isNotNull(email) && Utility.isNotNull(password)) {
            params.put("username", email);
            params.put("password", password);
            params.put("names", names);
            params.put("surname", surname);
            params.put("email", email);
            params.put("cell1", mobile);

            invokeRegisterWS(params);
        }
    }

    private static void invokeRegisterWS(RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://" +
                        GlassfishConnProps.URL + ":" +
                        GlassfishConnProps.PORT + "/" +
                        GlassfishConnProps.WEB_APP + "/" +
                        GlassfishConnProps.REST_HOME + "/" +
                        "register/doRegister",
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
                            if(obj.getBoolean("status")) {
                                sqlResult = 1;
                                mResponse.onResponse(sqlResult);
                            } else {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                            }
                        } catch (IOException ex){
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
                            String line = "", response = "";
                            while ((line = reader.readLine()) != null) {
                                response += line;
                            }

                            reader.close();

                            if (i == 404) {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                            }
// When Http response code is '500'
                            else if (i == 500) {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                        }
// When Http response code other than 404, 500
                            else {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                            }
                        } catch (IOException ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            ex.printStackTrace();
                        } catch (Exception ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            ex.printStackTrace();
                        }
                    }
                });
    }

    public static Restaurant getRestaurant(int id) {
        RequestParams params = new RequestParams();
        params.put("id", id);

        return invokeGetRestaurantWS(params);
    }

    public static Restaurant invokeGetRestaurantWS(RequestParams params) {
        final Restaurant restaurant = new Restaurant();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://" +
                        GlassfishConnProps.URL + ":" +
                        GlassfishConnProps.PORT + "/" +
                        GlassfishConnProps.WEB_APP + "/" +
                        GlassfishConnProps.REST_HOME + "/" +
                        "findEntity/restaurant",
                params,
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


                            JSONObject obj = new JSONObject(response);

                            if(obj.getInt("restaurant_id") > 0) {
                                restaurant.setRestaurantId(obj.getInt("restaurant_id"));
                                restaurant.setName(obj.getString("name"));
                                restaurant.setDescription(obj.getString("description"));
                                restaurant.setProfilePic(obj.getString("profile_pic"));
                                restaurant.setOperatingHours(obj.getString("operatingHours"));
                                Log.i("Self", "Good: " + obj.getInt("restaurant_id"));

                                mResponse.onResponse(sqlResult);

                                Log.i("Self", "Good: " + obj.getString("name"));

                                sqlResult = 1;
                                //Toast.makeText(getActivity(), "You are successfully logged in!", Toast.LENGTH_SHORT).show();
                            } else {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                            }
                        } catch (IOException ex){
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            //Toast.makeText(getActivity(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_SHORT).show();
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        //mProgressDialog.hide();

                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
                            String line = "", response = "";
                            while ((line = reader.readLine()) != null) {
                                response += line;
                            }

                            reader.close();

                            Log.i("Self", "Bad: " + response);

                            if (i == 404) {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                                // Toast.makeText(getActivity(), "Requested resource not found", Toast.LENGTH_SHORT).show();
                            }
// When Http response code is '500'
                            else if (i == 500) {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                                // Toast.makeText(getActivity(), "Something went wrong at server end", Toast.LENGTH_SHORT).show();
                            }
// When Http response code other than 404, 500
                            else {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                                //Toast.makeText(getActivity(), "Unexpected Error occcured! [Most common Error.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            ex.printStackTrace();
                        } catch (Exception ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            //Toast.makeText(getActivity(), "Unexpected Error occcured!", Toast.LENGTH_SHORT).show();
                            ex.printStackTrace();
                        }
                    }
                });

        return restaurant;
    }

    public static List<Restaurant> getRestaurants() {
        final List<Restaurant> restaurants = new ArrayList<>();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://" +
                        GlassfishConnProps.URL + ":" +
                        GlassfishConnProps.PORT + "/" +
                        GlassfishConnProps.WEB_APP + "/" +
                        GlassfishConnProps.REST_HOME + "/" +
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

                            sqlResult = 1;
                            mResponse.onResponse(sqlResult);

                            /*JSONObject obj = new JSONObject(response);

                            if(obj.getInt("restaurant_id") > 0) {
                                Log.i("Self", "Good: " + obj.getInt("restaurant_id"));

                                Log.i("Self", "Good: " + obj.getString("name"));
                                //Toast.makeText(getActivity(), "You are successfully logged in!", Toast.LENGTH_SHORT).show();
                            } else {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                            }*/
                        } catch (IOException ex){
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            //Toast.makeText(getActivity(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_SHORT).show();
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        //mProgressDialog.hide();

                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
                            String line = "", response = "";
                            while ((line = reader.readLine()) != null) {
                                response += line;
                            }

                            reader.close();

                            Log.i("Self", "Bad: " + response);

                            if (i == 404) {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                                // Toast.makeText(getActivity(), "Requested resource not found", Toast.LENGTH_SHORT).show();
                            }
// When Http response code is '500'
                            else if (i == 500) {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                                // Toast.makeText(getActivity(), "Something went wrong at server end", Toast.LENGTH_SHORT).show();
                            }
// When Http response code other than 404, 500
                            else {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                                //Toast.makeText(getActivity(), "Unexpected Error occcured! [Most common Error.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            ex.printStackTrace();
                        } catch (Exception ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            //Toast.makeText(getActivity(), "Unexpected Error occcured!", Toast.LENGTH_SHORT).show();
                            ex.printStackTrace();
                        }
                    }
                });

        return restaurants;
    }

    public static Cuisine getCuisine(int id) {
        final Cuisine cuisine = new Cuisine();
        RequestParams params = new RequestParams();
        params.put("id", id);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://" +
                        GlassfishConnProps.URL + ":" +
                        GlassfishConnProps.PORT + "/" +
                        GlassfishConnProps.WEB_APP + "/" +
                        GlassfishConnProps.REST_HOME + "/" +
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

                            JSONArray objArr = new JSONArray(response);
                            for(int x = 0; x < objArr.length(); x++) {
                                JSONObject obj = objArr.getJSONObject(x);
                                cuisine.setCuisineId(obj.getInt("cuisineId"));
                                cuisine.setName(obj.getString("name"));
                                cuisine.setDescription(obj.getString("description"));
                                cuisine.setPrice(obj.getString("price"));
                                cuisine.setProfilePic(obj.getString("profilePic"));
                                cuisine.setIngredients(obj.getString("ingredients"));
                                cuisine.setPreparation(obj.getString("preparation"));
                                cuisine.setDateAdded(obj.getString("dateAdded"));
                            }

                            sqlResult = 1;
                            mResponse.onResponse(sqlResult);

                            /*JSONObject obj = new JSONObject(response);

                            if(obj.getInt("restaurant_id") > 0) {
                                Log.i("Self", "Good: " + obj.getInt("restaurant_id"));

                                Log.i("Self", "Good: " + obj.getString("name"));
                                //Toast.makeText(getActivity(), "You are successfully logged in!", Toast.LENGTH_SHORT).show();
                            } else {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                            }*/
                        } catch (IOException ex){
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            //Toast.makeText(getActivity(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_SHORT).show();
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        //mProgressDialog.hide();

                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
                            String line = "", response = "";
                            while ((line = reader.readLine()) != null) {
                                response += line;
                            }

                            reader.close();

                            Log.i("Self", "Bad: " + response);

                            if (i == 404) {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                                // Toast.makeText(getActivity(), "Requested resource not found", Toast.LENGTH_SHORT).show();
                            }
// When Http response code is '500'
                            else if (i == 500) {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                                // Toast.makeText(getActivity(), "Something went wrong at server end", Toast.LENGTH_SHORT).show();
                            }
// When Http response code other than 404, 500
                            else {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                                //Toast.makeText(getActivity(), "Unexpected Error occcured! [Most common Error.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            ex.printStackTrace();
                        } catch (Exception ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            //Toast.makeText(getActivity(), "Unexpected Error occcured!", Toast.LENGTH_SHORT).show();
                            ex.printStackTrace();
                        }
                    }
                });

        return cuisine;
    }

    public static List<Cuisine> getCuisines(int id) {
        final List<Cuisine> cuisines = new ArrayList<>();
        RequestParams params = new RequestParams();
        params.put("id", id);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://" +
                        GlassfishConnProps.URL + ":" +
                        GlassfishConnProps.PORT + "/" +
                        GlassfishConnProps.WEB_APP + "/" +
                        GlassfishConnProps.REST_HOME + "/" +
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
                            mResponse.onResponse(sqlResult);

                            /*JSONObject obj = new JSONObject(response);

                            if(obj.getInt("restaurant_id") > 0) {
                                Log.i("Self", "Good: " + obj.getInt("restaurant_id"));

                                Log.i("Self", "Good: " + obj.getString("name"));
                                //Toast.makeText(getActivity(), "You are successfully logged in!", Toast.LENGTH_SHORT).show();
                            } else {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                            }*/
                        } catch (IOException ex){
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            //Toast.makeText(getActivity(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_SHORT).show();
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        //mProgressDialog.hide();

                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
                            String line = "", response = "";
                            while ((line = reader.readLine()) != null) {
                                response += line;
                            }

                            reader.close();

                            Log.i("Self", "Bad: " + response);

                            if (i == 404) {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                                // Toast.makeText(getActivity(), "Requested resource not found", Toast.LENGTH_SHORT).show();
                            }
// When Http response code is '500'
                            else if (i == 500) {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                                // Toast.makeText(getActivity(), "Something went wrong at server end", Toast.LENGTH_SHORT).show();
                            }
// When Http response code other than 404, 500
                            else {
                                sqlResult = 3;
                                mResponse.onResponse(sqlResult);
                                //Toast.makeText(getActivity(), "Unexpected Error occcured! [Most common Error.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            ex.printStackTrace();
                        } catch (Exception ex) {
                            sqlResult = 3;
                            mResponse.onResponse(sqlResult);
                            //Toast.makeText(getActivity(), "Unexpected Error occcured!", Toast.LENGTH_SHORT).show();
                            ex.printStackTrace();
                        }
                    }
                });

        return cuisines;
    }

    public static class ImageLoader {

        private static ServImgResponse mImgResponse;

        public interface ServImgResponse {
            void onResponse(Bitmap bitmap);
        }

        public void setServImgResponse(ServImgResponse servImgResponse) {
            mImgResponse = servImgResponse;
        }

        public static void downloadImage(String path) {
            RequestParams params = new RequestParams();
            params.put("path", path);

            AsyncHttpClient client = new AsyncHttpClient();
            client.get("http://" +
                    GlassfishConnProps.URL + ":" +
                    GlassfishConnProps.PORT + "/" +
                    GlassfishConnProps.WEB_APP + "/" +
                    GlassfishConnProps.REST_HOME + "/" +
                    "fileservice/download/image",
                    params,
                    new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    try {
                        setImage(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                        mImgResponse.onResponse(getImage());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
                        String line = "", response = "";
                        while ((line = reader.readLine()) != null) {
                            response += line;
                        }

                        reader.close();

                        if (i == 404) {
                            //sqlResult = 3;
                            //mResponse.onResponse(sqlResult);
                            // Toast.makeText(getActivity(), "Requested resource not found", Toast.LENGTH_SHORT).show();
                        }
    // When Http response code is '500'
                        else if (i == 500) {
                            //sqlResult = 3;
                            //mResponse.onResponse(sqlResult);
                            // Toast.makeText(getActivity(), "Something went wrong at server end", Toast.LENGTH_SHORT).show();
                        }
    // When Http response code other than 404, 500
                        else {
                            //sqlResult = 3;
                            //mResponse.onResponse(sqlResult);
                            //Toast.makeText(getActivity(), "Unexpected Error occcured! [Most common Error.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException ex) {
                        //sqlResult = 3;
                        //mResponse.onResponse(sqlResult);
                        ex.printStackTrace();
                    } catch (Exception ex) {
                        //sqlResult = 3;
                        //mResponse.onResponse(sqlResult);
                        //Toast.makeText(getActivity(), "Unexpected Error occcured!", Toast.LENGTH_SHORT).show();
                        ex.printStackTrace();
                    }
                }
            });
        }

        public static void setImage(Bitmap bitmap) {
            bitmapImg = bitmap;
        }

        public static Bitmap getImage(String path) {
            downloadImage(path);
            return bitmapImg;
        }

        public static Bitmap getImage() {
            return bitmapImg;
        }
    }

}
