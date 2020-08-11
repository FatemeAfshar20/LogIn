package com.example.loginpage.Controller;

import androidx.appcompat.app.AppCompatActivity;

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
    public static final String EXTRA_USER_NAME= "com.example.loginpage.Controller.User Name";
    Button mBtnSignIn;
    EditText mPass,mUserName;
    UserInfo mUserInfo=new UserInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        findElem();
        setListener();
    }

    private void findElem(){
        mBtnSignIn=findViewById(R.id.btn_sign_in_signin);
        mPass=findViewById(R.id.pass_sigin);
        mUserName=findViewById(R.id.user_name_signin);
    }

    private void setListener(){
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mUserInfo.setUserName(mUserName.getText().toString());
                mUserInfo.setPassword(mPass.getText().toString());
                if(mUserInfo.getUserName().length()!=0 && mUserInfo.getPassword().length()!=0){
                    setInfo(mUserInfo);
                    finish();
                }else
                    Toast.makeText(SignInActivity.this,R.string.toast_text,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setInfo(UserInfo userInfo){
        Intent intent=new Intent();
            intent.putExtra(EXTRA_USER_NAME,  userInfo.getUserName());
            intent.putExtra(EXTRA_USER_PASS,  userInfo.getPassword());
            setResult(RESULT_OK,intent);
    }
}