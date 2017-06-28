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
import com.reverside.uberfood.entity.Order;
import com.reverside.uberfood.entity.OrderList;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import cz.msebera.android.httpclient.Header;

/**
 * Created by StrettO on 2017/06/04.
 */

public class Self {

    private static Self mSelf;

    private static int sqlResult;

    public static Typeface mUberFont;

    private Context mContext;

    private User mCurrentUser;
    private Person mCurrentPerson;
    private Contact mCurrentContact;
    private Restaurant mSelectedRestaurant;
    private Cuisine mSelectedCuisine;

    private Order mOrder;
    //private List<OrderList> mOrderList = new ArrayList<>();
    private ConcurrentMap<Restaurant, List<OrderList>> mOrderList;
    private ConcurrentMap<Restaurant, Order> mOrders;

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

    public ConcurrentMap<Restaurant, Order> getOrders() {
        if(mOrders == null) {
            mOrders = new ConcurrentHashMap<>();
        }
        return mOrders;
    }

    public Order getOrder(final int restaurantId) {
        Iterator it = getOrders().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Restaurant restaurant = (Restaurant) pair.getKey();
            if(restaurant.getRestaurantId() == restaurantId) {
                mOrder = (Order) pair.getValue();
                Log.i(Constants.UNI_TAG, "getOrder(ForEach) = " + mOrder);
                break;
            } else {
                Log.i(Constants.UNI_TAG, "getOrder(ForEach) = no such");
            }
            //it.remove(); // avoids a ConcurrentModificationException
        }

        /*getOrders().forEach(new BiConsumer<Restaurant, Order>() {
            @Override
            public void accept(Restaurant restaurant, Order order) {
                if(restaurant.getRestaurantId() == restaurantId) {
                    mOrder = order;
                    Log.i(Constants.UNI_TAG, "getOrder(ForEach) = " + mOrder);
                    return;
                } else {
                    Log.i(Constants.UNI_TAG, "getOrder(ForEach) = no such");
                }
            }
        });*/
        return mOrder;
    }

    public void setOrders(ConcurrentMap<Restaurant, Order> orders) {
        mOrders = orders;
    }

    public int getOrderListItemIndex(Restaurant restaurant, int cuisineId) {
        int a = 0;
        for(OrderList orderList : getOrderList(restaurant)) {
            a += 1;
            if(orderList.getCuisineId().equals(cuisineId + "")) {
                return a;
            }
        }

        return a;
    }

    public OrderList getOrderListItem(Restaurant restaurant, int cuisineId) {
        if(getOrderList(restaurant) != null) {
            for (OrderList orderList : getOrderList(restaurant)) {
                if (orderList.getCuisineId().equals(cuisineId + "")) {
                    return orderList;
                }
            }
        }

        return new OrderList();
    }

    public List<OrderList> getOrderList(Restaurant restaurant) {
        return mOrderList.get(restaurant);
    }

    private ConcurrentMap<Restaurant, List<OrderList>> getOrderLists() {
        return mOrderList;
    }

    public List<OrderList> setOrderList(Restaurant restaurant, List<OrderList> orderList) {
        if(getOrderList(restaurant) == null) {
            getOrderLists().put(restaurant, orderList);
        } else {
            getOrderLists().replace(restaurant, orderList);
        }

        return orderList;
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

        public ServComBuilder addOrder(Order order) {
            RequestParams params = new RequestParams();
            params.put("jsonOrder", order.toString());
            Order.invokeAddOrder(params, this);

            /*try {
                StringEntity entity = new StringEntity(order.toString());
                entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                Order.invokeAddOrder(mContext,
                        entity,
                        this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }*/

            return this;
        }

        public ServComBuilder testOrder() {
            Order.invokeResponse(null, this);
            return this;
        }
    }

    private static void invokeLoginWS(RequestParams params, final ServComBuilder servComBuilder) {
        //mProgressDialog.show();
        //1 = ok, 2 = ok - sum, 3 - fault

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

}
