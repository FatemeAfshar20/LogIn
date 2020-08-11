package com.example.loginpage.Model;

import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.loginpage.Controller.SignInActivity;
import com.example.loginpage.R;

import java.util.Objects;

public class UserInfo {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(isNumeric(password))
            this.password = password;
        else
            this.password="";
    }

    private static boolean isNumeric(String strNum) {
        for (char c : strNum.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(userName, userInfo.userName) &&
                Objects.equals(password, userInfo.password);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }
}
