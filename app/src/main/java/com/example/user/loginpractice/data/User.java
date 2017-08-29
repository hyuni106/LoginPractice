package com.example.user.loginpractice.data;

/**
 * Created by user on 2017-08-29.
 */

public class User {
    String id;
    String password;
    String name;
    String profileURL;

    public User() {
    }

    public User(String id, String password, String name, String profileURL) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.profileURL = profileURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String name) {
        this.id = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
