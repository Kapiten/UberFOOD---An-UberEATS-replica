package com.reverside.uberfood.account;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by StrettO on 2017/06/16.
 */

public class AuthenticatorService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        AccountAuthenticator authenticator = new AccountAuthenticator(this);

        return authenticator.getIBinder();
    }
}
