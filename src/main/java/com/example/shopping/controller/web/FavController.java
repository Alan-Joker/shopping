package com.example.shopping.controller.web;

import com.example.shopping.Entity.*;
import com.example.shopping.service.FavoriteService;
import com.example.shopping.service.GoodsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 收藏功能
 */
@Controller
public class FavController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private GoodsService goodsService;

    /**
     *添加收藏
     * @param goodsid
     * @param session
     * @return
     */
    @PostMapping("/collect")
    @ResponseBody
    public Msg collect(Integer goodsid, HttpSession session){
        //用户没有登录则返回登录界面
        User user = (User) session.getAttribute("user");
        if(user==null){
            return Msg.fail("收藏失败");
        }

        Favorite favorite=new Favorite();
        favorite.setCollecttime(new Date());
        favorite.setGoodsid(goodsid);
        favorite.setUserid(user.getUserId());
        try {
            favoriteService.addFavorite(favorite);
            return Msg.success("收藏成功");
        } catch (Exception e) {
            e.printStackTrace();
           return Msg.success("收藏失败");
        }
    }

    /**
     * 取消收藏
     */
    @RequestMapping("/deleteCollect")
    @ResponseBody
    public Msg deleteCollect(Integer goodsid,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user==null){
            return Msg.fail("取消收藏失败");
        }
        Integer userId = user.getUserId();
        try {
            favoriteService.delete(new FavoriteKey(userId,goodsid));
            return Msg.success("取消收藏成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail("取消收藏失败");
        }
    }


    /**
     * 我的收藏
     */
    @RequestMapping("/info/favorite")
    public String favorite(@RequestParam(value = "page",defaultValue = "1") Integer page, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        Goods goods=new Goods();
        //根据用户id查询收藏的商品id
        Integer userId = user.getUserId();
        List<Integer> gid=favoriteService.findGoodId(userId);
        List<Goods> goodsList=new ArrayList<Goods>();
        if(! gid.isEmpty()){
            //根据商品id查询所有商品
            for (Integer integer : gid) {
               goodsList.add(goodsService.findAllById(integer));
            }
        }
        if(! goodsList.isEmpty()){
            //获取图片地址
            for (int i = 0; i < goodsList.size(); i++) {
                //获取每一个商品
                Goods goods1 = goodsList.get(i);
                //查询图片地址
                List<String> imagePath = goodsService.findImagePath(goods1.getGoodsid());
                goods1.setImagePaths(imagePath);
                //是否收藏
                goods1.setFav(true);
                goodsList.set(i,goods1);
            }
        }
        //显示几个页号
        PageInfo info = new PageInfo(goodsList,5);
        model.addAttribute("pageInfo", info);
        return "favorite";
    }
}
