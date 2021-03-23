package com.example.loginpage;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.example.loginpage.dagger.ApplicationComponent;
import com.example.loginpage.dagger.DaggerApplicationComponent;

import dagger.internal.DaggerCollections;

public class LoginApplication extends Application {
    public  static ApplicationComponent sApplicationComponent= DaggerApplicationComponent.create();
    @SuppressLint("StaticFieldLeak")
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        LoginApplication.sContext=this.getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
