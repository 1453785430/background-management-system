package com.web.service;

import com.web.entity.BusinessInfo;

import javax.servlet.http.HttpServletRequest;

public interface BusinessService {
    void getBusinessInfo(HttpServletRequest request);

    String getBusinessById(HttpServletRequest request);

    String editBusinessInfo(HttpServletRequest request);

    String updateBusinessStatus(HttpServletRequest request);

}
