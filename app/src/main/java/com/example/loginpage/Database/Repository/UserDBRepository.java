package com.example.loginpage.Database.Repository;

import android.content.Context;

import androidx.room.Room;

import com.example.loginpage.Database.UserDAO;
import com.example.loginpage.Database.UserDatabase;
import com.example.loginpage.Model.User;

import java.util.List;
import java.util.UUID;

public class UserDBRepository implements IRepository<User> {
    private static UserDBRepository sInstance;
    private Context mContext;

    private UserDAO mDAO;

    public static UserDBRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new UserDBRepository(context);
        return sInstance;
    }

    private UserDBRepository(Context context) {
        mContext = context.getApplicationContext();

        UserDatabase database =
                Room.databaseBuilder(mContext,
                        UserDatabase.class,
                        UserSchema.NAME).
                        allowMainThreadQueries().
                        build();

        mDAO=database.getUserDao();
    }

    @Override
    public List<User> getList() {
        return mDAO.getList();
    }

    @Override
    public User get(UUID id) {
        return mDAO.get(id);
    }

    public User get(String username) {
        return mDAO.get(username);
    }

    @Override
    public void delete(User user) {
        mDAO.delete(user);
    }

    @Override
    public void insert(User user) {
        mDAO.insert(user);
    }

    @Override
    public void update(User user) {
        mDAO.update(user);
    }

    public boolean IsUserExist(String username){
       if (mDAO.findUser(username)!=null)
           return true;
       return false;
    }

}
