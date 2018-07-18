package com.dyy.intercept;

import com.dyy.dataSource.Constants;
import com.dyy.dataSource.DataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 动态数据库的拦截器方法
 */
@Component
@Aspect
public class DataSourceExchange {

    /**
     * JoinPoint的API：
     *public interface JoinPoint {
     *     String toString();         //连接点所在位置的相关信息
     *     String toShortString();    //连接点所在位置的简短相关信息
     *     String toLongString();     //连接点所在位置的全部相关信息
     *     Object getThis();          //返回AOP代理对象
     *     Object getTarget();        //返回目标对象
     *     Object[] getArgs();        //返回被通知方法参数列表
     *     Signature getSignature();  //返回当前连接点签名
     *     SourceLocation getSourceLocation();//返回连接点方法所在类文件中的位置
     *     String getKind();          //连接点类型
     *     StaticPart getStaticPart(); //返回连接点静态部分
     * }
     */

    /**
     * 所有数据库操作的方法加入切面,
     * 即所有的数据库操作之前都先判断，进而确定数据源
     */
    @Before("execution(* com.dyy.service.*.select*(..))")
    public void before(JoinPoint point){

        // 获取目标对象的类类型
        Class<?> cla = point.getTarget().getClass();

        // 获取包名用于区分不同数据源
        // cla.getName() : com.dyy.com.dyy.service.dataSource2.xxx  或  com.dyy.com.dyy.service..xxx
        System.out.println("cla.getName():"+cla.getName());
        String whichDataSource = cla.getName().substring(16,cla.getName().lastIndexOf("."));
        if ("dataSource2".equals(whichDataSource)){
            DataSourceHolder.setDataSources(Constants.DATASOURCE_TWO);
        }else {
            DataSourceHolder.setDataSources(Constants.DATASOURCE_ONE);
        }
    }

    /**
     * 执行后将数据源置为空
     */
    @After("execution(* com.dyy.service.*.select*(..))")
    public void after(){
        DataSourceHolder.setDataSources(null);
    }

}
