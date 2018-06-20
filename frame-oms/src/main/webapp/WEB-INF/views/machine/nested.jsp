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
            <th>设备IP</th>
            <th>控制端版本号</th>
            <th>娃娃</th>
            <th>代理商</th>
            <th>是否在线</th>
            <th>是否维护</th>
            <%--<th>产品库存</th>--%>
            <th>排序</th>
            <%--<th>注册时间</th>--%>
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
                <td>${machine.clientIp}</td>
                <td>${machine.versionCode}</td>
                <td>${machine.product.name}</td>
                <td>${machine.agent.name}</td>
                    <%--<td>${machine.chatRoom}</td>--%>

                <td>
                    <c:if test="${machine.online eq 1}"><span class="text-success">在线</span></c:if>
                    <c:if test="${machine.online eq 0}"><span class="text-danger">离线</span></c:if>
                </td>
                <td>
                    <c:if test="${machine.fixStatus eq 2}"><span class="text-danger">手工设置维护</span></c:if>
                    <c:if test="${machine.fixStatus eq 1}"><span class="text-danger">系统设置维护</span></c:if>
                    <c:if test="${machine.fixStatus eq 0}"><span class="text-success">正常</span></c:if>
                </td>
                    <%--<td>
                        <c:if test="${empty machine.product}">无数据</c:if>
                        <c:if test="${not empty machine.product && machine.product.stock eq -1}">不限制</c:if>
                        <c:if test="${not empty machine.product && machine.product.stock != -1 }">${machine.product.stock}</c:if>
                    </td>--%>
                <td>
                    <input type="hidden" value="${machine.sn}">
                    <input type="text" class="sort-class" value="${machine.sort}" />
                    <input type="hidden" value="${machine.id}">
                </td>
                    <%--<td><fmt:formatDate value="${machine.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>--%>
                <th><a href="javascript:choosePro(${machine.id},'${machine.sn}','${machine.product.id}')">选择娃娃&nbsp;&nbsp;&nbsp;&nbsp;</a>
                        <%--<c:if test="${isAgent}"><a href="javascript:updateStock('${machine.id}')">添加库存</a></c:if>--%>
                    <%--<c:if test="${isAgent}"><a href="javascript:updateType('${machine.id}','${machine.machineType}')">切换机器类型</a></c:if>--%>
                    <c:if test="${!isAgent}"><a href="javascript:chooseAgent('${machine.id}','${machine.agent.id}')">设置代理商</a></c:if>
                    <c:if test="${!isAgent}"><a href="javascript:showSetting(${machine.id},'${machine.sn}')">&nbsp;&nbsp;&nbsp;&nbsp;机器设置</a></c:if>
                    <c:if test="${!isAgent}"><a href="javascript:paramSet(${machine.id})">&nbsp;&nbsp;&nbsp;&nbsp;机器推流设置</a></c:if>
                </th>
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

