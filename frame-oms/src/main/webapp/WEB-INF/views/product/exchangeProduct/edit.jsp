<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="兑换商品管理"/>
    </c:import>
    <link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css"/>
    <link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css"/>

    <style>
        .productImgs{
            height: 150px;
            width: auto;
        }
        .winImgs{
            height: 150px;
            width: auto;
        }
        .detailImgs{
            height: 150px;
            width: auto;
        }

        .picItem{
            float: left;
            margin-bottom: 20px;
        }
        /*#detailPicsFileList .picItem, #winPicsFileList .picItem {
            float: left;
            margin-bottom: 20px;
        }
        #pic_upload, #win_pic_upload {
            clear: both;
        }*/
        #pic_upload, #detail_pic_upload ,#win_pic_upload,.help-block {
            clear: both;
        }

    </style>

</head>
<c:set var="mainTitle" value="兑换商品管理"/>
<c:set var="subTitle" value="编辑兑换商品"/>


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
                                  action="${ctx}/product/save">
                                <div class="row">
                                    <div class="col-md-12">
                                        <input type="hidden" id="id" name="id" value="${product.id}">
                                        <input type="hidden"  name="type" value="${product.type}">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label required">兑换商品名称:</label>
                                            <div class="col-md-8">
                                                <input type="text" name="name" id="name" placeholder="请输入娃娃名称"
                                                       class="form-control" value="${product.name}">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-2 control-label required">商品实际价值(钻石数):</label>
                                            <div class="col-md-8">
                                                <input type="number" name="diamondRate" id="diamondRate" placeholder="请输入商品实际价值"
                                                       class="form-control" value="${product.diamondRate}">
                                            </div>
                                        </div>


                                        <div class="form-group" style="display: none;">
                                            <label class="col-md-2 control-label required">商品库存(-1表示不限制):</label>
                                            <div class="col-md-8">
                                                <input type="number" name="stock" max="999999" min="0" id="stock" placeholder="请输入商品库存"
                                                       class="form-control" value="-1">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-2 control-label required">兑换商品图片:</label>
                                            <input type="hidden" name="imgs" id="imgs" value="${product.imgs}">
                                            <div class="col-md-8">
                                                <div id="picsFileList">
                                                    <c:if test="${not empty product.imgs}">
                                                        <c:set value="${ fn:split(product.imgs, ';') }" var="picList"/>
                                                        <c:forEach var="pic" items="${picList}">
                                                            <div class="picItem">
                                                                <img class="productImgs col-md-6" style="float:none"
                                                                     target="_blank" src=${pic}>
                                                                <a class="btn red del_file"
                                                                   data-filename="${pic}">删除</a>
                                                            </div>
                                                        </c:forEach>
                                                    </c:if>
                                                </div>
                                                <input id="pic_upload" name="pic_upload" type="file">
                                            </div>

                                        </div>

                                        <div class="form-group">
                                            <label class="col-md-2 control-label required">详情页面大图片:</label>
                                            <input type="hidden" name="detailImg" id="detailImg" value="${product.detailImg}">
                                            <div class="col-md-8">
                                                <div id="detailPicsFileList">
                                                    <c:if test="${not empty product.detailImg}">
                                                        <c:set value="${ fn:split(product.detailImg, ';') }" var="detailList"/>
                                                        <c:forEach var="detailP" items="${detailList}">
                                                            <div class="picItem">
                                                                <img class="detailImgs col-md-6" style="float:none"
                                                                     target="_blank" src=${detailP}>
                                                                <a class="btn red del_file"
                                                                   data-filename="${detailP}">删除</a>
                                                            </div>
                                                        </c:forEach>
                                                    </c:if>

                                                    <%--<c:if test="${not empty product.detailImg}">
                                                        <div class="picItem">
                                                            <img class="filename detailImgs col-md-6" id="detail" style="float:none"
                                                                 target="_blank" src=${product.detailImg}>
                                                            <a class="btn red del_file"
                                                               data-filename="${product.detailImg}">删除</a>
                                                        </div>
                                                    </c:if>--%>
                                                </div>
                                                <input id="detail_pic_upload" name="pic_upload" type="file">
                                                <p class="help-block">最多可上传5张图片</p>
                                            </div>
                                        </div>


                                       <%-- <div class="form-group">
                                            <label class="col-md-2 control-label required">中奖图片:</label>
                                            <input type="hidden" name="winImg" id="winImg" value="${product.winImg}">
                                            <div class="col-md-8">
                                                <div id="winPicsFileList">
                                                    <c:if test="${not empty product.winImg}">
                                                        <div class="picItem">
                                                            <img class="filename winImgs col-md-6" id="win" style="float:none"
                                                                 target="_blank" src=${product.winImg}>
                                                            <a class="btn red del_file"
                                                               data-filename="${product.winImg}">删除</a>
                                                        </div>
                                                    </c:if>
                                                </div>
                                                <input id="win_pic_upload" name="pic_upload" type="file">
                                            </div>
                                        </div>--%>
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
<c:import url="/WEB-INF/layouts/footer.jsp"/>


<script src="${ctx}/static/third/bootstrap/js/bootstrap-slider.min.js"></script>
<script src="${ctx}/static/libs/jquery-upload/jquery.ui.widget.js"></script>
<script src="${ctx}/static/libs/jquery-upload/jquery.fileupload.js"></script>
<script src="${ctx}/static/js/common/timeSecond.js"></script>
<script src="${ctx}/static/js/product/exchangeProduct/edit.js"></script>
<script>
</script>
<script type="text/javascript">

</script>
</body>

</html>