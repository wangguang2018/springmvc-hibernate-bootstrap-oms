<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
    <h4 class="modal-title">机器摄像参数修改(第一行的值帮助动态修改所有机器)</h4>
</div>
<div class="modal-body">
    <form action="${ctx}/machine/setStreams" id="set-form">
        <c:forEach items="${stationIds}" var="stationId">
            <input type="hidden" name="stationId" value="${stationId}">
        </c:forEach>
    <table class="table">
        <thead>
        <tr>
            <th></th>
            <th>设备IP</th>
            <th>产品</th>
            <th>副摄像头曝光</th>
            <th>主摄像头曝光</th>
            <th>分辨率</th>

        </tr>
        </thead>
        <tbody>
        <tr>
            <td></td>
            <td>包含下面所有</td>
            <td>包含下面所有</td>
            <td><input  id="masterExposureAll" type="number" min="1"  class="form-control "></td>
            <td><input  id="slaveExposureAll"  type="number" min="1" class="form-control "></td>
            <td>
                <select id='levelsAll'   class="form-control" >
                    <option value=''  >不设置</option>
                    <c:forEach items="${encodeLevels}" var="level">
                        <option value='${level.value}'> ${level.label}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    <c:forEach items="${machines}" var="machine" varStatus="status">
        <tr>
            <td><input type="hidden" name="id[${status.index}]" value="${machine.id}"></td>
            <td>${machine.clientIp}</td>
            <td>${machine.product.name}</td>
            <td>
                <input type="number" min="1" name="masterExposure[${status.index}]"
                    <c:if test="${empty machine.machineSet.masterExposure}"> value="120" </c:if>
                    <c:if test="${not empty machine.machineSet.masterExposure}"> value="${machine.machineSet.masterExposure}" </c:if>
                       class="form-control masterExposure">
            </td>
            <td>
                <input type="number" min="1" name="slaveExposure[${status.index}]"
                <c:if test="${empty machine.machineSet.slaveExposure}"> value="120" </c:if>
                <c:if test="${not empty machine.machineSet.slaveExposure}"> value="${machine.machineSet.slaveExposure}" </c:if>
                       class="form-control slaveExposure">
            </td>
            <td>
                <input type="hidden" name="encodeLevel[${status.index}]" class="form-control encodeLevel"
                <c:if test="${empty machine.machineSet.encodeLevel}">
                       value="2"
                </c:if>
                <c:if test="${not empty machine.machineSet.encodeLevel}">
                       <c:if test="${level.value eq machine.machineSet.encodeLevel}" >selected="selected"</c:if>
                </c:if>
                       value="${machine.machineSet.encodeLevel}"
                >
            <select class="form-control select-level" >
                    <option value=''  >不设置</option>
                <c:forEach items="${encodeLevels}" var="level">
                    <option value='${level.value}'
                            <c:if test="${empty machine.machineSet.encodeLevel}">
                                <c:if test="${level.value eq 2}" >selected="selected"</c:if>
                            </c:if>
                            <c:if test="${not empty machine.machineSet.encodeLevel}">
                                <c:if test="${level.value eq machine.machineSet.encodeLevel}" >selected="selected"</c:if>
                            </c:if>

                        > ${level.label}</option>
                </c:forEach>
            </select>
            </td>
        </tr>
    </c:forEach>
        </tbody>
    </table>
    </form>
</div>
<div id="modify-div" class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>

    <button type="button" class="btn btn-primary btn-modify-level" data-loading-text="修改中...">更改</button>


</div>

