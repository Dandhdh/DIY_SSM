package com.dyy.api.impl;

public interface DemoApi {
    /**
     * 获取用户信息
     * @param userId
     * @return
     * @throws RuntimeException
     */
    public String readMsg(int userId) throws RuntimeException ;
}
