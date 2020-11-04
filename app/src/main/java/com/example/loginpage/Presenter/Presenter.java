package com.example.loginpage.Presenter;

import android.widget.EditText;

import com.example.loginpage.Model.UserInfo;

public interface Presenter {
    interface LoginPresenter{
        void isCorrectInfo(EditText username,
                              EditText password,
                              UserInfo user);
    }

    interface SignInPresenter{
        boolean checkCorrectFormat(EditText pass,EditText userName,UserInfo user);
    }
}
