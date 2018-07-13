package com.dyy.intercept;

import com.dyy.util.RedisUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MethodCacheInterceptor implements MethodInterceptor {

    private Logger logger = LoggerFactory.getLogger(MethodCacheInterceptor.class);
    private RedisUtil redisUtil;
    /** 不加入缓存的service名称 */
    private List<String> targetNamesList;
    /** 不加入缓存的方法名称 */
    private List<String> methodNamesList;
    /** 缓存默认的过期时间 */
    private Long defaultCacheExpireTime;

    private Long xxxRecordManagerTime;
    private Long xxxSetRecordManagerTime;


    /**
     * 初始化读取不需要加入缓存的类名和方法名称
     */
    public MethodCacheInterceptor(){
        try {

//            String[] targetNames = {"com.dyy.service.impl.RediscontentServiceImpl"};
//            String[] methodNames = {"selectByPage"};
            // 分割字符串，这里没有加入任何方法
            String[] targetNames = {};
            String[] methodNames = {};

            // 加载过期时间设置
            defaultCacheExpireTime = 3600L;
            xxxRecordManagerTime = 60L;
            xxxSetRecordManagerTime = 60L;

            // 创建list
            targetNamesList = new ArrayList<String>(targetNames.length);
            methodNamesList = new ArrayList<String>(methodNames.length);
            Integer maxLen = targetNames.length > methodNames.length ? targetNames.length
                    : methodNames.length;

            // 将不需要缓存的类名和方法名添加到list中
            for (int i=0; i<maxLen; i++){
                if (i<targetNames.length){
                    targetNamesList.add(targetNames[i]);
                }
                if (i < methodNames.length) {
                    methodNamesList.add(methodNames[i]);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Object value = null;

        // 获取拦截方法的类名和方法名
        String targetName = invocation.getThis().getClass().getName();
        String methodName = invocation.getMethod().getName();

        // 不需要缓存的内容
        //if (!isAddCache(StringUtil.subStrForLastDot(targetName), methodName)) {
        if (!isAddCache(targetName, methodName)) {
            // 执行方法返回结果
            return invocation.proceed();  // 动态代理拦截的方法，并返回（即后面的不再执行）
        }
        // 获取拦截方法的参数
        Object[] arguments = invocation.getArguments();
        String key = getCacheKey(targetName, methodName, arguments);
        logger.debug("redisKey: " + key);
        try {
            // 判断是否有缓存
            if (redisUtil.exists(key)) {
                // 返回缓存的数据
                return redisUtil.get(key);
            }
            // 先执行完拦截的方法，获取返回值value（查询的数据），再执行后面的代码
            value = invocation.proceed();
            if (value != null) {
                final String tkey = key;
                final Object tvalue = value;
                // 因为redis为单线程，为了防止阻塞，创建线程将缓存数据写入redis
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (tkey.startsWith("com.service.impl.xxxRecordManager")) {
                            redisUtil.set(tkey, tvalue, xxxRecordManagerTime);
                        } else if (tkey.startsWith("com.service.impl.xxxSetRecordManager")) {
                            redisUtil.set(tkey, tvalue, xxxSetRecordManagerTime);
                        } else {
                            redisUtil.set(tkey, tvalue, defaultCacheExpireTime);
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (value == null) {
                return invocation.proceed();
            }
        }
        return value;
    }

    /**
     * 是否加入缓存
     *
     * @return
     */
    private boolean isAddCache(String targetName, String methodName) {
        boolean flag = true;
        if (targetNamesList.contains(targetName)
                || methodNamesList.contains(methodName)) {
            flag = false;
        }
        return flag;
    }

    /**
     * 创建缓存key
     *
     * @param targetName
     * @param methodName
     * @param arguments
     */
    private String getCacheKey(String targetName, String methodName,
                               Object[] arguments) {

        // 拼凑成存于redis的key：如 com.dyy.service.impl.RediscontentServiceImpl_selectByPage_0_0
        StringBuffer sbu = new StringBuffer();
        sbu.append(targetName).append("_").append(methodName);
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                sbu.append("_").append(arguments[i]);
            }
        }
        return sbu.toString();
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
}
