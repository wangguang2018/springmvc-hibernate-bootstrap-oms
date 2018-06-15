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
            <%--<th>
                <div class="checkbox c-checkbox">
                    <label>
                        <input type="checkbox" class="checkbox-global">
                        <span class="fa fa-check"></span>
                    </label>
                </div>
            </th>--%>
            <th>订单号</th>
            <th>用户</th>
            <th>用户手机</th>
            <th>下单日期</th>
            <th>是否发货</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="6" class="text-center">没有查询到内容！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="order" varStatus="status">
            <tr>
                <%--<td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${order.id}" >
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>--%>
                <td><a href="#" onclick="order('${order.orderSn}')">${order.orderSn}</a></td>
                <td>${order.member.nickname}</td>
                <td>${order.member.mobile}</td>
                <td><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>
                    <c:if test="${order.status eq 0}"><span style="color:#27c24c;">已发货</span></c:if>
                    <c:if test="${order.status eq 1}"><span style="color: #f05050;">未发货</span></c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
