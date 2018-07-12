package com.dyy.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniFactorySupport;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class TestShiro {

    public static void main(String[] args) {
        // 读取shiro配置文件-shiro.ini，初始化工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        // 获取 SecurityManager的实例对象
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        // 得到当前的用户
        Subject currentUser = SecurityUtils.getSubject();
        // 用当前账号密码去认证
        UsernamePasswordToken token = new UsernamePasswordToken("dyy","123");
        try{
            currentUser.login(token);
            System.out.println("认证成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("认证失败");
        }
        currentUser.logout();

    }

}
