<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
    <h4 class="modal-title">
        <c:if test="${empty product}">设置列表页查询得到的所有机器的参数</c:if>
        <c:if test="${not empty product}">设置产品(${product.name})对应的机器参数</c:if>
    </h4>
</div>
<form id="form-machine-param"   >


        <ul class="nav nav-tabs tabs-ul nav-ul-task">
        <li role="presentation" class="active"><a href="#camera" data-toggle="tab">摄像头设置</a></li>
        <c:if test="${oneMachineFlag}" >
            <li role="presentation"><a href="#stream" data-toggle="tab">推流设置</a></li>
            <li role="presentation"><a href="#machineSet" data-toggle="tab">机器启动操作</a></li>
        </c:if>
        </ul>
    <div class="tab-content">
        <div id="camera" class="row tab-pane fade in active">

    <input type="hidden" id="machineId" name="machineId">


                <div class="modal-body">
    <label class="col-md-3 control-label ">曝光模式: <span style="color:red">*</span></label>
    <input type="hidden" name="exposureMode" value="${set.exposureMode}">
    <div class="col-md-6">
        <select  id="exposureMode" class="form-control" >
            <c:forEach items="${modes}" var="mode">
                <option value='${mode.value}'
                    <c:if test="${mode.value eq set.exposureMode}">selected="selected"</c:if>  >
                        ${mode.label}
                </option>
            </c:forEach>
        </select>
    </div>
                </div>



<%--<div class="modal-body">
    <label class="col-md-3 control-label">曝光开关设置:</label>
    <div class="col-md-9">
        <label class="radio-inline c-radio">
            <input type="radio" name="openExposureSetting" value="1" checked
            ${set.openExposureSetting eq 1 ? 'checked' : ''}>
            <span class="fa fa-circle"></span>开
        </label>

        <label class="radio-inline c-radio">
            <input type="radio" name="openExposureSetting" value="0"
            ${set.openExposureSetting  eq 0 ? 'checked' : ''}>
            <span class="fa fa-circle"></span>关
        </label>

    </div>
</div>--%>

<!--*****************主副摄像头的是反过来的-->
<div class="modal-body exposure-div ">
    <label class="col-md-3 control-label">主摄像头曝光: <span style="color:red">*</span></label>
    <div class="col-md-6">

        <input type="number"  placeholder="请输入主摄像头曝光" name="slaveExposure" class="form-control" value="${set.slaveExposure}">
    </div>
</div>
<!--*****************主副摄像头的是反过来的-->
<div class="modal-body exposure-div ">
    <label class="col-md-3 control-label">从摄像头曝光: <span style="color:red">*</span></label>
    <div class="col-md-6">
        <input type="number"  placeholder="请输入从摄像头曝光" name="masterExposure" class="form-control" value="${set.masterExposure}">
    </div>
</div>

<div class="modal-body">
    <label class="col-md-3 control-label">主摄像头亮度:</label>
    <div class="col-md-6">
        <input type="number"  min="0" max="255" placeholder="请输入主摄像头亮度" name="slaveBrightness" class="form-control" value="${set.slaveBrightness}">
    </div>
</div>
<div class="modal-body">
    <label class="col-md-3 control-label">从摄像头亮度:</label>
    <div class="col-md-6">
        <input type="number"  min="0" max="255" placeholder="请输入从摄像头亮度" name="masterBrightness" class="form-control" value="${set.masterBrightness}">
    </div>
</div>

<div class="modal-body">
    <label class="col-md-3 control-label">主摄像头饱和度:</label>
    <div class="col-md-6">
        <input type="number"  min="0" max="255"  placeholder="请输入主摄像头饱和度" name="slaveSaturability" class="form-control" value="${set.slaveSaturability}">
    </div>
</div>
<div class="modal-body">
    <label class="col-md-3 control-label">从摄像头饱和度:</label>
    <div class="col-md-6">
        <input type="number"  min="0" max="255" placeholder="请输入从摄像头饱和度" name="masterSaturability" class="form-control" value="${set.masterSaturability}">
    </div>
</div>

<div class="modal-body">
    <label class="col-md-3 control-label">请选择分辨率: <span style="color:red">*</span></label>
    <input type="hidden" name="encodeLevel" value="${set.encodeLevel}">
    <div class="col-md-6">
        <select   id="encode-level-select" class="form-control" >
            <option value=''  >不设置</option>
            <c:forEach items="${levels}" var="level">
                <option value='${level.value}'
                        <c:if test="${level.value eq set.encodeLevel}">selected="selected"</c:if>  >
                        ${level.label}
                </option>
            </c:forEach>
        </select>
    </div>
</div>


<div class="modal-body user-defined-div "
    <c:if test="${ userDefined  !=  set.encodeLevel}" >style="display:none " </c:if>
    >
    <label class="col-md-3 control-label">自定义分辨率宽度: <span style="color:red">*</span></label>
    <div class="col-md-6">
        <input type="number" name="width"  min="0" placeholder="请输入自定义分辨率宽度" class="form-control" value="${set.width}">
    </div>
</div>
<div class="modal-body user-defined-div "
    <c:if test="${ userDefined  !=  set.encodeLevel}" >style="display:none " </c:if>
    >
    <label class="col-md-3 control-label">自定义分辨率高度: <span style="color:red">*</span></label>
    <input type="hidden" id="userDefined" value="${userDefined}">
    <div class="col-md-6">
        <input type="number"  min="0" name="height" placeholder="请输入自定义分辨率高度" class="form-control" value="${set.height}">
    </div>
</div>

        </div>

<c:if test="${oneMachineFlag}" >
        <div id="stream" class="row tab-pane fade   ">
            <div class="modal-body  ">
                <label class="col-md-3 control-label">即构appId: </label>
                <div class="col-md-6">
                    <c:if test="${empty agent.zegoId}">
                        暂无数据
                    </c:if>
                    <c:if test="${not empty agent.zegoId}">
                        ${agent.zegoId}
                    </c:if>

                </div>
            </div>
            <div class="modal-body  ">
                <label class="col-md-3 control-label">即构key: </label>
                <div class="col-md-6 auto-newline">
                    <c:if test="${empty agent.zegoKey}">
                        暂无数据
                    </c:if>
                    <c:if test="${not empty agent.zegoKey}">
                        ${agent.zegoKey}
                    </c:if>

                </div>

            </div>
            <div class="modal-body  ">
                <label class="col-md-3 control-label">即构server: </label>
                <div class="col-md-6">
                    <c:if test="${empty agent.zegoServer}">
                        暂无数据
                    </c:if>
                    <c:if test="${not empty agent.zegoServer}">
                        ${agent.zegoServer}
                    </c:if>
                </div>
            </div>
            <div class="modal-body">
                <label class="col-md-3 control-label">是否使用测试流: <span style="color:red">*</span></label>
                <div class="col-md-6">
                    <label class="radio-inline c-radio">
                        <input type="radio" name="testStreamSwitch" value="1" checked
                        ${set.testStreamSwitch eq 1 ? 'checked' : ''}>
                        <span class="fa fa-circle"></span>是
                    </label>

                    <label class="radio-inline c-radio">
                        <input type="radio" name="testStreamSwitch" value="0"
                        ${set.testStreamSwitch  eq 0 ? 'checked' : ''}>
                        <span class="fa fa-circle"></span>否
                    </label>

                </div>
            </div>
            <div class="modal-body">
                <label class="col-md-3 control-label ">旋转角度: <span style="color:red">*</span></label>
                <input type="hidden" name="rotationAngle" value="0">
                <div class="col-md-6">
                    <select  id="rotationAngle" class="form-control" >
                        <c:forEach items="${angles}" var="angle">
                            <option value='${angle.value}'
                                    <c:if test="${angle.value eq set.rotationAngle}">selected="selected"</c:if>  >
                                    ${angle.label}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>


        </div >
        <div id="machineSet" class="row tab-pane fade ">
            <button type="button" class="btn btn-default machine-set reset-machine" data-dismiss="modal">重启机器</button>
            <button type="button" class="btn btn-default machine-set close-machine" data-dismiss="modal">关闭机器</button>
        </div>
</c:if>


    </div>
</form>


<div id="modify-div" class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>

    <button type="button" class="btn btn-primary btn-machine-param " data-loading-text="修改中...">更改</button>


</div>

    <style>
    .modal-body{
    margin-top: 20px;
    }
    .modal-footer{
    margin-top: 65px;
    }
    .auto-newline{
        width: 70%;
        word-wrap: break-word;
    }
    </style>