package com.web.dao;

import com.web.entity.BusinessInfo;

import java.util.List;

public interface BusinessDao {
    List<BusinessInfo> getBusinessAllById(String shopName);

    BusinessInfo getBusinessAllById(Integer id);

    int addBusiness(String shopName, String tel, String address);

    int updateBusinessInfoById(String shopName, String tel, String address,String id);

    int updateBusinessStatus(Integer id);

}
