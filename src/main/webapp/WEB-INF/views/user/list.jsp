<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: wangyy
  Date: 2018/3/13
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <%--<c:param name="question" value="抓取记录管理" />--%>
    </c:import>
    <title>标题</title>
</head>
<body>
<div class="wrapper">
    <c:import url="/WEB-INF/layouts/nav.jsp"/>
        <section>
            <div class="content-wrapper">
            </div>
        </section>
    <c:import url="/WEB-INF/layouts/content_footer.jsp" />

</div>
---${fn:length(menus)}----
<c:import url="/WEB-INF/layouts/footer.jsp" />
</body>
</html>
