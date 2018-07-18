<%--
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>后台</title>
</head>
<body>
<shiro:hasRole name="admin">
    这是admin角色登录：<shiro:principal></shiro:principal>  <!-- 角色是admin就会显示 -->
</shiro:hasRole>

<shiro:hasPermission name="user:create">
    有user:create权限信息       <!-- 有user:create权限就会显示 -->
</shiro:hasPermission>
<br>
登录成功
<br/>
<div>
    <form action="${pageContext.request.contextPath }/student" method="post">
        <button id="submit1" type="submit">管理学生</button>
    </form>
    <form action="${pageContext.request.contextPath }/teacher" method="post">
        <h1></h1>
        <button id="submit2" type="submit">管理老师</button>
    </form>
</div>
</body>
</html>
