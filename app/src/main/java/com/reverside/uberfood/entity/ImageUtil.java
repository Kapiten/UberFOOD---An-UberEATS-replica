package com.reverside.uberfood.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reverside.uberfood.essentials.Constants;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.Header;

/**
 * Created by reversidesoftwaresolutions on 2017/06/12.
 */

public class ImageUtil {

    private static Bitmap bitmapImg;

    private static ServImgResponse mImgResponse;

    public interface ServImgResponse {
        void onResponse(Bitmap bitmap);
    }

    public void setServImgResponse(ServImgResponse servImgResponse) {
        mImgResponse = servImgResponse;
    }

    public static void downloadImage(String path) {
        RequestParams params = new RequestParams();
        params.put("path", path);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://" +
                        Constants.GlassfishConnProps.URL + ":" +
                        Constants.GlassfishConnProps.PORT + "/" +
                        Constants.GlassfishConnProps.WEB_APP + "/" +
                        Constants.GlassfishConnProps.REST_HOME + "/" +
                        "fileservice/download/image",
                params,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {
                            setImage(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                            mImgResponse.onResponse(getImage());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
                            String line = "", response = "";
                            while ((line = reader.readLine()) != null) {
                                response += line;
                            }

                            reader.close();

                            if (i == 404) {
                                //sqlResult = 3;
                                //mResponse.onResponse(sqlResult);
                                // Toast.makeText(getActivity(), "Requested resource not found", Toast.LENGTH_SHORT).show();
                            }
                            // When Http response code is '500'
                            else if (i == 500) {
                                //sqlResult = 3;
                                //mResponse.onResponse(sqlResult);
                                // Toast.makeText(getActivity(), "Something went wrong at server end", Toast.LENGTH_SHORT).show();
                            }
                            // When Http response code other than 404, 500
                            else {
                                //sqlResult = 3;
                                //mResponse.onResponse(sqlResult);
                                //Toast.makeText(getActivity(), "Unexpected Error occcured! [Most common Error.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException ex) {
                            //sqlResult = 3;
                            //mResponse.onResponse(sqlResult);
                            ex.printStackTrace();
                        } catch (Exception ex) {
                            //sqlResult = 3;
                            //mResponse.onResponse(sqlResult);
                            //Toast.makeText(getActivity(), "Unexpected Error occcured!", Toast.LENGTH_SHORT).show();
                            ex.printStackTrace();
                        }
                    }
                });
    }

    public static void setImage(Bitmap bitmap) {
        bitmapImg = bitmap;
    }

    public static Bitmap getImage(String path) {
        downloadImage(path);
        return bitmapImg;
    }

    public static Bitmap getImage() {
        return bitmapImg;
    }
}
