package com.zkproject.services;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SidebarPage implements Serializable{
    private static final long serialVersionUID = 1L;
    String name;
    String label;
    String iconUri;
    String uri;
    Set<String> roles = new HashSet<String>();


    public SidebarPage(String name, String label, String iconUri, String uri, Set<String> roles) {
        this.name = name;
        this.label = label;
        this.iconUri = iconUri;
        this.uri = uri;
        this.roles = roles;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public String getIconUri() {
        return iconUri;
    }

    public String getUri() {
        return uri;
    }

}