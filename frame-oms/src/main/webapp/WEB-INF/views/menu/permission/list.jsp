<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="title" value="权限点列表" />
    </c:import>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css"/>

</head>

<c:set var="mainTitle" value="系统管理" />
<c:set var="subTitle" value="${menu.name} - 权限点列表" />
<c:set var="title" value="${empty permission ? '添加' : '编辑'}权限点" />
<body>
<div class="wrapper">
    <c:import url="/WEB-INF/layouts/nav.jsp" />
    <section>
        <div class="content-wrapper">
            <h3>${mainTitle}
                <%--<small>${subTitle}</small>--%>
            </h3>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">${subTitle}</div>
                        <div class="panel-body table-fit">
                            <form id="pagination-form" class="" method="POST" action="${ctx}/menu/permissions">
                                <div class="search-group form-inline">
                                    <input type="text" name="search_LIKE_name" placeholder="权限点名称" class="form-control">
                                    <button type="button" class="btn btn-primary btn-search">搜索</button>
                                </div>
                                <div class="action-group">
                                    <%--<a href="#" onclick="setStock()" class="btn btn-add btn-success pull-right">添加</a>--%>
                                    <a class="btn btn-add btn-success pull-right">添加</a>
                                    <a href="${ctx}/menus?page=${menuPage}&pid=${pid}" class="btn btn-default pull-right">返回</a>
                                    <div class="btn-group">
                                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="menu" class="dropdown-menu animated swing">
                                            <li><a href="javascript:void(0);"  class="btn-edit-action">编辑</a>
                                            </li>
                                            <li><a href="javascript:void(0);" class="btn-delete-action">删除</a>
                                            </li>
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

<%--<div id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="false" class="modal fade in">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">

        </div>
    </div>
</div>--%>

<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">编辑权限点</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" action="${ctx}/menu/permission/save" role="form">
                    <input type="hidden" name="id" value="${permission.id}">
                    <input type="hidden" name="menu.id" value="240">
                    <select class="form-control " style="width:80%;"  id="menusSelect" name="menuId" data-select="" >
                    </select>
                    <div class="form-group">
                        <label>名称</label>
                        <input type="text" name="name" id="name" value="${permission.name}"
                               placeholder="请输入权限点名称" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>权限点</label>
                        <input type="text" name="permission" id="permission" value="${permission.permission}"
                               placeholder="请输入权限点" class="form-control">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">关闭</button>
                <button type="button" class="btn btn-save btn-primary" data-loading-text="保存中...">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<c:import url="/WEB-INF/layouts/footer.jsp" />
<script src="${ctx}/static/js/common/pagination.js"></script>
<script src="${ctx}/static/js/menu/permission/list.js"></script>
<script type="text/javascript">
    var menuId = "${menuId}";
//    $("#menuId").val(menuId);
//    function setStock() {
//        $('#editModal').modal();
//    }
//    $("#setStockBtn").click(function () {
//        var name = $("#name").val();
//        var permission = $("#permission").val();
//        if(name == '' || permission==''){
//            Dialog.danger("请输入值");
//            return;
//        }
//        $("#setStockBtn").button("loading");
//        $.ajax({
//            url: ctx+"/savePermission",
//            type: "POST",
//            data: {
//                point: name,
//                money:permission,
//                menuId:menuId
//            },
//            dataType: "JSON",
//            success: function(data) {
//                if (data.code == 0) {
//                    Dialog.success(data.msg, function() {
//                        Pagination.reload();
//                        $("#editModal").modal("hide");
//                        $("#setStockBtn").button("reset");
//                    }, 1500);
//                } else {
//                    validator.showErrors(data.errors);
//                    $("#setStockBtn").button("reset");
//                    Dialog.danger(data.msg);
//                }
//            }
//        });
//    })
</script>
</body>

</html>