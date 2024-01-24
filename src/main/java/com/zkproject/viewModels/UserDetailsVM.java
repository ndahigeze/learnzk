package com.zkproject.viewModels;

import com.zkproject.domain.Todo;
import com.zkproject.domain.User;
import com.zkproject.services.TodoService;
import com.zkproject.services.UserService;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.annotation.Command;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zuti.zul.Apply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UserDetailsVM {

    @WireVariable
    private UserService userService;
    private User user;

    @WireVariable
    private TodoService todoService;

    private List<Todo> userTodos = new ArrayList<>();

    private Todo selectedTodo;

    private List<Todo> todoList = new ArrayList<>();

    @Init
    public void init(@ExecutionParam("user") final User user){
        this.user=user;
        this.userTodos=this.todoService.findByUser(user);
        this.todoList= this.todoService.findAll();
        this.filterTodos();
    }

    @Command
    @NotifyChange({"userTodos","todoList","selectedTodo"})
    public void addTodo(){
        this.selectedTodo.setUser(this.user);
        this.todoService.update(this.selectedTodo);
        this.userTodos=this.todoService.findByUser(user);
        this.selectedTodo=null;
        this.filterTodos();
    }

    public void filterTodos(){
        List<Todo> todos = new ArrayList<>();
        for(Todo todo:this.todoList){
           if(!this.userTodos.contains(todo)){
               todos.add(todo);
           }
        }
        this.todoList=todos;
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TodoService getTodoService() {
        return todoService;
    }

    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }
    public Todo getSelectedTodo() {
        return selectedTodo;
    }

    public void setSelectedTodo(Todo selectedTodo) {
        this.selectedTodo = selectedTodo;
    }

    public List<Todo> getUserTodos() {
        return userTodos;
    }

    public void setUserTodos(List<Todo> userTodos) {
        this.userTodos = userTodos;
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }
}
