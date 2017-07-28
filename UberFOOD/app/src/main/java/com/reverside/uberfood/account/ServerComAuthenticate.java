package com.reverside.uberfood.account;

import android.accounts.AccountManager;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reverside.uberfood.connection.Utility;
import com.reverside.uberfood.entity.Contact;
import com.reverside.uberfood.entity.Person;
import com.reverside.uberfood.entity.User;
import com.reverside.uberfood.essentials.Constants;
import com.reverside.uberfood.essentials.Self;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.Header;

/**
 * Created by StrettO on 2017/06/16.
 */
public class ServerComAuthenticate implements ServerAuthenticate {

    public static LoginResponse mLoginResponse;

    public interface LoginResponse {
        void onResponse();
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        mLoginResponse = loginResponse;
    }

    @Override
    public String userSignUp(User user, Person person, Contact contact, String authType) throws Exception {
        Self.ServComBuilder builder = new Self.ServComBuilder();
        RequestParams params = new RequestParams();
        params.put("jsonUser", user.toString());
        params.put("jsonPerson", person.toString());
        params.put("jsonContact", person.toString());

        invokeRegisterWS(params, builder);
        builder.setServResponse(new Self.ServComBuilder.ServResponse<User>() {

            @Override
            public void onResponse(int responseCode, String response) {

            }

            @Override
            public void onResponse(User obj) {
                authToken = obj.getSessionToken();
                mLoginResponse.onResponse();
            }
        });

        return authToken;
    }

    String authToken = "";
    @Override
    public String userSignIn(User user, String authType) throws Exception {
        Self.ServComBuilder builder = new Self.ServComBuilder();
        RequestParams params = new RequestParams();
        params.put("jsonUser", user.toString());

        invokeLoginWS(params, builder);
        builder.setServResponse(new Self.ServComBuilder.ServResponse<User>() {

            @Override
            public void onResponse(int responseCode, String response) {

            }

            @Override
            public void onResponse(User obj) {
                authToken = obj.getSessionToken();
                mLoginResponse.onResponse();
            }
        });

        return authToken;
    }

    private static int sqlResult = 0;
    private void invokeLoginWS(RequestParams params, final Self.ServComBuilder servComBuilder) {
        //1 = ok, 2 = ok - sum, 3 - fault
        sqlResult = 0;

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
                            User user = User.fromString(obj.toString());
                            if(user != null) {
                                sqlResult = 1;
                                servComBuilder.mServResponse.onResponse(1, "You are successfully logged in!");
                                servComBuilder.mServResponse.onResponse(user);
                            } else {
                                sqlResult = 3;
                                servComBuilder.mServResponse.onResponse(3, "Incorrect Username | Password combination.");
                            }
                        } catch (IOException ex){
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            sqlResult = 3;
                            servComBuilder.mServResponse.onResponse(3, "Error Occurred - Server's JSON response might be invalid");
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Utility.getFailureMsg(i, bytes, servComBuilder);
                    }

                });
    }

    private void invokeRegisterWS(RequestParams params, final Self.ServComBuilder servComBuilder) {
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
                            String line = "", response = "";
                            while ((line = reader.readLine()) != null) {
                                response += line;
                            }
                            reader.close();

                            JSONObject obj = new JSONObject(response);
                            User user = User.fromString(obj.toString());
                            if(obj.getBoolean("status")) {
                                sqlResult = 1;
                                servComBuilder.mServResponse.onResponse(1, "Successfully registered user");
                            } else {
                                sqlResult = 3;
                                servComBuilder.mServResponse.onResponse(3, "Error while registering user");
                                servComBuilder.mServResponse.onResponse(user);
                            }
                        } catch (IOException ex){
                            ex.printStackTrace();
                        } catch (JSONException ex) {
                            sqlResult = 3;
                            servComBuilder.mServResponse.onResponse(3, "Error Occurred - Server's JSON response might be invalid");
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
