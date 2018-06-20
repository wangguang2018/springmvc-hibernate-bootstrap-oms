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
                <div class="checkbox c-checkbox">
                    <label>
                        <input type="checkbox" class="checkbox-global">
                        <span class="fa fa-check"></span>
                    </label>
                </div>
            </th>
            <th style="display: none">机器编号</th>
            <th>机器设备号</th>
            <th>娃娃</th>
            <th>是否在线</th>
            <th>处理状态</th>
            <th>游戏模式</th>
            <th>抓取娃娃几率</th>
            <th>强力电压</th>
            <th>弱力电压</th>
            <th>强转弱时间</th>
            <th>到顶转弱抓力</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty page.content}">
            <tr>
                <td colspan="12" class="text-center">没有查询到内容！</td>
            </tr>
        </c:if>
        <c:forEach items="${page.content}" var="machine" varStatus="status">
            <tr>
                <td>
                    <div class="checkbox c-checkbox">
                        <label>
                            <input type="checkbox" class="checkbox-item" name="id" value="${machine.id}" >
                            <span class="fa fa-check"></span>
                        </label>
                    </div>
                </td>
                <td style="display: none">
                    <input type="hidden" value="${machine.sn}">
                    <input type="text" class="mark-class" value="${machine.mark}" />
                    <input type="hidden" value="${machine.id}">
                </td>
                <td>${machine.sn}</td>
                <td>${machine.product.name}</td>

                <td>
                    <c:if test="${machine.online eq 1}"><span class="text-success">在线</span></c:if>
                    <c:if test="${machine.online eq 0}"><span class="text-danger">离线</span></c:if>
                </td>
                <td>
                        <c:if test="${machine.machineSet.status eq 1}">
                            等待设置
                        </c:if>
                    <c:if test="${machine.machineSet.status eq 2}">
                        设置成功
                    </c:if>
                    <c:if test="${machine.machineSet.status eq 3}">
                        设置失败
                    </c:if>
                </td>
                <td>
                    <c:if test="${machine.machineSet.gameMode eq 0 }">弱抓力模式</c:if>
                    <c:if test="${machine.machineSet.gameMode eq 1 }">固定强抓力</c:if>
                    <c:if test="${machine.machineSet.gameMode eq 2 }">随机强抓力</c:if>
                    <c:if test="${machine.machineSet.gameMode eq 3 }">固定强抓力--补</c:if>
                    <c:if test="${machine.machineSet.gameMode eq 4 }">随机强抓力--补</c:if>
                </td>
                <td><c:out value="${machine.machineSet.probability}" default="-" /></td>
                <td><c:out value="${machine.machineSet.strongVoltage}" default="-" />V</td>
                <td><c:out value="${machine.machineSet.smallVoltage}" default="-" />V</td>
                <td><c:out value="${machine.machineSet.changeTime}" default="-" />秒</td>
                <td>
                    <c:if test="${machine.machineSet.changeWeak eq 0 }">关闭</c:if>
                    <c:if test="${machine.machineSet.changeWeak eq 1 }">开启</c:if>
                </td>
                <td>
                    <a href="javascript:showSetting(${machine.id},'${machine.sn}')">&nbsp;&nbsp;&nbsp;&nbsp;机器设置</a>
                    <a href="javascript:paramSet(${machine.id})">&nbsp;&nbsp;&nbsp;&nbsp;机器推流设置</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:import url="/WEB-INF/layouts/pagination.jsp" />
</div>
<c:import url="/WEB-INF/views/machine/addProductStock.jsp" />
<style>
/*.sort-class {
    width : 60px;
    border-style : none;
}
.mark-class{
    width : 60px;
    border-style : none;
}*/
</style>
<script src="${ctx}/static/js/machine/mark.js"></script>
<script>
$(function(){

    $(".sort-class").css({"width":"60px","border-style":"none"});

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


function updateType(id,type) {
    if(!(type == 1 || type ==2)){
        Dialog.danger("数据错误");
        return;
    }
    var meg = "";
    if(type == 1){
        meg = "确认要改成线下吗？";
    }else {
        meg = "确认要改成线上吗？";
    }
    Dialog.confirm("修改提示", meg , function(result) {
        if (result == true) {
            $.ajax({
                url: window.ctx + "/machine/updateType",
                type: "POST",
                dataType: "json",
                data:{id:id},
                success: function(data) {
                    if(data.code == 0){
                        Dialog.success(data.msg, function() {
                            location.href = ctx + "/machine/list";
                        }, 1500);
                    }else {
                        Dialog.danger(data.msg);
                    }

                }
            });
        }
    });

}

</script>

