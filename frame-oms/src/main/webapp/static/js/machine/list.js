$(function() {
    var list = {
        /** 初始化 */
        init: function() {
            this.initPagination();
            this.bindDisableAction();
            this.bindMachineParamSet();
        },

        /** 初始化分页 */
        initPagination: function() {
            Pagination.init({
                pageSize: 50,
                editBtn: {
                    url: ctx + "/machine/edit/{id}"
                },
                deleteBtn: {
                    url: ctx + "/machine/delete",
                    callback: function(data) {

                    }
                }
            });
        },

        bindMachineParamSet:function () {

        },
        /** 绑定禁用操作 */
        bindDisableAction: function() {
            $(".btn-disable").on("click", function() {
                var checkItems = $("#pagination-form").find("input[class='checkbox-item']");
                // 查询选中的复选框值
                var ids = getIds(checkItems);
                // 判断是否选中了要编辑的内容
                if (ids.length < 1) {
                    Dialog.info("请先选中您要勾选的机器");
                    return;
                }

                Dialog.confirm("删除机器", "确认要删除这些机器吗？",  function(result) {
                    if (result == true) {
                        $.ajax({
                            url: ctx + "/machine/delete",
                            type: "POST",
                            data: {
                                id: ids
                            },
                            dataType:"json",
                            success: function(data) {
                                if (data.code == 0) {
                                    Dialog.success(data.msg);
                                    Pagination.reload();
                                }
                            }
                        })
                    }
                });
            });
        },
    };
    list.init();
    $(".btn-add").on("click", function() {
        location.href = ctx + "/machine/edit";
    });

    $(".btn-online-update").on("click", function() {
        location.href = ctx + "/machine/onlineUpdate";
    });

    $.ajax({
        url: window.ctx + "/agent/getList",
        dataType:'json',
        success:function(data){
            $(".agents").select2({
                data: data,
                matcher: function (params, data) {
                    if ($.trim(params.term) === '') {
                        return data;
                    }
                    if (data.text.indexOf(params.term) > -1) {
                        return $.extend({}, data, true);
                    }
                    return null;
                }
            });
        }
    })


});

/**
 * 查询选中的复选框值
 *
 * @param items
 */
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

function getProductList(sn){
    $.ajax({
        url: window.ctx + "/product/getList/"+sn,
        dataType:'json',
        success:function(data){
            $("#productId").select2({
                data: data,
                matcher: function (params, data) {
                    if ($.trim(params.term) === '') {
                        return data;
                    }
                    if (data.text.indexOf(params.term) > -1) {
                        return $.extend({}, data, true);
                    }
                    return null;
                }
            });
        }
    })
}

function view(id){
    window.open(ctx + "/liveroom/info/"+id);
}

function showSetting(id,sn){
    $("#settingModal").modal('show');
    $.ajax({
        url: ctx + "/machine/machineSet",
        type: "post",
        dataType: "JSON",
        data: {
            machineId: id
        },
        success: function(data) {
            if (data.code == 0) {
                var machine = data.machine;
                if(machine.status==1){
                    $("#status_mark").html("处理状态： 等待设置");
                }else if(machine.status==2){
                    $("#status_mark").html("处理状态： 设置成功");
                }else if(machine.status==3){
                    $("#status_mark").html("处理状态： 设置失败");
                }
                $("#gameMode").val(machine.gameMode);
                $("#probability").val(machine.probability);
                $("#strongVoltage").val(machine.strongVoltage);
                $("#smallVoltage").val(machine.smallVoltage);
                $("#changeTime").val(machine.changeTime);
                $("#changeWeak").val(machine.changeWeak);
            }
        }
    });

    function checkDigt(num){
        if(num==undefined || num.trim()==''){
            return false;
        }
        var reg = new RegExp("^[0-9]*$");
        if(!reg.test(num)){
            return false;
        }
        return true;
    }

    //校验必须有1位小数的数字
    function  checkOneFloat(str) {
        return /^\d+\.\d{1}$/.test(str);
    }

    function checkAll(gameMode,probability,strongVoltage,smallVoltage,changeTime,changeWeak) {
        if(!checkDigt(gameMode)){
            Dialog.danger("游戏模式请输入整数");
            return false;
        }
        if(!(gameMode>=0 && gameMode<=4)){
            Dialog.danger("游戏模式请输入0~4之间");
            return false;
        }
        if(!checkDigt(probability)){
            Dialog.danger("抓取娃娃几率请输入数字");
            return false;
        }
        if(!(probability>=1 && probability<=888)){
            Dialog.danger("抓取娃娃几率请输入1~888之间");
            return false;
        }
        if(!checkOneFloat(strongVoltage)){
            Dialog.danger("强力电压请保留1位有效数字");
            return false;
        }
        if(!(strongVoltage>=15.0 && strongVoltage<=47.5)){
            Dialog.danger("强力电压请输入15.00~47.50之间");
            return false;
        }

        if(!checkOneFloat(smallVoltage)){
            Dialog.danger("弱力电压请保留1位有效数字");
            return false;
        }
        if(!(smallVoltage>=4.5 && smallVoltage<=40)){
            Dialog.danger("弱力电压请输入4.5~40之间");
            return false;
        }

        if(!checkOneFloat(changeTime)){
            Dialog.danger("强转弱时间请保留1位有效数字");
            return false;
        }
        if(!(changeTime>=0.10 && changeTime<=3)){
            Dialog.danger("强转弱时间请输入0.10~3之间");
            return false;
        }
        if(changeWeak!=0 && changeWeak!=1){
            Dialog.danger("请输入0或者1");
            return false;
        }
        return true;
    }

    $('#changeSetting').unbind("click");
    $("#changeSetting").click(function(){
        var gameMode = $("#gameMode").val();
        var probability = $("#probability").val();
        var strongVoltage = $("#strongVoltage").val();
        var smallVoltage = $("#smallVoltage").val();
        var changeTime = $("#changeTime").val();
        var changeWeak = $("#changeWeak").val();
        if(!checkAll(gameMode,probability,strongVoltage,smallVoltage,changeTime,changeWeak)){
            return false;
        }

        $.ajax({
            url: ctx+"/machine/setting",
            type: "POST",
            data: {"machineId":id,"gameMode":gameMode,"probability":probability,"strongVoltage":strongVoltage,"smallVoltage":smallVoltage,"changeTime":changeTime,"changeWeak":changeWeak},
            dataType: "JSON",
            success: function(data) {
                if (data.code == 0) {
                    Dialog.success("设置成功", function() {
                        location.href = ctx + "/machine/list";
                    }, 1500);
                } else {
                    validator.showErrors(data.errors);
                    $submitBtn.button("reset");
                    Dialog.danger(data.msg);
                }
                $("#settingModal").modal('hide');
            }
        });
    });
}


function choosePro(id,sn,productId){
    initProductsData(productId,sn);
    //getProductList(sn)
    $("#chooseModal").modal('show');
    $('#chooseSetting').unbind("click")
    $("#chooseSetting").click(function(){
        var productId = $("#productId").val();
        if(productId==-1){
            Dialog.danger("请选择娃娃！");
            return;
        }
        $.ajax({
            url: ctx+"/machine/choosePro",
            type: "POST",
            data: {"id":id,"productId":productId},
            dataType: "JSON",
            success: function(data) {
                if (data.code == 0) {
                    Dialog.success("设置成功", function() {
                        location.href = ctx + "/machine/list";
                    }, 1500);
                } else {
                    validator.showErrors(data.errors);
                    $submitBtn.button("reset");
                    Dialog.danger(data.msg);
                }
                $("#chooseModal").modal('hide');
            }
        });
    });
}
function initMachineData(agentId){

    $.fn.modal.Constructor.prototype.enforceFocus = function () { }
    $.ajax({
        url: window.ctx + "/agent/getMyListAgents",
        dataType:'json',
        async:false,
        success:function(data){
            /*$(".agent-select").attr('data-init', '测试2222');
            $(".agent-select").attr('value', '14');*/
            $(".agent-select").select2({
                data: data,
                matcher: function (params, data) {
                    if ($.trim(params.term) === '') {
                        return data;
                    }
                    if (data.text.indexOf(params.term) > -1) {
                        return $.extend({}, data, true);
                    }
                    return null;
                }
            }).val(""+agentId).trigger("change");

        }
    });
}

function initProductsData(productId,sn){

    $.fn.modal.Constructor.prototype.enforceFocus = function () { }
    $.ajax({
        url: window.ctx + "/product/getList/"+sn,
        dataType:'json',
        async:false,
        success:function(data){
            /*$(".agent-select").attr('data-init', '测试2222');
            $(".agent-select").attr('value', '14');*/
            $("#productId").select2({
                data: data,
                matcher: function (params, data) {
                    if ($.trim(params.term) === '') {
                        return data;
                    }
                    if (data.text.indexOf(params.term) > -1) {
                        return $.extend({}, data, true);
                    }
                    return null;
                }
            }).val(""+productId).trigger("change");

        }
    });

}

function chooseAgent(id,agentId){
    $("#agentModal").modal('show');
    initMachineData(agentId);

    $('#chooseAgent').unbind("click")
    $("#chooseAgent").click(function(){
        var agentId = $("#agentSelect").val();
        if(agentId==''){
            Dialog.danger("请选择代理商！");
            return;
        }
        $.ajax({
            url: ctx+"/machine/chooseAgent",
            type: "POST",
            data: {"id":id,"agentId":agentId},
            dataType: "JSON",
            success: function(data) {
                if (data.code == 0) {
                    Dialog.success("设置成功", function() {
                        location.href = ctx + "/machine/list";
                    }, 1500);
                } else {
                    validator.showErrors(data.errors);
                    $submitBtn.button("reset");
                    Dialog.danger(data.msg);
                }
                $("#agentModal").modal('hide');
            }
        });
    });
}


function updateStock(id){
    $("#addProductStock").modal('show');
    //initMachineData(agentId);

    $('#stock-submit').unbind("click")
    $("#stock-submit").click(function(){
        var stockNum = $("#stockNum").val();
        /*if(agentId==''){
            Dialog.danger("请选择代理商！");
            return;
        }*/
        $.ajax({
            url: ctx+"/machine/updateStock",
            type: "POST",
            data: {"id":id,"stockNum":stockNum},
            dataType: "JSON",
            success: function(data) {
                if (data.code == 0) {
                    Dialog.success("设置成功", function() {
                        location.href = ctx + "/machine/list";
                    }, 1500);
                } else {
                    validator.showErrors(data.errors);
                    $submitBtn.button("reset");
                    Dialog.danger(data.msg);
                }
                $("#addProductStock").modal('hide');
            }
        });
    });
}
