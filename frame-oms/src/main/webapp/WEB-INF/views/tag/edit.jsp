<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="机器标签管理"/>
    </c:import>
    <link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css"/>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/multi-select/css/multi-select.css" rel="stylesheet" type="text/css"/>

    <style type="text/css">
        .uploadify-button {
            border: none;
            padding: 0;
        }

        .has-feedback .form-control {
            padding-right: 0px;
        }
    </style>
</head>
<c:set var="mainTitle" value="机器标签管理"/>
<c:if test="${empty tag.id}" >
    <c:set var="subTitle" value="添加机器标签"/>
</c:if>
<c:if test="${not empty tag.id}" >
    <c:set var="subTitle" value="编辑机器标签"/>
</c:if>



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
                                  action="${ctx}/tag/save">
                                <div class="row">
                                    <div class="col-md-12">
                                        <input type="hidden" id="id" name="id" value="${tag.id}">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label required">标签名称:</label>
                                            <div class="col-md-8">
                                                <input type="text" name="tagName" id="tagName" placeholder="请输入标签名称"
                                                       class="form-control" value="${tag.tagName}">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label required">标签种类:</label>
                                            <div class="col-md-8">
                                                <input type="text" name="tagCategoryId" id="categories" class="form-control">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label required">标签状态:</label>
                                            <div class="col-md-9">
                                                <label class="radio-inline c-radio">
                                                    <input type="radio" name="status" value="1" checked
                                                    ${tag.status eq 1 ? 'checked' : ''}>
                                                    <span class="fa fa-circle"></span>正常
                                                </label>
                                                <label class="radio-inline c-radio">
                                                    <input type="radio" name="status" value="0"
                                                    ${tag.status eq 0 ? 'checked' : ''}>
                                                    <span class="fa fa-circle"></span>关闭
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">房间列表:</label>
                                            <div class="col-md-6">
                                                <select id='machineList' class="form-control" multiple='multiple'>
                                                    <c:forEach items="${machineList}" var="machine">
                                                        <option value='${machine.id}'  name=""
                                                                <c:if test="${fn:contains(machineSn,machine.sn)==true}">selected</c:if>>${machine.sn}_
                                                                <c:out value="${machine.product.name}" default="-" /></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <a class="btn btn-sm btn-back btn-default" href="javascript:history.go(-1);" role="button">返回</a>
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
<script src="${ctx}/static/multi-select/js/jquery.multi-select.js"></script>
<script src="${ctx}/static/multi-select/js/jquery.quicksearch.js"></script>
<script src="${ctx}/static/js/common/timeSecond.js"></script>
<script src="${ctx}/static/js/tag/edit.js"></script>
<script>
</script>
<script type="text/javascript">
    $(function () {
        $('#machineList').multiSelect({
            selectableHeader: "<input type='text' class='search-input form-control' autocomplete='off' placeholder='输入关键字搜索未绑定的房间'>",
            selectionHeader: "<input type='text' class='search-input form-control' autocomplete='off' placeholder='输入关键字搜索已绑定的房间'>",
            afterInit: function (ms) {
                var that = this,
                        $selectableSearch = that.$selectableUl.prev(),
                        $selectionSearch = that.$selectionUl.prev(),
                        selectableSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selectable:not(.ms-selected)',
                        selectionSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selection.ms-selected';
                that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
                        .on('keydown', function (e) {
                            if (e.which === 40) {
                                that.$selectableUl.focus();
                                return false;
                            }
                        });

                that.qs2 = $selectionSearch.quicksearch(selectionSearchString)
                        .on('keydown', function (e) {
                            if (e.which == 40) {
                                that.$selectionUl.focus();
                                return false;
                            }
                        });
            },
            afterSelect: function () {
                this.qs1.cache();
                this.qs2.cache();
            },
            afterDeselect: function () {
                this.qs1.cache();
                this.qs2.cache();
            }
        });
    })
</script>
</body>

</html>