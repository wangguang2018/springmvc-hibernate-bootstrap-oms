
$(function(){
    $("#streamParamModal").modal("hide");

    $("input[name='encodeLevelSwitch']").on('click',function () {
        var checked = $("input[name='encodeLevelSwitch']:checked").val();
        if(checked==1){
            $(".encodeLevel-div").show();
        }else{
            $(".encodeLevel-div").hide();
        }
    });

    $(".streamsParamPage").on("click", function() {
        var ids = Pagination.getCheckedIds();

        if (ids.length <= 0) {
            Dialog.warning("请选择要修改的机器");
            return;
        }
        $.ajax({
            url: window.ctx + "/machine/levelModal",
            type: "POST",
            data: {
                id: ids
            },
            success: function(data) {
                $("#modifyEncodeLevel").find(".modal-content").empty().append(data);
                $("#modifyEncodeLevel").modal('show');
                bindModifyEvent();
            }
        })

    });

});


function bindModifyEvent() {
    $(".btn-modify-level").on("click", function() {
        /*var $this = $(this);

        $this.button("loading");*/
        $('#modify-div').button("loading");

        // 获取选择中的站点
        $.ajax({
            url: window.ctx + "/machine/saveLevel",
            type: "POST",
            data: $("#set-form").serialize(),
            dataType: "json",
            success: function(data) {
                Dialog.success('操作成功');
                $("#modifyEncodeLevel").modal('hide');
            }
        })
    });

    $("#masterExposureAll").on("change",function () {
        $(".masterExposure").val($(this).val());
    });

    $("#slaveExposureAll").on("change",function () {
        $(".slaveExposure").val($(this).val());
    });

    $("#levelsAll").on("change",function () {
        var val = $(this).val();
        $(".select-level").find("option[value="+val+"]").prop("selected","selected");
        $(".encodeLevel").val(val);
    });


    $(".select-level").on("change",function () {
        var levelV = $(this).val();
        $($(this).parents("td")[0]).find("input").val(levelV);
    });
}


function streamParamPage(){
    init();

    var checkItems = $("#pagination-form").find("input[class='checkbox-item']");
    // 查询选中的复选框值
    var ids = getIds(checkItems);
    // 判断是否选中了要编辑的内容
    if (ids.length < 1) {
        Dialog.danger("请先选中您要勾选的内容");
        return;
    }
    $('#streamParamModal').modal();
}

function streamsParamPage() {
    init();

    var checkItems = $("#pagination-form").find("input[class='checkbox-item']");
    // 查询选中的复选框值
    var ids = getIds(checkItems);
    // 判断是否选中了要编辑的内容
    if (ids.length < 1) {
        Dialog.danger("请先选中您要勾选的内容");
        return;
    }
    $('#streamParamModal').modal();
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
    $(".param").val(120);
    $("input[name='encodeLevelSwitch'][value='0']").prop("checked",true);
    $(".encodeLevel-div").hide();


}

function init() {
    $(".param").val(120);
    $("input[name='encodeLevelSwitch'][value='0']").prop("checked",true);
    $(".encodeLevel-div").hide();


}

function checkParam() {
    var flag = true;
    $(".param").each(function () {
        if($(this).val()<=0){
            Dialog.danger("请设置大于0的整数");
            flag = false;
        }
    });
    return flag;
}

$("#setStreamParam").click(function () {

    //校验
    if(!checkParam()){
        return;
    }
    var checkItems = $("#pagination-form").find("input[class='checkbox-item']");
    // 查询选中的复选框值
    var ids = getIds(checkItems);

    var masterExposure = $("input[name='masterExposure']").val();
    var slaveExposure = $("input[name='slaveExposure']").val();
    var encodeLevelSwitch = $("input[name='encodeLevelSwitch']:checked").val();
    var encodeLevel = $("select[name='encodeLevel']").val();

    $("#setStockBtn").button("loading");
    $.ajax({
        url: ctx+"/machine/setStream",
        type: "POST",
        data: {
            id:ids,
            masterExposure : masterExposure,
            slaveExposure : slaveExposure,
            encodeLevelSwitch : encodeLevelSwitch,
            encodeLevel : encodeLevel
        },
        dataType: "JSON",
        success: function(data) {
            if (data.code == 0) {
                Dialog.success(data.msg, function() {
                    $("#streamParamModal").modal("hide");
                    Pagination.reload();
                }, 1500);
            } else {
                validator.showErrors(data.errors);
                $("#streamParamModal").modal("hide");
                Dialog.danger(data.msg);
            }
        }
    });
})