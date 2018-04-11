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

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<style>
#mainDiv {
	/*position:absolute;
	left:0px;
	top:0px;*/
	position: relative;
	margin: 0 auto;
	margin-left: 50% auto width:1100px;
	height: 600px;
	z-index: 6;
	top: 0px;
	width: 1100px;
}

#container1 {
	position: absolute;
	width: 500px;
	height: 200px;
	left: -50px;
	top: 0px;
}

#container2 {
	position: absolute;
	width: 500px;
	height: 200px;
	left: 470px;
	top: 0px;
}

#container3 {
	position: absolute;
	width: 500px;
	height: 200px;
	left: -50px;
	top: 200px;
}

#container4 {
	position: absolute;
	width: 500px;
	height: 200px;
	left: 470px;
	top: 200px;
}

#container5 {
	position: absolute;
	width: 500px;
	height: 200px;
	left: -50px;
	top: 400px;
}

#container6 {
	position: absolute;
	width: 500px;
	height: 200px;
	left: 470px;
	top: 400px;
}

#container7 {
	position: absolute;
	width: 1100px;
	height: 400px;
	left: -50px;
	top: 600px;
}

#containerControl {
	position: absolute;
	width: 100px;
	left: 980px;
	top: 80px;
	height: 20px;
	z-index: 55;
}

#hadoopControl {
	position: absolute;
	width: 200px;
	left: 980px;
	top: 300px;
	height: 20px;
	z-index: 55;
}

#startButton {
	color: rgb(255, 255, 255);
	font-size: 12px;
	padding-top: 2px;
	padding-bottom: 2px;
	padding-left: 6px;
	padding-right: 6px;
	border-width: 0px;
	border-color: rgb(197, 229, 145);
	border-style: solid;
	border-radius: 3px;
	background-color: rgb(120, 195, 0);
}

#startButton:hover {
	color: #ffffff;
	background-color: #84cf0b;
	border-color: #c5e591;
}

#startHadoopButton1 {
	color: rgb(255, 255, 255);
	font-size: 12px;
	padding-top: 2px;
	padding-bottom: 2px;
	padding-left: 6px;
	padding-right: 6px;
	border-width: 0px;
	border-color: rgb(197, 229, 145);
	border-style: solid;
	border-radius: 3px;
	background-color: rgb(120, 195, 0);
}

#startHadoopButton1:hover {
	color: #ffffff;
	background-color: #84cf0b;
	border-color: #c5e591;
}

#deleteButton {
	color: rgb(255, 255, 255);
	font-size: 12px;
	padding-top: 2px;
	padding-bottom: 2px;
	padding-left: 6px;
	padding-right: 6px;
	border-width: 0px;
	border-color: rgb(197, 229, 145);
	border-style: solid;
	border-radius: 3px;
	background-color: rgb(255, 81, 0);
}

#deleteButton:hover {
	color: #ffffff;
	background-color: #ff6f00;
	border-color: #84cf0b;
}

#viewButton {
	color: rgb(255, 255, 255);
	font-size: 12px;
	padding-top: 2px;
	padding-bottom: 2px;
	padding-left: 6px;
	padding-right: 6px;
	border-width: 0px;
	border-color: rgb(197, 229, 145);
	border-style: solid;
	border-radius: 3px;
	background-color: rgb(11, 82, 224);
}

#viewButton:hover {
	color: #ffffff;
	background-color: #2256f2;
	border-color: #c5e591;
}
</style>
</head>

<body>
	<div id="mainDiv">
		<div id="container1"></div>
		<div id="container2"></div>
		<div id="containerControl">
			<span style="font-family: 微软雅黑; font-size: 14px;">线程控制:</span><input
				type="button" id="startButton" value="开始" onclick="start();"
				style="cursor: pointer">
		</div>
		<div id="hadoopControl">
			<div>
				<span style="font-family: 微软雅黑; font-size: 14px;">启动hadoop任务</span>
			</div>
			<div id="hadoopControl_title">
				<input type="button" id="viewButton" value="运行"
					onclick="startHadoop();" style="cursor: pointer"><input
					type="text" id="maps" style="width: 56px; height: 20px;"
					placeholder="map数量"><input type="text" id="reduces"
					style="width: 66px; height: 20px;" placeholder="reduce数量">
			</div>
			<div>
				<span style="font-family: 微软雅黑; font-size: 14px;" id="res"></span>
			</div>
		</div>
		<div id="container3"></div>
		<div id="container4"></div>
		<div id="container5"></div>
		<div id="container6"></div>
		<div id="container7"></div>
		<script type="text/javascript" src="statics/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="statics/js/highcharts.js"></script>
		<script type="text/javascript" src="statics/js/highcharts-more.js"></script>
		<script type="text/javascript">
		//---实时曲线绘制---//
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
			}
		}	
	});
},5000);
  
	var flag=false;
 
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
            type: 'scatter',
            animation: Highcharts.svg, // don't animate in old IE
            marginRight: 10,
            events: {
                load: function () {
                    // set up the updating of the chart each second
                    var series = this.series[0];
                    var a=null;
                    setInterval(function (){
                    	if(flag==true){  
			                $.ajax({
								async:true,
								type:"post",
								url:"getWebSearchQueryTime.do",
								data:{},
								dataType:"text",
								success:function(returned){
									 a=returned;
								}	
							});
			               if(a!=null){
			            		x=(new Date()).getTime(); // current time
	  							y=parseFloat(a);           
				                series.addPoint([x,y], true, true); 
			               }
                    	}
                    },1000);
                    }
                }
        },
        title: {
            text: 'solrCloud query benchmark'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: 'queryTime'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
           
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
        exporting: {
            enabled: false
        },
        series:[${solrCloud}]
    });
    /**
     * webServer
     */
    Highcharts.chart('container2', {
        chart: {
            type: 'scatter',
            animation: Highcharts.svg, // don't animate in old IE
            marginRight: 10,
            events: {
                load: function () {
                    // set up the updating of the chart each second
                    var series = this.series[0];
                    var a=null;
                    setInterval(function (){
                    	if(flag==true){  
			                $.ajax({
								async:true,
								type:"post",
								url:"getWebServerQueryTime.do",
								data:{},
								dataType:"text",
								success:function(returned){
									 a=returned;
								}	
							});
			                if(a!=null){
			            		x=(new Date()).getTime(); // current time
	  							y=parseFloat(a);    
			            		if(y>0){
			            			 series.addPoint([x,y], true, true); 
			            		}
				              
			               }
                    	}
                    },1000);
                    }
                }
        },
        title: {
            text: 'webServer query benchmark'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150,
        },
        yAxis: {
            title: {
                text: 'queryTime'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
           
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
        exporting: {
            enabled: false
        },
        series:[${webServer}]
    });
    /**
     * cpu
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
								type:"post",
								url:"getCpuUseRate.do",
								data:{},
								dataType:"text",
								success:function(returned){
									a=returned;
								}	
							});
			              if(a!=null){
			            		x=(new Date()).getTime(); //current time
			            		console.log(x);
	  							y=parseFloat(a);           
				                series.addPoint([x,y], true, true); 
			               }     
                    	}
                    }, 1000);
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
      Highcharts.chart('container4', {
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
  			                $.ajax({
  								async:true,
  								type:"post",
  								url:"getMemUseRate.do",
  								data:{},
  								dataType:"text",
  								success:function(returned){
  									a=returned;
  								}	
  							});
  			              
  			              if(a!=null){
			            		x=(new Date()).getTime(); // current time
	  							y=parseFloat(a);           
				                series.addPoint([x,y], true, true); 
			               } 
                      	}
                      },1000);
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
       Highcharts.chart('container5', {
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
  								type:"post",
  								url:"getIoUseRate.do",
  								data:{},
  								dataType:"text",
  								success:function(returned){
  									a=returned;
  								}	
  							});
  			              if(a!=null){
  			            		x=(new Date()).getTime(); //current time
  	  							y=parseFloat(a);           
  				                series.addPoint([x,y], true, true); 
  			               }     
                      	}
                      }, 1000);
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
        Highcharts.chart('container6', {
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
   								type:"post",
   								url:"getNetUseRate.do",
   								data:{},
   								dataType:"text",
   								success:function(returned){
   									a=returned;
   								}	
   							});
   			              if(a!=null){
   			            		x=(new Date()).getTime(); //current time
   	  							y=parseFloat(a);           
   				                series.addPoint([x,y], true, true); 
   			               }     
                       	}
                       }, 1000);
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
      Highcharts.chart('container7', {
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
                      }, 1000);
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
<script type="text/javascript">
//实时数据表格
function ajaxTable(page){
	
	if(page < 1){
		page = 1;
		return 0;
	}else if(page > ${totalpage}){
		page = totalpage;
		return 0;
	}
			   var tableTitle = "<table border='0' align='center' cellpadding='0' " +
			                    "cellspacing='0' class='tableContent' >" +
			                    "<tr><th>监控节点</th><th>节点类型</th><th>时间</th>" +
			                    "<th>温度(℃)</th><th>湿度(%RH)</th></tr>";
			   var tableContent = "";
			   var tableClass = "";
			   var temColor = "green";
			   var humColor = "green";
			   
			   document.getElementById('tableDiv').innerHTML = "";
			   if(returnedData!=""){
				   for(var i = 0; i < nodeNum ; i++){
					   if(returnedData[i]!=null&&returnedData[i]!=""){
					   var nodeID = returnedData[i].nodeID&0xff;//注意&左右不能有空格
					   var nodeName = nodeID + "号节点";
					   var collectTime = returnedData[i].collectTime;
				       var tem = returnedData[i].tem;
				       var hum = returnedData[i].hum;
				       var nodeStyle = "环境节点";
				       if(nodeID > 50){
				    	   nodeStyle = "展柜节点";
				       }
				       
				       tableClass = (i % 2 == 0)?"":"tableBg";
				       temColor = (tem >= TmaxValue)?"red":(tem <= TminValue)?"blue":"green";
				       humColor = (hum >= HmaxValue)?"red":(hum <= HminValue)?"blue":"green";
				       
				       tableContent += "<tr align='center' class='"+tableClass+"' >" 
				                       + "<td>"+nodeName+"</td>"
				                       + "<td>"+nodeStyle+"</td>" 
				                       + "<td>"+collectTime+"</td>"
				                       + "<td><font style='color:"+temColor+"'>"+tem+"</font></td>" 
				                       + "<td><font style='color:"+humColor+"'>"+hum+"</font></td></tr>";
					   }
				   }
			   }
			   
			   var realTable = tableTitle + tableContent +"</table>";
			   var realPage = "";
			   
			   if(${totalpage} != 1){
				   var realPage ="<div id='tablePagePre' onclick='ajaxTable("+(page-1)+")' ><img src='images/btnPre2.gif' /></div> " 
			                   + "<div id='tablePageNum' style='cursor: pointer;margin-left: 245px;'>"+page+"/${totalpage}</div>"
			                   + "<div id='tablePageNext' onclick='ajaxTable("+(page+1)+")' ><img src='images/btnNext.gif'  /></div>";
			   }
			                
			   document.getElementById("tableDiv").innerHTML = realTable + realPage;
}

//定时刷新
setInterval("ajaxTable(1);",5000);
</script>
	</div>
</body>
</html>
