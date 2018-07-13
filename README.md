## DIY_SSM
SSM框架整合

使用Idea搭建的Maven项目，并逐渐将某些技术添加进去<br>
[仿照项目](https://github.com/crossoverJie/SSM)

* [x] 待续

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