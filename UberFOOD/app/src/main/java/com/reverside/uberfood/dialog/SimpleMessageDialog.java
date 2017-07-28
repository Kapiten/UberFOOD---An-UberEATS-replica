package com.reverside.uberfood.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.reverside.uberfood.R;

public class SimpleMessageDialog extends DialogFragment {
    private ImageButton mCloseBtn;
    private TextView mMessageTV;
    private Button mNegativeBtn;
    private DialogParams mParams;
    private Button mPositiveBtn;
    private TextView mTitleTV;
    private int result = 0;

    public static SimpleMessageDialog newInstance() {
        return new SimpleMessageDialog();
    }

    @NonNull
    public Dialog onCreateDialog(final Bundle bundle) {
        final View inflate = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_simple_message, null);
        mTitleTV = (TextView) inflate.findViewById(R.id.dialog_simple_title_TV);
        mMessageTV = (TextView) inflate.findViewById(R.id.dialog_simple_message_TV);
        mNegativeBtn = (Button) inflate.findViewById(R.id.dialog_simple_negative_BTN);
        mPositiveBtn = (Button) inflate.findViewById(R.id.dialog_simple_positive_BTN);
        mCloseBtn = (ImageButton) inflate.findViewById(R.id.dialog_simple_close_BTN);

        mParams = DialogParams.get(getActivity());

        mPositiveBtn.setOnClickListener(onClickListener(null));
        if (mParams.getTitleTextId() != 0 & mParams.getTitleText().isEmpty()) {
            mParams.setTitleText(getString(mParams.getTitleTextId()));
        }
        if (!mParams.getTitleText().isEmpty()) {
            mTitleTV.setText(mParams.getTitleText());
        }
        if (!mParams.hasTitle()) {
            mTitleTV.setVisibility(View.GONE);
        }

        if (mParams.getMessageTextId() != 0 & mParams.getMessageText().isEmpty()) {
            mParams.setMessageText(getString(mParams.getMessageTextId()));
        }
        if (!mParams.getMessageText().isEmpty()) {
            mMessageTV.setText(mParams.getMessageText());
        }

        if (!mParams.getPositiveBtnText().isEmpty() |
                mParams.getPositiveBtnTextId() != 0) {
            if (mParams.getPositiveBtnTextId() != 0 &
                    mParams.getPositiveBtnText().isEmpty()) {
                mParams.setPositiveBtnText(getString(mParams.getPositiveBtnTextId()));
            }
            if (!mParams.getPositiveBtnText().isEmpty()) {
                mPositiveBtn.setVisibility(View.VISIBLE);
                mPositiveBtn.setText(mParams.getPositiveBtnText());
                mPositiveBtn.setOnClickListener(onClickListener(mParams.getPositiveListener()));
            }
        }

        if (!mParams.getNegativeBtnText().isEmpty() |
                mParams.getNegativeBtnTextId() != 0) {
            if (mParams.getNegativeBtnTextId() != 0 &
                    mParams.getNegativeBtnText().isEmpty()) {
                mParams.setNegativeBtnText(getString(mParams.getNegativeBtnTextId()));
            }
            if (!mParams.getNegativeBtnText().isEmpty()) {
                mParams.setNegativeButton(true);
            }
        }

        if (mParams.hasNegativeButton() || mParams.getNegativeListener() != null) {
            mNegativeBtn.setVisibility(View.VISIBLE);
            if (mParams.getNegativeBtnText().isEmpty()) {
                mParams.setNegativeBtnText(getString(R.string.cancel));
            }
            mNegativeBtn.setText(mParams.getNegativeBtnText());
            mNegativeBtn.setOnClickListener(onClickListener(mParams.getNegativeListener()));
        }

        mCloseBtn.setOnClickListener(new OnClickListener() {
            public void onClick(final View view) {
                dismiss();
            }
        });
        if (mParams.isHideCloseIcon()) {
            mCloseBtn.setVisibility(View.GONE);
        } else {
            mCloseBtn.setVisibility(View.VISIBLE);
        }
        return new AlertDialog.Builder(getActivity())
                .setView(inflate)
                .create();
    }

    public static class Builder {
        SimpleMessageDialog dialog;

        public Builder(final Context context) {
            dialog = SimpleMessageDialog.newInstance();
            dialog.mParams = DialogParams.get(context);
            dialog.mParams.nullify();
        }

        public SimpleMessageDialog create() {
            return dialog;
        }

        public Builder hasNegativeButton(final boolean negativeButton) {
            dialog.mParams.setNegativeButton(negativeButton);
            return this;
        }

        public Builder setHideCloseButton(final boolean hideCloseIcon) {
            dialog.mParams.setHideCloseIcon(hideCloseIcon);
            return this;
        }

        public Builder setMessage(final int messageTextId) {
            dialog.mParams.setMessageTextId(messageTextId);
            return this;
        }

        public Builder setMessage(final CharSequence charSequence) {
            dialog.mParams.setMessageText(charSequence.toString());
            return this;
        }

        public Builder setNegativeButton(final int negativeBtnTextId, final OnClickListener negativeListener) {
            dialog.mParams.setNegativeBtnTextId(negativeBtnTextId);
            dialog.mParams.setNegativeListener(negativeListener);
            return this;
        }

        public Builder setNegativeButton(final CharSequence charSequence, final OnClickListener negativeListener) {
            dialog.mParams.setNegativeBtnText(charSequence.toString());
            dialog.mParams.setNegativeListener(negativeListener);
            return this;
        }

        public Builder setOnDismiss(final DialogInterface.OnDismissListener onDismissListener) {
            dialog.mParams.setOnDismissListener(onDismissListener);
            return this;
        }

        public Builder setPositiveButton(final int positiveBtnTextId, final OnClickListener positiveListener) {
            dialog.mParams.setPositiveBtnTextId(positiveBtnTextId);
            dialog.mParams.setPositiveListener(positiveListener);
            return this;
        }

        public Builder setPositiveButton(final CharSequence charSequence, final OnClickListener positiveListener) {
            dialog.mParams.setPositiveBtnText(charSequence.toString());
            dialog.mParams.setPositiveListener(positiveListener);
            return this;
        }

        public Builder setTitle(final int titleTextId) {
            dialog.mParams.setTitleTextId(titleTextId);
            return this;
        }

        public Builder setTitle(final CharSequence charSequence) {
            dialog.mParams.setTitleText(charSequence.toString());
            return this;
        }
    }

    private OnClickListener onClickListener(final OnClickListener onClickListener) {
        return new OnClickListener() {
            public void onClick(final View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
                if (view.equals(mPositiveBtn)) {
                    result = Activity.RESULT_OK;
                }
                dismiss();
            }
        };
    }

    private int sendResult(final int result) {
        if (getTargetFragment() == null) {
            return 0;
        }
        getTargetFragment().onActivityResult(getTargetRequestCode(), result, null);
        return result;
    }

    public void onDismiss(final DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (result != -1) {
            sendResult(0);
        }
    }
}