package com.example.loginpage.database.Repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.loginpage.database.UserDAO;
import com.example.loginpage.database.UserDatabase;
import com.example.loginpage.model.User;

import java.util.List;
import java.util.UUID;

public class UserDBRepository implements IRepository<User> {
    private UserDAO mDAO;
    private UserDatabase mDatabase;

    public UserDBRepository() {
        mDatabase =UserDatabase.getInstance();
        mDAO=mDatabase.getUserDao();
    }

    @Override
    public LiveData<List<User>> getList() {
        return mDAO.getList();
    }

    @Override
    public LiveData<User> get(UUID id) {
        return mDAO.get(id);
    }

    @Override
    public LiveData<User> get(String username) {
        return mDAO.get(username);
    }

    @Override
    public void delete(final User user) {
        mDatabase.executorDatabase.execute(()->mDAO.delete(user));
    }

    @Override
    public void insert(User user) {
        mDatabase.executorDatabase.execute(()->mDAO.insert(user));
    }

    @Override
    public void update(User user) {
        mDatabase.executorDatabase.execute(()->mDAO.update(user));
    }
}
