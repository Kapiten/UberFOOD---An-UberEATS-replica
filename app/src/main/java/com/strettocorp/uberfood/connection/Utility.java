package com.strettocorp.uberfood.connection;

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
}
