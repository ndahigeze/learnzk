package com.zkproject.viewModels;

import com.zkproject.domain.Priority;
import com.zkproject.domain.Todo;
import com.zkproject.domain.User;
import com.zkproject.domain.UserCredential;
import com.zkproject.services.AuthenticationService;
import com.zkproject.services.TodoService;
import com.zkproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.annotation.Command;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Listitem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AddTodoViewModel {

    @WireVariable
    private TodoService todoService;

    @WireVariable
    private UserService userService;

    @WireVariable
    private AuthenticationService authService;

    @Wire
    private Listitem todoItem;

    private Todo newTodo;

    private List<Priority> priorities;

    private List todos;

    private Todo selectedTodo;


    public AddTodoViewModel(){
        this.newTodo=new Todo();
        this.selectedTodo=new Todo();
    }

    @Init
    public void init(){
        UserCredential userCredential=this.authService.getUserCredential();
        User user=this.userService.findByEmail(userCredential.getEmail());
        if(user!=null){
            this.todos=this.todoService.findByUser(user);
        }else{
            this.todos=new ArrayList<Todo>();
        }

    }


    @Command
    @NotifyChange("newTodo")
    public void select(){
        this.newTodo=this.selectedTodo;
    }

    @Command
    @NotifyChange("newTodo")
    public void cancel(){
        this.newTodo=new Todo();
    }

    @Command
    @NotifyChange("newTodo")
    public void save(){
        UserCredential userCredential=this.authService.getUserCredential();
        User user=this.userService.findByEmail(userCredential.getEmail());

        if(user!=null){
            this.newTodo.setUser(user);
            String res=this.todoService.create(this.newTodo);
            this.todos=this.todoService.findByUser(user);
            Clients.showNotification(res);
        }else {
            Clients.showNotification("User Not found");
        }
    }

    public Todo getNewTodo() {
        return newTodo;
    }
    public void setNewTodo(Todo newTodo) {
        this.newTodo = newTodo;
    }

    public List<Priority> getPriorities() {
        return Arrays.asList(Priority.values());
    }

    public void setPriorities(List<Priority> priorities) {
        this.priorities = priorities;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    public Todo getSelectedTodo() {
        return selectedTodo;
    }

    public void setSelectedTodo(Todo selectedTodo) {
        this.selectedTodo = selectedTodo;
    }

}
