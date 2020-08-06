package com.example.shopping.controller.web;

import com.example.shopping.Entity.Goods;
import com.example.shopping.Entity.Msg;
import com.example.shopping.Entity.Shopcart;
import com.example.shopping.Entity.User;
import com.example.shopping.service.GoodsService;
import com.example.shopping.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ShopCarController {

    @Autowired
    private ShopCartService shopCartService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 跳转到购物车界面
     */
    @RequestMapping("/showcart")
    public String showcart(HttpSession session){
        //用户没登陆则重定向到登陆界面
        User user = (User) session.getAttribute("user");
        if(user==null){
            return "redirect:/login";
        }
        return "shopcart";
    }


    /**
     * 添加商品至购物车
     * @return
     */
    @RequestMapping("/addCart")
    public String addcart(Shopcart shopcart,HttpSession session){
        //用户没登陆重定向到登陆界面
        User user= (User) session.getAttribute("user");
        if(user==null){
            return "redirect:/login";
        }
        Integer userId = user.getUserId();
        //判断商品是否加入购物车
        Shopcart car=shopCartService.findById(shopcart.getGoodsid());
        if(car!=null){
            //已加入购物车
            return "redirect:/showcart";
        }

        //没加入购物车,将商品加入购物车
        //用户
        shopcart.setUserid(userId);
        //商品
        shopcart.setGoodsid(shopcart.getGoodsid());
        //时间
        shopcart.setCatedate(new Date());
        //数量
        shopcart.setGoodsnum(shopcart.getGoodsnum());
        shopCartService.inset(shopcart);
        //返回购物车界面
        return "redirect:/showcart";
    }

    /**
     * 显示购物车中的信息
     */
    @RequestMapping("/cartjson")
    @ResponseBody
    public Msg cartjson(HttpSession session) {
        User user = (User) session.getAttribute("user");

        if(user == null) {
            return Msg.fail("请先登录");
        }
        Integer userId = user.getUserId();

        //获取商品的信息
        List<Shopcart> list=shopCartService.findByUserId(userId);
        //储存每一个商品信息
        List<Goods> goodsList=new ArrayList<Goods>();
        if(! list.isEmpty()){
            for (int i = 0; i < list.size(); i++) {
                Shopcart shopcart = list.get(i);
                Goods allById = goodsService.findAllById(shopcart.getGoodsid());
                //添加图片路径
                List<String> imagePath = goodsService.findImagePath(shopcart.getGoodsid());
                allById.setImagePaths(imagePath);
                allById.setNum(shopcart.getGoodsnum());
                goodsList.add(allById);
            }
        }
        return Msg.success("查询成功").add("shopcart",goodsList);
    }


    /**
     * 删除购物车信息
     */
    @DeleteMapping("/deleteCart/{goodsId}")
    @ResponseBody
    public Msg deleteCart(@PathVariable("goodsId") Integer goodsid,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user==null){
            return Msg.fail("请先登录");
        }
        Integer userId = user.getUserId();
        try {
            shopCartService.deleteById(goodsid,userId);
            return Msg.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.success("删除失败");
        }
    }

    /**
     * 更新购物车信息
     */
    @RequestMapping("/update")
    @ResponseBody
    public Msg updateCart(Integer goodsid,Integer num,HttpSession session){
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        if(user==null){
            return Msg.fail("请先登录");
        }
        Shopcart shopcart=new Shopcart();
        shopcart.setGoodsid(goodsid);
        shopcart.setUserid(userId);
        shopcart.setGoodsnum(num);
        shopcart.setCatedate(new Date());
        shopCartService.update(shopcart);
        return Msg.success("更新购物车成功");
    }
}
