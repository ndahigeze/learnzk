package com.zkproject.services;

import com.zkproject.domain.Todo;
import com.zkproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    public String create(User user);
    public void update(User user);
    public List<User> findAll();
    public User findById(int id);
    public User findByFullName(String subject);
    public User findByEmail(String email);
}
