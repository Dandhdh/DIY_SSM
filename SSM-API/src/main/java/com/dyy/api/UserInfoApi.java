package com.dyy.api;

import com.dyy.dto.UserInfoRsp;

/**
 * Function:用户API
 * @author chenjiec
 * Date: 2017/4/4 下午9:46
 * @since JDK 1.7
 */
public interface UserInfoApi {

    /**
     * 获取用户信息
     * @param userId
     * @return
     * @throws Exception
     */
    public UserInfoRsp getUserInfo(int userId) throws Exception;
}
