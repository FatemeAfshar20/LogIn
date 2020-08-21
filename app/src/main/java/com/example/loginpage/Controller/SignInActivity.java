package com.example.loginpage.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginpage.Model.UserInfo;
import com.example.loginpage.R;
import com.google.android.material.snackbar.Snackbar;

public class SignInActivity extends AppCompatActivity {

    public static final String EXTRA_USER_INFO_SIGN = "com.example.loginpage.Controller.User Information";
    private Button mBtnSignIn;
    private EditText mPass, mUserName;
    private UserInfo mUserInfoThis = new UserInfo();
    private UserInfo mUserInfoLogin = new UserInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        findElem();
        setListener();
        setTextInEditText();
    }

    private void setTextInEditText() {
        mUserInfoLogin = getIntent().getParcelableExtra(LogInActivity.EXTRA_USER_INFO_LOGIN);

        mUserName.setText(mUserInfoLogin.getUserName());
        mUserInfoThis.setUserName(mUserInfoLogin.getUserName());

        mPass.setText(mUserInfoLogin.getPassword());
        mUserInfoThis.setPassword(mUserInfoLogin.getPassword());
    }

    private void findElem() {
        mBtnSignIn = findViewById(R.id.btn_sign_in_signin);
        mPass = findViewById(R.id.pass_sigin);
        mUserName = findViewById(R.id.user_name_signin);
    }

    private void setListener() {
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNumeric(mPass.getText().toString())) {
                    if (mPass.getText().toString().trim().length() > 8){
                        if (mUserName.getText().toString().length() != 0 && mPass.getText().toString().length() != 0) {
                            mUserInfoThis.setUserName(mUserName.getText().toString());
                            mUserInfoThis.setPassword(mPass.getText().toString());
                            setInfo(mUserInfoThis);
                            finish();
                        } else
                            returnSnackbar(view, R.string.in_correct_input);
                    }else
                        returnSnackbar(view,R.string.in_correct_length);
                } else
                    returnSnackbar(view, R.string.in_correct_pass);
            }
        });
    }

    private void setInfo(UserInfo userInfo) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_USER_INFO_SIGN, userInfo);
        setResult(RESULT_OK, intent);
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