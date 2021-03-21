package com.example.loginpage.viewModel;

import android.app.Application;
import android.text.Editable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginpage.R;
import com.example.loginpage.database.Repository.UserDBRepository;
import com.example.loginpage.model.User;

public class LogInVm extends AndroidViewModel {
    private UserDBRepository mRepository;
    private User mUser;
    private String mUserName;
    private String mPass;

    private MutableLiveData<Boolean> mIsOk=new MutableLiveData<>();

    public LogInVm(@NonNull Application application) {
        super(application);
        mRepository = UserDBRepository.getInstance(getApplication());
    }

    public void afterTextChangedUserName(Editable editable){
        mUserName=editable.toString();
    }

    public void afterTextChangedPassword(Editable editable){
        mPass=editable.toString();
    }

    public void onLoginBtnClickListener(){
        if (checkInputInfo())
            returnToast(R.string.success_login);
        else
            returnToast(R.string.fail_login);
    }

    public void onSignInBtnClickListener(){
        mIsOk.setValue(true);
    }

    private boolean checkInputInfo() {
        if (mRepository.IsUserExist(mUserName)) {
            mUser = mRepository.get(mUserName);
            if (mUser.getPassword().equals(mPass))
                return true;
        } else {
            returnToast(R.string.at_first_sign);
            return false;
        }
        return true;
    }

    public LiveData<Boolean> getIsOk() {
        return mIsOk;
    }

    public void returnToast(int msg) {
        Toast.makeText(getApplication(), msg, Toast.LENGTH_LONG).show();
    }
}
