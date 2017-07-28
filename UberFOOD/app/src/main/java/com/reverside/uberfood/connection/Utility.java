package com.reverside.uberfood.connection;

import android.util.Log;

import com.reverside.uberfood.essentials.Self;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by StrettO on 2017/06/04.
 */

public class Utility {
    private static Pattern mPattern;
    private static Matcher mMatcher;

    private static final String EMAIL_PATTERN =
            "^[_A‐Za‐z0‐9‐\\+]+(\\.[_A‐Za‐z0‐9‐]+)*@"
                    + "[A‐Za‐z0‐9‐]+(\\.[A‐Za‐z0‐9]+)*(\\.[A‐Za‐z]{2,})$";

    private static boolean validate(String email) {
        mPattern = Pattern.compile(EMAIL_PATTERN);
        mMatcher = mPattern.matcher(email);
        return mMatcher.matches();
    }

    public static boolean isNotNull(String txt) {
        return txt!=null && txt.trim().length()>0 ? true: false;
    }



    public static String getFailureMsg(int i, byte[] bytes, Self.ServComBuilder servComBuilder) {
        String sqlResult = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
            String line = "", response = "";
            while ((line = reader.readLine()) != null) {
                response += line;
            }

            reader.close();

            if (i == 404) {
                sqlResult = "Requested resource not found";
                servComBuilder.mServResponse.onResponse(3, sqlResult);
            }
// When Http response code is '500'
            else if (i == 500) {
                sqlResult = "Something went wrong at server end";
                servComBuilder.mServResponse.onResponse(3, sqlResult);
            }
// When Http response code other than 404, 500
            else {
                sqlResult = "Unexpected Error occcured! - Possible client";
                servComBuilder.mServResponse.onResponse(3, sqlResult);
            }
        } catch (IOException ex) {
            sqlResult = "Unexpected IOError occcured!";
            servComBuilder.mServResponse.onResponse(3, sqlResult);
            ex.printStackTrace();
        } catch (Exception ex) {
            sqlResult = "Unexpected Error occcured!";
            servComBuilder.mServResponse.onResponse(3, sqlResult);
            ex.printStackTrace();
        }

        return sqlResult;
    }
}
