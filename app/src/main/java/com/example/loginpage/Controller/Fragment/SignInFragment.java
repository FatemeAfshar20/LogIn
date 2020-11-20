package com.example.loginpage.Controller.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.loginpage.Database.Repository.UserDBRepository;
import com.example.loginpage.Model.User;
import com.example.loginpage.R;
import com.google.android.material.snackbar.Snackbar;

public class SignInFragment extends Fragment {
    private Button mBtnSignIn;
    private EditText mPass, mUserName;

    private User mUser;
    private UserDBRepository mRepository;

    private SignInCallbacks mCallback;

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof SignInCallbacks)
            mCallback = (SignInCallbacks) context;
        else
            throw new ClassCastException(
                    "Must implement SignInCallbacks interface");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository =
                UserDBRepository.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin,
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
                if (checkValidInputInfo(view))
                    mCallback.getLoginFragment();
            }
        });
    }

    public boolean checkValidInputInfo(View view) {
        if (!mRepository.IsUserExist(mUserName.getText().toString())) {
            if (isNumeric(mPass.getText().toString())) {
                if (mPass.getText().toString().trim().length() > 8) {
                    if (mUserName.getText().toString().length() != 0 && mPass.getText().toString().length() != 0) {
                        mUser = new User(mUserName.getText().toString(), mPass.getText().toString());
                        mRepository.insert(mUser);
                        return true;
                    } else {
                        returnSnackbar(view, R.string.in_correct_input);
                    }
                } else {
                    returnSnackbar(view, R.string.in_correct_length);
                }
            } else {
                returnSnackbar(view, R.string.in_correct_pass);
            }
        } else {
            returnSnackbar(view, R.string.user_find);
        }
        return false;
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

    public interface SignInCallbacks {
        void getLoginFragment();
    }
}