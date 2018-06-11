
var $picture = $("#imgs");
var $winImg = $("#winImg");
var $detailImg = $("#detailImg");
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
                    name:{
                        required: true,
                        maxlength: 11
                    },
                    diamondRate:{
                        required: true,
                        digits: true
                    },
                    stock:{
                        required: true,
                        digits: true
                    }
                },
                messages: {
                    name:{
                        required: "请输入兑换商品名称",
                        maxlength: "最多50个字"
                    },
                    diamondRate:{
                        required: "请输入商品实际价值"
                    },
                    stock:{
                        required: "请输入商品库存"
                    }
                },
                submitHandler: function() {
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
                                    location.href = ctx + "/product/exchangeProduct/list";
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
    var detailPicList=$(".detailImgs");
    if(detailPicList.length >= 5){
        $("#detail_pic_upload").hide();
    }else{
        $("#detail_pic_upload").show();
    }
}

$('#pic_upload').fileupload({
    url: window.ctx + '/attachment/uploadImage',
    dataType: 'json',
    paramName: "fileData",
    done: function (e, data) {
        var src = data.result.url;
        $("#picsFileList").empty().append('<div class="picItem"><img  class="productImgs" src='+ src +'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
        delFile(data.result.link);
    }
});

$('#detail_pic_upload').fileupload({
    url: window.ctx + '/attachment/uploadImage',
    dataType: 'json',
    paramName: "fileData",
    done: function (e, data) {
        var src = data.result.url;
        $("#detailPicsFileList").append('<div class="picItem"><img   id="detail" class="detailImgs filename" src='+ src +'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
        delFile(data.result.link);
        checkImgNum();
    }
});

$('#win_pic_upload').fileupload({
    url: window.ctx + '/attachment/uploadImage',
    dataType: 'json',
    paramName: "fileData",
    done: function (e, data) {
        var src = data.result.url;
        $("#winPicsFileList").empty().append('<div class="picItem"><img   id="win" class="winImgs filename" src='+ src +'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
        delFile(data.result.link);
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
    var proPicList=$(".productImgs");
    var proPics = "";
    if(proPicList.length > 0){
        for(var i=0;i<proPicList.length;i++){ //循环为每个img设置
            var index = proPicList[i].src;
            proPics +=  index + ";";
        }
    }else{
        Dialog.danger("请选择一张兑换商品图片");
        return false;
    }
    var detailPicList=$(".detailImgs");
    var detailPics = "";
    if(detailPicList.length > 0){
        for(var i=0;i<detailPicList.length;i++){ //循环为每个img设置
            var index = detailPicList[i].src;
            detailPics +=  index + ";";
        }
    }else{
        Dialog.danger("请选择一张兑换商品图片");
        return false;
    }

    /*if($("#win").attr("src") == undefined){
        Dialog.danger("请选择一张中奖图");
        return false;
    }*/
    $picture.attr("value",proPics);
    $detailImg.attr("value",detailPics);
    //$winImg.attr("value",$("#win").attr("src"));

    return true;
});
