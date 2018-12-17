<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/11 0011
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>欢迎登陆!</h2>
    <hr>
    <form action="/login" method="post">
        用户名:<input type="text" name="username"/><br/>
        用户密码:<input type="password" name="password"/><br/>
        <input type="submit" value="用户登录"/>
    </form>
</body>
</html>
