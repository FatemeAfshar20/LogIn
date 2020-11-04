package com.example.loginpage.Presenter;

import android.widget.EditText;

import com.example.loginpage.Model.UserInfo;
import com.example.loginpage.R;

public class LoginPresenter implements Presenter.LoginPresenter {
    private View mView;

    public LoginPresenter(View view) {
        mView = view;
    }

    @Override
    public void isCorrectInfo(EditText username, EditText password, UserInfo user) {
        if (username.getText().toString().equals(user.getUserName()) &&
                password.getText().toString().equals(user.getPassword())) {
            mView.returnSnackbar(R.string.success_login);
        } else
            mView.returnSnackbar(R.string.fail_login);
    }
}
