package com.zkproject.services;

import com.zkproject.domain.Todo;
import com.zkproject.domain.User;

import java.util.List;

public interface TodoService {

    public String create(Todo todo);
    public String update(Todo todo);
    public List findAll();
    public Todo findById(int id);
    public List findBySubject(String subject);
    public List<Todo> findByUser(User user);

}
