package com.dyy.dto;

import java.io.Serializable;

/**
 * 用户信息
 */
public class UserInfoRsp implements Serializable {
    private Integer id;
    private String userName;
    private String password;
    private Integer roleId ;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}