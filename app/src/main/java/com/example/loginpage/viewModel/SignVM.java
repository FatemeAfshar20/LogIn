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

public class SignVM extends ViewModel {
    private  User mUser=new User();
    private UserDBRepository mRepository;
    private MutableLiveData<Boolean> mIsOk=new MutableLiveData<>();

    public SignVM(UserDBRepository userDBRepository) {
        mRepository =userDBRepository;
    }

    public void afterTextChangedUserName(Editable editable){
        mUser.setUserName(editable.toString());
    }

    public void afterTextChangedPassword(Editable editable){
        mUser.setPassword(editable.toString());
    }

    public void onSignBtnClickListener(){
            checkValidInputInfo();
    }

   public void checkValidInputInfo(){
        mRepository.get(mUser.getUserName()).observeForever(new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user!=null) {
                    returnToast(R.string.user_find);
                    mIsOk.setValue(false);
                }else if (isNumeric(mUser.getPassword())) {
                    if (mUser.getPassword().length()>= 8) {
                        if (mUser.getUserName().length() != 0 && mUser.getUserName().length() != 0) {
                            mUser = new User(mUser.getUserName(), mUser.getPassword());
                            mRepository.insert(mUser);
                            mIsOk.setValue(true);
                        } else {
                            returnToast(R.string.in_correct_input);
                        }
                    } else {
                        returnToast( R.string.in_correct_length);
                    }
                } else {
                    returnToast( R.string.in_correct_pass);
                }
            }
        });
    }

    public void returnToast(int msg) {
       Toast.makeText(LoginApplication.getContext(), msg, Toast.LENGTH_LONG).show();
    }

    private static boolean isNumeric(String strNum) {
        for (char c : strNum.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    public LiveData<Boolean> getIsOk() {
        return mIsOk;
    }
}
