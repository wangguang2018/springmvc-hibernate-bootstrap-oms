
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

                    $.ajax({
                        url: $form.attr("action"),
                        type: "POST",
                        data: $form.serialize(),
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
});

