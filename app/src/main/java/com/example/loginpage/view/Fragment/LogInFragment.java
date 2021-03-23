package com.example.loginpage.view.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginpage.LoginApplication;
import com.example.loginpage.database.Repository.UserDBRepository;
import com.example.loginpage.model.User;
import com.example.loginpage.R;
import com.example.loginpage.databinding.FragmentLoginBinding;
import com.example.loginpage.viewModel.LogInVm;
import com.google.android.material.snackbar.Snackbar;


public class LogInFragment extends Fragment {
    private FragmentLoginBinding mBinding;
    private LogInVm mVm;

    private LogInCallback mCallback;

    public LogInFragment() {
        // Required empty public constructor
    }

    public static LogInFragment newInstance() {
        LogInFragment fragment = new LogInFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof LogInCallback)
            mCallback = (LogInCallback) context;
        else
            throw new ClassCastException(
                    "Must Implement LogInCallback interface");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVm= LoginApplication.sApplicationComponent.getLoginVm();

        mVm.getIsOk().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean)
                    mCallback.getSignInFragment();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false);
      mBinding.setViewModel(mVm);
        return mBinding.getRoot();
    }

    public interface LogInCallback {
        void getSignInFragment();
    }
}