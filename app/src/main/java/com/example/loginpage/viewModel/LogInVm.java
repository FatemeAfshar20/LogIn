package com.example.loginpage.viewModel;

import android.text.Editable;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.loginpage.LoginApplication;
import com.example.loginpage.R;
import com.example.loginpage.database.Repository.UserDBRepository;
import com.example.loginpage.model.User;

import javax.inject.Inject;

public class LogInVm extends ViewModel {
    private UserDBRepository mRepository;

    private User mUser;
    private String mUserName;
    private String mPass;

    private MutableLiveData<Boolean> mIsOk = new MutableLiveData<>();

    @Inject
    public LogInVm(UserDBRepository userDBRepository) {
        mRepository = userDBRepository;
    }

    public void afterTextChangedUserName(Editable editable) {
        mUserName = editable.toString();
    }

    public void afterTextChangedPassword(Editable editable) {
        mPass = editable.toString();
    }

    public void onLoginBtnClickListener() {
        checkInputInfo();
    }

    public void onSignInBtnClickListener() {
        mIsOk.setValue(true);
    }

    private void checkInputInfo() {
        mRepository.get(mUserName).observeForever(new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mUser = user;
                if (user == null) {
                    returnToast(R.string.at_first_sign);
                    return;
                } else {
                    if (mUser.getPassword().equals(mPass))
                        returnToast(R.string.success_login);
                    else returnToast(R.string.fail_login);
                }
            }
        });
    }

    public LiveData<Boolean> getIsOk() {
        return mIsOk;
    }

    public void returnToast(int msg) {
        Toast.makeText(LoginApplication.getContext(), msg, Toast.LENGTH_LONG).show();
    }
}
