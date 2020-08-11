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

public class LogInActivity extends AppCompatActivity {

    Button mBtnLogin, mBtnSignIn;
    EditText mPass, mUserName;
    int REQUEST_CODE_SIGNIN = 1;
    String mPassText, mUsernameText;

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
                Intent intent = new Intent(LogInActivity.this, SignInActivity.class);
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
            mPassText = data.getStringExtra(SignInActivity.EXTRA_USER_PASS);
            mUsernameText = data.getStringExtra(SignInActivity.EXTRA_USER_NAME);
        }
    }

    private boolean isCorrectInfo() {
        if (mUserName.getText().toString().equals(mUsernameText) &&
                mPass.getText().toString().equals(mPassText)) {
            return true;
        }
        return false;
    }

    public void returnToast(int msg) {
        Toast toast = Toast.makeText(LogInActivity.this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

}