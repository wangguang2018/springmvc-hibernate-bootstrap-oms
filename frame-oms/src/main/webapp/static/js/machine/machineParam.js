$machineParam = $("#machineParam");
$machineParamSubmitFlag = false;
$(function(){


    init();
    $(".exposure-div").show();

    $("input[name='encodeLevelSwitch']").on('click',function () {
        var checked = $("input[name='encodeLevelSwitch']:checked").val();
        if(checked==1){
            $(".encodeLevel-div").show();
        }else{
            $(".encodeLevel-div").hide();
        }
    });



});

function paramSet(id) {
    //id==-1 对列表查询到的所有机器进行设置
    //id != -1 //对当前的机器进行设置

    $.ajax({
        url: window.ctx + "/machine/paramPage",
        type: "GET",
        data: {
            id: id
        },
        success: function(data) {
            $machineParam.find(".modal-content").empty().append(data);
            $machineParam.modal('show');
            bindElementEvent(id);
            initMyData();
        }
    })

}

function initMyData() {
    /*var setting = $('input:radio[name="openExposureSetting"]:checked').val();
    if(setting==1){//开启
        $(".exposure-div").show();
    }else{
        $(".exposure-div").hide();
    }*/
}

function machineSetListener(id) {
    $(".machine-set").on("click",function () {
        if($(this).hasClass("reset-machine")){
            Dialog.confirm("重启提示", "确认要重启机器吗？", function(result) {
                if (result == true) {
                    $.ajax({
                        url: window.ctx + "/machine/set/1",
                        type: "GET",
                        dataType: "json",
                        data:{id:id},
                        success: function(data) {
                            if(data.code == 0){
                                Dialog.info(data.msg);
                            }else {
                                Dialog.danger(data.msg);
                            }
                            $machineParam.modal('hide');
                            $machineParamSubmitFlag = false;

                        }
                    });
                }
            });
        }

        if($(this).hasClass("close-machine")) {
            Dialog.confirm("关闭提示", "确认要关闭机器吗？", function (result) {
                if(result){
                    $.ajax({
                        url: window.ctx + "/machine/set/2",
                        type: "GET",
                        dataType: "json",
                        success: function (data) {
                            if(data.code == 0){
                                Dialog.info(data.msg);
                            }else {
                                Dialog.danger(data.msg);
                            }
                            $machineParam.modal('hide');
                            $machineParamSubmitFlag = false;

                        }
                    });
                }
            });
        }

    });
}
function bindElementEvent(id) {

    machineSetListener(id);

    /*$("input[name='openExposureSetting']").on("click",function () {
        var setting = $('input:radio[name="openExposureSetting"]:checked').val();
        if(setting==1){//开启
            $(".exposure-div").show();
        }else{
            $(".exposure-div").hide();
        }
    });*/

    $("#machineId").val(id);
    $("#encode-level-select").change(function () {
        var userDefined = $("#userDefined").val();
        var selectedVal = $("#encode-level-select").val();
        $("input[name='encodeLevel']").val(selectedVal);
        if(selectedVal == userDefined){
            $(".user-defined-div").show();
        }else{
            $(".user-defined-div").hide();
        }

    });

    $("#exposureMode").change(function () {
        $("input[name='exposureMode']").val($("#exposureMode").val());
    });

    $("#rotationAngle").change(function () {
        $("input[name='rotationAngle']").val($("#rotationAngle").val());
    });



    $(".btn-machine-param ").on("click", function() {
        /*if($machineParamSubmitFlag){
            Dialog.danger("不能重复设置");
            return ;
        }*/
        var tabType = 0;
        var curSelectedTab = $(".nav-ul-task").eq(0).find(".active a").attr("href");
        if(curSelectedTab=='#camera'){
            tabType = 1;
        }else if(curSelectedTab=='#stream'){
            tabType = 2;
        }

        $machineParamSubmitFlag = true;
        $.ajax({
            url: window.ctx + "/machine/setParam",
            type: "POST",
            dataType: "json",
            data: $("#form-machine-param").serialize()+"&"+$("#pagination-form").serialize()+"&tabType="+tabType,
            success: function(data) {
                if(data.code == 0){
                    Dialog.info(data.msg);
                }else {
                    Dialog.danger(data.msg);
                }
                $machineParam.modal('hide');
                $machineParamSubmitFlag = false;

            }
        })

    });
}












function getIds (items) {
    var ids = [];
    if (typeof items != "undefined") {
        items.each(function() {
            if (this.checked) {
                ids.push(this.value);
            }
        });
    }
    return ids;
}



function init() {
    $machineParam.modal("show");

}



