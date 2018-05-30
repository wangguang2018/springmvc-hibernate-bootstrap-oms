<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="活动管理" />
    </c:import>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
</head>

<c:set var="mainTitle" value="活动列表" />
<c:set var="subTitle" value="活动列表" />

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
                            <form id="pagination-form" class="" method="POST" action="${ctx}/umeditor/activity/list">
                                <div class="search-group form-inline">
                                    <input type="text" name="search_LIKE_title" placeholder="主标题" class="form-control">
                                    <input type="text" name="search_LIKE_subTitle" placeholder="副标题" class="form-control">
                                    <c:if test="${!isAgent}">
                                        <input type="text" name="search_EQ_agentId" id="agents" class="form-control">
                                    </c:if>
                                    <div id="startDate" class="input-group date">
                                        <input type="text" id="gtCreateTime" name="search_GTE_createTime" class="form-control" value="" placeholder="起始日期">
                                        <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
                                    </div>
                                    <div id="endDate" class="input-group date">
                                        <input type="text" id="ltCreateTime" name="search_LTE_createTime" class="form-control" value="" placeholder="结束日期">
                                        <span class="input-group-addon">
		                                    <span class="fa fa-calendar"></span>
		                                 </span>
                                    </div>
                                    <button type="button" class="btn btn-primary btn-search">搜索</button>
                                </div>
                                <div class="action-group">
                                    <%--<a href="javascript:void(0);" class="btn btn-add btn-success pull-right">添加</a>--%>
                                    <div class="btn-group">
                                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="menu" class="dropdown-menu animated swing">
                                            <li><a href="javascript:void(0);" class="btn-edit-action">编辑</a>
                                            </li>
                                           <%-- <li><a href="javascript:void(0);" class="btn-delete-action">删除</a>
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
    <c:import url="/WEB-INF/layouts/content_footer.jsp" />
</div>
<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/js/common/pagination.js"></script>
<script src="${ctx}/static/js/activity/list.js"></script>
<script src="${ctx}/static/js/agent/select.js"></script>
<script src="${ctx}/static/js/common/timeDay.js"></script>
<script type="text/javascript">

</script>
</body>

</html>