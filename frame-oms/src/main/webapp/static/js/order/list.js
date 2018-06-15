$(function() {
    var list = {
        /** 初始化 */
        init: function() {
            this.initPagination();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 50
            });
        },
    };
    $("#exportExcel").click(function () {
        var currentHref = window.location.href;
        var now = getNowFormatDate2();
        var newHref = window.ctx + "/member/order/export?fileName=DollOrderExcel&now="+now;
        if (currentHref.indexOf("?") > -1) {
            newHref = newHref + "&" + currentHref.split("?")[1];
        }
        newHref = newHref + "&" + $("#pagination-form").serialize();
        window.location.href = newHref;
    });

    list.init();
    $(".btn-add").on("click", function() {
        location.href = ctx + "/member/order/uploadPage";
    });
});

function order(order){
    location.href = ctx + "/member/order/"+order;
}
