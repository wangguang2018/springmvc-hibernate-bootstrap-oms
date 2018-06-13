
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
                    name:{
                        required: true,
                        maxlength: 50
                    },
                    phone:{
                        required: true,
                        mobile: true
                    },
                    contact:{
                        required: true,
                        maxlength: 10
                    },
                    account:{
                        required: true,
                        maxlength: 50
                    }
                },
                messages: {
                    name:{
                        required: "请输入代理商名称",
                        maxlength: "最多50字"
                    },
                    phone: {
                        required: "请输入手机号码",
                        mobile: "您输入的手机号码格式不正确"
                    },
                    contact:{
                        required: "请输入代理商联系人",
                        maxlength: "最多10字"
                    },
                    account:{
                        required: "请输入代理商后台帐号",
                        maxlength: "最多50字"
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
                                    location.href = ctx + "/agent/list";
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
    $(".btn-submit").on("click",function () {
        return checkParamData();
    });
});


function  checkParamData() {
    var name = $("#name").val();
    var phone = $("#phone").val();
    var contact = $("#contact").val();
    var account = $("#account").val();

    if(!checkStr(name,50)){
        Dialog.danger("请输入50字以内的代理商名称");
        return false;
    }
    if(!checkStr(contact,10)){
        Dialog.danger("请输入10字以内的代理商联系人");
        return false;
    }
    if(!checkStr(account,50)){
        Dialog.danger("请输入50字以内的代理商后台帐号");
        return false;
    }
    if(!checkMobile(phone)){
        Dialog.danger("请输入正确格式的手机号码");
        return false;
    }
    return true;

}


function checkStr(str,max){
    if(str==undefined || str.trim()==""){
        return false;
    }
    if(str.length>max){
        return false;
    }
    return true;
}

function checkMobile(num){
    if(num==undefined || num.trim()==''){
        return false;
    }
    var reg = new RegExp("^(0|86|17951)?(13[0-9]|15[012356789]|17[012356789]|18[012356789]|14[57])[0-9]{8}$");
    if(!reg.test(num)){
        return false;
    }
    return true;
}

