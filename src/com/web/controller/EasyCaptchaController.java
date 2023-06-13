package com.web.controller;

import com.web.annotation.*;
import com.web.service.EasyCaptchaService;
import com.web.service.impl.EasyCaptchaServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/captcha")
public class EasyCaptchaController {

    private EasyCaptchaService easyCaptchaService =new EasyCaptchaServiceImpl();
    @RequestMapping("/code")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        easyCaptchaService.captcha(request,response);
    }

    @RequestMapping("/check")
    @ResponseBody
    public String check(HttpServletRequest request,HttpServletResponse response) {
        return easyCaptchaService.check(request);
    }
}
