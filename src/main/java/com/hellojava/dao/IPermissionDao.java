package com.hellojava.dao;

import com.hellojava.entity.Permission;

import java.util.List;
import java.util.Map;

public interface IPermissionDao {
    public List<Permission> loadAll();
    public List<Permission> loadPermissionByRoleId(int roleId);
    public int deleteAllPermission(int roleId);
    public int assignPermission(Map<String,Integer> maps);
}
