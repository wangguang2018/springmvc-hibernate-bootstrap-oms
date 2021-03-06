<%--
  Created by IntelliJ IDEA.
  User: whan
  Date: 10/9/15
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th>
                <div class="checkbox c-checkbox">
                    <label>
                        <input type="checkbox" class="checkbox-global">
                        <span class="fa fa-check"></span>
                    </label>
                </div>
            </th>
            <th>娃娃名称</th>
            <th>图片</th>
            <th>抓取一次需要钻石数</th>
            <th>娃娃实际价值(钻石数)</th>
            <th>创建日期</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="5" class="text-center">没有查询到内容！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="product" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${product.id}" >
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${product.name}</td>
                <td>
                    <c:set value="${ fn:split(product.imgs, ';') }" var="picList"/>
                    <img class="" height="30px" src=${picList[0]}>
                </td>
                <td>${product.price}</td>
                <td>${product.diamondRate}</td>
                <td><fmt:formatDate value="${product.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
