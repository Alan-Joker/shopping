package com.example.shopping.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 邢彬 on 2020.7.29.
 */
@Controller
public class VerificationCodeImgController {
    @RequestMapping("/verificationcodeimg")
    public ModelAndView verificationcodeimg(){
        ModelAndView verificationcodeimg=new ModelAndView();
        verificationcodeimg.setViewName("verificationcodeimg");
        return verificationcodeimg;
    }
}
