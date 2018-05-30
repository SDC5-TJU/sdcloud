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
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="statics/css/pintuer.css">
<link rel="stylesheet" href="statics/css/admin.css">
<script src="statics/js/jquery.js"></script>
<script src="statics/js/pintuer.js"></script>
</head>
<body style="height: 3300px;">
	<div> 
		<!--图-->
		<div id="chart">
			<div id="websearch1"
				style="width: 1200px; height: 400px; position: absolute; left: 50px; top: 100px;"></div>
			<div id="websearch2"
				style="width: 1200px; height: 400px; position: absolute; left: 50px; top: 550px;"></div>
		</div>


	</div>
	<script type="text/javascript" src="statics/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="statics/js/highcharts.js"></script>
	<script type="text/javascript" src="statics/js/highcharts-more.js"></script>
	<script type="text/javascript" src="statics/js/highstock.js"></script>
	<script type="text/javascript" src="statics/js/exporting.js"></script>
	<script type="text/javascript" src="statics/js/highcharts-zh_CN.js"></script>
	<script type="text/javascript">
Highcharts.setOptions({ 
	global: { 
		useUTC: false 
		} 
	});
Highstock.setOptions({ 
	global: { 
		useUTC: false 
		} 
    });
$(document).ready(function() {
    Highstock.stockChart('websearch1', {
    	
    	credits:{ 
    	      enabled:false 
    	},
    	scrollbar : {
            enabled : false
        },
    	rangeSelector: {
    	    buttons: [{
    	        type: 'minute',
    	        count: 1,
    	        text: '1min'
    	    }, {
    	        type: 'minute',
    	        count: 5,
    	        text: '5min'
    	    }, {
    	        type: 'minute',
    	        count: 30,
    	        text: '0.5h'
    	    }, {
    	        type: 'all',
    	        text: '所有'
    	    }]
    	},
        title: {
            text: '物理机CPU使用率 %'
        },
        yAxis: {
            title: {
                text: 'used rate'
            },
       	    min:0,
       	    max:100
        },
        colors: ['#058DC7', '#ff3300'],
        xAxis:{
       	 plotLines:[${appRecordLineStr}]
       },
        series: [${str1},${appRecordStr}]
     });

    Highcharts.chart('websearch2', {
        chart: {
            type: 'scatter',
            zoomType: 'x'
        },
        plotOptions: {
            series: {
                marker: {
                    radius: 1
                }
            }
        },
        boost: {
            useGPUTranslations: true
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        title: {
            text: '访问延迟分布'
        },
        legend: {                                                                    
            enabled: false                                                           
        } ,
        yAxis: {
            title: {
                text: '响应时间/ms'
            },
        },
        tooltip: {
            formatter:function(){
                return'<strong>'+this.series.name+'</strong><br/>'+
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S.%L',this.x)+'<br/>'+'响应时间：'+this.y+' ms';
            },
        },
        series: [${solrCloudStr}]
    });
  

});
</script>

</body>
</html>