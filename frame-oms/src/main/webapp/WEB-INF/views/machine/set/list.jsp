<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="娃娃机管理"/>
    </c:import>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css"/>
</head>

<c:set var="mainTitle" value="娃娃机列表"/>
<c:set var="subTitle" value="娃娃机列表"/>

<body>
<div class="wrapper">
    <c:import url="/WEB-INF/layouts/nav.jsp"/>
    <section>
        <div class="content-wrapper">
            <h3>${mainTitle}
                <small>${subTitle}</small>
            </h3>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">${subTitle}</div>
                        <div class="panel-body table-fit">
                            <form id="pagination-form" class="" method="POST" action="${ctx}/machine/set/list">
                                <div class="search-group form-inline">
                                    <input type="text" name="search_LIKE_sn" placeholder="机器设备号" class="form-control search-cell">
                                    <input type="text" name="search_LIKE_product.name" placeholder="娃娃名称" class="form-control search-cell">
                                    <select class="form-control search-cell" name="search_EQ_online">
                                        <option value="">请选择是否在线</option>
                                        <option value="1">在线</option>
                                        <option value="0">离线</option>
                                    </select>
                                    <c:if test="${!isAgent}">
                                        <%--<input type="text" name="search_EQ_agentId" id="agents" class="form-control">--%>
                                    </c:if>
                                    <button type="button" class="btn btn-primary btn-search search-cell">搜索</button>
                                   
                                </div>



                                <div class="action-group" style="margin-bottom: 53px;">

                                    <a  href="javascript:paramSet(-1)"   class="btn  btn-primary pull-right machine-param">机器推流设置</a>
                                    <a href="javascript:showSetting(-1,'')" class="btn  btn-primary pull-right machine-param">机器设置</a>

                                    <%--<div class="btn-group">
                                        <button type="button" data-toggle="dropdown"
                                                class="btn dropdown-toggle btn-primary">操作
                                            <span class="caret"></span>
                                        </button>
                                        <ul role="menu" class="dropdown-menu animated swing">
                                            &lt;%&ndash;<li><a href="#" class="btn-edit-action">编辑</a>&ndash;%&gt;
                                            &lt;%&ndash;</li>&ndash;%&gt;
                                            &lt;%&ndash;<li class="divider"></li>&ndash;%&gt;
                                            &lt;%&ndash;<li><a href="javascript:void(0);" class="btn-edit-action">编辑</a>&ndash;%&gt;
                                            &lt;%&ndash;</li>&ndash;%&gt;
                                            <li><a href="javascript:void(0);" class="btn-disable">删除</a>
                                            </li>
                                        </ul>
                                    </div>--%>

                                </div>

                                <div id="pagination-body">

                                </div>
                                <input type="hidden" name="page" value="${pagination.page}">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <c:import url="/WEB-INF/layouts/content_footer.jsp"/>
</div>
<c:import url="/WEB-INF/layouts/footer.jsp"/>
<div class="modal fade" id="modifyEncodeLevel" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">

        </div>
    </div>
</div>

<div class="modal fade" id="machineParam" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">

        </div>
    </div>
</div>

<div class="modal fade" id="settingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改机器设置</h4>
            </div>
            <div class="modal-body">
                <div class="row" style="padding: 10px;">
                    <div class="form-group">
                        <div style="color: red" id="status_mark">

                        </div>
                    </div>
                    <div class="form-group">
                        游戏模式：0弱抓力模式 1固定强抓力 2随机强抓力 3固定强抓力--补 4随机强抓力--补
                    </div>
                    <div class="form-group">
                        <div>
                            <input type="number" min="0" max="4" class="form-control" id="gameMode"  placeholder="游戏模式">
                        </div>
                    </div>
                    <div class="form-group">
                        抓取娃娃几率：1~888，必须为整数
                    </div>
                    <div class="form-group">
                        <div>
                            <input class="form-control" id="probability" type="number" placeholder="抓取娃娃几率(1～888)">
                        </div>
                    </div>
                    <div class="form-group">
                        强力电压：15.0～47.5  一定要保留1位有效数字
                    </div>
                    <div class="form-group">
                        <div>
                            <input type="number" min="15" max="47.5" class="form-control" id="strongVoltage"  placeholder="强力电压">
                        </div>
                    </div>
                    <div class="form-group">
                        弱力电压：4.5～40.0  一定要保留1位有效数字
                    </div>
                    <div class="form-group">
                        <div>
                            <input type="number" min="4.5" max="40.0" class="form-control" id="smallVoltage"  placeholder="弱力电压">
                        </div>
                    </div>
                    <div class="form-group">
                        强转弱时间：0.1～3.0  一定要保留1位有效数字
                    </div>
                    <div class="form-group">
                        <div>
                            <input type="number" min="0.1" max="3.0" class="form-control" id="changeTime"  placeholder="强转弱时间">
                        </div>
                    </div>
                    <div class="form-group">
                        到顶转弱抓力  0关闭，1开启
                    </div>
                    <div class="form-group">
                        <div>
                            <input type="number" min="0" max="1" class="form-control" id="changeWeak"  placeholder="到顶转弱抓力">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="changeSetting">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="chooseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="chooseModalLabel">选择产品</h4>
            </div>
            <div class="modal-body">
                <div class="row" style="padding: 10px;">
                    <div class="form-group">
                        <div class="col-md-12">
                            <select  id="productId" class="form-control" style="width:80%;">
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="chooseSetting">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<div class="modal fade" id="agentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择代理商</h4>
            </div>
            <div class="modal-body">
                <div class="row" style="padding: 10px;">
                    <div class="form-group">
                        <div class="col-md-12">
                            <select class="form-control agent-select"  id="agentSelect" style="width:80%;" >
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="chooseAgent">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<div class="modal fade" id="streamParamModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content encode-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"  id="masterExposure">机器推流参数设置</h4>
                <input type="hidden" name="optionId">
            </div>
            <form>
                <div class="modal-body">
                    <label class="col-md-2 control-label">主摄像头曝光:</label>
                    <div class="col-md-6">
                    <input type="number" name="masterExposure" placeholder="请输入主摄像头曝光" class=" param " >
                    </div>
                </div>
                <div class="modal-body">
                    <label class="col-md-2 control-label">副摄像头曝光:</label>
                    <div class="col-md-6">
                    <input type="number" name="slaveExposure" placeholder="请输入副摄像头曝光" class=" param " >
                    </div>
                </div>

                <div class="modal-body">
                    <label class="col-md-2 control-label">设置分辨率:</label>
                    <div class="col-md-8">
                        <label><input value="0" name="encodeLevelSwitch" type="radio" checked="checked" />否</label>
                        <label><input value="1" name="encodeLevelSwitch" type="radio" />是</label>
                    </div>
                </div>

                <div class="modal-body  encodeLevel-div ">
                        <label class="col-md-2 control-label">请选择分辨率:</label>
                        <div class="col-md-6">
                            <select id='levels' name="encodeLevel" class="form-control" >
                                <c:forEach items="${encodeLevels}" var="level">
                                    <option value='${level.value}'  name=""> ${level.label}</option>
                                </c:forEach>
                            </select>
                        </div>
                </div>

                <div class="modal-footer encode-footer">
                    <button type="button" id="setStreamParam" class="btn btn-submit btn-sm btn-primary"
                            data-loading-text="设置中...">
                        设置
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script src="${ctx}/static/js/common/pagination.js"></script>
<script src="${ctx}/static/js/machine/set/list.js"></script>
<script src="${ctx}/static/js/machine/set/machineParam.js"></script>
<script src="${ctx}/static/js/machine/set/modal.js"></script>
<script src="${ctx}/static/js/agent/select.js"></script>
<script src="${ctx}/static/js/common/timeDay.js"></script>

<style>

 .encode-content{
        width: 800px;
 }

.btn-online {
  color: #ffffff !important;
  background-color: #5d9cec !important;
  margin-left: 26px;
  margin-right: 20px;
  float: right
}
.btn-outline{  
  color: #ffffff !important;
  background-color: #5d9cec !important;
  margin-left: 20px;
  float: right

}

.encode-footer{
    margin: 37px;
}
      
</style>
<script type="text/javascript">
  //上下线操作
  function onlineOperate(val) {
       
        var checkItems = $("#pagination-form").find("input[class='checkbox-item']");
        // 查询选中的复选框值
        var ids = getIds(checkItems);
        // 判断是否选中了要编辑的内容
        if (ids.length < 1) {
            Dialog.info("请先选中您要勾选的内容");
            return;
        }

        $.ajax({
            url: ctx + "/machine/onlineOperate",
            type: "post",
            dataType: "JSON",
            data: {
                id: ids,
                val:val
            },
            success: function(data) {
                if (data.code == 0) {
                    Dialog.success(data.msg);
                }
                if (data.code == 1) {
                    Dialog.warning(data.msg);
                }
                location.href = ctx + "/machine/list";
            }
        });

    }

    
    function isShow(val) {
        var checkItems = $("#pagination-form").find("input[class='checkbox-item']");
        // 查询选中的复选框值
        var ids = getIds(checkItems);
        // 判断是否选中了要编辑的内容
        if (ids.length < 1) {
            Dialog.info("请先选中您要勾选的内容");
            return;
        }

        $.ajax({
            url: ctx + "/machine/settop",
            type: "post",
            dataType: "JSON",
            data: {
                id: ids,
                type:val
            },
            success: function(data) {
                if (data.code == 0) {
                    Dialog.success(data.msg);
                }
                if (data.code == 1) {
                    Dialog.warning(data.msg);
                }
                location.href = ctx + "/machine/list";
            }
        });

    }

    /**
     * 查询选中的复选框值
     *
     * @param items
     */
    function getIds (items) {
        var ids = [];
        if (typeof items != "undefined") {
            items.each(function() {
                if (this.checked) {
                    ids.push(this.value);
                }
            });
        }
        return ids;
    }
</script>
</body>

</html>