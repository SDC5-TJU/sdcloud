<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<!--测试控制模块-->
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />


    <link rel="stylesheet" href="statics/css/pintuer.css">
    <link rel="stylesheet" href="statics/css/admin.css">

    <link rel="stylesheet" type="text/css" href="statics/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="statics/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="statics/css/iconfont.css" />

    <link rel="stylesheet" type="text/css" href="statics/css/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="statics/css/style.css" />

    <link href="statics/css/webuploader.css" rel="stylesheet" type="text/css" />

</head>
<body>
<!--<div >
    <ul class="nav nav-tabs" >
        <li class="active" style="float: left;">
            <a  href="#home" id="onlineControl">在线服务</a>
        </li>
        <li class="" style="float: left;">
            <a  href="#" id="offlineControl">离线服务</a>
        </li>
    </ul>

</div>-->
<div>
    <div class="new-page-container" >
        <form action="" method="post" class="form form-horizontal" id="form-article-add">
            <!--在线-->
            <div id="online">
                <div class="row cl">
                    <label class="col-xs-4 new-col-sm-2 new-center1"></label>
                    <label class="col-xs-4 new-col-sm-2 new-center1" >请求总数</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">预热次数</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">请求策略</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">间隔单位</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1"></label>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-2">web搜索：</label>
                    <div class="formControls col-xs-8 col-sm-9">
                        <input style="margin-left:-20px; " class="input new-w50 new-col-sm-2 " type="text"  value="${webSearch.requestCount}" placeholder="5000" id="config1-1" name="requestCount" >
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text" value="${webSearch.warmUpCount}" placeholder="" id="config1-2" name="warmUpCount">
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text"  value="${webSearch.pattern}" placeholder="定值/均匀/指数/泊松" id="config1-3" name="pattern" >
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text"  value="${webSearch.intensity}" placeholder="2000" id="config1-4"name="intensity">
                        <input style="margin-left:20px; " type="button"  class="config " onclick="" value="开启" id="">
                        <input style="margin-left:20px; " type="button"  class="config" onclick="" value="基准采集" id="">
                        <!--<input style="float: right;" type="button"  class="btn btn-primary radius " value="禁用" >-->
                    </div>

                </div>
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-2">电商服务：</label>
                    <div class="formControls col-xs-8 col-sm-9">
                        <input style="margin-left:-20px; " class="input new-w50 new-col-sm-2 " type="text"  value="${webServer.requestCount}" placeholder="5000" id="config2-1" name="requestCount">
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text" value="${webServer.warmUpCount}" placeholder="" id="config2-2" name="warmUpCount">
                        <input  style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text"  value="${webServer.pattern}" placeholder="10" id="config2-3" name="pattern" >
                        <input  style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text"  value="${webServer.intensity}" placeholder="2000" id="config2-4" name="intensity">
                        <input style="margin-left:20px; " type="button"  class="config " onclick="" value="开启" id="">
                        <input style="margin-left:20px; " type="button"  class="config" onclick="" value="基准采集" id="">
                    </div>
                </div>
                <div class="row cl">
                    <label class="col-xs-4 new-col-sm-2 new-center1"></label>
                    <label class="col-xs-4 new-col-sm-2 new-center1" >请求总数</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">预热次数</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">请求策略</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">QPS</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1"></label>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-2">memcached:</label>
                    <div class="formControls col-xs-8 col-sm-9">
                        <input style="margin-left:-20px; " class="input new-w50 new-col-sm-2 " type="text"  value="${memcached.requestCount}" placeholder="20000" id="config3-1" name="requestCount" >
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text"  value="${memcached.warmUpCount}" placeholder="2000" id="config3-2" name="intensity" >
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text" value="均匀" placeholder="" id="" name="" disabled="disabled">
						<input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text"  value="${memcached.intensity}" placeholder="2000" id="config3-3" name="warmUpCount">
                        <input style="margin-left:20px; " type="button"  class="config " onclick="" value="开启" id="">
                        <input style="margin-left:20px; " type="button"  class="config" onclick="" value="基准采集" id="">
                    </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-2">silo:</label>
                    <div class="formControls col-xs-8 col-sm-9">
                        <input style="margin-left:-20px; " class="input new-w50 new-col-sm-2 " type="text"  value="${silo.requestCount}" placeholder="20000" id="config4-1" name="requestCount">
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text"  value="${silo.warmUpCount}" placeholder="2000" id="config4-2" name="warmUpCount" >
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text" value="均匀" placeholder="" id="" name="" disabled="disabled">
						<input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text"  value="${silo.intensity}" placeholder="2000" id="config4-3" name="intensity">
                        <input style="margin-left:20px; " type="button"  class="config " onclick="" value="开启" id="">
                        <input style="margin-left:20px; " type="button"  class="config" onclick="" value="基准采集" id="">
                    </div>
                </div>
            </div>

            <!--离线-->
            <div id="offline">
                <div class="row cl">
                    <label class="col-xs-4 new-col-sm-2 new-center1">读取操作</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1" >写入操作</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">负载策略</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">读写块大小</label>
					<label class="col-xs-4 new-col-sm-2 new-center1"></label>
                    <label class="col-xs-4 new-col-sm-2 new-center1"></label>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-2">Bonnie：</label>
                    <div class="formControls col-xs-8 col-sm-9">
                        <input style="margin-left:-20px; " class="input new-w50 new-col-sm-2 " type="text"  value="" placeholder="按块读取" id="" name="" disabled="disabled">
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text" value="" placeholder="按块写入" id="config5-1" name="" disabled="disabled">
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text"  value="循环" placeholder="" id="" name="" disabled="disabled">
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text"  value="${bonnie.intensity}" placeholder="" id="" name="intensity" >
                        <input style="margin-left:20px; " type="button"  class="config " onclick="" value="开启" id="">
                        <input style="margin-left:20px; " type="button"  class="config " onclick="" value="停止" id="">
                    </div>
                </div>
                <div class="row cl">
                    <label class="col-xs-4 new-col-sm-2 new-center1"></label>
                    <label class="col-xs-4 new-col-sm-2 new-center1" >请求总数</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">预热次数</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">负载策略</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">线程数量</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1"></label>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-2">scimark：</label>
                    <div class="formControls col-xs-8 col-sm-9">
                        <input style="margin-left:-20px; " class="input new-w50 new-col-sm-2 " type="text"  value="" placeholder="" id="" name="" disabled="disabled">
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text" value="" placeholder="" id="config6-1" name="" disabled="disabled">
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text"  value="循环" placeholder="" id="" name="" disabled="disabled">
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text"  value="${scimark.intensity}" placeholder="" id="" name="intensity" >
                        <input style="margin-left:20px; " type="button"  class="config " onclick="" value="开启" id="">
                    </div>
                </div>
                <div class="row cl">
                    <label class="col-xs-4 new-col-sm-2 new-center1"></label>
                    <label class="col-xs-4 new-col-sm-2 new-center1"></label>
                    <label class="col-xs-4 new-col-sm-2 new-center1"></label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">负载类型</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">reduce数</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1"></label>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-2">Hadoop：</label>
                    <div class="formControls col-xs-8 col-sm-9">
                        <input style="margin-left:-20px; " class="input new-w50 new-col-sm-2 " type="text"  value="" placeholder="" id="config7-1" name="" disabled="disabled">
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text" value="" placeholder="" id="config7-2" name="" disabled="disabled">
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text"  value="${hadoop.pattern}" placeholder="IO密集型/CPU密集型/综合" id="" name="pattern" >
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text"  value="${hadoop.intensity}" placeholder="" id="" name="intensity" >
                        <input style="margin-left:20px; " type="button"  class="config " onclick="" value="开启" id="">
                    </div>
                </div>
				<div class="row cl">
                    <label class="col-xs-4 new-col-sm-2 new-center1"></label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">请求总数</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">预热总数</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">负载策略</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1">间隔单位/ms</label>
                    <label class="col-xs-4 new-col-sm-2 new-center1"></label>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-2">Cassandra：</label>
                    <div class="formControls col-xs-8 col-sm-9">
                        <input style="margin-left:-20px; " class="input new-w50 new-col-sm-2 " type="text"  value="${cassandra.requestCount}" placeholder="" id="config8-1" name="requestCount" disabled="disabled">
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text" value="" placeholder="" id="config8-2" name="" disabled="disabled">
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text"  value="" placeholder="" id="" name="" disabled="disabled">
                        <input style="margin-left:20px; " class="input new-w50 new-col-sm-2" type="text"  value="${cassandra.intensity}" placeholder="" id="" name="intensity" >
                        <input style="margin-left:20px; " type="button"  class="config " onclick="" value="开启" id="">
                    </div>
                </div>
            </div>

        </form>
    </div>

    <div id="time" style="position: absolute; top:90px;right: 70px; width: 27%; ">
        <div class="row cl">
            <label style="width: 30%; float: left;text-align: right;" >开始时间：</label>
                <input style="margin-left:10px; width: 60%;" class="input new-w50 new-col-sm-2 " type="text"  value="${recordBean.startTime}" placeholder="" id="startTime" name="" >

        </div>
        <div class="row cl" style="margin-top: 30px;">
            <label style="width: 30%; float: left;text-align: right;" >结束时间：</label>
            <input style="margin-left:10px; width: 60%;" class="input new-w50 new-col-sm-2 " type="text"  value="${recordBean.endTime}" placeholder="" id="endTime" name="" >

        </div>
    </div>

    <div id="chart1" style="position: absolute; top:290px;right: 70px; width: 400px; height: 200px;"></div>
    <div id="chart2" style="position: absolute; top:500px;right: 70px; width: 400px; height: 200px;"></div>


</div>


<script src="statics/js/jquery.js"></script>
<script src="statics/js/pintuer.js"></script>
<script type="text/javascript" src="statics/js/highcharts.js"></script>
<script type="text/javascript" src="statics/js/highcharts-more.js"></script>
<script type="text/javascript">
/**
 * ajax控制判断所有按钮的状态 enable/disable
 */
 setInterval(function() {
		$.ajax({
			async:true,
			type:"post",
			url:"getPhyResourceUse.do",
			data:{},
			dataType:"json",
			success:function(returned){
				if(returned!=null&&returned!=""&&returned!="null"){
					returnedData = returned;
					console.log(returned);
					//to do
					/*遍历json数组，获取并判断每个应有的状态，为true 则disable的按钮 为false 则相反*/
				}
			}	
		});
},5000);
 
/**
 * 8个应用正式测试和基准测试的控制按钮动作
 */
$("#addButton").click(function(){
		    $.ajax({
				async:true,
				type:"post",
				url:"executeWebSearchApp.do",
				data:{isBase:0},
				dataType:"text",
				success:function(returned){ 
				}	
			});
});
$("#addButton").click(function(){
    $.ajax({
		async:true,
		type:"post",
		url:"executeWebSearchApp.do",
		data:{isBase:1},
		dataType:"text",
		success:function(returned){ 
		}	
	});
});
</script>
<script type="text/javascript">
   $(document).ready(function() {
 /* $('#offlineControl').click(function(){
            $("#online").hide();   //隐藏
            $("#offline").show();
        })

        $("#onlineControl").click(function(){
            $("#online").show();   //隐藏
            $("#offline").hide();
        })*/
/*        $('#startTime').click(function(){
           var mydate = new Date();
           var t=mydate.toLocaleString();
           $("#startTime").val(t);
       })
       $('#endTime').click(function(){
           var mydate1 = new Date();
           var t1=mydate1.toLocaleString();
           $("#endTime").val(t1);
       }) */

       //绘图
       Highcharts.setOptions({
           global: {
               useUTC: false
           }
       });
       Highcharts.chart('chart1', {
           chart: {
               zoomType: 'x'
           },
           title: {
               text: 'webServer query benchmark'
           },

           xAxis: {
               type: 'datetime',
               // maxZoom:24 * 3600 * 1000, // x轴总共显示的时间
               //min:<?=strtotime(date('Y-m-d'))?>,
               dateTimeLabelFormats: {
//                  minute: '%H:%M'
                   day: '%H:%M'
               }
           },
           yAxis: {
               title: {
                   text: 'query time'
               },
               min:0

           },
           legend: {
               layout: 'vertical',
               align: 'left',
               verticalAlign: 'top',
               x: 100,
               y: 70,
               floating: true,
               backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF',
               borderWidth: 1
           },
           plotOptions: {
               area: {
                   /*fillColor: {
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
                   },*/
                   marker: {
                       radius: 5
                   },
                   lineWidth: 1,
                   states: {
                       hover: {
                           lineWidth: 0
                       }
                   },
                   threshold: null
               }
           },

           series: [{
               type: 'scatter',
               name:'测试一下',
               data:[100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,214,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,22,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
               ],
               pointInterval: 306000,
               pointStart: Date.UTC(2014, 6, 10,0,0,0),
               pointEnd:Date.UTC(2014,6,10,23,59,59),
               // pointEnd:Date.UTC(<?php date('Y , m, d',strtotime('-1 month'))?>, 0, 0, 0;?>)
           }]
       });

       Highcharts.chart('chart2', {
           chart: {
               zoomType: 'x'
           },
           title: {
               text: 'solrCloud query benchmark'
           },

           xAxis: {
               type: 'datetime',
               // maxZoom:24 * 3600 * 1000, // x轴总共显示的时间
               //min:<?=strtotime(date('Y-m-d'))?>,
               dateTimeLabelFormats: {
//                  minute: '%H:%M'
                   day: '%H:%M'
               }
           },
           yAxis: {
               title: {
                   text: 'query time'
               },
               min:0

           },
           legend: {
               layout: 'vertical',
               align: 'right',
               verticalAlign: 'middle'
           },
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

           series: [{
               type: 'area',
               name:'测试一下',
               data:[100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,214,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,22,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
               ],
               pointInterval: 306000,
               pointStart: Date.UTC(2014, 6, 10,0,0,0),
               pointEnd:Date.UTC(2014,6,10,23,59,59),
               // pointEnd:Date.UTC(<?php date('Y , m, d',strtotime('-1 month'))?>, 0, 0, 0;?>)
           }]
       });

    });



</script>
</body>
</html>