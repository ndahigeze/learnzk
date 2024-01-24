package com.zkproject.serviceImpl;

import com.zkproject.domain.User;
import com.zkproject.domain.UserCredential;
import com.zkproject.services.AuthenticationService;
import com.zkproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import java.io.Serializable;

@Service("authService")
public class AuthenticationServiceImpl implements AuthenticationService, Serializable {

    @Autowired
    private UserService userService;

    @Override
    public boolean login(String email, String password) {

        User user= this.userService.findByEmail(email);

        if(user==null|| !user.getPassword().equals(password)){
          return false;
        }

        Session sess = Sessions.getCurrent();
        UserCredential cre = new UserCredential(user.getAccount(),user.getFullName(),user.getEmail());
         cre.addRole(user.getRole());
        sess.setAttribute("userCredential",cre);
        return true;
    }

    @Override
    public void logout() {
        Session sess = Sessions.getCurrent();
        sess.removeAttribute("userCredential");
    }

    @Override
    public UserCredential getUserCredential() {

        Session session= Sessions.getCurrent();

        UserCredential userCredential=(UserCredential) session.getAttribute("userCredential");

        if(userCredential==null){
            userCredential = new UserCredential();
            session.setAttribute("userCredential",userCredential);
        }

        return userCredential;
    }
}
