
$(function() {
    var $form = $("#form-update");
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
                    title: {
                        required: true,
                        maxlength: 30
                    },
                    versionCode: {
                        required: true
                    },
                    content:{
                        required: true
                    },
                    updateUrl:{
                         required: true
                    }
                },
                messages: {
                    title: {
                        required: "请输入升级标题",
                        maxlength: "升级标题长度不能超过{0}个字符"
                    },
                    versionCode: {
                        required: "请填写版本号"
                    },
                    content:{
                        required: "请填写内容"
                    },
                     updateUrl:{
                         required: "请上传软件包"
                    }
                },
                submitHandler: function() {
                 console.log("into......");
                    var buttonObj = this.submitButton;
                    $(buttonObj).button("loading");

                    var $updateUrl = $("#updateUrl");
                    if($updateUrl==undefined || $updateUrl.val()==''){
                                            Dialog.alert("请上传软件包");
                                            return;
                    }


                    var machineIds = $('#machineList').data('multiselect').selectList();

                    console.log("machineIds:"+machineIds);
                    if(machineIds === null){
                        machineIds = ''
                    }
                    $.ajax({
                        url: $form.attr("action"),
                        type: "POST",
                        data: $form.serialize()+"&machineIds="+machineIds,
                        dataType: "JSON",
                        success: function(data) {
                            if (data.code == 0) {
                                Dialog.success(data.msg, function() {
                                    location.href = ctx + "/machine/list";
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

    $("#detailPic_upload").uploadify(
            {
                height : 30,
                auto : true,
                swf : window.ctx + '/static/libs/uploadify/uploadify.swf',
                uploader : window.ctx + '/attachment/uploadApk;jsessionid=${jsessionid}',
                width : 120,
                fileObjName : 'file',
                fileTypeDesc: 'aaa',
                //允许上传的文件后缀
                //fileTypeExts: '*.gif; *.jpg; *.jpeg; *.png',
                fileTypeExts: '*.*',
                buttonText : "上传",
                debug : false,
                uploadLimit : 10,
                onUploadStart : function() {
                },
                onFallback:function(){
                   //在初始化时检测不到浏览器有兼容性的flash版本时实触发
                   var bb = "bb";

                },
                onUploadError : function(file, errorCode, errorMsg, errorString) {
                    alert('The file ' + file.name
                        + ' could not be uploaded: '
                        + errorString);
                },
                onUploadSuccess : function(file, data, response) {
                if(file.size>=104857600){
                     $("#detailPicFileList").html('<span>上传文件请小于100MB</span>');
                     $("#updateUrl").attr("value","");
                }else{

                    data = eval("(" + data + ")");
                    console.log(data);
                    if(data.state=='FAIL'){
                        $("#detailPicFileList").html('<span>'+data.mes+'</span>');
                         $("#updateUrl").attr("value","");
                    }else{

                     /*$("#detailPicFileList")
                                        .append(
                                            '<div style="margin-bottom:10px"><img  height="200" width= "200" class="filename2" src='+data.image_url+data.url+'>&nbsp;&nbsp;<a class="btn red del_file" >删除</a></div>');*/
                       $("#detailPicFileList").html('<span>上传已完成！</span>');
                       $("#updateUrl").attr("value",data.url);
                        delFile(data.url);
                        //$("#btn-save-goods").prop("disabled", false);文件要小于100M
                    }


                }

                }
            });

        delFile();

        function delFile(fileName){
            $(".del_file").off("click").on("click",function(){
                var obj = $(this);
                if(null==fileName || fileName == ""){
                    fileName = obj.attr("data-filename");
                }

                obj.parent().remove();

            })
        }
});

