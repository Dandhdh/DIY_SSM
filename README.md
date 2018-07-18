## DIY_SSM
SSM框架整合

使用Idea搭建的Maven项目，并逐渐将某些技术添加进去<br>
[仿照项目](https://github.com/crossoverJie/SSM)

***

* [x] 待续

* [x] (六)SSM动态切换数据源
  - 拓展多数据源（相当于一个dataSource的路由）
  - 通过切面自动切换数据源，思路
    - 通过JoinPoint对象获取目标对象。
    - 在目标对象中获取包名来区分不同的数据源。
    - 根据不同数据源来进行赋值。
    - 执行完毕之后将数据源清空。
  - 绕坑
  ```
   当使用xml配置的切面编程拦截所有的数据库操作时，一直报错
   No MethodInvocation found: Check that an AOP invocation is in progress, and that the ExposeInvocationInterceptor is upfront in the interceptor chain. Specifically, note that advices with order HIGHEST_PRECEDENCE will execute before ExposeInvocationInterceptor!
   通过使用注解配置切面代替了xml配置，进而解决。
  ```
  [解决方法](https://blog.csdn.net/qingrunhao/article/details/53102511)
  执行test中的单元测试即可查运行情况
* [x] (五)在JavaWeb应用中使用Redis
  - 自定义拦截器
  - spring与redis整合以及基于aop对service的select开头的方法进行拦截，见spring-redis.xml
* [x] (四)基于webSocket的聊天室
  - 基于ueditor编写聊天室界面
  - 编程基于webSocket的接口（websocket类需要使用@ServerEndpoint(value="/websocket")注入）
  - 启动服务，地址栏进入 http://localhost/ssm/turnToWebSocketIndex 即可
* [x] (三)ssm整合shiro
  - 创建自定义MyRealm类
    - 登录的验证 
    - 对指定角色的验证
  - web.xml配置shiro过滤器
  - 配置文件spring-shiro.xml编写，对URL的验证
  - 在首页，点击后台，即可展示
* [x] (二)Lucene全文检索<br>
  - 编写Lucene工具类主要有方法：增删索引，查询关键字段
  - 查询Controller的编写：IndexController
  - 全文查询后的分页代码genUpAndDownPageCode()
  - 帮助文章 [Lucene介绍与入门使用](https://www.cnblogs.com/xiaobai1226/p/7652093.html)
  - 首页上导航栏，第一次进行需要先生成索引，在输入关键字查询
* [x] (一)ssm框架基本整合
  - 详情自行查找