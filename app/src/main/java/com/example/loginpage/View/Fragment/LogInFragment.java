package com.example.loginpage.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.loginpage.Model.UserInfo;
import com.example.loginpage.Presenter.LoginPresenter;
import com.example.loginpage.R;
import com.google.android.material.snackbar.Snackbar;

public class LogInFragment extends Fragment implements com.example.loginpage.Presenter.View {

    public static final String BUNDLE_USER_INFO = "User Login Information (Save Instance)";
    public static final String ARGS_USER_SIGNIN = "User SignIn";

    private Button mBtnLogin, mBtnSignIn;
    private EditText mPass, mUserName;
    private UserInfo mUserInfoSignIn = new UserInfo();

    private LoginPresenter mPresenter;
    private OnClickSignIn mCallback;

    public static LogInFragment newInstance(UserInfo userInfo) {

        Bundle args = new Bundle();
        args.putParcelable(ARGS_USER_SIGNIN, userInfo);
        LogInFragment fragment = new LogInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnClickSignIn)
            mCallback = (OnClickSignIn) context;
        else
            throw new ClassCastException
                    ("Must Implement OnClickSignIn Interface");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserInfoSignIn = getArguments().getParcelable(ARGS_USER_SIGNIN);

        mPresenter = new LoginPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.
                inflate(R.layout.fragment_login,
                        container,
                        false);
        findElem(view);
        setListener();
        // saveUserInformation(savedInstanceState);
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
                mCallback.onClickSignIn();
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mPresenter.isCorrectInfo(mUserName, mPass, mUserInfoSignIn);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_USER_INFO, mUserInfoSignIn);
    }


    @Override
    public void returnSnackbar(int msg) {
        Snackbar snackbar = Snackbar.make(getView(), msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }


    public interface OnClickSignIn {
        void onClickSignIn();
    }
}