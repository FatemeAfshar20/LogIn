package com.example.loginpage.View.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginpage.Model.UserInfo;
import com.example.loginpage.R;
import com.example.loginpage.View.Activity.LogInActivity;
import com.google.android.material.snackbar.Snackbar;

public class SignInFragment extends Fragment {
    private Button mBtnSignIn;
    private EditText mPass, mUserName;

    public static SignInFragment newInstance() {

        Bundle args = new Bundle();

        SignInFragment fragment = new SignInFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_sign_in,
                container,
                false);

        findElem(view);
        setListener();
        return view;
    }

    private void findElem(View view) {
        mBtnSignIn = view.findViewById(R.id.btn_sign_in_signin);
        mPass = view.findViewById(R.id.pass_sigin);
        mUserName = view.findViewById(R.id.user_name_signin);
    }

    private void setListener() {
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo mUserInfoThis = new UserInfo();

                if (isNumeric(mPass.getText().toString())) {
                    if (mPass.getText().toString().trim().length() > 8){
                        if (mUserName.getText().toString().length() != 0 && mPass.getText().toString().length() != 0) {
                            mUserInfoThis.setUserName(mUserName.getText().toString());
                            mUserInfoThis.setPassword(mPass.getText().toString());
                            LogInActivity.start(getContext(),mUserInfoThis);
                            getActivity().finish();
                        } else
                            returnSnackbar(view, R.string.in_correct_input);
                    }else
                        returnSnackbar(view,R.string.in_correct_length);
                } else
                    returnSnackbar(view, R.string.in_correct_pass);
            }
        });
    }

    public void returnSnackbar(View view, int msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private static boolean isNumeric(String strNum) {
        for (char c : strNum.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}