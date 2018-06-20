
$(function(){

    $(".mark-class").css({"width":"60px","border-style":"none"});

    $(".mark-class").on('blur',function(){
        $(this).css("border-style","none");
        var id = $(this).next().val();
        var mark = $(this).val().trim();
        var sn = $(this).prev().val();
        var checkFlag = true;
        console.log("id:"+id);
        if(mark == undefined || mark.trim()==''){
            return;
        }
        $.ajax({
            url: window.ctx + "/machine/checkMark",
            type: "POST",
            async : false,
            data: {
                "id":id,
                "mark":mark
            },
            dataType: "JSON",
            success: function(data) {
                if(data.code != 0){
                    checkFlag = false;
                    Dialog.danger("设备号为("+sn+")的机器:"+data.msg);
                }
            }
        });

        console.log("flag:"+checkFlag);
        if(!checkFlag){
            return;
        }
        updateMark(id,mark,sn);

    });

    $(".mark-class").on('focus',function(){
        $(this).css("border-style","block");
    });

});




function updateMark(id,mark,sn){
    $.ajax({
        url: window.ctx + "/machine/updateMark",
        type: "POST",
        data: {
            "id":id,
            "mark":mark
        },
        dataType: "JSON",
        success: function(data) {
            if(data.code != 0){
                Dialog.danger("设备号为("+sn+")的机器:"+data.msg);
            }
        }
    });
}