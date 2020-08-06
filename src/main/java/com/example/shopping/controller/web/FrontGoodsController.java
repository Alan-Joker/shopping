package com.example.shopping.controller.web;

import com.example.shopping.Entity.*;
import com.example.shopping.service.ActivityService;
import com.example.shopping.service.CategoryService;
import com.example.shopping.service.FavoriteService;
import com.example.shopping.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品详情
 */
@Controller
public class FrontGoodsController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/test")
    public String to(){
        return "detail";
    }
    @GetMapping("/detail")
    public String deatil(@RequestParam("goodsId")Integer goodsId, Model mode, HttpSession session){

        if(goodsId==null){
            return "redirect:/main";
        }

        //获取用户的信息
        User user = (User) session.getAttribute("user");
       // Integer userId = user.getUserId();
        //将传回的信息存入Map
        Map<String,Object> map=new HashMap<String, Object>();

        //查询商品信息
        Goods goods=goodsService.findAllById(goodsId);

        //查询商品类别
        Integer category1 = goods.getCategory();
        Category category=categoryService.findAllById(category1);

        //查询商品图片
        List<String> imagepaths=goodsService.findImagePath(goodsId);
       // String imagepaths=goodsService.findImagePath(goodsId);

        //优惠活动
        Activity activity=activityService.findById(goods.getActivityid());
        goods.setActivity(activity);
        //判断是否收藏
        if(user==null){
            //用户没有登录
            goods.setFav(false);
        }else {
            System.out.println(goods.getGoodsid());
            Integer favorite= favoriteService.findById(new FavoriteKey(user.getUserId(),goods.getGoodsid()));
            if(favorite==0){
                //没收藏
                goods.setFav(false);
            }else {
                goods.setFav(true);
            }
        }


        map.put("goods",goods);
        map.put("cate",category);
        map.put("image",imagepaths);
        mode.addAttribute("goodsInfo",map);
        mode.addAttribute("imagepaths",imagepaths);
        return "detail";
    }

    /**
     * 侧边框商品导航
     */
    @RequestMapping("/category")
    public String category(String cate,@RequestParam(value = "page",defaultValue = "1")Integer page,Model model,HttpSession session){

        User user = (User) session.getAttribute("user");
        //每页显示16条数据
        PageHelper.startPage(page,16);

        //根据类别进行查询，找到商品id
        Category byName = categoryService.findByName(cate);
        List<Goods> goodsList = goodsService.findAllsById(byName.getCateid());

        //获取图片地址
        for (int i = 0; i < goodsList.size(); i++) {
            Goods goods = goodsList.get(i);
            List<String> imagePath = goodsService.findImagePath(goods.getGoodsid());
            goods.setImagePaths(imagePath);

            //判断是否收藏
            if(user==null){
                //用户没有登录
                goods.setFav(false);
            }else {
                Integer userId = user.getUserId();

                System.out.println(goods.getGoodsid());
                Integer favorite= favoriteService.findById(new FavoriteKey(userId,goods.getGoodsid()));
               if(favorite==0){
                   //没收藏
                   goods.setFav(false);
               }else {
                   goods.setFav(true);
               }
            }
            goodsList.set(i,goods);
        }

        //显示几个页号
        PageInfo info = new PageInfo(goodsList,5);
        model.addAttribute("pageInfo", info);
        model.addAttribute("cate", cate);
        return "category";
    }
}
