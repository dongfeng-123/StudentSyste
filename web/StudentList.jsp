<%@ page import="实体类.User.Student" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        tr {
            text-align: center;
            color: hotpink;
            font-size: 25px;
        }

        caption {
            font-size: 25px;
        }
    </style>
    <title>学生信息表</title>
</head>
<body>
<%

    List<Student> stu = (List<Student>) request.getAttribute ("stuList");
%>
<%--stu.get(0)拿到第一个student对象， 并获得对应的id,name，sex，age--%>
<a href="regidter.jsp">新增数据</a>
<center>
    <form action="querOne.do" method="post">
        ID:<input type="text" name="stuid"><br>
        姓名:<input type="text" name="stuName">
        <input type="submit" value="查询">
    </form>
    <br> <br>
    <hr>
    <table border="1" cellspacing="0" width="1366px" height="150px">
        <caption>学生信息列表</caption>
        <tr>
            <th>ID</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>操作</th>
        </tr>
        <%
            for (int i = 0; i < stu.size (); i++) {
        %>
        <tr>
            <td><%=stu.get (i).getId ()%>
            </td>
            <td><%=stu.get (i).getName ()%>
            </td>
            <td><%=stu.get (i).getAge ()%>
            </td>
            <td><%=stu.get (i).getSex ()%>
            </td>
            <td><a href="update.do?id=<%=stu.get(i).getId()%>">修改</a>

                <a href="delete.do?id=<%=stu.get(i).getId()%>">删除</a>
            </td>

        </tr>
        <%
            }
        %>
    </table>
</center>
</body>
</html>