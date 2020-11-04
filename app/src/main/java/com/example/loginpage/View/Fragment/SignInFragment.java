package com.example.loginpage.View.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.transition.SidePropagation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginpage.Model.UserInfo;
import com.example.loginpage.Presenter.SignInPresenter;
import com.example.loginpage.R;
import com.example.loginpage.View.Activity.LogInActivity;
import com.google.android.material.snackbar.Snackbar;

public class SignInFragment extends Fragment implements com.example.loginpage.Presenter.View {
    private Button mBtnSignIn;
    private EditText mPass, mUserName;
    private SignInPresenter mPresenter;

    public static SignInFragment newInstance() {

        Bundle args = new Bundle();

        SignInFragment fragment = new SignInFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter=new SignInPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_signin,
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
                if (mPresenter.checkCorrectFormat(mPass,mUserName,mUserInfoThis)) {
                    LogInActivity.start(getContext(), mUserInfoThis);
                    getActivity().finish();
                }
            }
        });
    }

    @Override
    public void returnSnackbar(int msg) {
        Snackbar snackbar = Snackbar.make(getView(), msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}