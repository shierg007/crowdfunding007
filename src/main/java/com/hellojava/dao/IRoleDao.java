package com.hellojava.dao;

import com.hellojava.entity.Role;

import java.util.List;

public interface IRoleDao {
    public List<Role> loadAll();
    public int totalCount();
    public int addRole(Role role);
    public int maxId();
    public Role loadByRoleId(int roleId);
    public int updateRole(Role role);
    public int deleteRole(List<Integer> ids);
    public List<Role> fuzzyQueryRole(String roleName);
    public int maxCountFuzzy(String roleName);
}
