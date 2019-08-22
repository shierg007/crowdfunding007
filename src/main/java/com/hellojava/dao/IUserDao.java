package com.hellojava.dao;

import com.hellojava.entity.User;

import java.util.List;
import java.util.Map;

public interface IUserDao {
   public User loadUser(User user);
   public int saveUser(User user);
   public User checkName(User user);
   public User checkNickName(User user);
   //补全用户其他信息
   public int apply(User user);
   public int totalCount();
   public List<User> loadAll();
   public int addUser(User user);
   public User loadById(int userId);
   public int updateUser(User user);
   public int deleteUser(List<Integer> ids);
   public int addRole(Map<String,Integer> maps);
   public int removeRole(Map<String,Integer> maps);
   public List<User> fuzzyQueryUser(Map<String,String> maps);
   //模糊查询后的count
   public int fuzzyQueryUserCount(Map<String,String> maps);
   public List<User> loadNotActivatedUser();
   //通过人工审核
   public int activated(int userId);
   //驳回实名认证
   public int rejectActivated(int userId);
   //实名认证信息提交成功
   public int activate(String nickName);

   public String loadEmailByNickName(String nickName);
//   public User loadByNickName(String nickName);


//   public int fuzzyQueryUserTotalCount(Map<String,String> maps);
}
