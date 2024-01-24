package com.zkproject.serviceImpl;

import com.zkproject.dao.TodoDao;
import com.zkproject.domain.Todo;
import com.zkproject.domain.User;
import com.zkproject.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("todoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDao todoDao;

    @Override
    public String create(Todo todo) {
        return this.todoDao.create(todo);
    }

    @Override
    public String update(Todo todo) {
      return this.todoDao.update(todo);
    }

    @Override
    public List<Todo> findAll() {
     return this.todoDao.findAll(Todo.class);
    }

    @Override
    public List<Todo> findByUser(User user) {
        return this.todoDao.findByUser(user);
    }

    @Override
    public Todo findById(int id) {
     return null;
    }

    @Override
    public List<Todo> findBySubject(String subject) {
      return this.todoDao.findAll(Todo.class);
    }
}
