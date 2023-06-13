package com.web.service.impl;

import com.mysql.cj.util.StringUtils;
import com.web.component.BgmsConfig;
import com.web.dao.BusinessDao;
import com.web.dao.impl.BusinessDaoImpl;
import com.web.entity.BusinessInfo;
import com.web.service.BusinessService;
import com.web.utils.BaseResult;

import javax.servlet.http.HttpServletRequest;


public class BusinessServiceImpl implements BusinessService {
    private BusinessDao businessDao = new BusinessDaoImpl();

    @Override
    public void getBusinessInfo(HttpServletRequest request) {
        String keyword = request.getParameter(BgmsConfig.KEYWORD);
        request.setAttribute(BgmsConfig.KEYWORD,keyword);
        request.setAttribute(BgmsConfig.BUSINESS_LIST,businessDao.getBusinessAllById(keyword));
    }

    @Override
    public String getBusinessById(HttpServletRequest request) {
        try {
            Integer id = Integer.parseInt( request.getParameter("id"));
            return BaseResult.success(businessDao.getBusinessAllById(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return BaseResult.error(50001,"参数错误");
        }
    }

    @Override
    public String editBusinessInfo(HttpServletRequest request) {
        String id = request.getParameter("id");
        String shopName = request.getParameter("shopName");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        int rows = 0;
        if (StringUtils.isNullOrEmpty(shopName)){
            return BaseResult.error(50001,"参数异常");
        }
        if (StringUtils.isNullOrEmpty(id)){
            //执行新增
            rows = businessDao.addBusiness(shopName,tel,address);
        }else {
            //执行修改
            try {
                int businessId = Integer.parseInt(id);
                rows = businessDao.updateBusinessInfoById(shopName,tel,address,id);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return BaseResult.error(50001,"参数异常");
            }
        }

        if (rows>0){
            return BaseResult.success();
        }
        return BaseResult.error(50002,"保存失败");
    }

    @Override
    public String updateBusinessStatus(HttpServletRequest request) {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            int rows = businessDao.updateBusinessStatus(id);
            if (rows>0){
                return BaseResult.success();
            }else {
                return BaseResult.error(50002,"删除失败");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return BaseResult.error(50001,"参数有误");
        }
    }

}
