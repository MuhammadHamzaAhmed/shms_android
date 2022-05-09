package com.shms.shms_android.model;

import java.io.Serializable;

public class User implements Serializable {

    private final String userName;
    private final String email;
    private final String profile;
    private String password;

    public User(String userName, String email, String profile, String password) {
        this.userName = userName;
        this.email = email;
        this.profile = profile;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getProfile() {
        return profile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
