package com.hellojava.service.impl;

import com.hellojava.dao.IPermissionDao;
import com.hellojava.entity.Permission;
import com.hellojava.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> loadAll() {
        return permissionDao.loadAll();
    }

    @Override
    public List<Permission> loadPermissionByRoleId(int roleId) {
        return permissionDao.loadPermissionByRoleId(roleId);
    }

    @Override
    public boolean deleteAllPermission(int roleId) {
        int count = permissionDao.deleteAllPermission(roleId);
        return count>0;
    }

    @Override
    public boolean assignPermission(int rid, int pid) {
        Map<String,Integer> maps = new HashMap<>();
        maps.put("rid",rid);
        maps.put("pid",pid);
        int count = permissionDao.assignPermission(maps);
        return count>0;
    }
}
