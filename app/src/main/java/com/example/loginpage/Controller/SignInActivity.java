package com.example.loginpage.Controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginpage.Model.UserInfo;
import com.example.loginpage.R;

public class SignInActivity extends AppCompatActivity {
    public static final String EXTRA_USER_PASS = "com.example.loginpage.Controller.User Password";
    public static final String EXTRA_USER_NAME = "com.example.loginpage.Controller.User Name";
    private static final int REQUEST_CODE_LOGIN = 0;
    private Button mBtnSignIn;
    private EditText mPass, mUserName;
    private UserInfo mUserInfo = new UserInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        findElem();
        setListener();
        setTextInEditText();
    }

    private void setTextInEditText() {
        String usernameText = getIntent().getStringExtra(LogInActivity.EXTRA_USERNAME);
        mUserName.setText(usernameText);
        mUserInfo.setUserName(usernameText);
        String passText = getIntent().getStringExtra(LogInActivity.EXTRA_PASS);
        mPass.setText(passText);
        mUserInfo.setPassword(passText);
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
                if (mUserInfo.getUserName().length() != 0 && mUserInfo.getPassword().length() != 0) {
                    setInfo(mUserInfo);
                    finish();
                } else
                    Toast.makeText(SignInActivity.this, R.string.toast_text, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setInfo(UserInfo userInfo) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_USER_NAME, userInfo.getUserName());
        intent.putExtra(EXTRA_USER_PASS, userInfo.getPassword());
        setResult(RESULT_OK, intent);
    }
}