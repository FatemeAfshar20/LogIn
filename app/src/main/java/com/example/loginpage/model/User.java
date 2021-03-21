package com.example.loginpage.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.loginpage.database.Repository.UserSchema;
import com.example.loginpage.database.Repository.UserSchema.User.UserColumn;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = UserSchema.NAME)
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long mUserId;
    @ColumnInfo(name = UserColumn.UUID)
    private UUID mUserUUID;
    @ColumnInfo(name = UserColumn.USER_NAME)
    private String userName;
    @ColumnInfo(name = UserColumn.PASSWORD)
    private String password;

    public User() {
    }

    public User(String userName, String password) {
        mUserUUID=UUID.randomUUID();
        this.userName = userName;
        this.password = password;
    }

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
        this.password = password;
    }

    public long getUserId() {
        return mUserId;
    }

    public void setUserId(long userId) {
        mUserId = userId;
    }

    public UUID getUserUUID() {
        return mUserUUID;
    }

    public void setUserUUID(UUID userUUID) {
        mUserUUID = userUUID;
    }
}
