package com.hellojava.service;

import com.hellojava.entity.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> loadAll(int page,int rows);
    public int calcMaxPage(int rows);
    public boolean addRole(Role role);
    public Role loadByRoleId(int roleId);
    public boolean updateRole(Role role);
    public boolean deleteRole(List<Integer> ids);
    public List<Role> fuzzyQueryRole(String keyWords,int page,int rows);
    public int calcMaxPageFuzzy(int rows,String keyWords);
}
