package com.reverside.uberfood.maps;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.reverside.uberfood.R;
import com.reverside.uberfood.essentials.SingleFragmentActivity;

public class SavedPlacesActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SavedPlacesFragment.newInstance();
    }
}
