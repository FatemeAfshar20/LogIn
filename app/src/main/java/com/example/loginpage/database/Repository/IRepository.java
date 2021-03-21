package com.example.loginpage.database.Repository;

import java.util.List;
import java.util.UUID;

public interface IRepository<E>{
    List<E> getList();
    E get(UUID id);
    void delete(E user);
    void insert(E uer);
    void update(E user);
}
