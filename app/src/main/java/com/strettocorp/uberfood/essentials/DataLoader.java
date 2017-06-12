package com.strettocorp.uberfood.essentials;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by reversidesoftwaresolutions on 2017/06/08.
 */

public class DataLoader<T> extends HandlerThread {

    private static final String TAG = "DataLoader";

    private static final int MESSAGE_GETTER = 0;
    private static final int MESSAGE_GET_ALL = 1;
    private static final int MESSAGE_OPEN = 2;

    public static final int REQUEST_ALL = 0;
    public static final int REQUEST_READ = 30;
    public static final int REQUEST_SAVE = 40;

    private DataLoaderListener<T> mDataLoaderListener;
    private Self mSelf;
    private Handler mRequestHandler;
    private ConcurrentMap<T, String> mRequestMap;
    private ConcurrentMap<Integer, String> mRequestItemMap;
    private static Handler mResponseHandler;

    public DataLoader(final Context context, final Handler responseHandler) {
        super("DataLoader");

        mRequestMap = new ConcurrentHashMap<>();
        mRequestItemMap = new ConcurrentHashMap<>();
        mSelf = Self.get(context);
        mResponseHandler = responseHandler;
    }

    public interface DataLoaderListener<T> {
        void onDataLoaded(T object);
        void onImageLoaded(Bitmap bitmap);
    }

    public void setDataLoaderListener(final DataLoaderListener<T> dataLoaderListener) {
        mDataLoaderListener = dataLoaderListener;
    }

    @Override
    protected void onLooperPrepared() {
        mRequestHandler = new Handler(getLooper()) {
            public void handleMessage(final Message message) {
                switch (message.what) {
                    case MESSAGE_GETTER: {
                        handleRequest((T) message.obj);
                        break;
                    }
                }
            }
        };
    }

    public void clearQueue() {
        mRequestHandler.removeMessages(MESSAGE_GETTER);
        mRequestHandler.removeMessages(MESSAGE_GET_ALL);
        mRequestHandler.removeMessages(MESSAGE_OPEN);
    }

    public void queueItem(final T t, final int request) {
        if (request == 0) {
            mRequestMap.remove(t);
            return;
        }

        mRequestMap.put(t, "" + request);
        mRequestHandler.obtainMessage(MESSAGE_GETTER, t).sendToTarget();
    }

    public void queueImage(final T target, final String url) {
        if(url == null) {
            mRequestMap.remove(target);
        } else {
            mRequestMap.put(target, url);
            mRequestHandler.obtainMessage(MESSAGE_GETTER, target).sendToTarget();
        }
    }

    private void handleRequest(final T target) {
        try {
            final String url = mRequestMap.get(target);
            if (url == null) {
                return;
            }
            Self.ImageLoader imageLoader = new Self.ImageLoader();
            imageLoader.getImage(url);
            imageLoader.setServImgResponse(new Self.ImageLoader.ServImgResponse() {
                @Override
                public void onResponse(final Bitmap bitmap) {
                    mResponseHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (!mRequestMap.get(target).equals(url)) {
                                    return;
                                }
                            } catch (Exception ex) {
                                return;
                            }

                            mRequestMap.remove(target);
                            mDataLoaderListener.onDataLoaded(target);
                            mDataLoaderListener.onImageLoaded(bitmap);
                        }
                    });
                }
            });

        } catch (Exception ex) {
        }
    }

}
