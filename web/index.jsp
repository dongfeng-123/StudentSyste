<%--
  Created by IntelliJ IDEA.
  User: 风
  Date: 2019/12/19
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>


<%
    // 获取浏览器发送过来的cookie, 获取用户信息
    Cookie[] cookies = request.getCookies ();
    String username = "";
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("username".equals (cookie.getName ())) {
                username = cookie.getValue ();
            }
        }
    }
%>
<font color="red">${requestScope.message}</font>
<form method="post" action="loging.">
    用户名:<input name="username" type="text " value="<%=username%>"> <font color="red">${requestScope.error}</font><br>
    密码:<input type="text" name="password"><br>
    验证码:<input type="text" name="image">
    <img src="/login.">
    <input type="button" value="看不清换一张" id="btn"><font color="red">${requestScope.imageMess}</font><br>
    <input type="submit" value="登陆">
</form>
<a href="admin.jsp">注册账号</a>
<div>

    <video controls autoplay="autoplay">
        <source src="Music/清明上河图.kgma" type="audio/mpeg">
    </video>

</div>

<script type="text/javascript">
    document.getElementById("btn").onclick = function () {
        document.getElementsByTagName("img")[0].src = "login.?time" + new Date().getTime();
    };
</script>
</body>
</html>
