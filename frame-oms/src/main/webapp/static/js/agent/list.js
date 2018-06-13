$(function () {


    var list = {

        /** 初始化 */
        init: function () {
            this.initPagination();
        },

        /** 初始化分页 */
        initPagination: function () {
            Pagination.init({
                pageSize: 50,   // 每页显示12条记录
                editBtn: {
                    url: ctx + "/agent/edit/{id}"
                }
            });
        }
    };

    list.init();

    $(".btn-add").on("click", function () {
        location.href = ctx + "/agent/edit";
    });


    $(".btn-disable").on("click", function () {
        var ids = Pagination.getCheckedIds();
        if (ids.length <= 0) {
            Dialog.warning("请先选择您要禁用的代理商");
            return;
        }
        Dialog.confirm("禁用代理商", "确认要禁用这些代理商？", function (result) {
            if (result == true) {
                $.ajax({
                    url: ctx + "/agent/disable",
                    type: "POST",
                    data: {
                        id: ids
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 0) {
                            Dialog.success(data.msg);
                            Pagination.reload();
                        }
                    }
                })
            }
        });
    })
    $(".btn-enable").on("click", function () {
        var ids = Pagination.getCheckedIds();
        if (ids.length <= 0) {
            Dialog.warning("请先选择您要启用的代理商");
            return;
        }
        Dialog.confirm("启用代理商", "确认要启用这些代理商？", function (result) {
            if (result == true) {
                $.ajax({
                    url: ctx + "/agent/enable",
                    type: "POST",
                    data: {
                        id: ids
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 0) {
                            Dialog.success(data.msg);
                            Pagination.reload();
                        }
                    }
                })
            }
        });
    })
});


function add_sub(pid){
     window.location.href = ctx + "/agent/edit?pid="+pid;
}