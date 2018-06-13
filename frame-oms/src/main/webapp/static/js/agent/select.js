$(function(){

    $.ajax({
        url: window.ctx + "/agent/getMyList",
        dataType:'json',
        success:function(data){
            $("#agents").select2({
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
})