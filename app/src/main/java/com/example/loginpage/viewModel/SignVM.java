package com.example.loginpage.viewModel;

import android.app.Application;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginpage.R;
import com.example.loginpage.database.Repository.UserDBRepository;
import com.example.loginpage.model.User;
import com.google.android.material.snackbar.Snackbar;

public class SignVM extends AndroidViewModel {
    private  User mUser=new User();
    private UserDBRepository mRepository;
    private MutableLiveData<Boolean> mIsOk=new MutableLiveData<>();

    public SignVM(@NonNull Application application) {
        super(application);
        mRepository = UserDBRepository.getInstance(getApplication());
    }

    public void afterTextChangedUserName(Editable editable){
        mUser.setUserName(editable.toString());
    }

    public void afterTextChangedPassword(Editable editable){
        mUser.setPassword(editable.toString());
    }

    public void onSignBtnClickListener(){
        if (checkValidInputInfo()){
            mIsOk.setValue(true);
        }
    }

   public boolean checkValidInputInfo(){
       if (!mRepository.IsUserExist(mUser.getUserName())) {
           if (isNumeric(mUser.getPassword())) {
               if (mUser.getPassword().length()>= 8) {
                   if (mUser.getUserName().length() != 0 && mUser.getUserName().length() != 0) {
                       mUser = new User(mUser.getUserName(), mUser.getPassword());
                       mRepository.insert(mUser);
                       return true;
                   } else {
                       returnToast(R.string.in_correct_input);
                   }
               } else {
                   returnToast( R.string.in_correct_length);
               }
           } else {
               returnToast( R.string.in_correct_pass);
           }
       } else {
           returnToast( R.string.user_find);
       }
       return false;
    }

    public void returnToast(int msg) {
       Toast.makeText(getApplication(), msg, Toast.LENGTH_LONG).show();
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
