package com.saber.entity;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 5694326214674233661L;

    private Long id;

    private String username;

    private String password;

    private String salt;

    private Integer state;

    private String description;

    public String getCredentialsSalt() {
        return this.username + this.salt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
