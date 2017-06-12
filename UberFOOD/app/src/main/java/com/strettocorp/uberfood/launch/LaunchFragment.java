package com.strettocorp.uberfood.launch;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.strettocorp.uberfood.R;
import com.strettocorp.uberfood.account.AccountActivity;
import com.strettocorp.uberfood.cart.CartActivity;
import com.strettocorp.uberfood.connection.Utility;
import com.strettocorp.uberfood.essentials.Self;
import com.strettocorp.uberfood.restaurant.RestaurantActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by StrettO on 2017/05/27.
 */

public class LaunchFragment extends Fragment {

    private Button mEnterBTN, mLoginBTN, mRegisterBTN, mForgotPasswBTN;
    private EditText mUsernameET, mPasswordET;
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
        mLoginBTN = (Button) view.findViewById(R.id.launch_lgn_login_BTN);
        mRegisterBTN = (Button) view.findViewById(R.id.launch_lgn_register_BTN);
        mForgotPasswBTN = (Button) view.findViewById(R.id.launch_lgn_fgt_pword_BTN);
        mUsernameET = (EditText) view.findViewById(R.id.launch_lgn_usrname_ET);
        mPasswordET = (EditText) view.findViewById(R.id.launch_lgn_pword_ET);
        mLaunchSV = (ScrollView) view.findViewById(R.id.launch_SV);
        mLaunchLL = (LinearLayout) view.findViewById(R.id.launch_lgn_LL);
        mSplashIV = (ImageView) view.findViewById(R.id.launch_IV);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Logging in...");
        mProgressDialog.setCancelable(false);

        mUsernameET.setOnFocusChangeListener(onTextFocus());
        mPasswordET.setOnFocusChangeListener(onTextFocus());

        final ObjectAnimator mObjectAnimator = ObjectAnimator
                .ofFloat(mLaunchLL, "alpha", 0f, 1f)
                .setDuration(400);
        mObjectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        mEnterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mLaunchLL.getVisibility() != View.VISIBLE)
                    mObjectAnimator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {
                            mLaunchLL.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {

                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    mObjectAnimator.start();
            }
        });

        final Self self = Self.get(getActivity());

        mLoginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mUsernameET.getText().toString();
                String password = mPasswordET.getText().toString();

                mProgressDialog.show();
                self.loginUser(email, password);
                self.setServResponse(new Self.ServResponse() {
                    @Override
                    public void onResponse(int responseCode) {
                        if(responseCode == 1) {
                            mProgressDialog.hide();
                            startActivity(new Intent(getActivity(), RestaurantActivity.class));
                        } else {
                            mProgressDialog.hide();
                            Toast.makeText(getActivity(), "Problem logging in", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mRegisterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AccountActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS));
            }
        });

        mEnterBTN.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "font/clanpro_news.ttf"));

        return view;
    }

    public View.OnFocusChangeListener onTextFocus() {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    mEnterBTN.setVisibility(View.GONE);
                } else {
                    mEnterBTN.setVisibility(View.VISIBLE);
                }
            }
        };
    }



}
