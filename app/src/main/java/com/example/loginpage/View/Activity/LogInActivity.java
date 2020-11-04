package com.example.loginpage.View.Activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.loginpage.Model.UserInfo;
import com.example.loginpage.View.Fragment.LogInFragment;

public class LogInActivity extends SingleFragmentActivity implements LogInFragment.OnClickSignIn {

    public static final String EXTRA_USER = "com.example.loginpage.User";

    public static void start(Context context, UserInfo userInfo) {
        Intent starter = new Intent(context, LogInActivity.class);
        starter.putExtra(EXTRA_USER,userInfo);
        context.startActivity(starter);
    }

    @Override
    public Fragment getFragment() {
        Intent intent=getIntent();
        UserInfo user=intent.getParcelableExtra(EXTRA_USER);
        return LogInFragment.newInstance(user);
    }

    @Override
    public void onClickSignIn() {
        SignInActivity.start(this);
    }
}