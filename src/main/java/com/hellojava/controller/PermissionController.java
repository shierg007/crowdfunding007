package com.hellojava.controller;

import com.hellojava.entity.Permission;
import com.hellojava.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @ResponseBody
    @RequestMapping("loadallpermission")
    public List<Permission> loadAllPermission(){
        return permissionService.loadAll();
    }

    @ResponseBody
    @RequestMapping("currentpermission")
    public List<Permission> loadPermissionByRoleId(int roleId){
        List<Permission> currentPermission = permissionService.loadPermissionByRoleId(roleId);
        List<Permission> allPermission = permissionService.loadAll();

        for (Permission currentP:currentPermission){
            for (Permission allP:allPermission){
                if (currentP.getId()==allP.getId()){
                    allP.setChecked("true");
                    break;
                }
            }
        }
        return allPermission;
    }

    @ResponseBody
    @RequestMapping("assignpermission")
    public String assignPermission(int roleId,String pids){
        String[] pid = pids.split("-");
        boolean b = false;
        permissionService.deleteAllPermission(roleId);
        for (String s : pid) {
            b = permissionService.assignPermission(roleId,Integer.parseInt(s));
        }
        return b?"true":"false";
    }
}
