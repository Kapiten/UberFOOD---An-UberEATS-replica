package com.reverside.uberfood.maps;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.reverside.uberfood.R;
import com.reverside.uberfood.essentials.Constants;
import com.reverside.uberfood.utils.PictureUtils;

import java.io.File;

/**
 * A placeholder fragment containing a simple view.
 */
public class SavedPlacesFragment extends Fragment {

    private ImageView mImg;

    public static SavedPlacesFragment newInstance() {
        return new SavedPlacesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_saved_places, container, false);

        mImg = ((ImageView) view.findViewById(R.id.img));
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //intent.setType("/*");
                //intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    Log.v(Constants.UNI_TAG, "Start");
                    startActivityForResult(intent,
                            13);
                } catch (Exception ex) {
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            Log.v(Constants.UNI_TAG, "Position 0");
            return;
        }

        switch (requestCode) {
            case 13:
                Log.v(Constants.UNI_TAG, "Position 2");
                Uri uri = data.getData();

                String[] projection = {"_data"};
                Cursor cursor = getActivity()
                        .getContentResolver()
                        .query(uri, projection, null, null, null);
                int index = cursor.getColumnIndexOrThrow("_data");

                if (cursor.getCount() == 0) {
                    return;
                }

                if (cursor.moveToFirst()) {
                    String path = cursor.getString(index);
                    if (new File(path).exists()) {
                        Bitmap bitmap = PictureUtils.getScaledBitmap(path, mImg.getWidth(), mImg.getHeight());
                        mImg.setImageBitmap(bitmap);
                        //mProPicPath = path;
                        Log.v(Constants.UNI_TAG, "Path: " + path);
                    }
                }

                break;
        }
    }
}
