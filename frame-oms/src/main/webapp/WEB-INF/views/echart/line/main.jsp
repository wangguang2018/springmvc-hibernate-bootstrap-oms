<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="java.lang.String" %>
<%@ page import="com.wangguang.dto.CatchTotalChart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/layouts/tag.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="/WEB-INF/layouts/header.jsp">
        <c:param name="question" value="主页 -"/>
    </c:import>
<body>
<div class="wrapper">
    <c:import url="/WEB-INF/layouts/nav.jsp"/>
    <section>
        <div>
            <div>

            </div>
            <div class="col-md-5 height100" style="padding: 15px 30px 0 0;">
                <div id="station-table" class="table-responsive">
                    <div>
                        <div class="col-sm-6">
                            <div class="panel widget bg-primary">
                                <div class="row row-table">
                                    <div class="col-xs-4 text-center bg-primary-dark pv-lg">
                                        <em class="icon-users fa-3x"></em>
                                    </div>
                                    <div class="col-xs-8 pv-lg">
                                        <div class="mt0">${totalMember}</div>
                                        <div class="text-uppercase">用户总量</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="panel widget bg-warning">
                                <div class="row row-table">
                                    <div class="col-xs-4 text-center bg-warning-dark pv-lg">
                                        <em class="icon-settings fa-3x"></em>
                                    </div>
                                    <div class="col-xs-8 pv-lg">
                                        <div class="mt0">${totalMachine}</div>
                                        <div class="text-uppercase">机器总数</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="panel widget bg-success">
                                <div class="row row-table">
                                    <div class="col-xs-4 text-center bg-success-dark pv-lg">
                                        <em class="icon-cup fa-3x"></em>
                                    </div>
                                    <div class="col-xs-8 pv-lg">
                                        <div class="mt0">${todayMember}</div>
                                        <div class="text-uppercase">今日新增用户</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="panel widget bg-danger">
                                <div class="row row-table">
                                    <div class="col-xs-4 text-center bg-danger-dark pv-lg">
                                        <em class="fa fa-dollar fa-3x"></em>
                                    </div>
                                    <div class="col-xs-8 pv-lg">
                                        <div class="mt0">
                                            ${totalCharge}
                                        </div>
                                        <div class="text-uppercase">累计充值总额</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="main" style="width:100%;height:400px;">

            </div>


            <div id="agent-pagination-body" style="margin-top: 255px;
                    margin-bottom: -185px;
              <c:if test='${isAgent}'>display:none</c:if> "
            >

            </div>

              
            <div class="catch-part" style="margin-top: 255px;">
                <form   id="catchForm" class=""  method="POST" action="${ctx}/echart/line/main">
                  <div class="search-group form-inline">
                    <select name="agentId" class="form-control">
                        <c:forEach items="${agents}" var="agent">
                            <option value='${agent.id}' >${agent.name}</option>
                        </c:forEach>
                    </select>
                    <select name="searchType" class="form-control">
                        <c:forEach items="${searchTypes}" var="searchType">
                            <option value='${searchType.value}' >${searchType.label}</option>
                        </c:forEach>
                    </select>

                      <div id="startDate" class="input-group date">
                          <input type="text" id="gtCreateTime" name="startDate"
                                 class="form-control"
                                 value="<fmt:formatDate value='${defaultStartDate}' pattern='yyyy-MM-dd 00:00' />"
                                 placeholder="请输入起始日期">
                          <span class="input-group-addon">
                                  <span class="fa fa-calendar"></span>
                               </span>
                      </div>
                      <div id="endDate" class="input-group date">
                          <input type="text" id="ltCreateTime" name="endDate"
                                 class="form-control"
                                 value="<fmt:formatDate value='${defaultEndDate}' pattern='yyyy-MM-dd 23:59' />"
                                 placeholder="请输入结束日期">
                          <span class="input-group-addon">
                                  <span class="fa fa-calendar"></span>
                               </span>
                      </div>
                      <button type="button" class="btn btn-primary chart-btn-search">搜索</button>
                  </div>
                  </form>
                  <%--<div id="info"></div>--%>
                 <%-- <div id="catchChart" style="width:100%;height:400px;">
                  </div>--%>


            </div>


            <div id="pagination-body" style="margin-top: 30px">

            </div>
              <input type="hidden" name="page" value="${pagination.page}">
            <input type="hidden" id="isAgent" value="${isAgent}">

            


            

            
        </div>
    </section>
    <c:import url="/WEB-INF/layouts/content_footer.jsp"/>
</div>
<c:import url="/WEB-INF/layouts/footer.jsp"/>
<script src="${ctx}/static/js/statistics/echarts.js?t=<%=System.currentTimeMillis()%>"></script>
<script src="${ctx}/static/js/common/timeDay.js"></script>



<script type="text/javascript">


  function getDiamond(prices,sns){
  console.log("tt:"+prices+",sns:"+sns);
  var catchChart = echarts.init(document.getElementById('catchChart'));
  var dayLis = sns;
  var pric = prices;
  // 指定图表的配置项和数据
  optio = {
     title: {
         text: '钻石消费总金额'
     },
     tooltip: {
         trigger: 'axis'
     },
     legend: {
         data:['钻石消费总金额']
     },
     xAxis:  {
         type: 'category',
         boundaryGap: false,
         data: dayLis
     },
     yAxis: {
         type: 'value',
         axisLabel: {
             formatter: '{value} 次'
         }
     },
     series: [
         {
             name:'钻石消费总金额',
             type:'line',
             data:pric,
             markPoint: {
                 data: [
                     {type: 'max', name: '最大值'}
                 ]
             }
         },
         {
             name:'',
             type:'line',
             data:[],
             markPoint: {
                 data: [
                     {type: 'max', name: '最大值'}
                 ]
             }
         }
     ]
  };
  
  // 使用刚指定的配置项和数据显示图表。
  catchChart.setOption(optio);
  if($('#catchChart').css('display')== 'none'){
    console.log("bbb");
    $("#catchChart").show();
  }
  
  }
  </script>


<script type="text/javascript">


  function getcatch(sns,failRecord,successRecord){
  console.log("sns:"+sns+",failRecord:"+failRecord+",successRecord:"+successRecord);
  var catchChart = echarts.init(document.getElementById('catchChart'));
  var dayLis = sns;
  var failRecord = failRecord;
  var successRecord = successRecord;
  
 //var shows = showDatas;
  // 指定图表的配置项和数据
  optio = {
     title: {
         text: '抓取记录'
     },
     tooltip: {
         trigger: 'axis'/* ,
         formatter: function (params,ticket,callback) {
           var sn = params[0].name;
           var showInfo = "";
           for(var a = 0; a<sns.length;a++){
             if(sns[a] === sn){
                showInfo = shows[a]; 
             }
           }
           res = sn + " :  "+showInfo;

            setTimeout(function (){
                // 仅为了模拟异步回调
                callback(ticket, res);
            }, 1000)
            return 'loading';
        } */

     },
     legend: {
         data:['各机器的抓取情况']
     },
     xAxis:  {
         type: 'category',
         boundaryGap: false,
         data: dayLis
     },
     yAxis: {
         type: 'value',
         axisLabel: {
             formatter: '{value} 次'
         }
     },
     series: [
         {
             name:'失败总数',
             type:'line',
             data:failRecord,
             markPoint: {
                 data: [
                     {type: 'max', name: '最大值'}
                 ]
             }
         },
         {
             name:'成功总数',
             type:'line',
             data:successRecord,
             markPoint: {
                 data: [
                     {type: 'max', name: '最大值'}
                 ]
             }
         }
     ]
  };
  
  // 使用刚指定的配置项和数据显示图表。
  catchChart.setOption(optio);
  if($('#catchChart').css('display')== 'none'){
    console.log("bbb");
    $("#catchChart").show();
  }
  
  }
  </script>


<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));

    var dayList = [
        <%
            List<BigDecimal> logs = (List<BigDecimal>) request.getAttribute("chargeList");
            for(int i = 0;i < logs.size();i++){
        %>
        '<%=i+1 %>号',
        <%
            }
        %>
    ];

    var price = [
        <%
            logs = (List<BigDecimal>) request.getAttribute("chargeList");
            for(int i = 0;i < logs.size();i++){
        %>
        <%=logs.get(i) %>,
        <%
            }
        %>
    ];

    // 指定图表的配置项和数据
    option = {
        title: {
            text: '月充值记录'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['每日充值金额']
        },
        xAxis:  {
            type: 'category',
            boundaryGap: false,
            data: dayList
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} 元'
            }
        },
        series: [
            {
                name:'当天金额',
                type:'line',
                data:price,
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'}
                    ]
                }
            }
        ]
    };



    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
<script src="${ctx}/static/js/statistics/pagination.js"></script>
<script src="${ctx}/static/js/statistics/list.js"></script>



</body>
</html>