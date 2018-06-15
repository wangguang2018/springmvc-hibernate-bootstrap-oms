<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="娃娃订单管理"/>
    </c:import>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css"/>
</head>

<c:set var="mainTitle" value="娃娃订单列表"/>
<c:set var="subTitle" value="娃娃订单列表"/>

<body>
<div class="wrapper">
    <c:import url="/WEB-INF/layouts/nav.jsp"/>
    <section>
        <div class="content-wrapper">
            <h3>${mainTitle}
                <small>${subTitle}</small>
            </h3>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">${subTitle}</div>
                        <div class="panel-body table-fit">
                            <form id="pagination-form" class="" method="POST" action="${ctx}/member/order/list">
                                <div class="search-group form-inline box  ">
                                    <input type="text" name="search_LIKE_orderSn" placeholder="订单号"
                                           class="form-control search-cell">
                                    <input type="text" name="search_LIKE_member.profile.nickname" placeholder="会员昵称"
                                           class="form-control search-cell">
                                    <input type="text" name="search_LIKE_member.mobile" placeholder="会员手机号"
                                           class="form-control search-cell">
                                    <select class="form-control search-cell" name="search_EQ_status">
                                        <option value="-1">请选择发货状态</option>
                                        <option value="0">已发货</option>
                                        <option value="1">未发货</option>
                                    </select>
                                    <div id="startDate" class="input-group date search-cell">
                                        <input type="text" id="gtCreateTime" name="search_GTE_createTime"
                                               class="form-control"
                                               value="<fmt:formatDate value='${defaultStartDate}' pattern='yyyy-MM-dd 00:00' />"
                                               placeholder="请输入起始日期">
                                        <span class="input-group-addon">
                                                <span class="fa fa-calendar"></span>
                                             </span>
                                    </div>
                                    <div id="endDate" class="input-group date search-cell">
                                        <input type="text" id="ltCreateTime" name="search_LTE_createTime"
                                               class="form-control"
                                               value="<fmt:formatDate value='${defaultEndDate}' pattern='yyyy-MM-dd 23:59' />"
                                               placeholder="请输入结束日期">
                                        <span class="input-group-addon">
                                                <span class="fa fa-calendar"></span>
                                             </span>
                                    </div>
                                    <c:if test="${!isAgent}">
                                        <input type="text" name="search_EQ_agentId" id="agents" class="form-control">
                                    </c:if>
                                    <button type="button" class="btn btn-primary btn-search search-cell">搜索</button>
                                    <button type="button" class="btn btn-export btn-success search-cell " id="exportExcel">导出</button>
                                </div>
                                <div class="action-group">
                                    <a href="javascript:void(0);" class="btn btn-add btn-success pull-right">导入excel文件(物流信息)</a>
                                    <div class="btn-group">
                                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="menu" class="dropdown-menu animated swing">
                                            <%--<li><a href="#" class="btn-edit-action">编辑</a>--%>
                                            <%--</li>--%>
                                            <%--<li class="divider"></li>
                                            <li><a href="javascript:void(0);" class="btn-edit-action">编辑</a>
                                            </li>
                                            <li><a href="javascript:void(0);" class="btn-delete-action">删除</a>
                                            </li>--%>
                                        </ul>
                                    </div>
                                </div>
                                <div id="pagination-body">

                                </div>
                                <input type="hidden" name="page" value="${pagination.page}">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <c:import url="/WEB-INF/layouts/content_footer.jsp"/>
</div>
<c:import url="/WEB-INF/layouts/footer.jsp"/>
<script src="${ctx}/static/js/common/pagination.js"></script>
<script src="${ctx}/static/js/order/list.js"></script>
<script src="${ctx}/static/js/agent/select.js"></script>
<script src="${ctx}/static/js/common/timeDay.js"></script>
<script type="text/javascript">
</script>
</body>
<style>
    .box{
        flex-direction:row;
        flex-wrap:wrap;
        padding: 5px;
    }
</style>

</html>