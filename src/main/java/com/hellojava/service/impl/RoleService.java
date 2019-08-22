package com.hellojava.service.impl;

import com.github.pagehelper.PageHelper;
import com.hellojava.dao.IRoleDao;
import com.hellojava.entity.Role;
import com.hellojava.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> loadAll(int page,int rows) {
        PageHelper.startPage(page,rows);
        return roleDao.loadAll();
    }

    @Override
    public int calcMaxPage(int rows) {
        int count = roleDao.totalCount();
        return count%rows==0?count/rows:count/rows+1;
    }

    @Override
    public boolean addRole(Role role) {
        int maxId = roleDao.maxId();
        role.setRoleId(maxId+1);
        int count = roleDao.addRole(role);
        return count>0;
    }

    @Override
    public Role loadByRoleId(int roleId) {
        return roleDao.loadByRoleId(roleId);
    }

    @Override
    public boolean updateRole(Role role) {
        int count = roleDao.updateRole(role);
        return count>0;
    }

    @Override
    public boolean deleteRole(List<Integer> ids) {
        int count = roleDao.deleteRole(ids);
        return count>0;
    }

    @Override
    public List<Role> fuzzyQueryRole(String keyWords,int page,int rows) {
        String roleName = "%"+keyWords+"%";
        PageHelper.startPage(page,rows);
        return roleDao.fuzzyQueryRole(roleName);
    }

    @Override
    public int calcMaxPageFuzzy(int rows, String keyWords) {
        int count = roleDao.maxCountFuzzy(keyWords);
        return count%rows==0 ? count/rows :count/rows+1;
    }
}
