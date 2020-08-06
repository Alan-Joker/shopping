package com.example.shopping.controller.admin;

import com.example.shopping.Entity.*;
import com.example.shopping.service.GoodsService;
import com.example.shopping.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Component
@RequestMapping("/admin/order")
public class OrderAdminController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;
    /**
     *查询用户的订单
     * @return
     */
    @RequestMapping("/send")
    public String send(Model model, @RequestParam(value = "page",defaultValue = "1")Integer page, HttpSession session){

        Admin admin = (Admin) session.getAttribute("admin");
        if(admin==null){
            return "redirect:/admin/login";
        }
        //每页显示两条数据
        PageHelper.startPage(page,2);
        //查询未发货订单
        List<Order> orderList=orderService.findAllByAdmin();
        model.addAttribute("sendOrder", orderList);
        //查询订单中的商品
        List<Integer> goodsid=new ArrayList<>();
        List<Goods> goodsList=new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            List<Orderitem> allByOid = orderService.findAllByOid(order.getOrderid());
            for (int i1 = 0; i1 < allByOid.size(); i1++) {
                Orderitem orderitem = allByOid.get(i1);
                Goods goods = goodsService.findAllById(orderitem.getGoodsid());
                goods.setNum(orderitem.getNum());
                goodsList.add(goods);
            }
            order.setGoodsInfo(goodsList);
            //查询地址
            Address address=orderService.findAddress(order.getAddressid());
            order.setAddress(address);
            orderList.set(i,order);
        }
        PageInfo info = new PageInfo(orderList,5);
        model.addAttribute("pageInfo", info);

        return "adminAllOrder";
    }
    /**
     * 发货
     */
    @RequestMapping("/sendGoods")
    public String sendGoods(Integer orderid,HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        Order order = new Order();
        order.setOrderid(orderid);
        order.setIssend(true);
        orderService.updateOrder(order);
        return "redirect:/admin/order/send";
    }

    /**
     * 查询未收货的订单
     */
    @RequestMapping("/receiver")
    public String receiver(@RequestParam(value = "page",defaultValue = "1")Integer page, HttpSession session,Model model){
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin==null){
            return "redirect:/admin/login";
        }
        //每页显示两条数据
        PageHelper.startPage(page,2);
        //查询未收货订单
        List<Order> orderList=orderService.findAllByAdminNore();
        model.addAttribute("sendOrder", orderList);
        //查询订单中的商品
        List<Integer> goodsid=new ArrayList<>();
        List<Goods> goodsList=new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            List<Orderitem> allByOid = orderService.findAllByOid(order.getOrderid());
            for (int i1 = 0; i1 < allByOid.size(); i1++) {
                Orderitem orderitem = allByOid.get(i1);
                Goods goods = goodsService.findAllById(orderitem.getGoodsid());
                goods.setNum(orderitem.getNum());
                goodsList.add(goods);
            }
            order.setGoodsInfo(goodsList);
            //查询地址
            Address address=orderService.findAddress(order.getAddressid());
            order.setAddress(address);
            orderList.set(i,order);
        }
        PageInfo info = new PageInfo(orderList,5);
        model.addAttribute("pageInfo", info);

        return "adminOrderReceive";
    }

    /**
     * 已完成的订单
     */
    @RequestMapping("/complete")
    public String completeOrder(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin==null){
            return "redirect:/admin/login";
        }
        //每页显示两条数据
        PageHelper.startPage(page,2);
        //查询已完成订单
        List<Order> orderList=orderService.findAllByAdminCom();
        model.addAttribute("sendOrder", orderList);
        //查询订单中的商品
        List<Integer> goodsid=new ArrayList<>();
        List<Goods> goodsList=new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            List<Orderitem> allByOid = orderService.findAllByOid(order.getOrderid());
            for (int i1 = 0; i1 < allByOid.size(); i1++) {
                Orderitem orderitem = allByOid.get(i1);
                Goods goods = goodsService.findAllById(orderitem.getGoodsid());
                goods.setNum(orderitem.getNum());
                goodsList.add(goods);
            }
            order.setGoodsInfo(goodsList);
            //查询地址
            Address address=orderService.findAddress(order.getAddressid());
            order.setAddress(address);
            orderList.set(i,order);
        }
        PageInfo info = new PageInfo(orderList,5);
        model.addAttribute("pageInfo", info);

        return "adminOrderComplete";
    }
    }
