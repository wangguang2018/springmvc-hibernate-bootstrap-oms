
var $picture = $("#imgs");
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
                    tagName:{
                        required: true,
                        maxlength: 4
                    },
                    sn:{
                        required: true,
                        maxlength: 50
                    },
                    liveRoom1:{
                        required: true,
                        maxlength: 10
                    },
                    liveRoom2:{
                        required: true,
                        maxlength: 10
                    }
                },
                messages: {
                    tagName:{
                        required: "请输入机器标签",
                        maxlength: "最多4个字"
                    },
                    name:{
                        required: "请输入娃娃机编号",
                        maxlength: "最多50个字"
                    },
                    liveRoom1:{
                        required: "请输入房间号",
                        maxlength: "最多10个字"
                    },
                    liveRoom2:{
                        required: "请输入房间号",
                        maxlength: "最多10个字"
                    }
                },
                submitHandler: function() {
                    var buttonObj = this.submitButton;
                    $(buttonObj).button("loading");
                    var machineIds = $('#machineList').data('multiselect').selectList();
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
                                    location.href = ctx + "/tag/list";
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
    initCategory();
});



function  initCategory() {
    $.ajax({
        url: window.ctx + "/tag/category/list",
        dataType:'json',
        success:function(data){
            $("#categories").select2({
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

