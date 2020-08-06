package com.example.shopping.controller.web;

import com.example.shopping.Entity.Address;
import com.example.shopping.Entity.Msg;
import com.example.shopping.Entity.User;
import com.example.shopping.service.AddressService;
import com.example.shopping.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 顾客的登录注册功能
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;
    /**
     * 跳转到登录界面
     * @return
     */
    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }

    /**
     * 跳转到注册界面
     * @return
     */
    @GetMapping("/register")
    public String toRegister(){
        return "register";
    }


    /**
     * 登录功能
     */
    @PostMapping("/loginconfirm")
    public String loginConfirm( User user,@RequestParam("confirmlogo") String confirmlogo, Model result, HttpServletRequest request){

        //从session中获取验证码
        HttpSession session = request.getSession();
        String certCode = (String) session.getAttribute("certCode");

        //判断验证码是否正确
        if(!certCode.equalsIgnoreCase(confirmlogo)){
            result.addAttribute("errorMsg","验证码错误");
            return "login";
        }

        //判断用户密码是否正确
        User all = customerService.findAllByUsername(user);
        if(all!=null){
            //将用户信息存入session
            session.setAttribute("user",all);
            return "redirect:/main";
        }
        //用户名或密码错误
        result.addAttribute("errorMsg","用户名或密码有误");
       return "login";
    }

    /**
     * 用户退出
     */
    @RequestMapping("/logout")
    public String logOut(HttpServletRequest request){
        //从session中删除用户信息
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        //重定向到登录界面
        return "redirect:/login";
    }


    /**
     * 注册功能
     */
    @PostMapping("/registerresult")
    public String registerresult(User user,Model result){

        //判断用户是否存在
        User info=customerService.findAllByName(user.getUsername());
        if(info!=null){
            //存在此用户
            result.addAttribute("errorMsg","用户已存在");
            return "register";
        }
        //保存用户信息
        //获取当前时间使其成为注册时间
        Date date=new Date();
        user.setRegTime(date);
        customerService.saveUser(user);
        return "redirect:/login";
    }

    /**
     * 个人信息界面
     * @return
     */
    @GetMapping("/information")
    public String toInformation(HttpServletRequest request, Model result){
        //判断用户是否登录，如果没登陆则重定向到登陆界面
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/login";
        }
        //储存user信息
        result.addAttribute("user",user);
        return "information";
    }

    /**
     * 修改个人信息
     * @param
     * @return
     */
    @PostMapping("/saveInfo")
    @ResponseBody
    public Msg saveInfo(String telephone,String email,HttpServletRequest request){
        //获取该用户唯一id
        User customer = (User) request.getSession().getAttribute("user");
        Integer userId = customer.getUserId();
        try {
            customerService.updateById(telephone,email,userId);
            //更新session
            request.getSession().removeAttribute("user");
            //根据用户id查询用户信息
            User all=customerService.findById(userId);
            request.getSession().setAttribute("user",all);
            return Msg.success("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail("更新失败");
        }
    }

    /**
     *更新密码
     */
    @PostMapping("/savePsw")
    @ResponseBody
    public Msg savePsw(@RequestParam("Psw") String password,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        //获取用户id
        Integer userId = user.getUserId();
        try {
            customerService.updatePassword(userId,password);
            //更新session
            request.getSession().removeAttribute("user");
            //根据用户id查询用户信息
            User all=customerService.findById(userId);
            request.getSession().setAttribute("user",all);
            return Msg.success("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail("更新失败");
        }
    }

    /**
     *地址管理，展示所有的用户地址
     */
    @RequestMapping("/info/address")
    public String address(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        if(user==null){
            return "redirect:/login";
        }
       List<Address> addressList=addressService.findAllById(userId);
        model.addAttribute("addressList",addressList);
        return "address";
    }

    /**
     * 修改地址信息
     */
    @RequestMapping("/saveAddr")
    @ResponseBody
    public Msg saveAddr(Address address,HttpSession session){

        try {
            addressService.update(address);
            return Msg.success("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail("修改失败");
        }
    }
    /**
     * 添加用户信息
     */
    @RequestMapping("/insertAddr")
    @ResponseBody
    public Msg insertAddr(HttpSession session,Address address){
        User user = (User) session.getAttribute("user");
        address.setUserid(user.getUserId());
        try {
            addressService.insert(address);
            return Msg.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail("添加失败");
        }
    }
    /**
     * 删除地址信息
     */
    @RequestMapping("/deleteAddr")
    @ResponseBody
    public Msg deleteAddr(Address address) {
        try {
            addressService.delete(address);
            return Msg.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail("删除失败");
        }
    }
}
