<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>riscv redis实时响应数据</title>
<link rel="stylesheet" href="statics/css/pintuer.css">
<link rel="stylesheet" href="statics/css/admin.css">
<link rel="stylesheet" href="statics/css/physical.css">
</head>

<body>
	<div id="mainDiv">
		<div id="container1" style="width:1000px;height:350px"></div>
		<div id="container2" style="width:1000px;height:350px;left:-600px;top:350px"></div>
		 <div id="QpsDiv" style="width: 50px; height: 50px; position: absolute; left: 309px; top: 27px;">QPS:<span id="qps"></span></div>
		<div id="containerControl">
			<span style="font-family: 微软雅黑; font-size: 14px;">线程控制:</span> 
			<input type="button" id="startButton" value="继续" onclick="start();" style="cursor: pointer">
		</div> 

		<script type="text/javascript" src="statics/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="statics/js/highcharts.js"></script>
		<script type="text/javascript" src="statics/js/highcharts-more.js"></script>
		<script type="text/javascript">
		 
		var flag=false;
		var returnedRealData = null; 
		setInterval(function() {
			if(flag==true){
				$.ajax({
					async:true,
					type:"get",
					url:"getRiscvRedisRealQueryData.do",
					data:{},
					dataType:"json",
					success:function(returned) {  
						if(returned!=null&&returned!=""&&returned!="null"){
							returnedRealData = returned; 
						}
					}	
				});
			 
		   }
	    },1000);
	 
	function start(){
		if(flag==true){
			 flag=false;
			 document.getElementById('startButton').value="继续";
		}else{
			 flag=true;
			 document.getElementById('startButton').value="暂停";
		}
	}
	 
	 
 $(document).ready(function (){
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    /**
    * mean
    */
     Highcharts.chart('container1', {
    	 credits:{ 
     	      enabled:false 
     		},
        chart: {
            type: 'line',
            animation: Highcharts.svg, // don't animate in old IE
            marginRight: 10,
            events: {
                load: function () {
                    // set up the updating of the chart each second
                    var series = this.series[0];
                    var lastcollecttime=null;
                    var x,y;
                    setInterval(function (){
                    	if(flag==true){
            						  if(returnedRealData!=null){
          			            	    x = returnedRealData[0].collectTime;
          			            	    y = returnedRealData[0].mean;
          			            	    z = returnedRealData[0].qps;
          			            	    if(lastcollecttime==null){//如果第一次判断 直接添加点进去
          			            	    	 series.addPoint([x,y], true, true); 
          			            	    	 lastcollecttime = x;
          			            	    	 document.getElementById('qps').innerHTML=z;
          			            	    }else{ 
          			            	    	if(lastcollecttime<x){//如果不是第一次判断，则只有上次时间小于当前时间时才添加点
          			            	    		series.addPoint([x,y], true, true); 
          				            	    	lastcollecttime = x; 
          				            	    	document.getElementById('qps').innerHTML=z;
          			            	    	}
          			            	    } 
          			               }  
            				}
                    }, 1000);
                }
            }
        },
        title: {
            text: 'redis响应时间平均值'
        },
        xAxis: {
        	type: 'datetime',
            tickPixelInterval: 150,
            gridLineWidth: 1
        },
        yAxis: {
            title: {
                text: '响应时间 /ms'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }], 
			min:0,
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                    Highcharts.numberFormat(this.y,0)+'ms';
            }
        },
        legend: {
            enabled: false
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
        exporting: {
            enabled: false
        },
        series:[${redisMeanStr}]
    });
     /**
      * 95th
      */
       Highcharts.chart('container2', {
      	 credits:{ 
       	      enabled:false 
       		},
          chart: {
              type: 'line',
              animation: Highcharts.svg, // don't animate in old IE
              marginRight: 10,
              events: {
                  load: function () {
                      // set up the updating of the chart each second
                      var series = this.series[0];
                      var lastcollecttime=null;
                      var x,y;
                      setInterval(function (){
                      	if(flag==true){
              						  if(returnedRealData!=null){
            			            	    x = returnedRealData[0].collectTime;
            			            	    y = returnedRealData[0].nintyNineTh; 
            			            	    if(lastcollecttime==null){//如果第一次判断 直接添加点进去
            			            	    	 series.addPoint([x,y], true, true); 
            			            	    	 lastcollecttime = x; 
            			            	    }else{ 
            			            	    	if(lastcollecttime<x){//如果不是第一次判断，则只有上次时间小于当前时间时才添加点
            			            	    		series.addPoint([x,y], true, true); 
            				            	    	lastcollecttime = x;  
            			            	    	}
            			            	    } 
            			               }  
              				}
                      }, 1000);
                  }
              }
          },
          title: {
              text: 'redis响应时间99th值'
          },
          xAxis: {
          	type: 'datetime',
              tickPixelInterval: 150,
              gridLineWidth: 1
          },
          yAxis: {
              title: {
                  text: '响应时间 /ms'
              },
              plotLines: [{
                  value: 0,
                  width: 1,
                  color: '#808080'
              }], 
  			min:0,
          },
          tooltip: {
              formatter: function () {
                  return '<b>' + this.series.name + '</b><br/>' +
                      Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                      Highcharts.numberFormat(this.y,0)+'ms';
              }
          },
          legend: {
              enabled: false
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
          exporting: {
              enabled: false
          },
          series:[${redisNintyNineThStr}]
      });
 });
</script>
	</div>
</body>
</html>
