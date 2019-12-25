<%--
  Created by IntelliJ IDEA.
  User: 风
  Date: 2019/12/21
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆成功</title>
    <style type="text/css" >
        .gg{
            color: red;
            align-content: center;
        }
    </style>
</head>
<body>
<%
    //获取用户信息
    String username = (String) session.getAttribute ("username");
    if (username == null) {
        // 保存错误信息到request中, 然后转发到login3.jsp中, 提醒登录
        request.setAttribute ("message", "请登录");
        // 转发到登录页面
        request.getRequestDispatcher ("/index.jsp").forward (request, response);
    }
%>
<div class="gg">
    <h5>欢迎登陆:${sessionScope.username}!!!</h5>
<a href="StudentList.jsp">学生信息表系统</a>
</div>

</body>
</html>
