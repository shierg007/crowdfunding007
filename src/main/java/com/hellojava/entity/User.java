package com.hellojava.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private int userId;
    private String userName;
    private String userPwd;
    private String userType;
    private String email;
    private String realName;
    private String IDNumber;
    private String phoneNumber;
    private String nickName;
    private List<Role> roleList;
    private List<Role> noHaveRole;
    private int activated;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Role> getNoHaveRole() {
        return noHaveRole;
    }

    public void setNoHaveRole(List<Role> noHaveRole) {
        this.noHaveRole = noHaveRole;
    }

    public int getActivated() {
        return activated;
    }

    public void setActivated(int activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userType='" + userType + '\'' +
                ", email='" + email + '\'' +
                ", realName='" + realName + '\'' +
                ", IDNumber='" + IDNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nickName='" + nickName + '\'' +
                ", roleList=" + roleList +
                ", noHaveRole=" + noHaveRole +
                ", activated=" + activated +
                '}';
    }
}
