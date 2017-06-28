package com.reverside.uberfood.change_password;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.reverside.uberfood.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ChangePasswordFragment extends Fragment {

    private ViewFlipper mViewFlipper;
    private Button mSendBTN, mVerifyBTN, mSubmitBTN;
    private EditText mOldPwdET, mNewPwdET, mVerNewPwdET;
    private RadioGroup mContactRG;
    private RadioButton mContactCellRB, mContactEmailRB;

    public static ChangePasswordFragment newInatance() {
        return new ChangePasswordFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        mViewFlipper = (ViewFlipper) view.findViewById(R.id.chpwd_VF);
        mSendBTN = (Button) view.findViewById(R.id.chpwd_send_code_BTN);
        mVerifyBTN = (Button) view.findViewById(R.id.chpwd_verify_code_BTN);
        mSubmitBTN = (Button) view.findViewById(R.id.chpwd_submit_BTN);
        mOldPwdET = (EditText) view.findViewById(R.id.chpwd_old_pwd_ET);
        mNewPwdET = (EditText) view.findViewById(R.id.chpwd_new_pwd_ET);
        mVerNewPwdET = (EditText) view.findViewById(R.id.chpwd_verify_new_pwd_ET);
        mContactRG = (RadioGroup) view.findViewById(R.id.chpwd_contact_RG);
        mContactCellRB = (RadioButton) mContactRG.findViewById(R.id.chpwd_contact_cell_RB);
        mContactEmailRB = (RadioButton) mContactRG.findViewById(R.id.chpwd_contact_email_RB);

        mSendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewFlipper.setDisplayedChild(1);
            }
        });

        mVerifyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewFlipper.setDisplayedChild(2);
            }
        });

        mSubmitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mNewPwdET.getText().toString()
                        .equals(mVerNewPwdET.getText().toString())) {
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), "New password mismatch", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
