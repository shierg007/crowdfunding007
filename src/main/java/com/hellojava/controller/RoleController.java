package com.hellojava.controller;


import com.hellojava.entity.Role;
import com.hellojava.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("rolehandler")
    public String roleHandler(@RequestParam(required = false,defaultValue = "1")int page,
                              @RequestParam(required = false,defaultValue = "9")int rows,
                              Model model){
        int maxPage = roleService.calcMaxPage(rows);
        if (page<1){
            page = maxPage;
        }
        if (page>maxPage){
            page = 1;
        }
        List<Role> roleList = roleService.loadAll(page,rows);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("roleList",roleList);
        return "role";
    }

    @RequestMapping("addrole")
    public String addRole(Role role){
        boolean b = roleService.addRole(role);
        return b?"redirect:rolehandler":"error";
    }

    @RequestMapping("loadbyroleId")
    public String loadByRoleId(int roleId,Model model){
        Role role = roleService.loadByRoleId(roleId);
        model.addAttribute("role",role);
        return "updaterole";
    }
    @RequestMapping("updaterole")
    public String updateRole(Role role){
        boolean b = roleService.updateRole(role);
        return b?"redirect:rolehandler":"error";
    }

    @RequestMapping("deleterole")
    public String deleteRole(@RequestParam(value = "ids") List<Integer> ids){
        boolean b = roleService.deleteRole(ids);
        return b?"redirect:rolehandler":"error";
    }

    @RequestMapping("fuzzyqueryrole")
    public String fuzzyQueryRole(@RequestParam(required = false,defaultValue = "1") int page,
                                 @RequestParam(required = false,defaultValue = "8") int rows,
                                 String keyWords,Model model){
        int calcMaxPage = roleService.calcMaxPageFuzzy(rows,keyWords);
        if (page<1){
            page=calcMaxPage;
        }
        if (page>calcMaxPage){
            page=1;
        }
        List<Role> roleList = roleService.fuzzyQueryRole(keyWords,page,rows);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",calcMaxPage);
        model.addAttribute("roleList",roleList);
        return "role";
    }

}
