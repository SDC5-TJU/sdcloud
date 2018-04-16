<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<!--测评结果汇总-->
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
<div >
<ul class="nav nav-tabs" >
       <c:if test="${webSearch.enable==1}">
        <li class="" style="float: left;">
            <a  href="getWebSearchResult.do?testRecordId=${testRecordId}" target="_blank" id="websearch">web搜索</a>
        </li>
       </c:if>
       <c:if test="${webServer.enable==1}">
        <li class="" style="float: left;">
            <a  href="getWebServerResult.do?testRecordId=${testRecordId}" target="_blank" id="webserver">电商服务</a>
        </li>
       </c:if>
       <c:if test="${silo.enable==1}">        
        <li class="" style="float: left;">
            <a  href="getSiloResult.do?testRecordId=${testRecordId}" target="_blank" id="silo">silo</a>
        </li>
       </c:if>
       <c:if test="${memcached.enable==1}">  
        <li class="" style="float: left;">
            <a  href="getMemcachedResult.do?testRecordId=${testRecordId}" target="_blank" id="memcached">memcached</a>
        </li>
       </c:if> 
    </ul>

</div>
<script type="text/javascript" src="statics/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="statics/js/highcharts.js"></script>
<script type="text/javascript" src="statics/js/highcharts-more.js"></script>

</body>
</html>