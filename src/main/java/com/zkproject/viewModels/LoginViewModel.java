package com.zkproject.viewModels;

import com.zkproject.domain.User;
import com.zkproject.domain.UserCredential;
import com.zkproject.services.AuthenticationService;
import com.zkproject.services.UserService;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.annotation.Command;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class LoginViewModel  extends SelectorComposer<Component> {
    private static final long serialVersionUID = 1L;
     @Wire
     Textbox email;

     @Wire
     Textbox password;

    @Wire
    Label message;

    @WireVariable
    private UserService userService;

    @WireVariable
    AuthenticationService authService;

    private User user;

    @Listen("onClick=#login; onOK=#loginWin")
    public void login() {
        String email = this.email.getText();
        String password = this.password.getText();

        if(!this.authService.login(email, password)){
            Clients.showNotification("Wrong credentials");
            return;
        }

        UserCredential cre= this.authService.getUserCredential();

        this.message.setValue("Welcome, "+cre.getName());
        Executions.sendRedirect("/index.zul");

    }


    @Listen("onClick=#logout; onOK=#logoutWin")
    public void logout() {
       this.authService.logout();
        Executions.sendRedirect("/login.zul");
    }



    public void setUser(User user) {
        this.user = user;
    }



}
