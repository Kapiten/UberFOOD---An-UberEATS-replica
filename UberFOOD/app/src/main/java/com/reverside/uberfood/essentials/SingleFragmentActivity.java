package com.reverside.uberfood.essentials;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.reverside.uberfood.R;

public abstract class SingleFragmentActivity extends AppCompatActivity
{
    protected abstract Fragment createFragment();
    
    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(this.getLayoutResId());
        final FragmentManager supportFragmentManager = this.getSupportFragmentManager();
        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, this.createFragment()).commit();
        }
    }
}