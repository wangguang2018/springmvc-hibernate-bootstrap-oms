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
            });
        },
    };
    list.init();

    /**
     * 导出五福申请记录
     */
    $(".btn-excel").on("click", function() {
        // location.href = ctx + "/umeditor/activity/applyRecord/export";
        console.log(11212212)
        $.ajax({
            url: ctx + "/umeditor/activity/applyRecord/export",
            type: 'POST',
            data:{
                
            },
            dataType: 'JSON',
            success:function (data) {
                console.log(data.url);
                $("#exportExcel").attr("src",data.url);
            }
        })
    });
});
