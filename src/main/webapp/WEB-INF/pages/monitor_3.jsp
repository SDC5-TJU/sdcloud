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
		<div id="container1"></div>
		<div id="container2"></div>
		<script type="text/javascript" src="statics/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="statics/js/highcharts.js"></script>
		<script type="text/javascript" src="statics/js/highcharts-more.js"></script>
		<script type="text/javascript"> 
		var returnedPqosData = null;
		var flag=false;//flag为true,开启曲线绘制
		
	  	//定时函数,每隔1min执行一次,向后端请求新的数据
		setInterval(function() {
			if(flag==true){
				$.ajax({
					async:true,
					type:"get",
					url:"monitor_3.do",//发送的get请求
					data:{},
					dataType:"json",
					success:function(returned) {  
						if(returned!=null&&returned!=""&&returned!="null"){
							returnedPqosData = returned; //用当前页面的临时变量接受该返回值
						}
					}	
				}); 
		   }
	    },1000*60);
	 
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
                        // set up the updating of the chart each min
                        var series0 = this.series[0];
                        var series1 = this.series[1];
                        
                        var x1,x2,y;
                        setInterval(function (){
                     	   if(flag==true){
      			              if(returnedPqosData!=null&&returnedPqosData[0].time!=0){
      			            	    x1 = returnedPqosData[0].high;
      			            	    x2 = returnedPqosData[0].high_x86;
      			            	    y = returnedPqosData[0].cdf;
      			          
      			            	    series0.addPoint([x1,y], true, true); 
      			            	    series1.addPoint([x2,z], true, true);       			            	    
      				               
      			               }    
                          	}
                        }, 200);
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
				}
			},
			legend: {
				layout: 'horizontal',
				align: 'center',
				verticalAlign: 'bottom'
			},
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                        Highcharts.numberFormat(this.y, 2)+'MB/s';
                }
            },            
            exporting: {
                enabled: false
            },
            series:[${cdf_high}] //接受前端页面传递过来的曲线字符串
        });
       
        /**
         * 绘制高优先级cdf的曲线
         * 在container1的div里绘制该图
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
                          // set up the updating of the chart each min
                          var series0 = this.series[0];
                          var series1 = this.series[1];
                          
                          var x1,x2,y;
                          setInterval(function (){
                       	   if(flag==true){
        			              if(returnedPqosData!=null&&returnedPqosData[0].time!=0){
        			            	    x1 = returnedPqosData[0].high;
        			            	    x2 = returnedPqosData[0].high_x86;
        			            	    y = returnedPqosData[0].cdf;
        			          
        			            	    series0.addPoint([x1,y], true, true); 
        			            	    series1.addPoint([x2,z], true, true);       			            	    
        				               
        			               }    
                            	}
                          }, 200);
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
  				}
  			},
  			legend: {
  				layout: 'horizontal',
  				align: 'center',
  				verticalAlign: 'bottom'
  			},
              tooltip: {
                  formatter: function () {
                      return '<b>' + this.series.name + '</b><br/>' +
                          Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                          Highcharts.numberFormat(this.y, 2)+'MB/s';
                  }
              },            
              exporting: {
                  enabled: false
              },
              series:[${cdf_low}]  
          });
 });
</script>
</div>
</body>
</html>