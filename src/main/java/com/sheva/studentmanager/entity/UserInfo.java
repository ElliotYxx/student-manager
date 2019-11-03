package com.sheva.studentmanager.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -5922601033147634767L;

    private String username;

    private String password;

    public UserInfo(){

    }

    public UserInfo(String username, String password){
        this.username = username;
        this.password = password;
    }
}
