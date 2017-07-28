package com.reverside.uberfood.essentials;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reverside.uberfood.R;
import com.reverside.uberfood.connection.Utility;
import com.reverside.uberfood.entity.Contact;
import com.reverside.uberfood.entity.Cuisine;
import com.reverside.uberfood.entity.CuisineList;
import com.reverside.uberfood.entity.Orders;
import com.reverside.uberfood.entity.Person;
import com.reverside.uberfood.entity.Restaurant;
import com.reverside.uberfood.entity.User;
import com.reverside.uberfood.essentials.Constants.RestResponseCodes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by StrettO on 2017/06/04.
 */

public class Self {

    private static Self mSelf;

    private static int sqlResult;

    public static Typeface mUberFont;

    private static  Context mContext;

    private User mCurrentUser;
    private Person mCurrentPerson;
    private Contact mCurrentContact;
    private Restaurant mSelectedRestaurant;
    private Cuisine mSelectedCuisine;

    private Orders mOrder;
    //private List<CuisineList> mOrderList = new ArrayList<>();
    private ConcurrentMap<Restaurant, List<CuisineList>> mOrderList;
    private ConcurrentMap<Restaurant, Orders> mOrders;

    private Self(Context context) {
        mContext = context;
        mOrderList = new ConcurrentHashMap<>();
        mCurrentUser = new User();
        mCurrentPerson = new Person();
        mCurrentContact = new Contact();
    }

    public static Self get(Context context) {
        if(mSelf == null) {
            mSelf = new Self(context);
            mUberFont = Typeface.createFromAsset(context.getAssets(), "font/clanpro_news.ttf");
        }

        return mSelf;
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }

    public void setCurrentUser(User currentUser) {
        mCurrentUser = currentUser;
    }

    public Person getCurrentPerson() {
        return mCurrentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        mCurrentPerson = currentPerson;
    }

    public Contact getCurrentContact() {
        return mCurrentContact;
    }

    public void setCurrentContact(Contact currentContact) {
        mCurrentContact = currentContact;
    }

    public Restaurant getSelectedRestaurant() {
        return mSelectedRestaurant;
    }

    public void setSelectedRestaurant(Restaurant selectedRestaurant) {
        mSelectedRestaurant = selectedRestaurant;
    }

    public Cuisine getSelectedCuisine() {
        return mSelectedCuisine;
    }

    public void setSelectedCuisine(Cuisine selectedCuisine) {
        mSelectedCuisine = selectedCuisine;
    }

    public ConcurrentMap<Restaurant, Orders> getOrders() {
        if(mOrders == null) {
            mOrders = new ConcurrentHashMap<>();
        }
        return mOrders;
    }

    public Orders getOrder(final int restaurantId) {
        Iterator it = getOrders().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Restaurant restaurant = (Restaurant) pair.getKey();
            if(restaurant.getRestaurantId() == restaurantId) {
                mOrder = (Orders) pair.getValue();
                Log.i(Constants.UNI_TAG, "getOrder(ForEach) = " + mOrder);
                break;
            } else {
                Log.i(Constants.UNI_TAG, "getOrder(ForEach) = no such");
            }
            //it.remove(); // avoids a ConcurrentModificationException
        }

        /*getOrders().forEach(new BiConsumer<Restaurant, Orders>() {
            @Override
            public void accept(Restaurant restaurant, Orders order) {
                if(restaurant.getRestaurantId() == restaurantId) {
                    mOrders = order;
                    Log.i(Constants.UNI_TAG, "getOrder(ForEach) = " + mOrders);
                    return;
                } else {
                    Log.i(Constants.UNI_TAG, "getOrder(ForEach) = no such");
                }
            }
        });*/
        return mOrder;
    }

    public void setOrders(ConcurrentMap<Restaurant, Orders> orders) {
        mOrders = orders;
    }

    public int getOrderListItemIndex(Restaurant restaurant, int cuisineId) {
        int a = 0;
        for(CuisineList cuisineList : getOrderList(restaurant)) {
            a += 1;
            if(cuisineList.getCuisineId().equals(cuisineId + "")) {
                return a;
            }
        }

        return a;
    }

    public CuisineList getOrderListItem(Restaurant restaurant, int cuisineId) {
        if(getOrderList(restaurant) != null) {
            for (CuisineList cuisineList : getOrderList(restaurant)) {
                if (cuisineList.getCuisineId().equals(cuisineId + "")) {
                    return cuisineList;
                }
            }
        }

        return new CuisineList();
    }

    public List<CuisineList> getOrderList(Restaurant restaurant) {
        return mOrderList.get(restaurant);
    }

    private ConcurrentMap<Restaurant, List<CuisineList>> getOrderLists() {
        return mOrderList;
    }

    public List<CuisineList> setOrderList(Restaurant restaurant, List<CuisineList> cuisineList) {
        if(getOrderList(restaurant) == null) {
            getOrderLists().put(restaurant, cuisineList);
        } else {
            getOrderLists().replace(restaurant, cuisineList);
        }

        return cuisineList;
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

    public static class ServComBuilder {
        private Context mContext;
        public ServResponse mServResponse;

        public ServComBuilder() {
        }

        public ServComBuilder(Context context) {
            mContext = context;
        }

        public interface ServResponse<T> {
            void onResponse(int responseCode, String response);
            void onResponse(T object);
        }

        public ServComBuilder setServResponse(ServResponse servResponse) {
            mServResponse = servResponse;

            return this;
        }

        public ServComBuilder loginUser(String email, String password) {
            RequestParams params = new RequestParams();
            if(Utility.isNotNull(email) && Utility.isNotNull(password)) {
                User user = new User();
                user.setUsername(email);
                user.setPassword(password);
                user.setSessionToken(new Random().nextLong() + "");

                params.put("jsonUser", user.toString());
                invokeLoginWS(params, this);
            }

            return this;
        }

        public ServComBuilder registerUser(
                String jsonUser,
                String jsonPerson,
                String jsonContact) {
            RequestParams params = new RequestParams();
            if(Utility.isNotNull(jsonUser) &&
                    Utility.isNotNull(jsonPerson)) {
                params.put("jsonUser", jsonUser);
                params.put("jsonPerson", jsonPerson);
                params.put("jsonContact", jsonContact);

                invokeRegisterWS(params, this);
            }

            return this;
        }

        public ServComBuilder getRestaurant(int id) {
            RequestParams params = new RequestParams();
            params.put("id", id);
            Restaurant.invokeGetRestaurantWS(params, this);

            return this;
        }

        public ServComBuilder getRestaurants() {
            Restaurant.invokeGetRestaurantsWS(this);
            return this;
        }

        public ServComBuilder getCuisine(int id) {
            RequestParams params = new RequestParams();
            params.put("id", id);

            Cuisine.invokeGetCuisineWS(params, this);
            return this;
        }

        public ServComBuilder getCuisinesByRestaurantId(int restaurantId) {
            RequestParams params = new RequestParams();
            params.put("restaurantId", restaurantId);

            Cuisine.invokeGetCuisinesWS(params, this);
            return this;
        }

        public ServComBuilder addOrder(Orders orders) {
            RequestParams params = new RequestParams();
            params.put("jsonOrder", orders.toString());
            Orders.invokeAddOrder(params, this);

            /*try {
                StringEntity entity = new StringEntity(orders.toString());
                entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                Orders.invokeAddOrder(mContext,
                        entity,
                        this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }*/

            return this;
        }

        public ServComBuilder testOrder() {
            try {
                invokeResponse(null, this);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            return this;
        }
    }

    private static void invokeLoginWS(RequestParams params, final ServComBuilder servComBuilder) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://" +
                Constants.GlassfishConnProps.URL + ":" +
                Constants.GlassfishConnProps.PORT + "/" +
                Constants.GlassfishConnProps.WEB_APP + "/" +
                Constants.GlassfishConnProps.REST_HOME + "/" +
                "login/doLogin",
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
                            if(obj.has("userId")) {
                                servComBuilder.mServResponse.onResponse(RestResponseCodes.SUCCESS, "You are successfully logged in!");
                                servComBuilder.mServResponse.onResponse(User.fromString(obj.toString()));
                            } else {
                                servComBuilder.mServResponse.onResponse(RestResponseCodes.FAULT, "Incorrect Username | Password combination.");
                            }
                        } catch (IOException ex){
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            sqlResult = 3;
                            servComBuilder.mServResponse.onResponse(RestResponseCodes.FAULT, "Error Occurred - Server's JSON response might be invalid");
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Utility.getFailureMsg(i, bytes, servComBuilder);
                    }


                });
    }

    private static void invokeRegisterWS(RequestParams params, final ServComBuilder servComBuilder) {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://" +
                        Constants.GlassfishConnProps.URL + ":" +
                        Constants.GlassfishConnProps.PORT + "/" +
                        Constants.GlassfishConnProps.WEB_APP + "/" +
                        Constants.GlassfishConnProps.REST_HOME + "/" +
                        "register/doRegister",
                params,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
                            String      line = "", response = "";
                            while ((line = reader.readLine()) != null) {
                                response += line;
                            }
                            reader.close();

                            try {
                                Log.i(Constants.UNI_TAG, "Response: \n" + response);
                                JSONArray jsonArray = new JSONArray(response);
                                for(int x = 0; x < jsonArray.length(); x++) {
                                    Log.i(Constants.UNI_TAG, "JSONObject: \n" + jsonArray.getJSONObject(x).toString());

                                    JSONObject obj = jsonArray.getJSONObject(x);

                                    switch (x) {
                                        case 0:
                                            mSelf.setCurrentUser(User.fromString(obj.toString()));
                                            break;
                                        case 1:
                                            mSelf.setCurrentPerson(Person.fromString(obj.toString()));
                                            break;
                                        case 2:
                                            mSelf.setCurrentContact(Contact.fromString(obj.toString()));
                                            break;
                                    }
                                }

                                servComBuilder.mServResponse.onResponse(RestResponseCodes.SUCCESS, "Successful register");
                            } catch (JSONException ex) {
                                servComBuilder.mServResponse.onResponse(RestResponseCodes.FAULT, "Error while registering user");
                                ex.printStackTrace();
                            }

                        } catch (IOException ex){
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Utility.getFailureMsg(i, bytes, servComBuilder);
                    }
                });


    }

    public static void invokeResponse(RequestParams params, final Self.ServComBuilder servComBuilder)
            throws JSONException, UnsupportedEncodingException{
        JSONObject object = new JSONObject();

        User user = new User();
        user.setUsername("TheGuy");
        user.setPassword("password");
        user.setProfilePic("profile_pic");
        user.setTokenId("token_id");
        user.setSessionToken("session_token");
        object.put("user", user.toString());

        StringEntity entity = new StringEntity(object.toString());

        AsyncHttpClient client = new AsyncHttpClient();
        //client.post()

        client.post(
                "http://" +
                        Constants.GlassfishConnProps.URL + ":" +
                        Constants.GlassfishConnProps.PORT + "/" +
                        Constants.GlassfishConnProps.WEB_APP + "/" +
                        Constants.GlassfishConnProps.REST_HOME + "/" +
                        "fileservice/download/test",
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

                            if(!response.isEmpty()) {
                                servComBuilder.mServResponse.onResponse(RestResponseCodes.SUCCESS, response);
                            } else {
                                servComBuilder.mServResponse.onResponse(RestResponseCodes.FAULT, "Something went wrong while placing order");
                            }
                        } catch (IOException ex){
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        servComBuilder.mServResponse.onResponse(RestResponseCodes.FAULT,
                                Utility.getFailureMsg(i, bytes, servComBuilder));
                    }
                });
    }

}
