<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<!--测评结果汇总-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="statics/css/pintuer.css">
    <link rel="stylesheet" href="statics/css/admin.css">
    <script src="statics/js/jquery.js"></script>
    <script src="statics/js/pintuer.js"></script>
</head>
<body style="height:1200px;">
<div >
<ul class="nav nav-tabs" >
       <c:if test="${webSearch.enable==1}">
        <li class="" style="float: left;">
            <a  href="getWebSearchResult.do?testRecordId=${testRecordId}" target="_blank" id="websearch">web搜索</a>
        </li>
       </c:if>
       <c:if test="${webServer.enable==1}">
        <li class="" style="float: left;">
            <a  href="getWebServerResult.do?testRecordId=${testRecordId}" target="_blank" id="webserver">电商服务</a>
        </li>
       </c:if>
       <c:if test="${silo.enable==1}">        
        <li class="" style="float: left;">
            <a  href="getSiloResult.do?testRecordId=${testRecordId}" target="_blank" id="silo">silo</a>
        </li>
       </c:if>
       <c:if test="${memcached.enable==1}">  
        <li class="" style="float: left;">
            <a  href="getMemcachedResult.do?testRecordId=${testRecordId}" target="_blank" id="memcached">memcached</a>
        </li>
       </c:if> 
    </ul>

</div>
<div>
    <!--图-->
    <div style="width: 900px;" id="chart">
        <div id="websearch1" style="width: 550px;height: 300px; position: absolute;top: 50px;left:50px;"></div>
        <div id="websearch2" style="width: 550px;height: 300px;position: absolute; left:650px;top: 50px;"></div>
        <div id="websearch3" style="width: 550px;height: 300px;position: absolute; left:50px;top: 400px;"></div>
        <div id="websearch4" style="width: 550px;height: 300px;position: absolute; left:650px;top: 400px;"></div>
    </div>
    <!--表-->
        <div style=" width: 1100px; position: absolute; top: 750px;left: 100px;" id="table">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:电商服务</strong> <a href="" style="float:right; display:none;">添加字段</a></div>

                <table class="table table-hover text-center">
                    <tr>
                        <th style="background:#f9f9f9" width="">无干扰 响应时间90th</th>
                        <th style="background:#f1f5fa" width="">干扰下 响应时间90th</th>
                        <th  width="">变化率</th>
                        <th style="background:#f9f9f9" width="">无干扰 响应时间95th</th>
                        <th style="background:#f1f5fa" width="">干扰下 响应时间95th</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td style="background:#f9f9f9" >${webServerBaseResult.nintyTh}</td>
                        <td style="background:#f1f5fa" >${webServerResult.nintyTh}</td>
                        <c:choose>
                            <c:when test="${diffBean.nintyThDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.nintyThDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.nintyThDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                        <td style="background:#f9f9f9" >${webServerBaseResult.nintyFiveTh}</td>
                        <td style="background:#f1f5fa" >${webServerResult.nintyFiveTh}</td>
                        <c:choose>
                            <c:when test="${diffBean.nintyFiveThDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.nintyFiveThDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.nintyFiveThDiff}%</td>

                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <th style="background:#f9f9f9" width="">无干扰 响应时间99th</th>
                        <th style="background:#f1f5fa" width="">干扰下 响应时间99th</th>
                        <th width="">变化率</th>
                        <th style="background:#f9f9f9" width="">无干扰 响应时间方差</th>
                        <th style="background:#f1f5fa" width="">干扰下 响应时间方差</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td style="background:#f9f9f9" >${webServerBaseResult.nintyNineTh}</td>
                        <td style="background:#f1f5fa" >${webServerResult.nintyNineTh}</td>
                        <c:choose>
                            <c:when test="${diffBean.nintyNineThDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.nintyNineThDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.nintyFiveThDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                        <td style="background:#f9f9f9" >${webServerBaseResult.var}</td>
                        <td style="background:#f1f5fa" >${webServerResult.var}</td>
                        <c:choose>
                            <c:when test="${diffBean.varDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.varDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.varDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <th style="background:#f9f9f9" width="">无干扰 响应时间平均值</th>
                        <th style="background:#f1f5fa" width="">干扰下 响应时间平均值</th>
                        <th width="">变化率</th>
                        <th style="background:#f9f9f9" width="">无干扰 响应时间最小值</th>
                        <th style="background:#f1f5fa" width="">干扰下 响应时间最小值</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td style="background:#f9f9f9" >${webServerBaseResult.mean}</td>
                        <td style="background:#f1f5fa" >${webServerResult.mean}</td>
                        <c:choose>
                            <c:when test="${diffBean.varDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.meanDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.meanDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                        <td style="background:#f9f9f9" >${webServerBaseResult.min}</td>
                        <td style="background:#f1f5fa" >${webServerResult.min}</td>
                        <c:choose>
                            <c:when test="${diffBean.minDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.minDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.minDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <th style="background:#f9f9f9" width="">无干扰 响应时间最大值</th>
                        <th style="background:#f1f5fa" width="">干扰下 响应时间最大值</th>
                        <th width="">变化率</th>
                        <th style="background:#f9f9f9" width="">无干扰 缺失率</th>
                        <th style="background:#f1f5fa" width="">干扰下 缺失率</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td style="background:#f9f9f9" >${webServerBaseResult.max}</td>
                        <td style="background:#f1f5fa" >${webServerResult.max}</td>
                        <c:choose>
                            <c:when test="${diffBean.maxDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.maxDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.maxDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                        <td style="background:#f9f9f9" >${webServerBaseResult.missRate}</td>
                        <td style="background:#f1f5fa" >${webServerResult.missRate}</td>
                         <c:choose>
							<c:when test="${diffBean.missRateDiff>0}">
                               <td ><img src="statics/images/up.png"/>${diffBean.missRateDiff}%</td>							
                            </c:when>
							<c:otherwise>
							   <td ><img src="statics/images/down.png"/>${diffBean.missRateDiff}%</td>							
							</c:otherwise>
						</c:choose>
                    </tr>
                </table>
            </div>
        </form>
    </div>

</div>
</div>

<script type="text/javascript" src="statics/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="statics/js/highcharts.js"></script>
<script type="text/javascript" src="statics/js/highcharts-more.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
       

        Highcharts.chart('websearch1', {
            chart: {
                zoomType: 'x'
            },
            title: {
                text: 'webSever尾延迟累积分布'
            },

            xAxis: {
                title: {
                    text: '响应时间/ms'
                },
                min:0
            },
            yAxis: {
                title: {
                    text: '累积概率'
                },
                min:0,
                max:1,
                tickPositions: [0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0],
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle'
            },
            plotOptions: {

            },
        colors: ['#058DC7', '#ff3300'],
            // series: [${diffBean.baseCDFStr}]
            series:[${diffBean.baseCDFStr},${diffBean.CDFStr}]
});
        Highcharts.chart('websearch2', {

            chart: {
                type: 'column',
                zoomType: 'x'
            },
            title: {
                text: 'websever各项指标变化率'
            },
            xAxis: {
                categories: ['90th', '95th', '99th', '方差', '平均值','最小值','最大值']
            },
            yAxis: {
                title: {
                    text: '变化率/%'
                },
            },
            legend: {                                                                    
                enabled: false                                                           
            } ,
            tooltip: {
                formatter: function () {
                	return '<span style="color: '+ this.series.color + '">\u25CF</span> '+
                    this.series.name+': <br/><b>'+ this.x+'变化'+this.y+'%' +'</b><br/>.'
                  }
                }, 
            credits: {
                enabled: false
            },
           
            series: [{
            	name:'webSever',
                data: [${diffBean.nintyThDiff}, ${diffBean.nintyFiveThDiff}, ${diffBean.nintyNineThDiff},
                       ${diffBean.varDiff}, ${diffBean.meanDiff},${diffBean.minDiff},${diffBean.maxDiff}]
            }]


        });
        
        Highcharts.chart('websearch3', {
            title: {
                text: '无干扰下访问延迟分布'
            },
            chart: {
                zoomType: 'x'
            },
            boost: {
                useGPUTranslations: true
            },
            legend: {                                                                    
                enabled: false                                                           
            } ,
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: '响应时间/ms'
                },
            },
            tooltip: {
                
                xDateFormat: '%Y-%m-%d %H:%M:%S.%L',
                valueSuffix: 'ms',
            },
            series: [${diffBean.baseTimeStr}]
        });
        
        Highcharts.chart('websearch4', {
            title: {
                text: '干扰下访问延迟分布'
            },
            chart: {
                zoomType: 'x'
            },
            boost: {
                useGPUTranslations: true
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            colors: ['#ff3300'],
            yAxis: {
                title: {
                    text: '响应时间/ms'
                },
            },
            legend: {                                                                    
                enabled: false                                                           
            } ,
            tooltip: {
                
                xDateFormat: '%Y-%m-%d %H:%M:%S.%L',
                valueSuffix: 'ms'
            },
            series: [${diffBean.baseTimeStr}]
        }); 


});
</script>
</body>
</html>