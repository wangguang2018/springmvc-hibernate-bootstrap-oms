<%--
  Created by IntelliJ IDEA.
  User: wangyy
  Date: 2018/2/24
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户编辑页面</title>
</head>
<body>
<form method="post" action="/user/save" enctype="multipart/form-data">

    <input name="userName" placeholder="请填写姓名">

    <input name="password" placeholder="请填写密码">

    <input type="number" name="age" placeholder="请输入年龄">

    <button type="submit" >保存</button>
</form>
</body>
</html>
