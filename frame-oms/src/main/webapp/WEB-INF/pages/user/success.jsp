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
    <title>hello</title>
</head>
<body>
<h5>helloworld ,my boy!</h5>
<form method="post" action="/user/save" enctype="multipart/form-data">
    <input name="fatherDto.firstName" value="张">

    <input name="age" value="111">

    <input name="fatherDto.sex" value="1">

    <input name="name" value="汪天天">

    <input type="file" name="file">

    <button type="submit" >保存</button>
</form>
</body>
</html>
