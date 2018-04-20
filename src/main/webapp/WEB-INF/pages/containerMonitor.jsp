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

<script type="text/javascript" src="statics/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="statics/js/highcharts.js"></script>
<script type="text/javascript" src="statics/js/highcharts-more.js"></script>
<script type="text/javascript">
	//实时数据表格
	var returnedData = null;
	var lastreturnedData = null;
	var lastcollectTime = "";
	
	setInterval(function() {
		$.ajax({
			async : true,
			type : "post",
			url : "getContainerResourceUse.do",
			data : {},
			dataType : "json",
			success : function(returned) {
				var lastcollectTime = null;
				if (returned != null && returned != "" && returned != "null") { 
					returnedData = returned; 
				}
			}
		});
	}, 3000);

	//实时数据表格
	function ajaxTable(page) {

		if (page < 1) {
			page = 1;
			return 0;
		} else if (page > 38) {
			page = 1;
			return 0;
		}
		var tableTitle = "<table  class="table table-hover text-center" >"
				+ "<tr><th width= "200px">容器名称</th><th width = "340px">CPU %</th><th  width = "340px">mem usage</th><th  width = "340px">mem %</th>"
				+ "<th  width = "340px">net I/O</th><th  width = "340px">disk I/O</th>"
				+ "</tr>";
		var tableContent = "";
		var tableClass = "";

		document.getElementById('tableDiv').innerHTML = "";
		if (returnedData != "") {
			for (var i = 0; i < 38; i++) {
				if (returnedData[i] != null && returnedData[i] != "") {

					var nodeName = returnedData[i].containername;
					var cpu = returnedData[i].cpuusagerate * 100;
					var mem = returnedData[i].memusagerate * 100;
					var memamount = returnedData[i].memusagerate;
					if (lastreturnedData == null) {//第一次添加
						tableContent += "<tr align='center'  >" + "<td>"
								+ nodeName + "</td>" + "<td>" + cpu + "</td>"
								+ "<td>" + memamount + "</td>" + "<td>" + mem
								+ "</td>" + "<td>" + net + "</td>" + "<td>"
								+ io + "</td>" + "</tr>";
						lastreturnedData = returnedData;
					} else {
						if (lastreturnedData[i] != returnedData[i]) {
							tableContent += "<tr align='center'  >" + "<td>"
									+ nodeName + "</td>" + "<td>" + cpu
									+ "</td>" + "<td>" + memamount + "</td>"
									+ "<td>" + mem + "</td>" + "<td>" + net
									+ "</td>" + "<td>" + io + "</td>" + "</tr>"
						}
					}

				}
			}
			
		}

		var realTable = tableTitle + tableContent + "</table>";

		document.getElementById("tableDiv").innerHTML = realTable;
	}

	//定时刷新
	setInterval("ajaxTable(1);", 5000);
</script>

</head>

<body>

	<div  id ="tableDiv"  class="panel admin-panel"  style="width: 1300px;height:1450px; margin: 0 auto; background-color: #FFF;"></div>
	
		
</body>

</html>
