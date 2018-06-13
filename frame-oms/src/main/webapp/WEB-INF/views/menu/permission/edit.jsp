<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<c:set var="title" value="${empty permission ? '添加' : '编辑'}权限点" />
<div class="modal-header">
    <button type="button" data-dismiss="modal" aria-label="Close" class="close">
        <span aria-hidden="true">×</span>
    </button>
    <h4 id="editModalLabel" class="modal-title">${title}</h4>
</div>
<div class="modal-body">
    <form id="editForm"  role="form">
        <input type="hidden" name="id" value="${permission.id}">
        <input type="hidden" id="menu" value="${permission.menu.id}">
        <select class="form-control " id="menusSelect" style="width:80%;"  name="menuId" data-select="" >
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



<script>
    var $editForm = $("#editForm");
    $(function () {
        initMenu()
        // 提交编辑表单
        $(".btn-save").off("click").on("click", function() {
            //$editForm.submit();
            $(".btn-save").attr("disabled", true);
            //$(".btn-save").off("click")
            $.ajax({
                url: ctx + '/menu/permission/save',
                type: "POST",
                data: $editForm.serialize(),
                dataType: "JSON",
                success: function (data) {
                    if (data.code == 0) {
                        Dialog.success(data.msg, function () {
                            location.href = ctx + "/menu/permissions";
                        }, 1500);
                    } else {
                        validator.showErrors(data.errors);
                        $submitBtn.button("reset");
                        Dialog.danger(data.msg);
                    }
                }
            });
        });
    })
    function initMenu(){
        var menuId = $("#menu").val();
        console.log("menuIdaaa:"+menuId)
        $.fn.modal.Constructor.prototype.enforceFocus = function() {};
        $.ajax({
            url: window.ctx + "/select/getSonMenus",
            dataType:'json',
            success:function(data){
                $("#menusSelect").select2({
                    data: data,
                    matcher: function (params, data) {
                        if ($.trim(params.term) === '') {
                            return data;
                        }
                        if (data.text.indexOf(params.term) > -1) {
                            return $.extend({}, data, true);
                        }
                        return null;
                    }
                }).val(""+menuId).trigger("change");
                /* if(menuId){
                     $("#menusSelect").val("" + menuId).trigger
("change");
                 }
                 $("#menusSelect").trigger("change");*/
            }
        })
    }
</script>