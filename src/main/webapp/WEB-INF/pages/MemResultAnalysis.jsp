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
        <li class="" style="float: left;">
            <a  href="#" id="websearch">web搜索</a>
        </li>
        <li class="" style="float: left;">
            <a  href="#" id="webserver">电商服务</a>
        </li>
        <li class="" style="float: left;">
            <a  href="#" id="silo">silo</a>
        </li>
        <li class="" style="float: left;">
            <a  href="#" id="memcached">memcached</a>
        </li>
    </ul>

</div>
<div>
    <!--表-->
    <div>
        <div style=" width: 30%; position: absolute; top: 100px;left: 150px;" id="table">
            <form method="post" action="" id="">
                <div class="panel admin-panel">
                    <!--<div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:web搜索</strong> <a href="" style="float:right; display:none;">添加字段</a></div>-->

                    <table class="table table-hover text-center">
                        <tr>
                            <th width="">无干扰 响应时间90分位数</th>
                            <th width="">干扰下 响应时间90分位数</th>
                            <th width="">变化率</th>
                        </tr>
                        <tr>
                            <td >${MemcachedBaseResult.nintyTh}</td>
                            <td >${MemcachedResult.nintyTh}</td>
                            <c:choose>
                                <c:when test="${diffBean.nintyThDiff>0}">
                                    <td ><img src="statics/images/up.png"/>${diffBean.nintyThDiff}%</td>
                                </c:when>
                                <c:otherwise>
                                    <td ><img src="statics/images/down.png"/>${diffBean.nintyThDiff}%</td>

                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
        <div style=" width: 30%; position: absolute; top: 100px;margin-left: 650px;" id="table2">
            <form method="post" action="" id="">
                <div class="panel admin-panel">
                    <!--<div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:web搜索</strong> <a href="" style="float:right; display:none;">添加字段</a></div>-->

                    <table class="table table-hover text-center">
                        <tr>
                            <th width="">无干扰 响应时间95分位数</th>
                            <th width="">干扰下 响应时间95分位数</th>
                            <th width="">变化率</th>
                        </tr>
                        <tr>
                            <td >${MemcachedBaseResult.nintyFiveTh}</td>
                            <td >${MemcachedResult.nintyFiveTh}</td>
                            <c:choose>
                                <c:when test="${diffBean.nintyFiveThDiff>0}">
                                    <td ><img src="statics/images/up.png"/>${diffBean.nintyFiveThDiff}%</td>
                                </c:when>
                                <c:otherwise>
                                    <td ><img src="statics/images/down.png"/>${diffBean.nintyFiveThDiff}%</td>

                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </div>

    <div style=" width: 30%; position: absolute; top: 230px;left: 150px;" id="table3">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <!--<div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:电商服务</strong> <a href="" style="float:right; display:none;">添加字段</a></div>-->

                <table class="table table-hover text-center">
                    <tr>
                        <th width="">无干扰 响应时间99分位数</th>
                        <th width="">干扰下 响应时间99分位数</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${MemcachedBaseResult.nintyNineTh}</td>
                        <td >${MemcachedResult.nintyNineTh}</td>
                        <c:choose>
                            <c:when test="${diffBean.nintyNineThDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.nintyNineThDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.nintyNineThDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div style=" width: 30%; position: absolute; top: 230px;left: 650px;" id="table4">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <!--<div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:web搜索</strong> <a href="" style="float:right; display:none;">添加字段</a></div>-->

                <table class="table table-hover text-center">
                    <tr>
                        <th width="">无干扰 响应时间标准差</th>
                        <th width="">干扰下 响应时间标准差</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${MemcachedBaseResult.std}</td>
                        <td >${MemcachedResult.std}</td>
                        <c:choose>
                            <c:when test="${diffBean.stdDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.stdDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.stdDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div style=" width: 30%; position: absolute; top: 360px;left: 150px;" id="table5">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <!--<div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:web搜索</strong> <a href="" style="float:right; display:none;">添加字段</a></div>-->

                <table class="table table-hover text-center">
                    <tr>
                        <th width="">无干扰 平均延迟</th>
                        <th width="">干扰下 平均延迟</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${MemcachedBaseResult.avg_lat}</td>
                        <td >${MemcachedResult.avg_lat}</td>
                        <c:choose>
                            <c:when test="${diffBean.avg_latDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.avg_latDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.avg_latDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div style=" width: 30%; position: absolute; top: 360px;left: 650px;" id="table6">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <!--<div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:web搜索</strong> <a href="" style="float:right; display:none;">添加字段</a></div>-->

                <table class="table table-hover text-center">
                    <tr>
                        <th width="">无干扰 响应时间最小值</th>
                        <th width="">干扰下 响应时间最小值</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${MemcachedBaseResult.min}</td>
                        <td >${MemcachedResult.min}</td>
                        <c:choose>
                            <c:when test="${diffBean.minDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.minDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.minDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div style=" width: 30%; position: absolute; top: 490px;left: 150px;" id="table7">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <!--<div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:web搜索</strong> <a href="" style="float:right; display:none;">添加字段</a></div>-->

                <table class="table table-hover text-center">
                    <tr>
                        <th width="">无干扰 响应时间最大值</th>
                        <th width="">干扰下 响应时间最大值</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${MemcachedBaseResult.max}</td>
                        <td >${MemcachedResult.max}</td>
                        <c:choose>
                            <c:when test="${diffBean.maxDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.maxDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.maxDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div style=" width: 30%; position: absolute; top: 490px;left: 650px;" id="table8">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <!--<div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:web搜索</strong> <a href="" style="float:right; display:none;">添加字段</a></div>-->

                <table class="table table-hover text-center">
                    <tr>
                        <th width="">无干扰 未命中次数</th>
                        <th width="">干扰下 未命中次数</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${MemcachedBaseResult.misses}</td>
                        <td >${MemcachedResult.misses}</td>
                        <c:choose>
                            <c:when test="${diffBean.missesDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.missesDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.missesDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div style=" width: 30%; position: absolute; top: 620px;left: 150px;" id="table9">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <!--<div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:web搜索</strong> <a href="" style="float:right; display:none;">添加字段</a></div>-->

                <table class="table table-hover text-center">
                    <tr>
                        <th width="">无干扰 命中次数</th>
                        <th width="">干扰下 命中次数</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${MemcachedBaseResult.hits}</td>
                        <td >${MemcachedResult.hits}</td>
                        <c:choose>
                            <c:when test="${diffBean.hitsDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.hitsDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.hitsDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div style=" width: 30%; position: absolute; top: 620px;left: 650px;" id="table10">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <!--<div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:web搜索</strong> <a href="" style="float:right; display:none;">添加字段</a></div>-->

                <table class="table table-hover text-center">
                    <tr>
                        <th width="">无干扰 每秒钟请求数</th>
                        <th width="">干扰下 每秒钟请求数</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${MemcachedBaseResult.rps}</td>
                        <td >${MemcachedResult.rps}</td>
                        <c:choose>
                            <c:when test="${diffBean.rpsDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.rpsDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.rpsDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div style=" width: 30%; position: absolute; top: 750px;left: 150px;" id="table11">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <!--<div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:web搜索</strong> <a href="" style="float:right; display:none;">添加字段</a></div>-->

                <table class="table table-hover text-center">
                    <tr>
                        <th width="">无干扰 请求总数</th>
                        <th width="">干扰下 请求总数</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${MemcachedBaseResult.requests}</td>
                        <td >${MemcachedResult.requests}</td>
                        <c:choose>
                            <c:when test="${diffBean.requestsDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.requestsDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.requestsDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div style=" width: 30%; position: absolute; top: 750px;left: 650px;" id="table12">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <!--<div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:web搜索</strong> <a href="" style="float:right; display:none;">添加字段</a></div>-->

                <table class="table table-hover text-center">
                    <tr>
                        <th width="">无干扰 get数</th>
                        <th width="">干扰下 get数</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${MemcachedBaseResult.gets}</td>
                        <td >${MemcachedResult.gets}</td>
                        <c:choose>
                            <c:when test="${diffBean.getsDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.getsDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.getsDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div style=" width: 30%; position: absolute; top: 880px;left: 150px;" id="table13">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <!--<div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:web搜索</strong> <a href="" style="float:right; display:none;">添加字段</a></div>-->

                <table class="table table-hover text-center">
                    <tr>
                        <th width="">无干扰 set数</th>
                        <th width="">干扰下 set数</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${MemcachedBaseResult.sets}</td>
                        <td >${MemcachedResult.sets}</td>
                        <c:choose>
                            <c:when test="${diffBean.setsDiff>0}">
                                <td ><img src="statics/images/up.png"/>${diffBean.setsDiff}%</td>
                            </c:when>
                            <c:otherwise>
                                <td ><img src="statics/images/down.png"/>${diffBean.setsDiff}%</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </table>
            </div>
        </form>
    </div>

</div>
</div>

<script type="text/javascript" src="../statics/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../statics/js/highcharts.js"></script>
<script type="text/javascript" src="../statics/js/highcharts-more.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        /*        $('#tableOn').click(function () {
         $("#chart").hide();   //隐藏
         $("#table").show();
         })

         $("#chartOn").click(function () {
         $("#chart").show();   //隐藏
         $("#table").hide();
         })*/



    });
</script>
</body>
</html>