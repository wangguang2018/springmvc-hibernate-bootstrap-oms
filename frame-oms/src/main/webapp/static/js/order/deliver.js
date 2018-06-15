$(function () {
    var $form = $("#form-edit");
    var $submitBtn = $(".btn-submit");
    var edit = {

        /** 初始化函数 */
        init: function () {
            this.validateForm();
        },

        /** 验证表单字段 */
        validateForm: function () {
            var validator = $form.validate({
                ignore: "",
                rules: {
                  expressNo: {
                    required: true,
                    maxlength: 20
                  },
                  expressName: {
                    required: true,
                    maxlength: 50
                  }

                },
                messages: {
                    expressNo: {
                        required: "请输入单号",
                        maxlength: "单号过长"
                    },
                    expressName: {
                      required: "请输入快递名称",
                      maxlength: "名称过长"
                    }
                },

                submitHandler: function () {
                    var buttonObj = this.submitButton;
                    $(buttonObj).button("loading");
                    $.ajax({
                        url: $form.attr("action"),
                        type: "POST",
                        data: $form.serialize(),
                        dataType: "JSON",
                        success: function (data) {
                            if (data.code == 0) {
                                Dialog.success(data.msg, function () {
                                    location.href = window.ctx + "/member/order/list";
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


    
    //隐藏快递名称
    if(100 !=$("select[name='type']").val()){
      $(".express-name").hide();
      $("input[name='expressName']").val($("select[name='type']").find("option:selected").text().trim());
    }    

    $("select[name='type']").on('change',function(){
      if(100==$("select[name='type']").val()){//其他
        $(".express-name").show();
        $("input[name='expressName']").val("");
      }else{
        $("input[name='expressName']").val($("select[name='type']").find("option:selected").text().trim());
        $(".express-name").hide();
      }
      
    });

});