package com.example.loginpage.view.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.loginpage.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    public abstract Fragment getFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);

        Fragment fragment=getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (fragment==null)
            getSupportFragmentManager().
                    beginTransaction().
                    add(R.id.fragment_container,
                            getFragment()).commit();
    }
}
