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
<title>课题3展示</title>
<link rel="stylesheet" href="statics/css/pintuer.css">
<link rel="stylesheet" href="statics/css/admin.css">
<link rel="stylesheet" href="statics/css/physical.css">
</head>

<body>
	<div id="mainDiv"> 
		<div id="container1" style="width: 500px; height: 350px; "></div>
		<div id="container2" style="width: 500px; height: 350px; "></div>
		 
		<div id="containerControl">
			<span style="font-family: 微软雅黑; font-size: 14px;">线程控制:</span> 
			<input type="button" id="startButton" value="继续" onclick="start();" style="cursor: pointer">
		</div>
		<script type="text/javascript" src="statics/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="statics/js/highcharts.js"></script>
		<script type="text/javascript" src="statics/js/highcharts-more.js"></script>
		<script type="text/javascript"> 
		var localReturnedData = null;  
		 var splitData;
		var flag=false;//flag为true,开启曲线绘制
		var chart0=null,chart1=null;
	  	//定时函数,每隔1min执行一次,向后端请求新的数据
		setInterval(function() {
			if(flag==true){ 
				$.ajax({
					async:true,
					type:"get",
					url:"getMonitor3.do",//发送的get请求
					data:{},
					dataType:"text",
					success:function(returned) {   
						if(returned!=null&&returned!=""&&returned!="null"){
							localReturnedData = returned; //用当前页面的临时变量接受该返回值 
							splitData=localReturnedData.split("#");
						}
					}	
				}); 
		   }
	    },1000);
	 
		 $(document).ready(function (){
		    Highcharts.setOptions({
		        global: {
		            useUTC: false
		        }
		 }); 
       
       /**
       * 绘制高优先级cdf的曲线
       * 在container1的div里绘制该图
       */
       chart0=new Highcharts.chart('container1', {
    	   credits:{ 
       	      enabled:false 
       		},
       		plotOptions: {
       			 series: {
                 	marker: {
                     	symbol: 'circle' //曲线点类型："circle", "square", "diamond", "triangle","triangle-down"，默认是"circle"
                 }
             }
       		},
           chart: {
               type: 'line',
               animation: Highcharts.svg, // don't animate in old IE
               marginRight: 10,
               events: {
                   load: function () {
                 	// set up the updating of the chart each min
                       var series = this.series;
                       setInterval(function (){  
                    	   if(flag==true){
                    		  while(series.length > 0) {
              	                series[0].remove(false);
              	              } 
                    		 eval("chart0.addSeries("+splitData[0]+", false);");
                    		 eval("chart0.addSeries("+splitData[1]+", false);");
                    		 chart0.redraw();
                          }
                       }, 1000);
                   }
               }
           },
           title: {
				text: '高优先级部分服务延迟CDF对比'
			},
           xAxis: {
           	title: {
					text: 'Latency(millisecond)'
				}
           },
           yAxis: {
				title: {
						text: 'CDF'
				},
         	min:0,
         	max:1
			},
			legend: {
				layout: 'horizontal',
				align: 'center',
				verticalAlign: 'bottom'
			},
           tooltip: {
               formatter: function () {
                   return '<b>' + this.series.name + '</b><br/>' +'小于'+
                       Highcharts.numberFormat(this.x,2)+'ms'+'的概率:'+
                       Highcharts.numberFormat(this.y, 2);
               }
           },            
           exporting: {
               enabled: false
           },
           series:[${cdf_high}]  
       });
       
        /**
         * 绘制高优先级cdf的曲线
         * 在container1的div里绘制该图
         */
          chart1=new Highcharts.chart('container2', {
          	credits:{ 
          	      enabled:false 
          		},
          		plotOptions: {
          			 series: {
                    	marker: {
                        	symbol: 'circle' //曲线点类型："circle", "square", "diamond", "triangle","triangle-down"，默认是"circle"
                    }
                }
          		},
              chart: {
                  type: 'line',
                  animation: Highcharts.svg, // don't animate in old IE
                  marginRight: 10,
                  events: {
                      load: function () {
                    	// set up the updating of the chart each min
                          var series = this.series;
                          setInterval(function (){  
                       	   if(flag==true){
                       		  while(series.length > 0) {
                 	                series[0].remove(false);
                 	              } 
                       		 eval("chart1.addSeries("+splitData[2]+", false);");
                       		 eval("chart1.addSeries("+splitData[3]+", false);");
                       		 chart1.redraw();
                             }
                          }, 1000);
                      }
                  }
              },
              title: {
  				text: '低优先级部分服务延迟CDF对比'
  			},
              xAxis: {
              	title: {
  					text: 'Latency(millisecond)'
  				}
              },
              yAxis: {
  				title: {
  						text: 'CDF'
  				},
            	min:0,
            	max:1
  			},
  			legend: {
  				layout: 'horizontal',
  				align: 'center',
  				verticalAlign: 'bottom'
  			},
              tooltip: {
                  formatter: function () {
                      return '<b>' + this.series.name + '</b><br/>' +'小于'+
                          Highcharts.numberFormat(this.x,2)+'ms'+'的概率:'+
                          Highcharts.numberFormat(this.y, 2);
                  }
              },            
              exporting: {
                  enabled: false
              },
              series:[${cdf_low}]  
          });
 });
</script>
<script>
function start(){
	if(flag==true){
		 flag=false;
		 document.getElementById('startButton').value="继续";
	}else{
		 flag=true;
		 document.getElementById('startButton').value="暂停";
	}
}
</script>
</div>
</body>
</html>