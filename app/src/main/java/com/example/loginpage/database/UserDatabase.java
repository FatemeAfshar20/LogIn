package com.example.loginpage.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.loginpage.LoginApplication;
import com.example.loginpage.database.Repository.UserSchema;
import com.example.loginpage.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = UserSchema.VERSION)
@TypeConverters({Converter.class})
public abstract class UserDatabase extends RoomDatabase {
    private static volatile UserDatabase sInstance;
    public ExecutorService executorDatabase = Executors.newFixedThreadPool(3);

    public abstract UserDAO getUserDao();

    public static UserDatabase getInstance() {
        if (sInstance == null) {
            synchronized (UserDatabase.class) {
                sInstance = Room.databaseBuilder(
                        LoginApplication.getContext(),
                        UserDatabase.class,
                        "userDatabase.db").
                        build();
                return sInstance;
            }
        }
        return sInstance;
    }
}
