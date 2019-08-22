package com.hellojava.service.impl;

import com.github.pagehelper.PageHelper;
import com.hellojava.dao.IUserDao;
import com.hellojava.entity.User;
import com.hellojava.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Override
    public boolean checkUser(User user) {
        User u = userDao.loadUser(user);
        return u !=null;
    }

    @Override
    public User loadUser(User user) {
        return userDao.loadUser(user);
    }

    @Override
    public String checkType(User user) {
        String userType = "";
        if (checkUser(user)){
            User u = userDao.loadUser(user);
            userType = u.getUserType();
        }
        return userType;
    }

    @Override
    public boolean saveUser(User user) {
        int count = userDao.saveUser(user);
        return count>0;
    }

    @Override
    public boolean checkName(User user) {
        User u = userDao.checkName(user);
        return u != null;
    }

    @Override
    public boolean checkNickName(User user) {
        User u = userDao.checkNickName(user);
        return u != null;
    }

    //    认证补充用户信息
    @Override
    public boolean apply(User user) {
        int count = userDao.apply(user);
        return count>0;
    }

    @Override
    public int calcMaxPage(int rows) {
        int count = userDao.totalCount();
        int maxPage = count%rows==0 ? count/rows : count/rows+1;
        return maxPage;
    }

    @Override
    public int calcMaxPageFuzzy(int rows,String keyWords) {
        Map<String,String> maps = new HashMap<>();
        maps.put("nickName",keyWords);
        maps.put("email",keyWords);
        int count = userDao.fuzzyQueryUserCount(maps);
        return count%rows==0 ? count/rows :count/rows+1;
    }


    @Override
    public List<User> loadAll(int page, int rows) {
        PageHelper.startPage(page,rows);
        return userDao.loadAll();
    }

    @Override
    public boolean addUser(User user) {
        int count = userDao.addUser(user);
        return count>0;
    }

    @Override
    public User loadById(int userId) {
        return userDao.loadById(userId);
    }


    @Override
    public boolean updateUser(User user) {
        int count = userDao.updateUser(user);
        return count>0;
    }

    @Override
    public boolean deleteUser(List<Integer> ids) {
        int count = userDao.deleteUser(ids);
        return count>0;
    }

    @Override
    public boolean addRole(int uid, int rid) {
        Map<String,Integer> maps = new HashMap<>();
        maps.put("uid",uid);
        maps.put("rid",rid);
        int count = userDao.addRole(maps);
        return count>0;
    }

    @Override
    public boolean removeRole(int uid, int rid) {
        Map<String,Integer> maps = new HashMap<>();
        maps.put("uid",uid);
        maps.put("rid",rid);
        int count = userDao.removeRole(maps);
        return count>0;
    }

    @Override
    public List<User> fuzzyQuery(String keyWords,int page,int rows) {
        Map<String,String> maps = new HashMap<>();
        maps.put("nickName","%"+keyWords+"%");
        maps.put("email","%"+keyWords+"%");
        PageHelper.startPage(page,rows);
        return userDao.fuzzyQueryUser(maps);
    }

    @Override
    public List<User> loadNotActivatedUser() {
        return userDao.loadNotActivatedUser();
    }

    //通过
    @Override
    public boolean activated(int userId) {
        int count = userDao.activated(userId);
        return count > 0;
    }

    //驳回
    @Override
    public boolean rejectActivated(int userId) {
        int count = userDao.rejectActivated(userId);
        return count>0;
    }

    //未提交
    @Override
    public boolean activate(String nickName) {
        int count = userDao.activate(nickName);
        return count>0;
    }

    @Override
    public String loadEmailByNickName(String nickName) {
        return userDao.loadEmailByNickName(nickName);
    }

//    @Override
//    public User loadByNickName(String nickName) {
//        return userDao.loadByNickName(nickName);
//    }
}
