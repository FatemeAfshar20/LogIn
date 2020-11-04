package com.example.loginpage.Presenter;

import android.widget.EditText;

import com.example.loginpage.Model.UserInfo;
import com.example.loginpage.R;

public class SignInPresenter implements Presenter.SignInPresenter {
    private View mView;

    public SignInPresenter(View view) {
        mView = view;
    }

    @Override
    public boolean checkCorrectFormat(EditText pass, EditText userName, UserInfo user) {
        if (isNumeric(pass.getText().toString()) && pass.getText().toString().trim().length() > 8) {
            return true;
        } else {
            mView.returnSnackbar(R.string.in_correct_pass);
            return false;
        }
    }

    private static boolean isNumeric(String strNum) {
        for (char c : strNum.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}
