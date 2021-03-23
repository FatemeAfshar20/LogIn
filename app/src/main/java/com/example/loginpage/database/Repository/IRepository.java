package com.example.loginpage.database.Repository;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.UUID;

public interface IRepository<E>{
    LiveData<List<E>> getList();
    LiveData<E> get(UUID id);
    LiveData<E> get(String username);
    void delete(E user);
    void insert(E uer);
    void update(E user);
}
