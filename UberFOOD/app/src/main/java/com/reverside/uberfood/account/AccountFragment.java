package com.reverside.uberfood.account;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.reverside.uberfood.R;
import com.reverside.uberfood.dialog.SimpleMessageDialog;
import com.reverside.uberfood.entity.Contact;
import com.reverside.uberfood.entity.Person;
import com.reverside.uberfood.entity.User;
import com.reverside.uberfood.essentials.Constants;
import com.reverside.uberfood.essentials.Self;
import com.reverside.uberfood.maps.MapsActivity;
import com.reverside.uberfood.maps.SavedPlacesActivity;
import com.reverside.uberfood.restaurant.RestaurantActivity;
import com.reverside.uberfood.utils.PictureUtils;

import java.io.File;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * A placeholder fragment containing a simple view.
 */
public class AccountFragment extends Fragment {

    private static final int REQUEST_PRO_PIC = 10;
    private static final int REQUEST_PLACE_PICKER = 20;

    private static final String SAVED_IMG_PATH = "saved_img_path";

    private static final String SUCCESS = "success";

    private TextView mTitleTV, mPasswCharCntTV;
    private ImageView mProPicIV;
    private ImageButton mBackBTN;
    private EditText mFirstNameET, mLastNameET, mEmailET, mMobileET, mPasswordET, mPasswordVerifyET;
    private LinearLayout mPasswCharCntLL;
    private ProgressDialog mProgressDialog;
    private Button mGetLocationBTN;

    private String mProPicPath = "";

    private Self mSelf;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_account, container, false);

        ((AppCompatActivity) getActivity()).setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));

        mTitleTV = (TextView) view.findViewById(R.id.title_TV);
        mBackBTN = (ImageButton) view.findViewById(R.id.back_BTN);
        mProPicIV = (ImageView) view.findViewById(R.id.account_pro_pic_IV);
        mFirstNameET = (EditText) view.findViewById(R.id.account_firstname_ET);
        mLastNameET = (EditText) view.findViewById(R.id.account_lastname_ET);
        mEmailET = (EditText) view.findViewById(R.id.account_email_ET);
        mMobileET = (EditText) view.findViewById(R.id.account_mobile_ET);
        mPasswordET = (EditText) view.findViewById(R.id.account_password_ET);
        mPasswordVerifyET = (EditText) view.findViewById(R.id.account_verify_password_ET);
        mPasswCharCntTV = (TextView) view.findViewById(R.id.account_char_cnt_password_TV);
        mPasswCharCntLL = (LinearLayout) view.findViewById(R.id.account_char_cnt_password_LL);
        mGetLocationBTN = (Button) view.findViewById(R.id.account_get_locations_BTN);

        mSelf = Self.get(getActivity());

        mTitleTV.setTypeface(Self.mUberFont);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Registering...");
        mProgressDialog.setCancelable(false);

        mPasswordET.addTextChangedListener(onPasswordTextChanged());
        mPasswordVerifyET.addTextChangedListener(onVerifyPasswordTextChanged());

        mBackBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mProPicIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //intent.setType("/*");
                //intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(intent,
                            REQUEST_PRO_PIC);
                } catch (Exception ex) {
                }
            }
        });

        mPasswCharCntLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.password_count_msg, Toast.LENGTH_SHORT).show();
            }
        });

        mGetLocationBTN.setOnClickListener(onGetLocationClick());

        ((AccountActivity) getActivity()).mFragment = this;

        return view;
    }

    @NonNull
    private View.OnClickListener onGetLocationClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivityForResult(new Intent(getActivity(), MapsActivity.class), REQUEST_PRO_PIC);
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_account, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                new Register().validateAndRegister();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean back;

    public boolean onBack() {
        back = false;

        final String firstname = mFirstNameET.getText().toString();
        final String lastname = mLastNameET.getText().toString();
        final String email = mEmailET.getText().toString();
        final String mobile = mMobileET.getText().toString();

        User user =
                mSelf
                        .getCurrentUser();
        Person person = mSelf
                .getCurrentPerson();
        Contact contact = mSelf
                .getCurrentContact();

        if (person.getNames().equals(firstname) &&
                person.getSurname().equals(lastname) &&
                user.getUsername().equals(email) &&
                contact.getEmail().equals(email) &&
                contact.getCell1().equals(mobile)) {
            back = true;
        } else {
            SimpleMessageDialog dialog = new SimpleMessageDialog.Builder(getActivity())
                    .setTitle("Save Changes")
                    .setMessage("Do you want to save this information?")
                    .setPositiveButton("Save", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new Register().validateAndRegister();
                        }
                    })
                    .setNegativeButton("No", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getActivity().finish();
                        }
                    })
                    .create();
            dialog.show(getFragmentManager(), "SMPL_DLG");
        }

        return back;
    }

    private TextWatcher onPasswordTextChanged() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mPasswCharCntTV.setText(s.length() + "");

                if (s.length() < 8) {
                    mPasswCharCntLL.setBackground(getResources().getDrawable(R.drawable.bg_circle_text_red));
                } else {
                    if (new Register().validatePassword(s.toString())) {
                        mPasswCharCntLL.setBackground(getResources().getDrawable(R.drawable.bg_circle_text_green));
                    }
                }
            }
        };
    }

    private TextWatcher onVerifyPasswordTextChanged() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 8) {
                    if (mPasswordET.getText().toString().equals(mPasswordVerifyET.getText().toString())) {
                        mPasswordVerifyET.setCompoundDrawablesRelativeWithIntrinsicBounds(
                                null, null,
                                getResources().getDrawable(R.drawable.ic_complete), null);
                    } else {
                        mPasswordVerifyET.setCompoundDrawablesRelativeWithIntrinsicBounds(
                                null, null,
                                getResources().getDrawable(R.drawable.ic_invalid), null);
                    }
                } else {
                    mPasswordVerifyET.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            null, null,
                            getResources().getDrawable(R.drawable.ic_not_complete), null);
                }
            }
        };
    }

    private class Register {
        String firstname;
        String lastname;
        String email;
        String mobile;
        String password;

        public Register() {
            firstname = mFirstNameET.getText().toString();
            lastname = mLastNameET.getText().toString();
            email = mEmailET.getText().toString();
            mobile = mMobileET.getText().toString();
            password = mPasswordVerifyET.getText().toString();
        }

        private String formValidate() {
            String verdict = SUCCESS;

            if (!firstname.isEmpty() &&
                    !lastname.isEmpty() &&
                    !email.isEmpty() &&
                    !mobile.isEmpty() &&
                    !password.isEmpty()) {
                //verdict = true;
            } else {
                return "Missing fields";
            }

            if (password.length() >= 8) {
                if (!password.equals(mPasswordVerifyET.getText().toString())) {
                    if (!validatePassword(password) ||
                            !validatePassword(mPasswordVerifyET.getText().toString()))
                        return getString(R.string.password_count_msg);
                } else {
                    return "Password mismatch";
                }
            } else {
                return "Password must be atleast 8 chars";
            }

            return "";
        }

        public boolean validatePassword(String password) {
            if (Pattern.compile("\\d+").matcher(password).find()) {
                if (Pattern.compile("\\p{Punct}+").matcher(password).find() ||
                        Pattern.compile("\\p{Blank}+").matcher(password).find()) {
                    if (Pattern.compile("\\p{Alpha}+").matcher(password).find()) {
                        return true;
                    }
                }
            }

            return false;
        }

        public void validateAndRegister() {
            mProgressDialog.show();

            String response = formValidate();

            if (response.equals(SUCCESS)) {
                Person person = new Person();
                person.setNames(firstname);
                person.setSurname(lastname);

                Contact contact = new Contact();
                contact.setEmail(email);
                contact.setCell1(mobile);

                User user = new User();
                user.setUsername(email);
                user.setPassword(password);
                user.setTokenId(new Date().getTime() + "-" + new Random().nextInt());

                new Self.ServComBuilder(getActivity())
                        .registerUser(user.toString(), person.toString(), contact.toString())
                        .setServResponse(new Self.ServComBuilder.ServResponse() {
                            @Override
                            public void onResponse(int responseCode, String response) {
                                if (responseCode == 1) {
                                    mProgressDialog.hide();
                                    Toast.makeText(getActivity(), "Welcome to UberFood", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getActivity(), RestaurantActivity.class));
                                } else {
                                    mProgressDialog.hide();
                                    Toast.makeText(getActivity(), "Couldn't save your information", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onResponse(Object object) {

                            }
                        });

            /*try {
                sServerAuthenticate.userSignUp(user, person, contact, Constants.AccountDetails.ACCOUNT_TYPE);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            mProgressDialog.hide();
            Toast.makeText(getActivity(), "You are successfully registered", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getActivity(), RestaurantActivity.class));*/
            } else {
                mProgressDialog.hide();
                Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showMessage(String msg) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case REQUEST_PRO_PIC:
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
                        Bitmap bitmap = PictureUtils.getScaledBitmap(path, mProPicIV.getWidth(), mProPicIV.getHeight());
                        mProPicIV.setImageBitmap(bitmap);
                        mProPicPath = path;
                    }
                }

                break;
            case REQUEST_PLACE_PICKER:
                Place place = PlacePicker.getPlace(getActivity(), data);
                Log.v(Constants.UNI_TAG, "Id: " + place.getId());
                Log.v(Constants.UNI_TAG, "Name: " + place.getName());
                Log.v(Constants.UNI_TAG, "Address: " + place.getAddress());
                Log.v(Constants.UNI_TAG, "LatLng: " + place.getLatLng().toString());
                Log.v(Constants.UNI_TAG, "PhoneNum: " + place.getPhoneNumber());
                Toast.makeText(getActivity(), "Place: " + place.getName(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
