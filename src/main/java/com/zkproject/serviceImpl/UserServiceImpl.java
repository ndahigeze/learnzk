package com.zkproject.serviceImpl;

import com.zkproject.dao.UserDao;
import com.zkproject.domain.Todo;
import com.zkproject.domain.User;
import com.zkproject.services.TodoService;
import com.zkproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String create(User user) {
      return userDao.create(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void update(User user) {
       userDao.update(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll(User.class);
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByFullName(String subject) {
        return null;
    }
}
