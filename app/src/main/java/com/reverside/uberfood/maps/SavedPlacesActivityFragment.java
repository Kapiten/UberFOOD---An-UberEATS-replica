package com.reverside.uberfood.maps;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reverside.uberfood.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class SavedPlacesActivityFragment extends Fragment {

    public SavedPlacesActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_saved_places, container, false);
    }
}
