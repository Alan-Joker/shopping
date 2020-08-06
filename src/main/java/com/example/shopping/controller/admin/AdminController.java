package com.example.shopping.controller.admin;

import com.example.shopping.Entity.Admin;
import com.example.shopping.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 管理员登录与退出功能
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * 跳转到管理员登录界面
     */
    @RequestMapping("/login")
    public String toLogin(){
        return "adminLogin";
    }

    /**
     * 跳转到管理员监管界面
     */
    @RequestMapping("/show")
    public String toShowMange(){
        return "userManage";
    }

    @Autowired
    private AdminService adminService;

    /**
     * 管理员登录
     */
    @RequestMapping("/confirmLogin")
    public String confirmLogin(Admin admin, Model result, HttpServletRequest request){
        Admin all=adminService.findAll(admin);
        if(all == null){
            //无此管理员
            result.addAttribute("errorMsg","账户名或密码错误");
            return "adminLogin";
        }
        //将用户信息存入session
        HttpSession session = request.getSession();
        session.setAttribute("admin",all);
        return "redirect:/admin/user/show";
    }

    /**
     *管理员退出
     */
    @RequestMapping("/logout")
    public String logOut(HttpServletRequest request){
        //从session中删除用户信息
        request.getSession().removeAttribute("admin");
        //重定向到登录界面
        return "redirect:/admin/login";
    }
}
