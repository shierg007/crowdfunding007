package com.hellojava.controller;

import com.hellojava.entity.Role;
import com.hellojava.entity.User;
import com.hellojava.service.IUserService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Random;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;
    @RequestMapping("checkUser")
    public String checkUser(User user,String userCode,Model model,HttpServletRequest req){
        boolean b = userService.checkUser(user);
        //取出生成的验证码
        String code = (String) req.getSession().getAttribute("code");
        User u = userService.loadUser(user);
        String nickName = u.getNickName();
        int activated = u.getActivated();
        model.addAttribute("nickName",nickName);
        model.addAttribute("activated",activated);
        if (b && code.equalsIgnoreCase(userCode)){
            String userType = userService.checkType(user);
            return userType.equals("admin")?"redirect:mainview":"redirect:memberview";
        }else{
            return "error";
        }
    }

    @RequestMapping("register")
    public String register(User user){
        boolean b = userService.saveUser(user);
        return b?"login":"error";
    }

    @ResponseBody
    @RequestMapping("checkName")
    public int checkName(User user){
        boolean b = userService.checkName(user);
        return b?1:0;
    }

    @ResponseBody
    @RequestMapping("checknickname")
    public int checkNickName(User user){
        boolean b = userService.checkNickName(user);
        return b?1:0;
    }

    @RequestMapping("apply")
    public String authentication(User user,Model model){
        boolean b = userService.apply(user);
        model.addAttribute("user",user);
        return b?"apply-1":"apply";
    }

    @RequestMapping("uploadhandler")
    public String uploadHandler(MultipartFile uploadFile, HttpServletRequest request,String nickName ,Model model){
        String path = request.getServletContext().getRealPath("/upload"+"/"+nickName);
        File uploadRoot = new File(path);
        if (!uploadRoot.exists()) {
            uploadRoot.mkdirs();
        }
        try {
            InputStream inputStream = uploadFile.getInputStream();
            byte[] bs = new byte[inputStream.available()];
            inputStream.read(bs, 0, bs.length);
            inputStream.close();
            OutputStream outputStream = new FileOutputStream(uploadRoot + "/" + uploadFile.getOriginalFilename());
            outputStream.write(bs, 0, bs.length);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String email = userService.loadEmailByNickName(nickName);
        model.addAttribute("email",email);
        model.addAttribute("nickName",nickName);
        return "apply-2";
    }

    @RequestMapping("userhandler")
    public String userHandler(@RequestParam(defaultValue = "1",required = false)int page,
                              @RequestParam(defaultValue = "8",required = false)int rows,
                              Model model){
        int calcMaxPage = userService.calcMaxPage(rows);
        if (page<1){
            page = calcMaxPage;
        }
        if (page>calcMaxPage){
            page = 1;
        }
        List<User> userList = userService.loadAll(page,rows);
        model.addAttribute("userList",userList);
        model.addAttribute("currentPage",page);
        model.addAttribute("calcMaxPage",calcMaxPage);
        return "user";
    }

    @RequestMapping("adduser")
    public String addUser(User user){
        boolean b = userService.addUser(user);
        return b?"redirect:userhandler":"error";
    }

    @RequestMapping("updateview")
    public String loadById(int userId,Model model){
        User u = userService.loadById(userId);
        model.addAttribute("user",u);
        return "edit";
    }

    @RequestMapping("updateuser")
    public String updateUser(User user){
        boolean b = userService.updateUser(user);
        return b?"redirect:userhandler":"error";
    }

    @RequestMapping("deleteuser")
    public String deleteUser(@RequestParam(value = "ids") List<Integer> ids){
        boolean b = userService.deleteUser(ids);
        return b?"redirect:userhandler":"error";
    }

    @RequestMapping("assignrole")
    public String assignRole(@RequestParam(value = "userId") Integer userId,
                             Model model){
        User u = userService.loadById(userId);
        List<Role> roleList = u.getRoleList();
        List<Role> noHaveRole = u.getNoHaveRole();
        model.addAttribute("currentRole",roleList);
        model.addAttribute("noHaveRole",noHaveRole);
        model.addAttribute("userId",userId);
        return "assignRole";
    }

    @RequestMapping("moverole")
    public String moveRole(@RequestParam(required = false, defaultValue = "0") int nrs,
                           @RequestParam(required = false, defaultValue = "0") int crs,
                           int uid,Model model){
        boolean b = false;
        model.addAttribute("userId",uid);
        if (nrs!=0 && uid!=0){
            b = userService.addRole(uid,nrs);
        }
        if (crs!=0 && uid!=0){
            b = userService.removeRole(uid,crs);
        }
        return b?"redirect:assignrole":"error";
    }

    @RequestMapping("fuzzyqueryuser")
    public String fuzzyQueryUser(@RequestParam(required = false,defaultValue = "1") int page,
                                 @RequestParam(required = false,defaultValue = "8") int rows,
                                 String keyWords,Model model){
        int calcMaxPage = userService.calcMaxPageFuzzy(rows,keyWords);
        if (page<1){
            page=calcMaxPage;
        }
        if (page>calcMaxPage){
            page=1;
        }
        List<User> userList = userService.fuzzyQuery(keyWords,page,rows);
        model.addAttribute("userList",userList);
        model.addAttribute("currentPage",page);
        model.addAttribute("calcMaxPage",calcMaxPage);
        return "user";
    }

    @RequestMapping("auth_cert_handler")
    public String loadNotActivatedUser(Model model){
        List<User> notActivatedUser = userService.loadNotActivatedUser();
        model.addAttribute("notActivatedUser",notActivatedUser);
        return "auth_cert";
    }

    @RequestMapping("/loadIDpicture")
    public ResponseEntity<byte[]> downLoadHandler(String nickName,HttpServletRequest request){
        File fileRoot=new File(request.getServletContext().getRealPath("/upload/"+nickName));
        String fileName = null;
        if(fileRoot.exists()){
            String[] fs=fileRoot.list();
            for (String fn:fs){
                fileName = fn;
            }
        }
        //http请求的头信息对象
        HttpHeaders headers = new HttpHeaders();
        //设置下载的文件名
        headers.setContentDispositionFormData("attachment", fileName);
        //设置下载的内容类型
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        ResponseEntity<byte[]> entity=null;
        byte[] bs=null;
        if(fileRoot.exists()){
            try {
                FileInputStream fis = new FileInputStream(fileRoot + "/" + fileName);
                bs = new byte[fis.available()];
                fis.read(bs, 0, bs.length);
                fis.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            entity=new ResponseEntity<>(bs,headers, HttpStatus.CREATED);
        }
        return entity;
    }

    //审核通过
    @RequestMapping("setactivated")
    public String setActivated(int userId){
        boolean b = userService.activated(userId);
        return b?"redirect:auth_cert_handler":"error";
    }

    @RequestMapping("rejectactivated")
    public String rejectActivated(int userId){
        boolean b = userService.rejectActivated(userId);
        return b?"redirect:auth_cert_handler":"error";
    }

    //实名信息填完返回首页
    @RequestMapping("submitactivate")
    public String submitActivate(String nickName,Model model){
        boolean b = userService.activate(nickName);
        model.addAttribute("nickName",nickName);
        model.addAttribute("activated",1);
        return b?"redirect:memberview":"error";
    }


    @RequestMapping("sendemail")
    public String sendEmail(String nickName,String emailAddress,Model model){
        String result = "apply-3";
        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName("smtp.163.com");
        htmlEmail.setAuthentication("shierg007@163.com","xGq5741963");
        htmlEmail.setSubject("实名认证验证码");
        htmlEmail.setCharset("UTF-8");
        Random r = new Random();
        String emailContext=String.valueOf(r.nextInt(999999));
        model.addAttribute("nickName",nickName);
        model.addAttribute("emailCode",(emailContext));
        try {
            htmlEmail.setFrom("shierg007@163.com","千峰众筹客服");
            htmlEmail.setHtmlMsg(emailContext);
            htmlEmail.addTo(emailAddress);
            htmlEmail.send();
        } catch (EmailException e) {
            result = "error";
            e.printStackTrace();
        }
        return result;
    }

    //查验账户实名认证状态，
    // 0：未填写实名信息
    // 1：填写实名信息未审核
    // 2：已审核
//    @ResponseBody
//    @RequestMapping("checkact")
//    public int checkAct(String nickName){
//        User u = userService.loadByNickName(nickName);
//        return u.getActivated();
//    }

}
