package com.zkproject.services;

import com.zkproject.domain.UserCredential;

public interface AuthenticationService {


    public boolean login(String account, String password);


    public void logout();


    public UserCredential getUserCredential();
}
