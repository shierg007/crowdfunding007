package com.hellojava.service;

import com.hellojava.entity.Permission;

import java.util.List;

public interface IPermissionService {
    public List<Permission> loadAll();
    public List<Permission> loadPermissionByRoleId(int roleId);
    public boolean deleteAllPermission(int roleId);
    public boolean assignPermission(int rid,int pid);
}
