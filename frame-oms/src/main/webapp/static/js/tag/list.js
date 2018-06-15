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
                    url: ctx + "/tag/edit/{id}"
                },
                deleteBtn: {
                    url: ctx + "/tag/delete",
                    callback: function(data) {

                    }
                }
            });
        },
    };
    list.init();
    $(".btn-add").on("click", function() {
        location.href = ctx + "/tag/edit";
    });
});

