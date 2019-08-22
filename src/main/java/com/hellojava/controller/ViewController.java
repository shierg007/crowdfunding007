package com.hellojava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class ViewController {

    @RequestMapping("loginview")
    public String loginView(){
        return "login";
    }

    @RequestMapping("regview")
    public String regView(){
        return "reg";
    }

    @RequestMapping("accttypeview")
    public String acctTypeView(String nickName, Model model){
        model.addAttribute("nickName",nickName);
        return "accttype";
    }

    @RequestMapping("applyview")
    public String applyView(String nickName,Model model){
        model.addAttribute("nickName",nickName);
        return "apply";
    }

//    @RequestMapping("applyview3")
//    public String applyView3(String nickName,Model model){
//        model.addAttribute("nickName",nickName);
//        return "apply-3";
//    }

    @RequestMapping("addview")
    public String addView(){
        return "add";
    }

    @RequestMapping("addroleview")
    public String addRoleView(){
        return "addrole";
    }


    @RequestMapping("updateroleview")
    public String updateRoleView(){
        return "updaterole";
    }

    @RequestMapping("permissionview")
    public String permissionView(int roleId,Model model){
        model.addAttribute("roleId",roleId);
        return "assignPermission";
    }

    @RequestMapping("permissionhandler")
    public String permissionHandler(){
        return "permission";
    }

    @RequestMapping("indexview")
    public String index(){
        return "index";
    }

    @RequestMapping("projectview")
    public String project(){
        return "project";
    }

    @RequestMapping("memberview")
    public String memberView(@RequestParam(value = "nickName") String nickName,
                             @RequestParam(value = "activated") int activated,
                             Model model){
        model.addAttribute("nickName",nickName);
        model.addAttribute("activated",activated);
        return "member";
    }

    @RequestMapping("getCode")
    public void createCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int width=150,height=30;
        BufferedImage b = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = b.getGraphics();
        Random r = new Random();
        g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
        g.fillRect(0,0,width,height);
        for (int i = 0; i <10 ; i++) {
            g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
            g.drawLine(r.nextInt(width),r.nextInt(height),r.nextInt(width),r.nextInt(height));
        }
        String base="abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String code = "";
        while(code.length()<4){
            code+=base.charAt(r.nextInt(base.length()))+"";
        }
        //放入生成的验证码(无空格)
        req.getSession().setAttribute("code",code);
        String displayCode = "";
        char[] chars = code.toCharArray();
        for (int i = 0; i <chars.length ; i++) {
            displayCode+=(i==chars.length-1)?chars[i]:chars[i]+" ";
        }
        g.setFont(new Font("宋体",Font.BOLD,26));
        g.drawString(displayCode,10,25);
        //tomcat根目录下的temp文件夹必须支持写入
        ImageIO.write(b,"JPEG",resp.getOutputStream());
    }

    @RequestMapping("mainview")
    public String mainView(){
        return "main";
    }
}
