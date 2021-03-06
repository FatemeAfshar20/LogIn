package com.example.loginpage.Controller.Activity;

import androidx.fragment.app.Fragment;

import com.example.loginpage.Controller.Fragment.LogInFragment;
import com.example.loginpage.Controller.Fragment.SignInFragment;
import com.example.loginpage.R;

public class Activity extends SingleFragmentActivity
        implements LogInFragment.LogInCallback, SignInFragment.SignInCallbacks {

    @Override
    public Fragment getFragment() {
        return LogInFragment.newInstance();
    }

    @Override
    public void getSignInFragment() {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container,
                        SignInFragment.newInstance()).commit();
    }

    @Override
    public void getLoginFragment() {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container,
                        LogInFragment.newInstance()).commit();
    }
}