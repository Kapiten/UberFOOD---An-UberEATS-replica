package com.reverside.uberfood.launch;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.reverside.uberfood.R;
import com.reverside.uberfood.dialog.LoginDialog;
import com.reverside.uberfood.essentials.Self;
import com.reverside.uberfood.restaurant.RestaurantActivity;

//import com.reverside.uberfood.account.FirebaseLoginActivity;

/**
 * Created by StrettO on 2017/05/27.
 */

public class LaunchFragment extends Fragment {

    private static final int REQUEST_LOGIN = 1;

    private Button mEnterBTN;
    private ScrollView mLaunchSV;
    private LinearLayout mLaunchLL;
    private ImageView mSplashIV;
    private ProgressDialog mProgressDialog;

    public static LaunchFragment newInstance() {
        return new LaunchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_launch, container, false);

        mEnterBTN = (Button) view.findViewById(R.id.launch_enter_BTN);
        mLaunchSV = (ScrollView) view.findViewById(R.id.launch_SV);
        mLaunchLL = (LinearLayout) view.findViewById(R.id.launch_lgn_LL);
        mSplashIV = (ImageView) view.findViewById(R.id.launch_IV);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Logging in...");
        mProgressDialog.setCancelable(false);

        try {
            Glide.with(getActivity())
                    .load(Uri.parse("file:///android_asset/gifs/pancake.gif"))
                    .centerCrop()
                    //.listener()
                    .into(mSplashIV);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /*final ObjectAnimator mObjectAnimator = ObjectAnimator
                .ofFloat(mLaunchLL, "alpha", 0f, 1f)
                .setDuration(400);
        mObjectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());*/

        mEnterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginDialog dialog = LoginDialog.newInstance();
                dialog.show(getFragmentManager(), "LGN");
                //startActivityForResult(new Intent(getActivity(), FirebaseLoginActivity.class), REQUEST_LOGIN);
            }
        });

        mEnterBTN.setTypeface(Self.mUberFont);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case REQUEST_LOGIN:
                startActivity(new Intent(getActivity(), RestaurantActivity.class));
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
