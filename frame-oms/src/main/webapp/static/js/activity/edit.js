
var $baseImg = $("#baseImg");
$(function() {

    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");
    var edit = {
        /** 初始化函数 */
        init: function () {
            this.validateForm();
        },
        /**
         * 验证表单字段
         */
        validateForm: function() {
            var validator = $form.validate({
                rules: {
                    title:{
                        required: true,
                        maxlength: 50
                    },
                    subTitle:{
                        required: true,
                        maxlength: 50
                    },
                    content:{
                        required: true,
                        maxlength: 2000
                    },
                    joinCount:{
                        required:true,
                        digits: true
                    },
                    bonus:{
                        required: true,
                        checkPrice:true
                    }
                },
                messages: {
                    title:{
                        required: "请输入50字内的标题",
                        maxlength: "最多50个字"
                    },
                    subTitle:{
                        required: "请输入50字内的副标题",
                        maxlength: "最多50个字"
                    },
                    content:{
                        required: "请输入活动内容",
                        maxlength: "最多2000个字"
                    },
                    joinCount:{
                        required: "请输入正整数的参与人数"
                    },
                    bonus:{
                        required:"请输入两位小数的奖金"
                    }

                },
                submitHandler: function() {
                    if($("#content").val()==''){
                        Dialog.danger("请填写活动内容");
                        return false;
                    }

                    var buttonObj = this.submitButton;
                    $(buttonObj).button("loading");
                    $.ajax({
                        url: $form.attr("action"),
                        type: "POST",
                        data: $form.serialize(),
                        dataType: "JSON",
                        success: function(data) {
                            if (data.code == 0) {
                                Dialog.success(data.msg, function() {
                                    location.href = ctx + "/umeditor/activity/list";
                                }, 1500);
                            } else {
                                validator.showErrors(data.errors);
                                $submitBtn.button("reset");
                                Dialog.danger(data.msg);
                            }
                        }
                    });

                }
            });
        }
    };
    edit.init();
    checkImgNum();
    $.validator.addMethod("checkPrice",function(value,element,params){
        return this.optional(element) || /^\d+(\.\d{1,2})?$/.test(value);
    },"小数位不能超过三位");
});

function checkImgNum(){
    var detailPicList=$(".filename");
    if(detailPicList.length >= 6){
        $("#pic_upload").hide();
    }else{
        $("#pic_upload").show();
    }
}


$('#base_pic_upload').fileupload({
    url: window.ctx + '/attachment/uploadImage',
    dataType: 'json',
    paramName: "fileData",
    done: function (e, data) {
        var src = data.result.url;
        $("#baseImgList").empty().append('<div class="picItem"><img  height="200px" id="base" class="filename" src='+ src +'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
        delFile(data.result.link);
        checkImgNum();
    }
});

function delFile(fileName){
    $(".del_file").off("click").on("click",function(){
        var obj = $(this);
        if(null==fileName || fileName == ""){
            fileName = obj.attr("data-filename");
        }
        obj.parent().remove();
        checkImgNum();
    })
}
delFile();

$('.btn-submit').click(function(){

    if($("#base").attr("src") == undefined){
        Dialog.danger("请选择一张活动图片");
        return false;
    }
    $baseImg.attr("value",$("#base").attr("src"));



    //校验时间
    var stringStartTime = $("input[name='startTime']").val();
    var stringEndTime = $("input[name='endTime']").val();
    if(stringStartTime=='' || stringEndTime==''){
        Dialog.danger("请选择开始时间或者结束时间");
        return false;
    }
    var timestampStartTime = Date.parse(new Date(stringStartTime));
    var timestampEndTime = Date.parse(new Date(stringEndTime));
    if(timestampStartTime>=timestampEndTime){
        Dialog.danger("结束时间必须大于开始时间");
        return false;
    }
    return true;
});
