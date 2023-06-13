package com.web.controller;

import com.web.annotation.Controller;
import com.web.annotation.RequestMapping;
import com.web.annotation.ResponseBody;
import com.web.service.BusinessService;
import com.web.service.impl.BusinessServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/business")
public class BusinessController {
    private BusinessService businessService = new BusinessServiceImpl();
    @RequestMapping("/businessList")
    public String businessList(HttpServletRequest request, HttpServletResponse response){
        businessService.getBusinessInfo(request);
        return "/pages/business_list";
    }

    @RequestMapping("/getBusiness")
    @ResponseBody
    public String getBusinessById(HttpServletRequest request,HttpServletResponse response){
        return businessService.getBusinessById(request);
    }

    @RequestMapping("/editBusiness")
    @ResponseBody
    public String editBusiness(HttpServletRequest request,HttpServletResponse response){
        return businessService.editBusinessInfo(request);
    }

    @RequestMapping("/updateBusinessStatus")
    @ResponseBody
    public String updateBusinessStatus(HttpServletRequest request,HttpServletResponse response){
        return businessService.updateBusinessStatus(request);
    }
}
