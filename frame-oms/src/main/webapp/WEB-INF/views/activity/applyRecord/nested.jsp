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
            <th>序号</th>
            <th>会员编号</th>
            <th>会员昵称</th>
            <th>手机号码</th>
            <th>支付宝账户</th>
            <%--<th>申请状态</th>--%>
            <th>申请时间</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="8" class="text-center">没有查询到内容！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="log" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${log.id}" >
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td>${page.number * page.numberOfElements + status.index + 1}</td>
                <td>${log.member.id}</td>
                <td>${empty log.member.profile.nickname ? '-' : log.member.profile.nickname}</td>
                <td><c:out value="${log.phone}" default="-" /></td>
                <td>${log.alipayAccount}</td>
                <%--<td>--%>
                    <%--<c:if test="${log.status eq 0}">默认</c:if>--%>
                    <%--<c:if test="${log.status eq 1}">完成</c:if>--%>
                    <%--<c:if test="${log.status eq -1}">失败</c:if>--%>
                <%--</td>--%>
                <td><fmt:formatDate value="${log.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
