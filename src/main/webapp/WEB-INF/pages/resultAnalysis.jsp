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
<body style="height:3300px;">
<div >
<ul class="nav nav-tabs" style="">
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
       <c:if test="${cassandra.enable==1}">  
        <li class="" style="float: left;">
            <a  href="getCassandraResult.do?testRecordId=${testRecordId}" target="_blank" id="memcached">cassandra</a>
        </li>
       </c:if>
    </ul>
    
   <!--图-->
    <div id="chart">
        <div id="websearch1" style="width: 1200px;height: 300px;position: absolute; left:50px;top: 100px;"></div>
        <div id="websearch2" style="width: 1200px;height: 300px;position: absolute; left:50px;top: 450px;"></div>
        <div id="websearch3" style="width: 1200px;height: 300px;position: absolute; left:50px;top: 800px;"></div>
        <div id="websearch4" style="width: 1200px;height: 300px;position: absolute; left:50px;top: 1150px;"></div>
        <div id="websearch5" style="width: 1200px;height: 300px;position: absolute; left:50px;top: 1500px;"></div>
        <div id="websearch6" style="width: 1200px;height: 300px;position: absolute; left:50px;top: 1850px;"></div>
        <div id="websearch7" style="width: 1200px;height: 300px;position: absolute; left:50px;top: 2200px;"></div>
        <div id="websearch8" style="width: 1200px;height: 300px;position: absolute; left:50px;top: 2550px;"></div>
        <div id="websearch9" style="width: 1200px;height: 300px;position: absolute; left:50px;top: 2900px;"></div>
        <div id="websearch10" style="width: 1200px;height: 300px;position: absolute; left:50px;top: 3250px;"></div>
        
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
             text: '物理机CPU使用情况'
         },
         tooltip: {
             split: true,
             valueSuffix: '%'
         },
         xAxis: {
             title: {
                 text: '时间'
             },
             type: 'datetime',
             // maxZoom:24 * 3600 * 1000, // x轴总共显示的时间
             //min:<?=strtotime(date('Y-m-d'))?>,
             dateTimeLabelFormats: {
//               minute: '%H:%M'
                 day: '%H:%M'
             }
         },
         yAxis: {
             title: {
                 text: '使用率%'
             },
             min:0

         },
         legend: {
             layout: 'vertical',
             align: 'right',
             verticalAlign: 'middle',
             align: 'center', //水平方向位置
             verticalAlign: 'bottom', //垂直方向位置
             x: 0, //距离x轴的距离
             y: 0 //距离Y轴的距离
         },
         colors: ['#058DC7', '#ff3300'],
         plotOptions: {
             area: {
                 fillColor: {
                     linearGradient: {
                         x1: 0,
                         y1: 0,
                         x2: 0,
                         y2: 1
                     },
                     stops: [
                         [0, Highcharts.getOptions().colors[0]],
                         [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                     ]
                 },
                 marker: {
                     radius: 2
                 },
                 lineWidth: 1,
                 states: {
                     hover: {
                         lineWidth: 1
                     }
                 },
                 threshold: null
             }
         },

         series: [${sysUsageStr0}]
     });

     Highcharts.chart('websearch2', {
         chart: {
             zoomType: 'x'
         },
         title: {
             text: '物理机memory使用情况'
         },
         tooltip: {
             split: true,
             valueSuffix: '%'
         },
         xAxis: {
             title: {
                 text: '时间'
             },
             type: 'datetime',
             // maxZoom:24 * 3600 * 1000, // x轴总共显示的时间
             //min:<?=strtotime(date('Y-m-d'))?>,
             dateTimeLabelFormats: {
//               minute: '%H:%M'
                 day: '%H:%M'
             }
         },
         yAxis: {
             title: {
                 text: '使用率%'
             },
             min:0

         },
         legend: {
             layout: 'vertical',
             align: 'right',
             verticalAlign: 'middle',
             align: 'center', //水平方向位置
             verticalAlign: 'bottom', //垂直方向位置
             x: 0, //距离x轴的距离
             y: 0 //距离Y轴的距离
         },
         colors: ['#058DC7', '#ff3300'],
         plotOptions: {
             area: {
                 fillColor: {
                     linearGradient: {
                         x1: 0,
                         y1: 0,
                         x2: 0,
                         y2: 1
                     },
                     stops: [
                         [0, Highcharts.getOptions().colors[0]],
                         [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                     ]
                 },
                 marker: {
                     radius: 2
                 },
                 lineWidth: 1,
                 states: {
                     hover: {
                         lineWidth: 1
                     }
                 },
                 threshold: null
             }
         },

         series: [${sysUsageStr1}]
     });

     Highcharts.chart('websearch3', {
         chart: {
             zoomType: 'x'
         },
         title: {
             text: '物理机I/O使用情况'
         },
         tooltip: {
             split: true,
             valueSuffix: '%'
         },
         xAxis: {
             title: {
                 text: '时间'
             },
             type: 'datetime',
             // maxZoom:24 * 3600 * 1000, // x轴总共显示的时间
             //min:<?=strtotime(date('Y-m-d'))?>,
             dateTimeLabelFormats: {
//               minute: '%H:%M'
                 day: '%H:%M'
             }
         },
         yAxis: {
             title: {
                 text: '使用率%'
             },
             min:0

         },
         legend: {
             layout: 'vertical',
             align: 'right',
             verticalAlign: 'middle',
             align: 'center', //水平方向位置
             verticalAlign: 'bottom', //垂直方向位置
             x: 0, //距离x轴的距离
             y: 0 //距离Y轴的距离
         },
         colors: ['#058DC7', '#ff3300'],
         plotOptions: {
             area: {
                 fillColor: {
                     linearGradient: {
                         x1: 0,
                         y1: 0,
                         x2: 0,
                         y2: 1
                     },
                     stops: [
                         [0, Highcharts.getOptions().colors[0]],
                         [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                     ]
                 },
                 marker: {
                     radius: 2
                 },
                 lineWidth: 1,
                 states: {
                     hover: {
                         lineWidth: 1
                     }
                 },
                 threshold: null
             }
         },

         series: [${sysUsageStr2}]
     });
     Highcharts.chart('websearch4', {
         chart: {
             zoomType: 'x'
         },
         title: {
             text: '物理机网络使用情况'
         },
         tooltip: {
             split: true,
             valueSuffix: '%'
         },
         xAxis: {
             title: {
                 text: '时间'
             },
             type: 'datetime',
             // maxZoom:24 * 3600 * 1000, // x轴总共显示的时间
             //min:<?=strtotime(date('Y-m-d'))?>,
             dateTimeLabelFormats: {
//               minute: '%H:%M'
                 day: '%H:%M'
             }
         },
         yAxis: {
             title: {
                 text: '使用率%'
             },
             min:0
         },
         legend: {
             layout: 'vertical',
             align: 'right',
             verticalAlign: 'middle',
             align: 'center', //水平方向位置
             verticalAlign: 'bottom', //垂直方向位置
             x: 0, //距离x轴的距离
             y: 0 //距离Y轴的距离
         },
         colors: ['#058DC7', '#ff3300'],
         plotOptions: {
             area: {
                 fillColor: {
                     linearGradient: {
                         x1: 0,
                         y1: 0,
                         x2: 0,
                         y2: 1
                     },
                     stops: [
                         [0, Highcharts.getOptions().colors[0]],
                         [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                     ]
                 },
                 marker: {
                     radius: 2
                 },
                 lineWidth: 1,
                 states: {
                     hover: {
                         lineWidth: 1
                     }
                 },
                 threshold: null
             }
         },

         series: [${sysUsageStr3}]
     });
     Highcharts.chart('websearch5', {
         chart: {
             type: 'area',
             zoomType: 'x'
         },
         title: {
             text: '各应用CPU占用情况'
         },
         xAxis: {
             categories: ${appUsageStr6},
             tickmarkPlacement: 'on',
             title: {
                 enabled: false
             }
         },
         yAxis: {
             title: {
                 text: '使用率%'
             },
             min:0

         },
         legend: {
             layout: 'vertical',
             align: 'right',
             verticalAlign: 'middle'
         },
         tooltip: {
             split: true,
             valueSuffix: '%'
         },
         plotOptions: {
             area: {
                 stacking: 'normal',
                 lineColor: '#666666',
                 lineWidth: 1,
                 marker: {
                     lineWidth: 1,
                     lineColor: '#666666'
                 }
             }
         },
         series: [${appUsageStr0}]
     });
     Highcharts.chart('websearch6', {
         chart: {
             type: 'area',
             zoomType: 'x'
         },
         title: {
             text: '各应用内存占用情况'
         },
         xAxis: {
             categories: ${appUsageStr6},
             tickmarkPlacement: 'on',
             title: {
                 enabled: false
             }
         },
         yAxis: {
             title: {
                 text: '使用率%'
             },
             min:0

         },
         legend: {
             layout: 'vertical',
             align: 'right',
             verticalAlign: 'middle'
         },
         tooltip: {
             split: true,
             valueSuffix: '%'
         },
         plotOptions: {
             area: {
                 stacking: 'normal',
                 lineColor: '#666666',
                 lineWidth: 1,
                 marker: {
                     lineWidth: 1,
                     lineColor: '#666666'
                 }
             }
         },
         series: [${appUsageStr1}]
     });
     
     Highcharts.chart('websearch7', {
         chart: {
             type: 'area'
         },
         title: {
             text: 'I/O input'
         },
         xAxis: {
             categories: ${appUsageStr6},
             tickmarkPlacement: 'on',
             title: {
                 enabled: false
             }
         },
         yAxis: {
             title: {
                 text: 'MB'
             },
             min:0

         },
         legend: {
             layout: 'vertical',
             align: 'right',
             verticalAlign: 'middle'
         },
         tooltip: {
             split: true,
             valueSuffix: ' MB'
         },
         plotOptions: {
             area: {
                 stacking: 'normal',
                 lineColor: '#666666',
                 lineWidth: 1,
                 marker: {
                     lineWidth: 1,
                     lineColor: '#666666'
                 }
             }
         },
         series: [${appUsageStr2}]
     });
     
     Highcharts.chart('websearch8', {
         chart: {
             type: 'area'
         },
         title: {
             text: 'I/O output'
         },
         xAxis: {
             categories: ${appUsageStr6},
             tickmarkPlacement: 'on',
             title: {
                 enabled: false
             }
         },
         yAxis: {
             title: {
                 text: 'MB'
             },
             min:0

         },
         legend: {
             layout: 'vertical',
             align: 'right',
             verticalAlign: 'middle'
         },
         tooltip: {
             split: true,
             valueSuffix: ' MB'
         },
         plotOptions: {
             area: {
                 stacking: 'normal',
                 lineColor: '#666666',
                 lineWidth: 1,
                 marker: {
                     lineWidth: 1,
                     lineColor: '#666666'
                 }
             }
         },
         series: [${appUsageStr3}]
     });

     Highcharts.chart('websearch9', {
         chart: {
             type: 'area'
         },
         title: {
             text: 'net input'
         },
         xAxis: {
             categories: ${appUsageStr6},
             tickmarkPlacement: 'on',
             title: {
                 enabled: false
             }
         },
         yAxis: {
             title: {
                 text: 'MB'
             },
             min:0

         },
         legend: {
             layout: 'vertical',
             align: 'right',
             verticalAlign: 'middle'
         },
         tooltip: {
             split: true,
             valueSuffix: ' MB'
         },
         plotOptions: {
             area: {
                 stacking: 'normal',
                 lineColor: '#666666',
                 lineWidth: 1,
                 marker: {
                     lineWidth: 1,
                     lineColor: '#666666'
                 }
             }
         },
         series: [${appUsageStr4}]
     });
     
     Highcharts.chart('websearch10', {
         chart: {
             type: 'area'
         },
         title: {
             text: 'net output'
         },
         xAxis: {
             categories: ${appUsageStr6},
             tickmarkPlacement: 'on',
             title: {
                 enabled: false
             }
         },
         yAxis: {
             title: {
                 text: 'MB'
             },
             min:0

         },
         legend: {
             layout: 'vertical',
             align: 'right',
             verticalAlign: 'middle'
         },
         tooltip: {
             split: true,
             valueSuffix: ' MB'
         },
         plotOptions: {
             area: {
                 stacking: 'normal',
                 lineColor: '#666666',
                 lineWidth: 1,
                 marker: {
                     lineWidth: 1,
                     lineColor: '#666666'
                 }
             }
         },
         series: [${appUsageStr5}]
     });

});
</script>

</body>
</html>