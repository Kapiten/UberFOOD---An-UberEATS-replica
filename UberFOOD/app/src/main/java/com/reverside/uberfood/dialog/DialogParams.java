package com.reverside.uberfood.dialog;

import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.view.View.OnClickListener;

public class DialogParams {
    private Context mContext;
    private static DialogParams mDialogParams;
    private String mTitleText = "";
    private String mMessageText = "";
    private int mTitleTextId = 0;
    private int mMessageTextId = 0;
    private boolean mTitle;
    private String mPositiveBtnText = "";
    private String mNegativeBtnText = "";
    private int mPositiveBtnTextId;
    private int mNegativeBtnTextId;
    private boolean mNegativeButton;
    private OnClickListener mPositiveListener;
    private OnClickListener mNegativeListener;
    private boolean mHideCloseIcon;
    private OnDismissListener mOnDismissListener;

    private DialogParams(Context context) {
        mContext = context;
    }

    public static DialogParams get(Context context) {
        if (mDialogParams == null) {
            mDialogParams = new DialogParams(context);
        }

        return mDialogParams;
    }

    void nullify() {
        mTitleText = "";
        mMessageText = "";
        mTitleTextId = 0;
        mMessageTextId = 0;
        mTitle = true;
        mPositiveBtnText = "";
        mNegativeBtnText = "";
        mPositiveBtnTextId = 0;
        mNegativeBtnTextId = 0;
        mNegativeButton = false;
        mPositiveListener = null;
        mNegativeListener = null;
        mHideCloseIcon = false;
    }

    public String getTitleText() {
        return mTitleText;
    }

    public void setTitleText(String titleText) {
        mTitleText = titleText;
    }

    public boolean hasTitle() {
        return mTitle;
    }

    public void setTitle(boolean title) {
        mTitle = title;
    }

    public String getMessageText() {
        return mMessageText;
    }

    public void setMessageText(String messageText) {
        mMessageText = messageText;
    }

    public int getTitleTextId() {
        return mTitleTextId;
    }

    public void setTitleTextId(int titleTextId) {
        mTitleTextId = titleTextId;
    }

    public int getMessageTextId() {
        return mMessageTextId;
    }

    public void setMessageTextId(int messageTextId) {
        mMessageTextId = messageTextId;
    }

    public String getPositiveBtnText() {
        return mPositiveBtnText;
    }

    public void setPositiveBtnText(String positiveBtnText) {
        mPositiveBtnText = positiveBtnText;
    }

    public String getNegativeBtnText() {
        return mNegativeBtnText;
    }

    public void setNegativeBtnText(String negativeBtnText) {
        mNegativeBtnText = negativeBtnText;
    }

    public int getPositiveBtnTextId() {
        return mPositiveBtnTextId;
    }

    public void setPositiveBtnTextId(int positiveBtnTextId) {
        mPositiveBtnTextId = positiveBtnTextId;
    }

    public int getNegativeBtnTextId() {
        return mNegativeBtnTextId;
    }

    public void setNegativeBtnTextId(int negativeBtnTextId) {
        mNegativeBtnTextId = negativeBtnTextId;
    }

    public OnClickListener getPositiveListener() {
        return mPositiveListener;
    }

    public void setPositiveListener(OnClickListener positiveListener) {
        mPositiveListener = positiveListener;
    }

    public OnClickListener getNegativeListener() {
        return mNegativeListener;
    }

    public void setNegativeListener(OnClickListener negativeListener) {
        mNegativeListener = negativeListener;
    }

    public boolean hasNegativeButton() {
        return mNegativeButton;
    }

    public void setNegativeButton(boolean negativeButton) {
        mNegativeButton = negativeButton;
    }

    public OnDismissListener getOnDismissListener() {
        return mOnDismissListener;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
    }

    public boolean isHideCloseIcon() {
        return mHideCloseIcon;
    }

    public void setHideCloseIcon(boolean hideCloseIcon) {
        mHideCloseIcon = hideCloseIcon;
    }

}
