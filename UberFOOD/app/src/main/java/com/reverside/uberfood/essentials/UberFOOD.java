package com.reverside.uberfood.essentials;

import android.app.Application;

/**
 * Created by StrettO on 2017/06/24.
 */

public class UberFOOD extends Application {
    public static Self mSelf;

    @Override
    public void onCreate() {
        super.onCreate();

        mSelf = Self.get(this);
    }
}
