$(function() {
    var list = {
        /** 初始化 */
        init: function() {
            this.initPagination();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 50,
                editBtn: {
                    url: ctx + "/umeditor/activity/edit/{id}"
                },
                deleteBtn: {
                    url: ctx + "/umeditor/activity/delete",
                    callback: function(data) {

                    }
                }
            });
        },
    };
    list.init();
    $(".btn-add").on("click", function() {
        location.href = ctx + "/activity/edit";
    });
});
