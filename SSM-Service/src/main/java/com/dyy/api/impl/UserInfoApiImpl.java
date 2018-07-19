package com.dyy.api.impl;

import com.dyy.api.UserInfoApi;
import com.dyy.api.dubbo.pojo.T_user;
import com.dyy.api.dubbo.service.T_userService;
import com.dyy.api.dubbo.util.CommonUtil;
import com.dyy.dto.UserInfoRsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoApiImpl implements UserInfoApi {
    private static Logger logger = LoggerFactory.getLogger(UserInfoApiImpl.class);

    @Autowired
    private T_userService t_userService ;

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public UserInfoRsp getUserInfo(int userId) throws Exception {
        logger.info("用户查询Id="+userId);

        //返回对象
        UserInfoRsp userInfoRsp = new UserInfoRsp() ;
        T_user t_user = t_userService.selectByPrimaryKey(userId) ;

        //构建
        buildUserInfoRsp(userInfoRsp,t_user) ;

        return userInfoRsp;
    }


    /**
     * 构建返回
     * @param userInfoRsp
     * @param t_user
     */
    private void buildUserInfoRsp(UserInfoRsp userInfoRsp, T_user t_user) {
        if (t_user ==  null){
            t_user = new T_user() ;
        }
        CommonUtil.setLogValueModelToModel(t_user,userInfoRsp);
    }
}
