package com.reverside.uberfood.essentials;

import com.reverside.uberfood.account.ServerAuthenticate;
import com.reverside.uberfood.account.ServerComAuthenticate;

/**
 * Created by StrettO on 2017/06/04.
 */

public class Constants {
    public static final String UNI_TAG = "UberFOOD_TAG";
    public static final String DATE_FORMAT = "dd MM yyyy hh:mm:ss.SSS";

    public static final class GlassfishConnProps {
        public static final String URL = "192.168.2.20";
        public static final String PORT = "8080";
        public static final String WEB_APP = "TheServerComp";
        public static final String REST_HOME = "rest";
    }

    public static final class JSON_Tags {
        public static final String EXTRAS = "extras";
        public static final String SPEC_INSTRU = "special_instructions";
    }

    public static final class AdditionalCosts {
        public static final double DELIVERY_FEE = 15.00;
        public static final double VAT = 0.14;
    }

    public static final class AccountDetails {
        public static final String ACCOUNT_TYPE = "com.reverside.uberfood.auth_type";

        public static final String ACCOUNT_NAME = "UberFOOD";

        public static final String AUTHTOKEN_TYPE_READ_ONLY = "Read only";
        public static final String AUTHTOKEN_TYPE_READ_ONLY_LABEL = "Read only access to a Reverside account";

        public static final String AUTHTOKEN_TYPE_FULL_ACCESS = "Full access";
        public static final String AUTHTOKEN_TYPE_FULL_ACCESS_LABEL = "Full access to a Reverside account";

        public static final ServerAuthenticate sServerAuthenticate = new ServerComAuthenticate();
    }

    public static final class RestResponseCodes {
        public static final Integer SUCCESS = 1;
        public static final Integer FAULT = 3;
    }

    public static final class RegexPatterns {
        public static final String PASSWORD = "[\\d+\\p{Punct}+\\p{Alpha}+\\p{Blank}+]{8,}";
        public static final String EMAIL = "\\w+@\\w+\\.\\S+";
    }
}
