<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="五福申请记录" />
    </c:import>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
</head>

<c:set var="mainTitle" value="五福申请记录" />
<c:set var="subTitle" value="五福申请记录" />

<body>
<div class="wrapper">
    <c:import url="/WEB-INF/layouts/nav.jsp" />
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
                            <form id="pagination-form" class="" method="POST" action="${ctx}/umeditor/activity/applyRecord/list">
                                <div class="search-group form-inline">
                                    <input type="text" name="search_LIKE_member.mobile" placeholder="会员手机" class="form-control">
                                    <input type="text" name="search_LIKE_member.profile.nickname" placeholder="会员昵称" class="form-control">
                                    <select name="search_EQ_type" class="form-control">
                                        <option value="">选择状态</option>
                                        <option value="1">充值</option>
                                        <option value="2">消费</option>
                                    </select>
                                    <div id="startDate" class="input-group date">
                                        <input type="text" id="gtCreateTime" name="search_GTE_createTime"
                                               class="form-control"
                                               value="<fmt:formatDate value='${defaultStartDate}' pattern='yyyy-MM-dd 00:00' />"
                                               placeholder="请输入起始日期">
                                        <span class="input-group-addon">
                                                <span class="fa fa-calendar"></span>
                                             </span>
                                    </div>
                                    <div id="endDate" class="input-group date">
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
                                    <button type="button" class="btn btn-primary btn-search">搜索</button>
                                    <a href="javascript:void(0)" class="btn btn-excel btn-success pull-right">导出</a>
                                </div>
                                <div >
                                    <iframe hidden="hidden" id="exportExcel" src=""></iframe>
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
    <c:import url="/WEB-INF/layouts/content_footer.jsp" />
</div>
<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/js/common/pagination.js"></script>
<script src="${ctx}/static/js/activity/applyRecord/list.js"></script>
<script src="${ctx}/static/js/agent/select.js"></script>
<script src="${ctx}/static/js/common/timeDay.js"></script>
<script type="text/javascript">

</script>
</body>

</html>