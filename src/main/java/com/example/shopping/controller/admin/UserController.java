package com.example.shopping.controller.admin;

import com.example.shopping.Entity.Msg;
import com.example.shopping.Entity.User;
import com.example.shopping.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理用户信息
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    /**
     * 跳转到用户管理页
     * @return
     */
    @RequestMapping("/show")
    public String userManage() {
        return "userManage";
    }

    @Autowired
    private UserService userService;
    /**
     * 用户管理，显示所有用户
     */
    @GetMapping("/showjson")
    @ResponseBody
    public Msg showjson(@RequestParam("page") Integer page){
        //每页显示10条数据
        PageHelper.startPage(page,10);
        List<User> all = userService.findAll();
        //显示有几个页号
        PageInfo info=new PageInfo(all,5);
        return Msg.success("查询成功").add("pageInfo",info);
    }

    /**
     * 删除用户信息
     */
    @DeleteMapping("/delete/{userId}")
    @ResponseBody
    public Msg delete(@PathVariable("userId")Integer userId){
        try {
            userService.deleteById(userId);
            return Msg.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail("删除失败");
        }
    }
}
