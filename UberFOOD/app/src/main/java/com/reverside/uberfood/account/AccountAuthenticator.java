package com.reverside.uberfood.account;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.reverside.uberfood.entity.User;
import com.reverside.uberfood.essentials.Constants;
import com.reverside.uberfood.essentials.Constants.AccountDetails;
import com.reverside.uberfood.essentials.Self;

import static com.reverside.uberfood.essentials.Constants.AccountDetails.sServerAuthenticate;

/**
 * Created by StrettO on 2017/06/15.
 */

public class AccountAuthenticator extends AbstractAccountAuthenticator {

    private Context mContext;

    public AccountAuthenticator(Context context) {
        super(context);

        mContext = context;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
        Log.i(Constants.UNI_TAG, "addAccount");

        final Intent intent = LoginActivity.newAccountIntent(mContext, accountType, authTokenType, true, response);

        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);

        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        Log.i(Constants.UNI_TAG, "getAuthToken");

        if(!authTokenType.equals(AccountDetails.AUTHTOKEN_TYPE_READ_ONLY) && !authTokenType.equals(AccountDetails.AUTHTOKEN_TYPE_FULL_ACCESS)) {
            final Bundle result = new Bundle();
            result.putString(AccountManager.KEY_ERROR_MESSAGE, "Invalid authTokenType");

            return result;
        }

        final AccountManager manager = AccountManager.get(mContext);

        String authToken = manager.peekAuthToken(account, authTokenType);

        if(TextUtils.isEmpty(authToken)) {
            final String password = manager.getPassword(account);
            if(password != null) {
                try {
                    User user = new User();
                    user.setUsername(account.name);
                    user.setPassword(password);

                    authToken = sServerAuthenticate.userSignIn(user, authTokenType);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        if(!TextUtils.isEmpty(authToken)) {
            final Bundle result = new Bundle();
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            result.putString(AccountManager.KEY_AUTHTOKEN, authToken);

            return result;
        }

        final Intent intent = LoginActivity.newAccountIntent(mContext,
                account.type,
                authTokenType,
                false,
                response);

        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);

        return bundle;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        if(AccountDetails.AUTHTOKEN_TYPE_FULL_ACCESS.equals(authTokenType))
            return AccountDetails.AUTHTOKEN_TYPE_FULL_ACCESS_LABEL;
        else if (AccountDetails.AUTHTOKEN_TYPE_READ_ONLY.equals(authTokenType))
            return AccountDetails.AUTHTOKEN_TYPE_READ_ONLY_LABEL;
        else
            return "AuthLabel: " + authTokenType;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        final Bundle result = new Bundle();
        result.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, false);
        return result;
    }
}
