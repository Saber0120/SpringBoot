package com.saber.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@ApiModel(description = "user")
public class User {

    @ApiModelProperty(value = "主键id", hidden = true)
    @Id
    @GeneratedValue
    private int id;

    @ApiModelProperty(value = "用户名")
    @Column
    @NotNull
    private String userName;

    @ApiModelProperty(value = "密码")
    @Column
    private String password;

    public User() {
    }

    public User(@NotNull String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
