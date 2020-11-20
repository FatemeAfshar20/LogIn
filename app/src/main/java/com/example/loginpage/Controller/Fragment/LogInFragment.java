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


public class LogInFragment extends Fragment {

    private Button mBtnLogin, mBtnSignIn;
    private EditText mPass, mUserName;

    private UserDBRepository mRepository;

    private LogInCallback mCallback;

    public LogInFragment() {
        // Required empty public constructor
    }


    public static LogInFragment newInstance() {
        LogInFragment fragment = new LogInFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof LogInCallback)
            mCallback = (LogInCallback) context;
        else
            throw new ClassCastException(
                    "Must Implement LogInCallback interface");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = UserDBRepository.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(
                R.layout.fragment_login,
                container,
                false);
        findElem(view);
        setListener();
        return view;
    }

    private void findElem(View view) {
        mBtnLogin = view.findViewById(R.id.btn_login);
        mBtnSignIn = view.findViewById(R.id.btn_sign_in_signin);
        mPass = view.findViewById(R.id.pass);
        mUserName = view.findViewById(R.id.user_name);
    }

    private void setListener() {
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.getSignInFragment();
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInputInfo(v))
                    returnSnackbar(v, R.string.success_login);
                else
                    returnSnackbar(v, R.string.fail_login);
            }
        });
    }

    private boolean checkInputInfo(View v) {
        if (mRepository.IsUserExist(mUserName.getText().toString())) {
            User user = mRepository.get(mUserName.getText().toString());
            if (user.getPassword().equals(mPass.getText().toString()))
                return true;
        } else {
            returnSnackbar(v, R.string.at_first_sign);
            return false;
        }
        return true;
    }

    public void returnSnackbar(View view, int msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public interface LogInCallback {
        void getSignInFragment();
    }
}