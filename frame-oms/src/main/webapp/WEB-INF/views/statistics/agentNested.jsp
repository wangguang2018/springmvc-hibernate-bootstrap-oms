<%--
  Created by IntelliJ IDEA.
  User: whan
  Date: 10/9/15
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th></th>
            <th>代理商</th>
            <th>用户总量</th>
            <th>机器总数</th>
            <th>今日新增用户</th>
            <th>累计充值总额</th>
            <th>月充值记录</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="17" class="text-center">没有查询到内容！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="sta" varStatus="status">
            <tr>
                <td></td>
                <td>${sta.agentName}</td>
                <td>${sta.todayMember}</td>
                <td>${sta.totalMember}</td>
                <td>${sta.totalMachine}</td>
                <td>${sta.totalCharge}元</td>
                <td>
                    <c:if test="${empty sta.monthCharge}">0.00元</c:if>
                    <c:if test="${not empty sta.monthCharge}">${sta.monthCharge}元</c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:import url="/WEB-INF/views/statistics/pagination.jsp" />
</div>
<style>
.sort-class{
    width:60px
}
</style>

<script>
$(function(){
    $(".sort-class").on('blur',function(){
        $(this).css("border-style","none");
        var id = $(this).next().val();
        var sort = $(this).val();
        var reg = new RegExp("^[0-9]*$");
        if(!reg.test(sort)){
            Dialog.alert("请输入数字!");
            return false;
        }
        var sn = $(this).prev().val();
        console.log("id:"+id);
        $.ajax({
          url: window.ctx + "/machine/updateSort",
          type: "POST",
          data: {
                  "id":id,
                  "sort":sort
                },
          dataType: "JSON",
          success: function(data) {
            if(data.code != 0){
              Dialog.alert("编号为："+sn+"的机器排序更新失败");
            }
          }
        });
        
    });

    $(".sort-class").on('focus',function(){
      $(this).css("border-style","block");
    });

});

</script>
