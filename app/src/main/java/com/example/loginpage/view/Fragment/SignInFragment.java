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

import com.example.loginpage.database.Repository.UserDBRepository;
import com.example.loginpage.model.User;
import com.example.loginpage.R;
import com.example.loginpage.databinding.FragmentSigninBinding;
import com.example.loginpage.viewModel.SignVM;
import com.google.android.material.snackbar.Snackbar;

public class SignInFragment extends Fragment {
    private FragmentSigninBinding mBinding;
    private SignVM mVM;
    private SignInCallbacks mCallback;

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof SignInCallbacks)
            mCallback = (SignInCallbacks) context;
        else
            throw new ClassCastException(
                    "Must implement SignInCallbacks interface");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVM=new ViewModelProvider(this).get(SignVM.class);

        mVM.getIsOk().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean)
                    mCallback.getLoginFragment();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_signin,container,false);
        mBinding.setViewModel(mVM);
        return mBinding.getRoot();
    }

    public interface SignInCallbacks {
        void getLoginFragment();
    }
}