package com.dyy.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LogUtil {

    /**
     * 生成日志实体工具
     */
    public static void setLogValueModelToModel(Object objectFrom, Object objectTo){

        Class<? extends Object> clazzForm = objectFrom.getClass();
        Class<? extends Object> clazzTo = objectTo.getClass();

        for (Method toSetMethod : clazzTo.getMethods()){
            String mName = toSetMethod.getName();
            if (mName.startsWith("set")){
                // 字段名
                String field = mName.substring(3);

                // 获取form值
                Object value;
                try {
                    if ("LogId".equals(field)){
                        continue;
                    }
                    Method formGetMetthod = clazzForm.getMethod("get"+field);
                    value = formGetMetthod.invoke(objectFrom);

                    // 设置值
                    toSetMethod.invoke(objectTo,value);
                }catch (NoSuchMethodException e){
                    throw new RuntimeException(e);
                }catch (InvocationTargetException e){
                    throw new RuntimeException(e);
                }catch (IllegalAccessException e){
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
