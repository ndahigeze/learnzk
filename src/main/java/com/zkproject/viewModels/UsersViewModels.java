package com.zkproject.viewModels;

import com.zkproject.domain.Todo;
import com.zkproject.domain.User;
import com.zkproject.services.TodoService;
import com.zkproject.services.UserService;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.annotation.Command;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import org.zkoss.zuti.zul.Apply;

import java.util.ArrayList;
import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UsersViewModels {

    @WireVariable
    private UserService userService;
    private User user;
    private List<User> users;

    private User selectedUser;

    @WireVariable
    private TodoService todoService;

    private List<Todo> todos=new ArrayList<>();

    private String keyword;

    private List<Todo> allTodos;

    private Todo selectedTodo;

    @Init
    public  void init(){
        this.users=userService.findAll();
        this.allTodos=this.todoService.findAll();
    }

    @Command("search")
    @NotifyChange("users")
    public void search(){
        this.users = this.userService.findAll();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public User getSelectedUser() {
        return selectedUser;
    }



    public void applyUserPage(String uri) {

        Apply apply = (Apply) Selectors.find("::shadow#content")
                .iterator().next();
        Execution execution= Executions.getCurrent();
        execution.setAttribute("user",this.selectedUser);
        apply.setTemplate(null);
        apply.setTemplateURI(uri);
        apply.recreate();
    }

    @NotifyChange({"todos","selectedUser"})
    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
        this.setTodos(this.todoService.findByUser(this.selectedUser));
        Session sess = Sessions.getCurrent();
        sess.setAttribute("selectedUser", this.selectedTodo);
        this.applyUserPage("/user-details.zul");

    }


    public List getTodos() {
        return this.todos;
    }


    public void setTodos(List todos) {
        this.todos = todos;
    }

    public List<Todo> getAllTodos() {
        return allTodos;
    }

    public void setAllTodos(List<Todo> allTodos) {
        this.allTodos = allTodos;
    }

    public Todo getSelectedTodo() {
        return selectedTodo;
    }
    public void setSelectedTodo(Todo selectedTodo) {
        this.selectedTodo = selectedTodo;
    }

}
