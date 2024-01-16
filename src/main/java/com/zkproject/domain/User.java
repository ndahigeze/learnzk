package com.zkproject.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

@Entity()
@Table(name = "user")
public class User implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(nullable = false, unique = true)
    String account;

    @Column(nullable = false)
    String fullName;

    @Column(nullable = false)
    String password = String.valueOf(Math.random());

    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false)
    Date birthday;

    @Column(nullable = false)
    String country;

    @Column(nullable = true,length = 1000)
    String bio;

    public User(){}
    public User(String account, String fullName, String password, String email, Date birthday, String country, String bio){
        this.account = account;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.country = country;
        this.bio = bio;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", country='" + country + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
