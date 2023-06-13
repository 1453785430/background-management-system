package com.web.service.impl;

import com.mysql.cj.util.StringUtils;
import com.web.component.BgmsConfig;
import com.web.dao.UserInfoDao;
import com.web.dao.impl.UserInfoDaoImpl;
import com.web.entity.UserInfo;
import com.web.service.UserInfoService;
import com.web.utils.BaseDao2;
import com.web.utils.BaseResult;
import com.web.utils.MD5Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserInfoServiceImpl implements UserInfoService {
    //引入dao层用户查询数据库
    private UserInfoDao userInfoDao =new UserInfoDaoImpl();
    @Override
    public String login(HttpServletRequest request) {
        //从请求中获取用户名和密码
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        //用户名和密码非空校验
        if (StringUtils.isNullOrEmpty(userName) || StringUtils.isNullOrEmpty(password)){
            return BaseResult.error(10001,"用户名或密码不能为空");
        }
        //给密码加密
        String encryptPwd = MD5Utils.encryptMD5(password, userName);
        //拿加密后的密码和用户名去数据库查询用户信息
        UserInfo userInfo = userInfoDao.getUserInfoByUserNameAndPassword(userName, encryptPwd);
        //如果查询到的userInfo为空,说明不存在,判定为用户名或密码错误
        if (userInfo==null){
            return BaseResult.error(10002,"用户名或密码错误");
        }
        //用户登录成功后,把用户信息存放到 session作用域中,用户后续使用
        request.getSession().setAttribute(BgmsConfig.SESSION_USER_KEY,userInfo);
        return BaseResult.success();
    }

    @Override
    public void getUserInfoAllById(HttpServletRequest request) {
//     List<UserInfo> userInfos = userInfoDao.getUserInfoById();
//     request.getSession().setAttribute(BgmsConfig.USER_LIST,userInfos);
        String keyword = request.getParameter(BgmsConfig.KEYWORD);
        request.setAttribute(BgmsConfig.KEYWORD,keyword);
        request.setAttribute(BgmsConfig.USER_LIST,userInfoDao.getUserInfoById(keyword));
    }

    @Override
    public String getUserInfoAllId(HttpServletRequest request) {
        try {
            Integer id = Integer.parseInt( request.getParameter("id"));
            return BaseResult.success(userInfoDao.getUserInfoAllId(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return BaseResult.error(40002,"参数错误");
        }
    }

    @Override
    public String editUser(HttpServletRequest request) {
        String id = request.getParameter("id");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String nickName = request.getParameter("nickName");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        String sex = request.getParameter("sex");
        int rows = 0;
        if (StringUtils.isNullOrEmpty(userName)) {
            return BaseResult.error(40002, "参数异常");
        }
        if (StringUtils.isNullOrEmpty(id)) {
            //执行新增
            String encrypted = MD5Utils.encryptMD5(password);
            rows = userInfoDao.addUserInfo(userName,encrypted,nickName,tel,address,sex);
        } else {
            //执行修改
            try {
                int userId = Integer.parseInt(id);
                String encrypted = MD5Utils.encryptMD5(password);
                rows = userInfoDao.updateUserInfoById(userName, encrypted, nickName, tel, address, sex, id);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return BaseResult.error(40002, "参数异常");
            }
        }

        if (rows > 0) {
            return BaseResult.success();
        }
        return BaseResult.error(40003, "保存失败");
    }

    @Override
    public String updateUserStatus(HttpServletRequest request) {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            int rows = userInfoDao.updateUserStatus(id);
            if (rows>0){
                return BaseResult.success();
            }else {
                return BaseResult.error(50003,"删除失败");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return BaseResult.error(50001,"参数错误");
        }
    }
}
