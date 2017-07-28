package com.reverside.uberfood.essentials;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

/**
 * Created by reversidesoftwaresolutions on 2017/06/20.
 */

public class FirebaseMsgService extends FirebaseMessagingService {
    public static final String TAG = "FirebaseFCM";
    public static final String REQUEST_ORDER = "request_order";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            LocalBroadcastManager broadcaster = LocalBroadcastManager.getInstance(getBaseContext());

            try {
                JSONObject jsonObject = new JSONObject(remoteMessage.getData());

                Intent intent = new Intent(REQUEST_ORDER);
                switch (jsonObject.getString("title")) {
                    case "Orders":
                        intent.putExtra("Key", "Orders");
                        intent.putExtra("MSG", jsonObject.getString("body"));
                        broadcaster.sendBroadcast(intent);
                        break;
                    case "Food":
                        intent.putExtra("Key", "Food");
                        intent.putExtra("MSG", jsonObject.getString("body"));
                        broadcaster.sendBroadcast(intent);
                        break;
                    case "Courier":
                        intent.putExtra("Key", "Courier");
                        intent.putExtra("MSG", jsonObject.getString("body"));
                        broadcaster.sendBroadcast(intent);
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {


            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }
}
