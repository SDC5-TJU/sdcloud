<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
<title>物理机监控</title>
   <link rel="stylesheet" href="statics/css/pintuer.css">
    <link rel="stylesheet" href="statics/css/admin.css">
    <link rel="stylesheet" href="statics/css/physical.css">
    
</head>

<body>

	<div id="mainDiv">
		<div id="container1"></div>
		<div id="container2"></div>
		<div id="containerControl"> 
			<span style="font-family: 微软雅黑;font-size: 14px;">线程控制:</span><input type="button" id="startButton" value="开始" onclick="start();" style="cursor:pointer">
		</div>
		<div id="hadoopControl">
			<div>
			<span style="font-family: 微软雅黑;font-size: 14px;">启动hadoop任务</span>
			</div>
			<div id="hadoopControl_title">
				<input type="button" id="viewButton" value="运行" onclick="startHadoop();" style="cursor:pointer"><input type="text" id="maps" style="width:56px; height:20px;" placeholder="map数量"><input type="text" id="reduces" style="width:66px; height:20px;" placeholder="reduce数量">
			</div>
			<div>
			<span style="font-family: 微软雅黑;font-size: 14px;" id="res"></span>
			</div>
		</div>
		<div id="container3"></div>
		<div id="container4"></div>
		<div id="container5"></div>

		<script type="text/javascript" src="statics/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="statics/js/highcharts.js"></script>
		<script type="text/javascript" src="statics/js/highcharts-more.js"></script>
		<script> var returnedData = null;var lastcollecttime = null; </script>
		
		<script type="text/javascript">

		 setInterval(function() {
				$.ajax({
					async:true,
					type:"get",
					url:"getPhyResourceUse.do?no=2",
					data:{},
					dataType:"json",
					success:function(returned) {
						var lastcollecttime = null;
						if(returned!=null&&returned!=""&&returned!="null"){
							//lastcollecttime = returnedData[0].collecttime;
							returnedData = returned;
							//console.log(lastcollecttime);
							console.log(returned);
							//console.log(returnedData[0].collecttime);
						}
						if(lastcollecttime == returnedData[0].collecttime.time && returnedData.collecttime.time == null)
							{
								//因为数据问题出现异常,时间为空或者时间与前一刻相同
								alert("异常");
							}
					}	
				});
			},10000);

	var flag=true;
 
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
    * solrCloud
    */
    
   
     Highcharts.chart('container1', {
        chart: {
            type: 'line',
            animation: Highcharts.svg, // don't animate in old IE
            marginRight: 10,
            events: {
                load: function () {
                    // set up the updating of the chart each second
                    var series = this.series[0];
                    var x,y,a;
                   
                    setInterval(function (){
                    	if(flag==true){
                    	
			              if(returnedData!=null){
			            	    x = returnedData[0].collecttime.time;
			            	    lastcollecttime = returnedData[0].collecttime.time;
								y = returnedData[0].cpuusagerate;
								y = parseFloat(y);
								
				                series.addPoint([x,y], true, true); 
			               }    
                    	}
                    }, 10000);
                }
            }
        },
        title: {
            text: 'CPU used(%)'
        },
        xAxis: {
        	type: 'datetime',
            tickPixelInterval: 150,
            gridLineWidth: 1
        },
        yAxis: {
            title: {
                text: 'used rate'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            max:100,
			min:0,
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                    Highcharts.numberFormat(this.y, 2)+'%';
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
        series:[${cpu}]
    });
     /**
      *memory
      */
      Highcharts.chart('container2', {
         chart: {
             type: 'line',
             animation: Highcharts.svg, // don't animate in old IE
             marginRight: 10,
             events: {
            	  load: function () {
                      // set up the updating of the chart each second
                      var series = this.series[0];
                      var a=null;
                      
                      setInterval(function (){
                      	if(flag==true){  
  			              if(returnedData!=null){
  			            		x = returnedData[0].collecttime.time;
		            	    	lastcollecttime = returnedData[0].collecttime.time;
								y = returnedData[0].memusagerate;  
								console.log(x);
								console.log(y);
				                series.addPoint([x,y], true, true); 
			               } 
                      	}
                      },10000);
                   }
             }
         },
         title: {
             text: 'memory used(%)'
         },
         xAxis: {
        	 type: 'datetime',
             tickPixelInterval: 150,
         },
         yAxis: {
             title: {
                 text: 'used rate'
             },
             plotLines: [{
                 value: 0,
                 width: 1,
                 color: '#808080'
             }],
             max:100,
 			 min:0,
         },
         tooltip: {
             formatter: function () {
                 return '<b>' + this.series.name + '</b><br/>' +
                     Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                     Highcharts.numberFormat(this.y, 2)+'%';
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
         series:[${memory}]
     });
      /**
       * IO
       */
       Highcharts.chart('container3', {
          chart: {
              type: 'line',
              animation: Highcharts.svg, // don't animate in old IE
              marginRight: 10,
              events: {
                  load: function () {
                      // set up the updating of the chart each second
                      var series = this.series[0];
                      var x,y,a;
                     
                      setInterval(function (){
                      	if(flag==true){
                      	  $.ajax({
  								async:true,
  								//type:"post",
  								//url:"getIoUseRate.do",
  								data:{},
  								dataType:"text",
  								/*success:function(returned){
  									a=returned;
  								}	*/
  							});
  			              if(returnedData!=null){
  			            	    x = returnedData[0].collecttime.time;
		            	    	lastcollecttime = returnedData[0].collecttime.time;
								y = returnedData[0].iousagerate;                
  				                series.addPoint([x,y], true, true); 
  			               }     
                      	}
                      }, 10000);
                  }
              }
          },
          title: {
              text: 'IO used(%)'
          },
          xAxis: {
          	type: 'datetime',
              tickPixelInterval: 150,
              gridLineWidth: 1
          },
          yAxis: {
              title: {
                  text: 'used rate'
              },
              plotLines: [{
                  value: 0,
                  width: 1,
                  color: '#808080'
              }],
              max:100,
  			min:0,
          },
          tooltip: {
              formatter: function () {
                  return '<b>' + this.series.name + '</b><br/>' +
                      Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                      Highcharts.numberFormat(this.y, 2)+'%';
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
          series:[${io}]
      });
       /**
        * net
        */
        Highcharts.chart('container4', {
           chart: {
               type: 'line',
               animation: Highcharts.svg, // don't animate in old IE
               marginRight: 10,
               events: {
                   load: function () {
                       // set up the updating of the chart each second
                       var series = this.series[0];
                       var x,y,a;
                      
                       setInterval(function (){
                       	if(flag==true){
                       	  $.ajax({
   								async:true,
   								//type:"post",
   								//url:"getNetUseRate.do",
   								data:{},
   								dataType:"text",
   								/*success:function(returned){
   									a=returned;
   								}	*/
   							});
   			              if(returnedData!=null){
   			            		x = returnedData[0].collecttime.time;
		            	    	lastcollecttime = returnedData[0].collecttime.time;
								y = returnedData[0].netusagerate;                   
   				                series.addPoint([x,y], true, true); 
   			               }     
                       	}
                       }, 10000);
                   }
               }
           },
           title: {
               text: 'net used(%)'
           },
           xAxis: {
           	type: 'datetime',
               tickPixelInterval: 150,
               gridLineWidth: 1
           },
           yAxis: {
               title: {
                   text: 'used rate'
               },
               plotLines: [{
                   value: 0,
                   width: 1,
                   color: '#808080'
               }],
               max:100,
   			min:0,
           },
           tooltip: {
               formatter: function () {
                   return '<b>' + this.series.name + '</b><br/>' +
                       Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                       Highcharts.numberFormat(this.y, 2)+'%';
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
           series:[${net}]
       });
     /**
     * cpu柱状图
     */
      Highcharts.chart('container5', {
          chart: {
              type: 'column',
              events: {
                  load: function () {
                      // set up the updating of the chart each second
                      var series0 = this.series[0];
                      var series1 = this.series[1];
                      var series2 = this.series[2];
                      setInterval(function () {
                    	  if(false){
	                    	  $.ajax({
	  							async:true,
	  							type:"post",
	  							url:"cpuBaseInfoMethod.do",
	  							data:{},
	  							dataType:"json",
	  							success:function(returned){
	  								a=returned;
	  							}	
	  						});
	                    	  if(a!=null){
	                    		  series0.setData(a[0].idle);//未使用
	                              series1.setData(a[0].user);
	                              series2.setData(a[0].sys);
	                    	  }
                    	  }
                      }, 10000);
                  }
              }
          },
          title: {
              text: 'cpus'
          },
          xAxis: {
              categories:[${cpuAxis}]
          },
          yAxis: {
              min: 0,
              title: {
                  text: 'CPU useInfo(%)'
              }
          },
          tooltip: {
              pointFormat: '<span style="color:{series.color}">{series.name}</span>:({point.percentage:.0f}%)<br/>',
              shared: true
          },
          plotOptions: {
              column: {
                  stacking: 'percent'
              }
          },
          series: [${cpuColumn}]
      });
});

</script>
	</div>
</body>
</html>
