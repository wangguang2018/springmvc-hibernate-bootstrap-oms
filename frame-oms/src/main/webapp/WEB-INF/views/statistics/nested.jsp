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
            <th>
                <!-- <div class="checkbox c-checkbox">
                    <label>
                        <input type="checkbox" class="checkbox-global">
                        <span class="fa fa-check"></span>
                    </label>
                </div> -->
            </th>
            <th>机器编号</th>
            <th>产品名称</th>
            <th>钻石消费值</th>
            <th>抓取失败总数</th>
            <th>抓取成功总数</th>
            <th>抓取总数</th>
            <th>抓取成功率</th>
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
                <td>
                    <!-- <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${sta.id}" >
                            <span class="fa fa-check"></span>
                        </label>
                    </div> -->
                </td>
                <td>${sta.sn}</td>
                <td>${sta.name}</td>
                <td>${sta.tot1}</td>
                <td>${sta.fail}</td>
                <td>${sta.success}</td>
                <td>${sta.fail+sta.success}</td>
                <td>
                <c:if test="${empty sta.sucRate}" >0.00</c:if>
                <c:if test="${not empty sta.sucRate}" ><fmt:formatNumber type="number" value="${sta.sucRate}" pattern="0.00" maxFractionDigits="2"/> </c:if></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:import url="/WEB-INF/layouts/pagination.jsp" />
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
