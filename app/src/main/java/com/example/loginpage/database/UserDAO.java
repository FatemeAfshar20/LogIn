package com.example.loginpage.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.loginpage.model.User;

import java.util.List;
import java.util.UUID;

@Dao
public interface UserDAO {
    @Query(value = "SELECT * FROM `user.db`")
    List<User> getList();
    @Query(value = "SELECT * FROM `user.db` WHERE uuid=:id")
    User get(UUID id);
    @Query(value = "SELECT * FROM `user.db` WHERE username=:username")
    User get(String username);
    @Delete
    void delete(User user);
    @Insert
    void insert(User uer);
    @Update
    void update(User user);
    @Query(value = "SELECT * FROM `user.db` WHERE username=:username")
    User findUser(String username);
}
