<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="娃娃订单管理"/>
    </c:import>
    <link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css"/>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css"/>
</head>
<c:set var="mainTitle" value="娃娃订单管理"/>
<c:set var="subTitle" value="娃娃订单"/>


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
                            <form id="form-edit" role="form" method="POST" class="form-horizontal" action="">
                                <div class="row">
                                    <div class="col-md-12">
                                        <input type="hidden" id="id" name="id" value="">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">上传excel文件</label>
                                            <div class="col-md-8">
                                                <div id="picsFileList">
                                                </div>
                                                <input id="pic_upload" name="pic_upload" type="file">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-5 col-md-offset-2">
                                        <a class="btn btn-sm btn-default" href="javascript:history.go(-1);"
                                           role="button">返回</a>
                                        <%--<button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="${empty banner.id ? "添加" : "保存"}中...">
                                            ${empty banner.id ? "添 加" : "保存"}
                                        </button>--%>
                                    </div>
                                </div>
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
<script src="${ctx}/static/js/order/uploadExcel.js"></script>
<script type="text/javascript">
</script>
</body>
</html>