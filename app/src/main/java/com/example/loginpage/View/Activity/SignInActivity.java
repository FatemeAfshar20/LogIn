package com.example.loginpage.View.Activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import com.example.loginpage.View.Fragment.SignInFragment;

public class SignInActivity extends SingleFragmentActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, SignInActivity.class);
        //starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    public Fragment getFragment() {
        return SignInFragment.newInstance();
    }
}