<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="活动管理"/>
    </c:import>
    <link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css"/>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css"/>

    <style>
        .productImgs, .winImgs {
            height: 150px;
            width: auto;
        }
        #picsFileList .picItem, #winPicsFileList .picItem {
            float: left;
            margin-bottom: 20px;
        }
        #pic_upload, #win_pic_upload {
            clear: both;
        }

    </style>
</head>
<c:set var="mainTitle" value="活动管理"/>
<c:set var="subTitle" value="活动娃娃"/>


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
                        <div class="panel-body">
                            <form id="form-edit" role="form" method="POST" class="form-horizontal"
                                  action="${ctx}/umeditor/activity/save">
                                <div class="row">
                                    <div class="col-md-12">
                                        <input type="hidden" id="id" name="id" value="${activity.id}">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label required">主标题:</label>
                                            <div class="col-md-6">
                                                <input type="text" name="title" id="title" placeholder="请输入主标题"
                                                       class="form-control" value="${activity.title}">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-2 control-label required">副标题:</label>
                                            <div class="col-md-6">
                                                <input type="text" name="subTitle" id="subTitle" placeholder="请输入副标题"
                                                       class="form-control" value="${activity.subTitle}">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-2 control-label required">参与人数:</label>
                                            <div class="col-md-2">
                                                <input type="number" name="joinCount" id="joinCount" placeholder="请输入参与人数"
                                                       class="form-control" value="${activity.joinCount}">
                                            </div>
                                            <label class="col-md-2 control-label required">奖金:</label>
                                            <div class="col-md-2">
                                                <input type="number" name="bonus" id="bonus" placeholder="请输入奖金"
                                                       class="form-control" value="${activity.bonus}">
                                            </div>
                                        </div>


                                        <div class="form-group">
                                            <label class="col-md-2 control-label required">活动封面图:</label>
                                            <input type="hidden" name="img" id="baseImg" value="${activity.img}">
                                            <div class="col-md-6">
                                                <div id="baseImgList">
                                                    <c:if test="${not empty activity.img}">
                                                        <div class="picItem">
                                                            <img class="filename baseImgs col-md-6" id="base" style="float:none"
                                                                 target="_blank" src=${activity.img}>
                                                            <a class="btn red del_file"
                                                               data-filename="${activity.img}">删除</a>
                                                        </div>
                                                    </c:if>
                                                </div>
                                                <input id="base_pic_upload" name="pic_upload" type="file">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-2 control-label required">活动内容:</label>
                                            <div class="col-md-8">
												<textarea hidden="hidden" id="content"
                                                          name="content">${activity.content}</textarea>
                                                <script type="text/plain" id="myEditor2"
                                                        style="width:1000px;height:240px;">
                                                    <p>${activity.content}</p>
                                                </script>
                                            </div>
                                        </div>

                                        <div class="form-group form-inline" style="margin-left: 270px;">
                                            <label class=" control-label required">开始时间:</label>
                                            <div  id="startDate" class="input-group date "  style="margin-right: 203px;">
                                                <input type="text" id="gtCreateTime" name="startTime"
                                                       class="form-control"
                                                       value="<fmt:formatDate value='${activity.startTime}' pattern='yyyy-MM-dd HH:mm:ss' />"
                                                       placeholder="请输入起始日期">
                                                <span class="input-group-addon">
                                                      <span class="fa fa-calendar"></span>
                                                </span>
                                            </div>
                                                <label class=" control-label required">结束时间:</label>
                                            <div  id="endDate" class="input-group date ">
                                                <input type="text" id="ltCreateTime" name="endTime"
                                            class="form-control"
                                            value="<fmt:formatDate value='${activity.endTime}' pattern='yyyy-MM-dd HH:mm:ss' />"
                                            placeholder="请输入结束日期">
                                                <span class="input-group-addon">
                                                <span class="fa fa-calendar"></span>
                                                </span>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 col-md-offset-2">
                                        <a class="btn btn-sm btn-default" href="javascript:history.go(-1);" role="button">返回</a>
                                        <button type="submit" class="btn btn-submit btn-sm btn-primary"
                                                data-loading-text="保存中...">
                                            保存
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <c:import url="/WEB-INF/layouts/content_footer.jsp"/>
</div>
<c:import url="/WEB-INF/layouts/footer.jsp" />


<script src="${ctx}/static/third/bootstrap/js/bootstrap-slider.min.js"></script>
<script src="${ctx}/static/libs/jquery-upload/jquery.ui.widget.js"></script>
<script src="${ctx}/static/libs/jquery-upload/jquery.fileupload.js"></script>
<script src="${ctx}/static/js/common/timeSecond.js"></script>
<script src="${ctx}/static/js/activity/edit.js"></script>

<script type="text/javascript" src="${ctx}/static/third/umeditor/third-party/template.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/static/third/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/static/third/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="${ctx}/static/third/umeditor/lang/zh-cn/zh-cn.js"></script>
<link href="${ctx}/static/third/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">

<script type="text/javascript">
    var um2 = UM.getEditor('myEditor2');
    $('.btn-submit').click(function () {
        var content =UM.getEditor('myEditor2').getContent();
        $("#content").val(content);
    })

</script>

</body>

</html>