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
<title>容器资源使用表</title>
<link rel="stylesheet" href="statics/css/pintuer.css">
<link rel="stylesheet" href="statics/css/admin.css">
<link rel="stylesheet" href="statics/css/physical.css">

<script type="text/javascript" src="statics/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="statics/js/highcharts.js"></script>
<script type="text/javascript" src="statics/js/highcharts-more.js"></script>
<script type="text/javascript">
	//实时数据表格
	var returnedData = null;
	var lastTime = null;
	setInterval(function() {
		$.ajax({
			async : true,
			type : "post",
			url : "getCacheUse.do",
			data : {},
			dataType : "json",
			success : function(returned) {
				if (returned != null && returned != "") {
					returnedData = returned;
					ajaxTable();
				}
			}
		});
	}, 3000);

	//实时数据表格
	var tableTitle = "<table  class=\"table table-hover text-center\" >"
			+ "<tr><th width= \"200px\">缓存miss次数</th><th width = \"340px\">一级数据缓存读取次数</th><th  width = \"340px\">一级数据缓存miss次数</th><th  width = \"340px\">末级缓存读取次数</th>"
			+ "<th  width = \"340px\">末级缓存miss次数</th>" + "</tr>";
	var tableContent = "";
	var tableClass = "";
	
	var misses = null;
	var l1d_load = null;
	var l1d_misses = null;
	var l3_load = null;
	var l3_misses = null;
	function ajaxTable() {
		if (returnedData.length > 0) {
			if (returnedData != null) {
				misses = returnedData[0][1];
				l1d_load = returnedData[1][1];
				l1d_misses = returnedData[2][1];
				l3_load = returnedData[3][1];
				l3_misses = returnedData[4][1];
				//第一次添加
				tableContent += "<tr align='center'  >" + "<td>" + misses
						+ "</td>" + "<td>" + l1d_load + "</td>" + "<td>"
						+ l1d_misses + "</td>" + "<td>" + l3_load + "</td>"
						+ "<td>" + l3_misses + "</td>" + "</tr>";
			}
			realTable = tableTitle + tableContent + "</table>";
			document.getElementById('tableDiv').innerHTML = "";
			document.getElementById("tableDiv").innerHTML = realTable;
			tableContent = "";
		}
	}
</script>
</head>
<body>
	<div id="tableDiv" class="panel admin-panel"
		style="width: 1300px; height: 1450px; margin: 0 auto; background-color: #FFF;"></div>

</body>

</html>
