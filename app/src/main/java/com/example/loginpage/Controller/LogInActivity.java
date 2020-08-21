package com.example.loginpage.Controller;

import androidx.annotation.NonNull;
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
import com.google.android.material.snackbar.Snackbar;

public class LogInActivity extends AppCompatActivity  {

    public static final String EXTRA_USER_INFO_LOGIN = "com.example.loginpage.Controller.User Information";
    public static final String BUNDLE_USER_INFO = "User Login Information (Save Instance)";

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
        saveUserInformation(savedInstanceState);
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

                if(isNumeric(mPass.getText().toString())) {
                    mUserInfoLogin.setUserName(mUserName.getText().toString());
                    mUserInfoLogin.setPassword(mPass.getText().toString());
                    Intent intent = new Intent(LogInActivity.this, SignInActivity.class);
                    intent.putExtra(EXTRA_USER_INFO_LOGIN, mUserInfoLogin);
                    startActivityForResult(intent, REQUEST_CODE_SIGNIN);
                }
                else
                    returnSnackbar(view,R.string.in_correct_pass);

            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCorrectInfo())
                    returnSnackbar(v,R.string.success_login);
                else
                    returnSnackbar(v,R.string.fail_login);
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
            if(mUserInfoSignIn!=null)
                  setUserInfo(mUserInfoSignIn);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_USER_INFO,mUserInfoSignIn);
    }

    public void saveUserInformation(Bundle bundle){
        if(bundle!=null) {
            mUserInfoSignIn = bundle.getParcelable(BUNDLE_USER_INFO);
            setUserInfo(mUserInfoLogin);
        }
    }

    private void setUserInfo(UserInfo userInfo) {
        mPass.setText(userInfo.getPassword());
        mUserName.setText(userInfo.getUserName());
    }


    private boolean isCorrectInfo() {
        if (mUserName.getText().toString().equals(mUserInfoSignIn.getUserName()) &&
                mPass.getText().toString().equals(mUserInfoSignIn.getPassword())) {
            return true;
        }
        return false;
    }

    public void returnSnackbar(View view,int msg) {
        Snackbar snackbar=Snackbar.make(view,msg,Snackbar.LENGTH_LONG);
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