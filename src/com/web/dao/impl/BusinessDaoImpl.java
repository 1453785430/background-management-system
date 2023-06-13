package com.web.dao.impl;

import com.mysql.cj.util.StringUtils;
import com.web.dao.BusinessDao;
import com.web.entity.BusinessInfo;
import com.web.service.BusinessService;
import com.web.utils.BaseDao2;

import java.util.List;

public class BusinessDaoImpl extends BaseDao2<BusinessInfo> implements BusinessDao {

    @Override
    public List<BusinessInfo> getBusinessAllById(String shopName) {
        String sql = "select * from t_business_info where `status`=1";
        if (!StringUtils.isNullOrEmpty(shopName)){
            sql+=" and shop_name like concat('%',?,'%')";
            return selectListForObject(sql, BusinessInfo.class,shopName);
        }
        return selectListForObject(sql, BusinessInfo.class) ;
    }

    @Override
    public BusinessInfo getBusinessAllById(Integer id) {
        String sql = "select * from t_business_info where id=?";
        return selectOne(sql, BusinessInfo.class,id);
    }

    @Override
    public int addBusiness(String shopName, String tel, String address) {
        String sql ="insert into t_business_info values(null,?,?,?,now(),default)";
        return executeUpdate(sql,shopName,tel,address);
    }

    @Override
    public int updateBusinessInfoById(String shopName, String tel, String address,String id) {
        String sql = "update t_business_info set shop_name=?,tel=?,address=? where id=?";
        return executeUpdate(sql,shopName,tel,address,id);
    }

    @Override
    public int updateBusinessStatus(Integer id) {
        String sql = "update t_business_info set `status`=IF(`status`=1,0,1) where id=?";
        return executeUpdate(sql,id);
    }

}
