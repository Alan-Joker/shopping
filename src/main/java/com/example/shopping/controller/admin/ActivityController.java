package com.example.shopping.controller.admin;

import com.example.shopping.Entity.Activity;
import com.example.shopping.Entity.Admin;
import com.example.shopping.Entity.Msg;
import com.example.shopping.Entity.User;
import com.example.shopping.service.ActivityService;
import com.example.shopping.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private GoodsService goodsService;
    /**
     * 展示所有的活动信息
     * @return
     */
    @RequestMapping("/show")
    public String show(@RequestParam(value = "/page",defaultValue = "1")Integer page, Model model, HttpSession session){
        //用户没登陆重定向到登录界面
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin==null){
            return "redirect:/admin/login";
        }
        //一页显示十条记录
        PageHelper.startPage(page,10);
        List<Activity> activityList = activityService.findAll();
        //显示5页
        PageInfo info=new PageInfo(activityList,5);
       model.addAttribute("pageInfo",info);
        return "activity";
    }

    /**
     * 商品添加活动
     * @param session
     * @return
     */
    @RequestMapping("/showjson")
    @ResponseBody
    public Msg showjson(HttpSession session){
        //用户没登陆重定向到登录界面
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin==null){
            return Msg.fail("请先登录");
        }
        List<Activity> activityList = activityService.findAll();
        return Msg.success("获取活动信息成功").add("activity",activityList);

    }

    /**
     * 添加活动
     */
    @RequestMapping("/add")
    public String add(HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin==null){
            return "redirect:/admin/login";
        }
        return "addActivity";
    }
    @RequestMapping("/addResult")
    public String addResult(Activity activity){
        try {
            activityService.insert(activity);
            return "redirect:/admin/activity/show";
        } catch (Exception e) {
            e.printStackTrace();
            return "addActivity";
        }
    }

    /**
     * 删除活动
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam("activityid")Integer aid){
        activityService.delete(aid);
        return "redirect:/admin/activity/show";
    }

    /**
     * 更新商品活动
     */
    @RequestMapping("/update")
    @ResponseBody
    public Msg update(Integer goodsid,Integer activityid,HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin==null){
            return Msg.fail("请先登录");
        }
        goodsService.updateAid(goodsid,activityid);
        return Msg.success("更新商品成功");
    }
}
