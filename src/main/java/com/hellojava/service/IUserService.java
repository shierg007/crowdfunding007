package com.hellojava.service;

import com.hellojava.entity.User;

import java.util.List;

public interface IUserService {
    public boolean checkUser(User user);
    public User loadUser(User user);
    public String checkType(User user);
    public boolean saveUser(User user);
    public boolean checkName(User user);
    public boolean checkNickName(User user);
    public boolean apply(User user);
    public int calcMaxPage(int rows);
    public int calcMaxPageFuzzy(int rows,String keyWords);
    public List<User> loadAll(int rows,int page);
    public boolean addUser(User user);
    public User loadById(int userId);
    public boolean updateUser(User user);
    public boolean deleteUser(List<Integer> ids);
    public boolean addRole(int uid,int rid);
    public boolean removeRole(int uid,int rid);
    public List<User> fuzzyQuery(String keyWords,int page,int rows);
    public List<User> loadNotActivatedUser();
    public boolean activated(int userId);
    public boolean rejectActivated(int userId);
    public boolean activate(String nickName);
    public String loadEmailByNickName(String nickName);
//    public User loadByNickName(String nickName);

//    public int calcMaxPageFuzzyQueryUser(String keyWords,int rows);
}
