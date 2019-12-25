<%--
  Created by IntelliJ IDEA.
  User: 风
  Date: 2019/12/25
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改页面</title>
</head>
<body>
<center>
    <form action="updateDate.do" method="post">
        姓名:<input type="text" name="name"><br>
        年龄:<input type="text" name="age"><br>
        性别:<br>
        男:<input type="radio" name="sex" value="男"><br>
        女:<input type="radio" name="sex" value="女"><br>
        <input type="submit" value="提交"> <br>
    </form>

</center>

</body>
</html>
