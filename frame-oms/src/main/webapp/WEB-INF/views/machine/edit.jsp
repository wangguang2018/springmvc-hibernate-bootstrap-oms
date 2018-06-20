<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="娃娃机管理"/>
    </c:import>
    <link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css"/>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css"/>

    <style type="text/css">

    </style>
</head>
<c:set var="mainTitle" value="娃娃机管理"/>
<c:set var="subTitle" value="添加娃娃机"/>


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
                        <div class="panel-body">
                            <form id="form-edit" role="form" method="POST" class="form-horizontal"
                                  action="${ctx}/machine/save">
                                <input name="memberCount" type="hidden" value="${machine.memberCount}"/>
                                <div class="row">
                                    <div class="col-md-12">
                                        <input type="hidden"  name="id" value="${machine.id}">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">娃娃机编号:</label>
                                            <div class="col-md-8">
                                                <input type="text" name="sn" readonly id="sn" placeholder="请输入娃娃机编号"
                                                       class="form-control" value="${machine.sn}">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <input type="hidden" name="id" value="${machine.clientIp}">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">机器IP:</label>
                                            <div class="col-md-8">
                                                <input type="text" name="sn" readonly id="clientIp" placeholder="机器IP"
                                                       class="form-control" value="${machine.clientIp}">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <input type="hidden"  name="id" value="${machine.versionCode}">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">请输入版本号:</label>
                                            <div class="col-md-8">
                                                <input type="text" name="sn" readonly id="versionCode" placeholder="请输入版本号"
                                                       class="form-control" value="${machine.versionCode}">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">娃娃:</label>
                                            <div class="col-md-2">
                                                <select class="form-control" name="productId">
                                                    <c:forEach var="product" items="${productList}">
                                                        <option <c:if test="${product.id eq machine.productId}">selected</c:if> value="${product.id}">${product.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <%--<div class="col-md-12">--%>
                                        <%--<div class="form-group">--%>
                                            <%--<label class="col-md-2 control-label">娃娃机状态:</label>--%>
                                            <%--<div class="col-md-9">--%>
                                                <%--<label class="radio-inline c-radio">--%>
                                                    <%--<input type="radio" name="status" value="0" checked--%>
                                                    <%--${machine.status eq 0 || machine.status eq 1 ? 'checked' : ''}>--%>
                                                    <%--<span class="fa fa-circle"></span>正常--%>
                                                <%--</label>--%>
                                                <%--<label class="radio-inline c-radio">--%>
                                                    <%--<input type="radio" name="status" value="2"--%>
                                                    <%--${machine.status eq 2 ? 'checked' : ''}>--%>
                                                    <%--<span class="fa fa-circle"></span>关闭--%>
                                                <%--</label>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>

                                </div>
                                <a class="btn btn-sm btn-primary" href="javascript:history.go(-1);" role="button">返回</a>
                                <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                        data-loading-text="保存中...">
                                    保存
                                </button>
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


<script src="${ctx}/static/third/bootstrap/js/bootstrap-slider.min.js"></script>
<script src="${ctx}/static/libs/jquery-upload/jquery.ui.widget.js"></script>
<script src="${ctx}/static/libs/jquery-upload/jquery.fileupload.js"></script>
<script src="${ctx}/static/js/common/timeSecond.js"></script>
<script src="${ctx}/static/js/machine/edit.js"></script>
<script>
</script>
<script type="text/javascript">

</script>
</body>

</html>