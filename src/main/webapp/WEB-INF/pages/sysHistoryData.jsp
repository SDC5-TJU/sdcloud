<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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
<form method="post" action="" id="listform">
    <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder"> 物理机测试记录查询</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
        <div class="padding border-bottom">
            <ul class="search" style="padding-left:10px;">
                <li> <a class="button border-main icon-plus-square-o" href=""> 开始时间</a> </li>
                <li><input type="text" placeholder="" name="keywords" id="startTime" class="input" style="width:180px; line-height:17px;display:inline-block" /></li>
                <li> <a class="button border-main icon-plus-square-o" href=""> 结束时间</a> </li>
                <li><input type="text" placeholder="" name="keywords" id="endTime" class="input" style="width:180px; line-height:17px;
                display:inline-block" /></li>
                <li><a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a></li>
            </ul>
        </div>
    </div>
</form>
<div>
    <!--图-->
    <div id="chart">
        <div id="websearch1" style="width: 1100px;height: 300px;position: absolute;top: 150px;left:150px;"></div>
        <div id="websearch2" style="width: 1100px;height: 300px;position: absolute; left:150px;top: 500px;"></div>
        <div id="websearch3" style="width: 1100px;height: 300px;position: absolute; left:150px;top: 850px;"></div>
        <div id="websearch4" style="width: 1100px;height: 300px;position: absolute; left:150px;top: 1200px;"></div>
    </div>

</div>
<script type="text/javascript" src="statics/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="statics/js/highcharts.js"></script>
<script type="text/javascript" src="statics/js/highcharts-more.js"></script>
<script type="text/javascript" src="statics/js/laydate.js"></script>
<script type="text/javascript">
laydate.render({
    elem: '#startTime'
    ,type: 'datetime' //指定元素
});

laydate.render({
    elem: '#endTime'
    ,type: 'datetime' //指定元素
});

</script>
<script type="text/javascript">
    $(document).ready(function() {
       
    	 Highcharts.chart('websearch1', {
             chart: {
                 zoomType: 'x'
             },
             title: {
                 text: '物理机CPU使用情况'
             },

             xAxis: {
                 type: 'datetime',
                 // maxZoom:24 * 3600 * 1000, // x轴总共显示的时间
                 //min:<?=strtotime(date('Y-m-d'))?>,
                 dateTimeLabelFormats: {
//                   minute: '%H:%M'
                     day: '%H:%M'
                 }
             },
             yAxis: {
                 title: {
                     text: 'used rate'
                 },
                 min:0

             },
             legend: {
                 layout: 'vertical',
                 align: 'right',
                 verticalAlign: 'middle'
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

             xAxis: {
                 type: 'datetime',
                 // maxZoom:24 * 3600 * 1000, // x轴总共显示的时间
                 //min:<?=strtotime(date('Y-m-d'))?>,
                 dateTimeLabelFormats: {
//                   minute: '%H:%M'
                     day: '%H:%M'
                 }
             },
             yAxis: {
                 title: {
                     text: 'used rate'
                 },
                 min:0

             },
             legend: {
                 layout: 'vertical',
                 align: 'right',
                 verticalAlign: 'middle'
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

             xAxis: {
                 type: 'datetime',
                 // maxZoom:24 * 3600 * 1000, // x轴总共显示的时间
                 //min:<?=strtotime(date('Y-m-d'))?>,
                 dateTimeLabelFormats: {
//                   minute: '%H:%M'
                     day: '%H:%M'
                 }
             },
             yAxis: {
                 title: {
                     text: 'used rate'
                 },
                 min:0

             },
             legend: {
                 layout: 'vertical',
                 align: 'right',
                 verticalAlign: 'middle'
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

             xAxis: {
                 type: 'datetime',
                 // maxZoom:24 * 3600 * 1000, // x轴总共显示的时间
                 //min:<?=strtotime(date('Y-m-d'))?>,
                 dateTimeLabelFormats: {
//                   minute: '%H:%M'
                     day: '%H:%M'
                 }
             },
             yAxis: {
                 title: {
                     text: 'used rate'
                 },
                 min:0
             },
             legend: {
                 layout: 'vertical',
                 align: 'right',
                 verticalAlign: 'middle'
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


});
</script>

</body>
</html>