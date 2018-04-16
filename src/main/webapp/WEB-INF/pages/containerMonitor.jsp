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
<title>物理机监控表</title>
<link rel="stylesheet" href="statics/css/pintuer.css">
<link rel="stylesheet" href="statics/css/admin.css">
<link rel="stylesheet" href="statics/css/physical.css">
<style>
.display {
	width: 150px;
	height: 50px;
	background: #86D8E6;
	border: 1px #86D8E6 solid;
	z-index: 10;
}

.node {
	width: 12px;
	height: 12px;
	z-index: 100;
}

.axis path, .axis line {
	fill: none;
	stroke: black;
	shape-rendering: crispEdges;
}

.axis text {
	font-family: sans-serif;
	font-size: 11px;
}

.MyRect {
	fill: steelblue;
}

.MyText {
	fill: white;
	text-anchor: middle;
}

.thead, tr {
	border-top-width: 1px;
	border-top-style: solid;
	border-top-color: rgb(230, 189, 189);
}

#table-1 {
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: rgb(230, 189, 189);
}

/* Padding and font style */
.td, th {
	padding: 5px 10px;
	font-size: 12px;
	font-family: Verdana;
	color: rgb(177, 106, 104);
}

/* Alternating background colors */
.tr:nth-child(even) {
	background: rgb(238, 211, 210)
}

.tr:nth-child(odd) {
	background: #FFF
}
</style>
<script type="text/javascript" src="statics/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="statics/js/highcharts.js"></script>
<script type="text/javascript" src="statics/js/highcharts-more.js"></script>
<script type="text/javascript">
	//实时数据表格
	var returnedData = null;
	var lastcollectTime = "";
	setInterval(
			function() {
				$.ajax({
							async : true,
							type : "post",
							url : "getContainerResourceUse.do",
							data : {},
							dataType : "json",
							success : function(returned) {
								var lastcollectTime = null;
								if (returned != null && returned != ""
										&& returned != "null") {
									//lastcollectTime = returnedData[0].collectTime;
									returnedData = returned;
									//console.log(returnedData);

									//console.log(returnedData[0].collectTime);
								}
								/* if (returnedData.collecttime.time == null
										&& lastcollectTime == returnedData[0].collecttime.time) {
									//因为数据问题出现异常,时间为空或者时间与前一刻相同
									alert("异常");

								} */
							}
						});
			}, 1000);

	//实时数据表格
	function ajaxTable(page) {

		if (page < 1) {
			page = 1;
			return 0;
		} else if (page > 38) {
			page = 1;
			return 0;
		}
		var tableTitle = "<table border='0' align='center' cellpadding='0' " +
			                    "cellspacing='0' class='tableContent' >" +
				"<tr><th>容器名称</th><th>CPU %</th><th>mem usage</th><th>mem %</th>" +
				/* "<th>net I/O</th><th>disk I/O</th>" +  */
				"</tr>";
		var tableContent = "";
		var tableClass = "";
		var temColor = "green";
		var humColor = "green";

		document.getElementById('tableDiv').innerHTML = "";
		if (returnedData != "") {
			console.log(returnedData);
			for (var i = 0; i < 38; i++) {
				if (returnedData[i] != null && returnedData[i] != "") {
					/* var nodeID = returnedData[i].nodeID&0xff;//注意&左右不能有空格
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
					                 + "<td><font style='color:"+humColor+"'>"+hum+"</font></td></tr>";*/
					var nodeName = returnedData[i].containername;
					var cpu = returnedData[i].cpuusagerate * 100;
					var mem = returnedData[i].memusagerate * 100;
					var memamount = returnedData[i].memusagerate;
					/* var net = returnedData[i].netusagerate * 100;
					var io = returnedData[i].iousagerate * 100; */
					tableContent += "<tr align='center'  >" + 
							"<td>" + nodeName + "</td>" +
							"<td>" + cpu + "</td>" +
							"<td>" + memamount + "</td>" +
							"<td>" + mem + "</td>" + 
							/* "<td>" + net + "</td>" +
							"<td>" + io + "</td>" + */
							"</tr>"

				}
			}
		}

		var realTable = tableTitle + tableContent + "</table>";
		var realPage = "";

		/* if(${totalpage} != 1){*/
		/*var realPage ="<div id='tablePagePre' onclick='ajaxTable("+(page-1)+")' ><img src='images/btnPre2.gif' /></div> " 
		            + "<div id='tablePageNum' style='cursor: pointer;margin-left: 245px;'>"+page+"/${totalpage}</div>"
		            + "<div id='tablePageNext' onclick='ajaxTable("+(page+1)+")' ><img src='images/btnNext.gif'  /></div>";
		}*/

		document.getElementById("tableDiv").innerHTML = realTable + realPage;
	}

	//定时刷新
	setInterval("ajaxTable(1);", 5000);
</script>

</head>

<body>




	<div id="mainPart">
		<div id="mainPartLeft" style="width: 438px;"></div>
		<div id="mainPartRight" style="width: 763px;">
			<h2>实时数据表格</h2>


			<div id="tabTable">

				<div id="tableDiv"
					style="width: 595px; height: 510px; margin: 0 auto; background-color: #FFF;"></div>



			</div>
		</div>
		<script>
			
		</script>
</body>

</html>
