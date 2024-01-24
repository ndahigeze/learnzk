package com.zkproject.viewModels;

import com.zkproject.domain.User;
import com.zkproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.annotation.Command;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

import java.util.ArrayList;
import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AddUserViewModel {

    @WireVariable
    private UserService userService;

    private User newUser;
    private List<String> countries;

    private String confirmPassword;



    public AddUserViewModel(){
        this.newUser = new User();
        if(this.newUser==null){
            return;
        }
    }

    @Command
    @NotifyChange("newUser")
    public void save(){
         if(this.confirmPassword.equals(this.newUser.getPassword())){
             String resutl=this.userService.create(this.newUser);
             Clients.showNotification(resutl);
             Executions.getCurrent().sendRedirect("login.zul");
         }else {
             Clients.showNotification("Password does not match");
         }
    }




    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public List<String> getCountries() {
        return new ArrayList<String>(){{
            add("Rwanda");
            add("Kenya");
            add("Burundi");
            add("Mukamba");
        }};
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}
