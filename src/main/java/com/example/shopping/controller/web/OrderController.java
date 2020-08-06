package com.example.shopping.controller.web;

import com.example.shopping.Entity.*;
import com.example.shopping.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private ShopCartService shopCartService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private OrderService orderService;
    /**
     * 显示订单详情
     * @return
     */
    @RequestMapping("/order")
    public String order(HttpSession session, Model model){
        //查询当前用户的收货地址
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        List<Address> addressList = addressService.findAllById(userId);
        model.addAttribute("address",addressList);

        //从购物车中获取信息
        List<Shopcart> car = shopCartService.findByUserId(userId);

        Float totalPrice = new Float(0);
        Integer oldTotalPrice = 0;

        //存储商品信息
        List<Goods> goodsList=new ArrayList<Goods>();
        if(!car.isEmpty()){
            for (int i = 0; i < car.size(); i++) {
                //获取每一个商品id
                Shopcart shopcart = car.get(i);
                Goods goods = goodsService.findAllById(shopcart.getGoodsid());
                //获取图片地址
                List<String> imagePath = goodsService.findImagePath(shopcart.getGoodsid());
                //封装商品数量及图片地址
                goods.setImagePaths(imagePath);
                goods.setNum(shopcart.getGoodsnum());

                //活动信息
                Activity activity = activityService.findById(goods.getActivityid());
                goods.setActivity(activity);

                if(activity.getDiscount() != 1) {
                    goods.setNewPrice(goods.getPrice()*goods.getNum()* activity.getDiscount());
                } else if(activity.getFullnum() != null) {
                    if (goods.getNum() >= activity.getFullnum()) {
                        goods.setNewPrice((float) (goods.getPrice()*(goods.getNum()-activity.getReducenum())));
                    } else {
                        goods.setNewPrice((float) (goods.getPrice()*goods.getNum()));
                    }
                } else {
                    goods.setNewPrice((float) (goods.getPrice()*goods.getNum()));
                }
                totalPrice = totalPrice + goods.getNewPrice();
                oldTotalPrice = oldTotalPrice + goods.getNum() * goods.getPrice();
                goodsList.add(goods);
            }
        }
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("oldTotalPrice", oldTotalPrice);
        model.addAttribute("goodsAndImage", goodsList);
        return "orderConfirm";
    }

    /**
     * 订单管理
     * @return
     */
    @RequestMapping("/info/list")
    public String list(HttpSession session,Model model){
        //用户没登陆则重定向到登录界面
        User user = (User) session.getAttribute("user");
        if(user==null){
            return "redirect:/login";
        }
        List<Order> orderList=orderService.findAll(user.getUserId());
        model.addAttribute("orderList",orderList);
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            List<Orderitem> orderitems=orderService.findAllByOid(order.getOrderid());
            //商品id
            List<Integer> goodsid=new ArrayList<>();
            //查询每个商品的id
            for (int i1 = 0; i1 < orderitems.size(); i1++) {
                Orderitem orderitem = orderitems.get(i1);
                goodsid.add(orderitem.getGoodsid());
            }
           List<Goods> goodsList= goodsService.findAllByList(goodsid);
            order.setGoodsInfo(goodsList);
            Address address=addressService.findAllByAid(order.getAddressid());
            order.setAddress(address);
            orderList.set(i,order);
        }
        model.addAttribute("orderList",orderList);
        return "list";
    }

    /**
     * 订单结算
     */
    @RequestMapping("/orderFinish")
    @ResponseBody
    public Msg orderFinish(Float oldPrice, Float newPrice, Boolean isPay, Integer addressid,HttpSession session){
        User user = (User) session.getAttribute("user");
        //获取购物车信息
        List<Shopcart> byUserId = shopCartService.findByUserId(user.getUserId());
        //删除购物车中的信息
        for (int i = 0; i < byUserId.size(); i++) {
            Shopcart shopcart = byUserId.get(i);
            shopCartService.deleteById(shopcart.getGoodsid(),shopcart.getUserid());
        }
        //把订单信息写入数据库
        Order order = new Order(null, user.getUserId(), new Date(), oldPrice, newPrice, isPay, false, false, false, addressid,null,null);
        orderService.insert(order);
        //获取插入的订单id
        Integer orderid = order.getOrderid();
        //把订单项写入orderitem表中
        for (Shopcart shopcart : byUserId) {
            orderService.insertOrderItem(new Orderitem(null, orderid, shopcart.getGoodsid(), shopcart.getGoodsnum()));
        }
        return Msg.success("购买成功");
    }

    /**
     * 用户完成订单
     */
    @RequestMapping("/finishList")
    @ResponseBody
    public Msg finishiList(Integer orderid){
        Order order=orderService.findById(orderid);
        order.setIsreceive(true);
        order.setIscomplete(true);
        orderService.updateOrderByKey(order);
        return Msg.success("完成订单成功");
    }

    /**
     * 删除订单
     * @param order
     * @return
     */
    @RequestMapping("/deleteList")
    @ResponseBody
    public Msg deleteList(Order order){
        orderService.deleteById(order.getOrderid());
        return Msg.success("删除成功");
    }

}
