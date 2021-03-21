package com.example.loginpage.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.loginpage.database.Repository.UserSchema;
import com.example.loginpage.model.User;

@Database(entities = {User.class},version = UserSchema.VERSION)
@TypeConverters({Converter.class})
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDAO getUserDao();
}
