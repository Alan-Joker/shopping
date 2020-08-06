package com.example.shopping.controller.admin;

import com.example.shopping.Entity.*;
import com.example.shopping.service.CategoryService;
import com.example.shopping.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 商品管理
 */
@Controller
@RequestMapping("/admin/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

//    //跳转到商品信息界面,显示类别
//    @RequestMapping("/show")
//    public String toGoodShow(@RequestParam("page")Integer page,HttpSession session){
//        Admin admin = (Admin) session.getAttribute("admin");
//        if(admin==null){
//            return "redirect:/admin/login";
//        }
//
//        return "adminAllGoods";
//    }

    @RequestMapping("/show")
    public String toGoodShow(Model model){

        //编辑页码显示商品种类
        List<Category> all= categoryService.findAll();
        model.addAttribute("categoryList",all);

        return "adminAllGoods";
    }

    /**
     * 展示所有商品
     */
    @RequestMapping("/showjson")
    @ResponseBody
    public Msg showJson(@RequestParam("page")Integer page, HttpSession session, Model result){
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return Msg.fail("请先登录");
        }
        PageHelper.startPage(page,10);
        List<Goods> all=goodsService.findAll();
        //显示的页码
        PageInfo info=new PageInfo(all,5);

        result.addAttribute("pageInfo",info);
        return Msg.success("查询成功").add("pageInfo",info);
    }

    /**
     * 添加商品
     */
    @RequestMapping("/add")
    public String toAdd(Model model, @ModelAttribute("successMsg")String msg){
        if(!msg.equals("")) {
            model.addAttribute("msg", msg);
        }

        List<Category> all= categoryService.findAll();
        model.addAttribute("categoryList",all);
        return "addGoods";
    }

    @RequestMapping("/addGoodsSuccess")
    public String addGoodsSuccess(Goods goods, RedirectAttributes redirectAttributes, HttpServletRequest request, @RequestParam("fileToUpload") MultipartFile[] fileToUpload){

        //判断管理员是否登录
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if(admin==null){
            return "redirect:/admin/login";
        }

        goods.setUptime(new Date());
        goods.setActivityid(1);
        goodsService.addGoods(goods);

        for (MultipartFile multipartFile : fileToUpload) {
            if(multipartFile!=null){
                //获取文件的扩展名
                String filename = multipartFile.getOriginalFilename();
                String realPaths = request.getSession().getServletContext().getRealPath("/");
                String imageName= UUID.randomUUID().toString().replace("-","")+filename;
                System.out.println(realPaths);
                //String imagePath="D:\\A_Project\\shopping\\shop\\images\\";
                String imagePath=realPaths+"\\images\\";
                //存储文件的路径
                File file=new File(imagePath+imageName);
                if(!file.getParentFile().exists()){
                    //文件父路径不存在则创建
                    file.mkdirs();
                }
                //存储文件
                try {
                    //将图片存入数据库
                    goodsService.addImagePath(new Imagepath(null,goods.getGoodsid(),imageName));
                    multipartFile.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        redirectAttributes.addFlashAttribute("successMsg","商品添加成功");
        return "redirect:/admin/goods/add";
    }

    /**
     * 删除商品记录
     */
    @DeleteMapping("/delete/{goodsid}")
    @ResponseBody
    public Msg delete(@PathVariable("goodsid")Integer goodsid){
        try {
            goodsService.deleteById(goodsid);
            return Msg.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail("删除失败");
        }
    }

    /**
     * 修改商品信息
     */

    @PostMapping("/update")
    @ResponseBody
    public Msg update(Goods goods,HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return Msg.fail("请先登录");
        }
        try {
            goods.setUptime(new Date());
            goodsService.update(goods);
            return Msg.success("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail("修改失败");
        }
    }
    /**
     * 添加分类
     */
    @RequestMapping("/addCategory")
    public String addCategory(Model model,@ModelAttribute("successMsg")String msg){
        //查询所有的分类
        List<Category> all = categoryService.findAll();

        model.addAttribute("categoryList",all);
        if(! msg.equals("")){
            model.addAttribute("msg",msg);
        }
        return "addCategory";
    }

    @RequestMapping("/addCategoryResult")
    public String addCategoryResult(Category category,RedirectAttributes redirectAttributes){
        //判断已有分类是否存在
        Category info=categoryService.findByName(category.getCatename());
        if(info !=null){
            //分类已经存在了
            redirectAttributes.addFlashAttribute("successMsg","分类已存在");
            return "redirect:/admin/goods/addCategory";
        }else {
            //分类不存在，可以添加
            try {
                categoryService.insert(category);
                redirectAttributes.addFlashAttribute("succeeMsg","分类添加成功");
                return "redirect:/admin/goods/addCategory";
            } catch (Exception e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("succeeMsg","分类添加失败");
                return "redirect:/admin/goods/addCategory";
            }
        }
    }

    /**
     * 删除分类
     */
    @RequestMapping("/deleteCate")
    @ResponseBody
    public Msg deleteCate(Category category){
        try {
            categoryService.deleteById(category.getCateid());
            return Msg.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.success("删除失败");
        }
    }

    /**
     * 修改分类名称
     */

    @RequestMapping("/saveCate")
    @ResponseBody
    public Msg saveCate(Category category){
        //判断该分类名是否存在
        Category byName = categoryService.findByName(category.getCatename());
        if(byName!=null){
            //分类名存在
            return Msg.fail("名字已经存在");
        }
        try{
            categoryService.saveByid(category);
            return Msg.success("更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return Msg.fail("更新失败");
        }
    }
}
