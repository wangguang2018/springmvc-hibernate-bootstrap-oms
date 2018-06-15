
var $picture = $("#imgUrl");
$(function() {

    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");

    var edit = {

        /** 初始化函数 */
        init: function() {
            this.validateForm();
        },
        /**
         * 验证表单字段
         */
        validateForm: function() {
            var validator = $form.validate({
                rules: {
                    imgUrl:{
                        required:true
                    }
                },
                messages: {

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
                                    location.href = ctx + "/order/list";
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

$('#pic_upload').fileupload({
    url: window.ctx + '/member/order/uploadExcel',
    dataType: 'json',
    paramName: "fileData",
    done: function (e, data) {
        if(data.result.code==0){
            Dialog.info("上传成功");
        }else{
            Dialog.danger("上传失败，原因:"+data.result.msg);
        }

        //var src = data.result.url;
        //$("#picsFileList").html('<div style="margin-bottom:10px"><img  height="200px" class="filename" src='+ src +'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');
        //delFile(data.result.link);
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
        Dialog.danger("请选择一个excel文件");
        return false;
    }
    $picture.attr("value",detailPics);
});
