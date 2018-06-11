
$(function () {
    $(".task-set").on("click",function () {
        var agentId = $($(this).parents('tr')[0].cells[0]).find('input').val();
        console.log("agentId:"+agentId);
        location.href = ctx + "/featureConfig/edit/"+agentId;
    });

    $(function () {
        $(".prize-set").on("click",function () {
            var agentId = $($(this).parents('tr')[0].cells[0]).find('input').val();
            console.log("agentId:"+agentId);
            location.href = ctx + "/featureConfig/edit/"+agentId;
        });
    });

});


