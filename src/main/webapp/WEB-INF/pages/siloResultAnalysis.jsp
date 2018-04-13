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
<body>
<div >
    <ul class="nav nav-tabs" >
        <li class="" style="float: left;">
            <a  href="#" id="websearch">web搜索</a>
        </li>
        <li class="" style="float: left;">
            <a  href="#" id="webserver">电商服务</a>
        </li>
        <li class="" style="float: left;">
            <a  href="#" id="silo">silo</a>
        </li>
        <li class="" style="float: left;">
            <a  href="#" id="memcached">memcached</a>
        </li>
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
                <div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:silo</strong> <a href="" style="float:right; display:none;">添加字段</a></div>

                <table class="table table-hover text-center">
                    <tr>
                        <th width="">无干扰 响应时间90th</th>
                        <th width="">干扰下 响应时间90th</th>
                        <th width="">变化率</th>
                        <th width="">无干扰 响应时间95th</th>
                        <th width="">干扰下 响应时间95th</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${siloBaseResult.nintyTh}</td>
                        <td >${siloResult.nintyTh}</td>
                      <c:choose>
							<c:when test="${diffBean.nintyThDiff>0}">
                               <td ><img src="statics/images/up.png"/>${diffBean.nintyThDiff}%</td>							
                            </c:when>
							<c:otherwise>
							   <td ><img src="statics/images/down.png"/>${diffBean.nintyThDiff}%</td>							
							
							</c:otherwise>
						</c:choose> 
                        <td >${siloBaseResult.nintyFiveTh}</td>
                        <td >${siloResult.nintyFiveTh}</td>
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
                        <th width="">无干扰 响应时间99th</th>
                        <th width="">干扰下 响应时间99th</th>
                        <th width="">变化率</th>
                        <th width="">无干扰 响应时间方差</th>
                        <th width="">干扰下 响应时间方差</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${siloBaseResult.nintyNineTh}</td>
                        <td >${siloResult.nintyNineTh}</td>
                         <c:choose>
							<c:when test="${diffBean.nintyNineThDiff>0}">
                               <td ><img src="statics/images/up.png"/>${diffBean.nintyNineThDiff}%</td>							
                            </c:when>
							<c:otherwise>
							   <td ><img src="statics/images/down.png"/>${diffBean.nintyFiveThDiff}%</td>							
							</c:otherwise>
						</c:choose> 
                        <td >${siloBaseResult.var}</td>
                        <td >${siloResult.var}</td>
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
                        <th width="">无干扰 响应时间平均值</th>
                        <th width="">干扰下 响应时间平均值</th>
                        <th width="">变化率</th>
                        <th width="">无干扰 响应时间最小值</th>
                        <th width="">干扰下 响应时间最小值</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${siloBaseResult.mean}</td>
                        <td >${siloResult.mean}</td>
                         <c:choose>
							<c:when test="${diffBean.varDiff>0}">
                               <td ><img src="statics/images/up.png"/>${diffBean.meanDiff}%</td>							
                            </c:when>
							<c:otherwise>
							   <td ><img src="statics/images/down.png"/>${diffBean.meanDiff}%</td>							
							</c:otherwise>
						</c:choose>   
                        <td >${siloBaseResult.min}</td>
                        <td >${siloResult.min}</td>
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
                        <th width="">无干扰 响应时间最大值</th>
                        <th width="">干扰下 响应时间最大值</th>
                        <th width="">变化率</th>
						<th width="">无干扰 缺失率</th>
                        <th width="">干扰下 缺失率</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${siloBaseResult.max}</td>
                        <td >${siloResult.max}</td>
                         <c:choose>
							<c:when test="${diffBean.maxDiff>0}">
                               <td ><img src="statics/images/up.png"/>${diffBean.maxDiff}%</td>							
                            </c:when>
							<c:otherwise>
							   <td ><img src="statics/images/down.png"/>${diffBean.maxDiff}%</td>							
							</c:otherwise>
						</c:choose> 
                        <td >${siloBaseResult.missRate}</td>
                        <td >${siloResult.missRate}</td>
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
                text: ''
            },

            xAxis: {
                title: {
                    text: '响应时间/ms'
                },
                min:0
            },
            yAxis: {
                title: {
                    text: '累计概率'
                },
                min:0,
                max:1,
                tickPositions: [0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0]
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
            series:[{name:'无干扰下cdf分布1',
                data:[[0.0,0.0],[1.0,0.01],[2.0,0.04],[3.0,0.09],[4.0,0.16],[5.0,0.25],[6.0,0.36],[7.0,0.49],[8.0,0.64],[9.0,0.81],[10.0,1.0]]}
                ,
                {name:'无干扰下cdf分布2',

                 data:[[0.0,0.05],[1.0,0.05],[2.0,0.04],[3.0,0.09],[4.0,0.16],[5.0,0.25],[6.0,0.36],[7.0,0.09],[8.0,0.64],[9.0,0.81],[10.0,1.0]]}]


});
        Highcharts.chart('websearch2', {

            chart: {
                type: 'column'
            },
            title: {
                text: '包含负值的柱形图'
            },
            xAxis: {
                categories: ['90th', '95th', '99th', '方差', '平均值','最小值','最大值','缺失率']
            },
            credits: {
                enabled: false
            },
            series: [{
                data: [${diffBean.nintyThDiff}, ${diffBean.nintyFiveThDiff}, ${diffBean.nintyNineThDiff},
                       ${diffBean.varDiff}, ${diffBean.meanDiff},${diffBean.minDiff},${diffBean.maxDiff},${diffBean.missRateDiff}]
            }]


        });
        
        Highcharts.chart('websearch3', {
        	 
            boost: {
                useGPUTranslations: true
            },
            title: {
                text: 'Highcharts drawing points'
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            subtitle: {
                text: 'Using the Boost module'
            },
            tooltip: {
                valueDecimals: 2
            },
            series: [${diffBean.baseTimeStr}]
        });
        
        Highcharts.chart('websearch4', {
        	 
            boost: {
                useGPUTranslations: true
            },
            title: {
                text: 'Highcharts drawing points'
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            subtitle: {
                text: 'Using the Boost module'
            },
            tooltip: {
                valueDecimals: 2
            },
            series: [${diffBean.baseTimeStr}]
        }); 


});
</script>
</body>
</html>