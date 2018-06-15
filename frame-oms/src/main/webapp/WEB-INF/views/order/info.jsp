<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="订单管理" />
    </c:import>
<link rel="stylesheet" href="${ctx}/static/third/bootstrap/css/bootstrap-slider.min.css" />
<link href="${ctx}/static/libs/select2/css/select2.css" rel="stylesheet" type="text/css" />
</head>
<c:set var="mainTitle" value="订单管理" />
<c:set var="subTitle" value="订单详细" />


<body>
<div class="wrapper">
    <c:import url="/WEB-INF/layouts/nav.jsp" />
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
                           <form id="form-edit" role="form" method="POST" class="form-horizontal" action="${ctx}/member/order/deliver">
							   <input hidden name="orderSn" value="${order.orderSn}"/>
	                            <div class="row">
	                                <div class="col-md-12">
										<h4>订单信息</h4>
										<div class="form-group">
											<label class="col-md-2 control-label">订单号:</label>
											<div class="col-md-8">
												<label class="control-label">${order.orderSn}</label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">下单时间:</label>
											<div class="col-md-8">
												<label class="control-label">${order.createTime}</label>
											</div>
										</div>
										<c:forEach items="${order.orderProducts}" var="product">
											<div class="form-group">
												<label class="col-md-2 control-label">娃娃名称:</label>
												<div class="col-md-1" style="width: auto;">
													<label class="control-label">${product.product.name}</label>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">娃娃价格:</label>
												<div class="col-md-1">
													<label class="control-label">${product.product.price}</label>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">兑换数量:</label>
												<div class="col-md-1">
													<label class="control-label">${product.num}</label>
												</div>
											</div>
										</c:forEach>
										<hr/>
										<h4>收货人信息</h4>
										<div class="form-group">
											<label class="col-md-2 control-label">收获地址:</label>
											<div class="col-md-8">
												<label class="control-label">${order.address}</label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">收货人:</label>
											<div class="col-md-8">
												<label class="control-label">${order.consignee}</label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">联系方式:</label>
											<div class="col-md-8">
												<label class="control-label">${order.mobile}</label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">状态:</label>
											<div class="col-md-8">
												<label class="control-label">
													<c:if test="${order.status eq 1}">
														未发货
													</c:if>
													<c:if test="${order.status eq 0}">
														已发货
													</c:if>

												</label>
											</div>
										</div>
										<hr/>
										<h4>快递信息</h4>
										<div class="form-group">
											<label class="col-md-2 control-label">快递</label>
											<div class="col-md-3">
												<select class="form-control" name="type">
													<c:forEach var="type" items="${expressType}">
														<option <c:if test="${order.type eq type.key}">selected</c:if> value="${type.key}">${type.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group express-name" >
                        <label class="col-md-2 control-label">快递名称</label>
                        <div class="col-md-3">
                          <input class="form-control" name="expressName" value="${order.expressName}"/>
                        </div>
                    </div>

										<div class="form-group">
											<label class="col-md-2 control-label">快递单号:</label>
											<div class="col-md-3">
												<input class="form-control" name="expressNo" value="${order.expressNo}"/>
											</div>
										</div>
										<c:if test="${order.status == 0}">
											<div class="form-group">
												<label class="col-md-2 control-label">发货时间:</label>
												<div class="col-md-3">
													<label class="control-label">${order.expressTime}</label>
												</div>
											</div>
										</c:if>
	                                 </div>
	                            </div>

								<div class="row">
									<div class="col-md-5 col-md-offset-2">
										<a class="btn btn-sm btn-default" href="javascript:history.go(-1);" role="button">返回</a>
										<button type="submit" class="btn btn-submit btn-sm btn-primary"
												data-loading-text="发货中...">
											发货
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
    <c:import url="/WEB-INF/layouts/content_footer.jsp" />
</div>
<c:import url="/WEB-INF/layouts/footer.jsp" />


<script src="${ctx}/static/third/bootstrap/js/bootstrap-slider.min.js"></script>
<script src="${ctx}/static/js/order/deliver.js"></script>
</body>
</html>