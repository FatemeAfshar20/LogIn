package com.example.loginpage.Controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginpage.Model.UserInfo;
import com.example.loginpage.R;

public class LogInActivity extends AppCompatActivity  {

    public static final String EXTRA_USER_INFO_LOGIN = "com.example.loginpage.Controller.User Information";

    private Button mBtnLogin, mBtnSignIn;
    private EditText mPass, mUserName;
    private int REQUEST_CODE_SIGNIN = 1;
    private UserInfo mUserInfoLogin =new UserInfo();
    private UserInfo mUserInfoSignIn =new UserInfo();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findElem();
        setListener();
    }

    private void findElem() {
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnSignIn = findViewById(R.id.btn_sign_in_signin);
        mPass = findViewById(R.id.pass);
        mUserName = findViewById(R.id.user_name);
    }

    private void setListener() {
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserInfoLogin.setUserName(mUserName.getText().toString());
                mUserInfoLogin.setPassword(mPass.getText().toString());
                Intent intent = new Intent(LogInActivity.this, SignInActivity.class);
                intent.putExtra(EXTRA_USER_INFO_LOGIN, mUserInfoLogin);
                startActivityForResult(intent, REQUEST_CODE_SIGNIN);
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCorrectInfo())
                    returnToast(R.string.success_login);
                else
                    returnToast(R.string.fail_login);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_SIGNIN) {
            mUserInfoSignIn=data.getParcelableExtra(SignInActivity.EXTRA_USER_INFO_SIGN);
            mPass.setText(mUserInfoSignIn.getPassword());
            mUserName.setText(mUserInfoSignIn.getUserName());
        }
    }

    private boolean isCorrectInfo() {
        if (mUserName.getText().toString().equals(mUserInfoSignIn.getUserName()) &&
                mPass.getText().toString().equals(mUserInfoSignIn.getPassword())) {
            return true;
        }
        return false;
    }

    public void returnToast(int msg) {
        Toast toast = Toast.makeText(LogInActivity.this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,100);
        toast.show();
    }

}