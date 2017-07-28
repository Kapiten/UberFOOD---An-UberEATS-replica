package com.reverside.uberfood.essentials;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by reversidesoftwaresolutions on 2017/06/20.
 */

public class FirebaseInstanceService extends FirebaseInstanceIdService {
    public static final String TAG = "FirebaseFCM";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        //Toast.makeText(this, "Token: " + token, Toast.LENGTH_SHORT).show();
        Log.v(TAG, "Token: " + token);
    }
}
