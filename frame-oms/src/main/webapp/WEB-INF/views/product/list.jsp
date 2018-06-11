<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="娃娃管理"/>
    </c:import>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css"/>
</head>

<c:set var="mainTitle" value="娃娃列表"/>
<c:set var="subTitle" value="娃娃列表"/>

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
                            <form id="pagination-form" class="" method="POST" action="${ctx}/product/list">
                                <div class="search-group form-inline">
                                    <input type="text" name="search_LIKE_name" placeholder="娃娃名称" class="form-control">
                                    <%--<input type="text" name="search_LIKE_machine.sn" placeholder="机器编号" class="form-control">--%>
                                    <c:if test="${!isAgent}">
                                        <input type="text" name="search_EQ_agentId" id="agents" class="form-control">
                                    </c:if>
                                    <button type="button" class="btn btn-primary btn-search">搜索</button>
                                </div>
                                <div class="action-group">
                                    <shiro:hasPermission name="product:edit">
                                        <a href="javascript:void(0);" class="btn btn-add btn-success pull-right">添加</a>
                                    </shiro:hasPermission>
                                    <div class="btn-group">
                                        <button type="button" data-toggle="dropdown"
                                                class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="menu" class="dropdown-menu animated swing">
                                            <%--<li><a href="#" class="btn-edit-action">编辑</a>--%>
                                            <%--</li>--%>
                                            <%--<li class="divider"></li>--%>
                                            <shiro:hasPermission name="product:edit">
                                                <li><a href="javascript:void(0);" class="btn-edit-action">编辑</a>
                                                </li>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="product:delete">
                                                <li><a href="javascript:void(0);" class="btn-delete-action">删除</a>
                                                </li>
                                            </shiro:hasPermission>
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
<script src="${ctx}/static/js/product/list.js"></script>
<script src="${ctx}/static/js/agent/select.js"></script>
<script src="${ctx}/static/js/common/timeDay.js"></script>
<script type="text/javascript">

</script>
</body>

</html>