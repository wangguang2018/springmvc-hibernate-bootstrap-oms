$(function() {
    var list = {
        /** 初始化 */
        init: function() {
            this.initPagination();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 10,
            });
        },
    };
    list.init();
    if($("#isAgent").val()=='false'){
        agentPageInit(1);
    }
});

function agentPageInit(page){
    $.ajax({
        url: ctx+"/echart/line/agent",
        type: "POST",
        data: {page:page,pageSize:5},
        success: function(data) {
            $("#agent-pagination-body").empty().append(data);
        }
    })
}




