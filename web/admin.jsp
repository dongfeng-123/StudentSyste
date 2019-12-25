<%@ page import="Jdbc.数据库.JDBC.JDBC" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %><%--
  Created by IntelliJ IDEA.
  User: 风
  Date: 2019/12/24
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主界面</title>
    <style type="text/css">
        error1{
            color: red;
        }
    </style>
</head>
<body>

<form action="regi" method="post">
    账号：<input type="text" autocomplete="off" name="Account" oninput="value=value.replace(/[^\d]/g,'')" maxlength="10"
              value="" placeholder="只能输入10位以内的数字"/>${requestScope.error1}<br>
    密码：<input type="password" name="Password" autocomplete="off" maxlength="11" value="" placeholder="只能输入11个字符"/>
    <input type="submit" value="提交">

</form>

<%--<%response.sendRedirect("index.jsp");%>--%>
</body>
</html>
