
var $picture = $("#imgUrl");
$(function() {

    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");

    var edit = {

        /** 初始化函数 */
        init: function() {
            this.addValidateMethods();
            this.validateForm();
        },
        /** 添加验证规则 */
        addValidateMethods: function() {
            //轮播图链接地址
            jQuery.validator.addMethod('checkLinkUrl', function(value, element) {
                var RegUrl = new RegExp();
                RegUrl.compile("^(https?|ftp|file|http)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]$");
                if (!RegUrl.test(value)) {
                    return false;
                }
                return true;
            }, "请输入合法链接地址");

        },

        /**
         * 验证表单字段
         */
        validateForm: function() {
            var validator = $form.validate({
                rules: {
                    imgUrl:{
                        required:true
                    },
                    title:{
                        required:true,
                        maxlength: 50
                    },
                    desc:{
                        required:true,
                        maxlength: 50
                    },
                    link: {
                        maxlength: 100,
                        checkLinkUrl: true
                    },

                },
                messages: {
                    title:{
                        required: "请输入标题",
                        maxlength: "最多50个字"
                    },
                    desc:{
                        required: "请输入描述",
                        maxlength: "最多50个字"
                    },
                    link: {
                        maxlength: "请输入100内容的合法URL",
                        checkUrl: true
                    }

                },
                submitHandler: function() {
                    var v = $(":input[name='type'] option:selected").val();
                    if(v==3){
                        if($("#url").val()==''){
                            //checkUrl();
                            Dialog.alert("请输入横幅链接");
                            return;
                        }
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
                                    location.href = ctx + "/agent/config/edit";
                                }, 1500);
                            } else {
                                validator.showErrors(data.errors);
                                $submitBtn.button("reset");
                                Dialog.danger(data.msg);
                            }
                        }
                    });
                }
            })
        }
    };
    edit.init();
    delFile();
});


function checkUrl(str) {
    var RegUrl = new RegExp();
    RegUrl.compile("^[A-Za-z]+://[A-Za-z0-9-_]+\\.[A-Za-z0-9-_%&\?\/.=]+$");
    if (!RegUrl.test(str)) {
        return false;
    }
    return true;
}

$('#pic_upload').fileupload({
    url: window.ctx + '/attachment/uploadImgAndCheckSize',
    dataType: 'json',
    paramName: "fileData",
    done: function (e, data) {
        if(data.result.status=="-1"){
            Dialog.danger("请上传1M以内的图片");
            return;
        }

        if(data.result.status=="error"){
            Dialog.danger("上传图片失败，请联系后台人员");
            return;
        }

        if(data.result.status=="ok") {
            var src = data.result.url;
            $("#picsFileList").html('<div style="margin-bottom:10px"><img  height="200px" class="filename" src=' + src + '>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
            delFile(data.result.link);
        }
    }
});

function delFile(fileName){
    $(".del_file").off("click").on("click",function(){
        var obj = $(this);
        if(null==fileName || fileName == ""){
            fileName = obj.attr("data-filename");
        }
        obj.parent().remove();
    })
}

$('.btn-submit').click(function(){
    var detailPicList= $(".filename");
    var detailPics = "";
    if(detailPicList.length > 0){
        for(var i=0;i<detailPicList.length;i++){
            var detailPics = detailPicList[i].src;
        }

    }else{
        Dialog.danger("请选择一张图片");
        return false;
    }
    $picture.attr("value",detailPics);
});
