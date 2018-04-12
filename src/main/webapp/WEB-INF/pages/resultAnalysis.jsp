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
    <!--图-->
    <div style="width: 900px;" id="chart">
        <div id="websearch1" style="width: 550px;height: 300px; position: absolute;top: 50px;left:50px;"></div>
        <div id="websearch2" style="width: 550px;height: 300px;position: absolute; left:600px;top: 50px;"></div>
    </div>
    <!--表-->
    <div style=" width: 30%; position: absolute; top: 400px;left: 150px;" id="table">
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
                        <td >${webServerBaseResult.nintyth}</td>
                        <td >${webServerResult.nintyth}</td>
                        <td >${diffBean.nintythDiff}%</td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div style=" width: 30%; position: absolute; top: 400px;left: 600px;" id="table2">
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
                        <td >${webServerBaseResult.nintyFiveTh}</td>
                        <td >${webServerResult.nintyFiveTh}</td>
                        <td >${diffBean.nintyFiveThDiff}%</td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div style=" width: 30%; position: absolute; top: 530px;left: 150px;" id="table3">
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
                        <td >${webServerBaseResult.nintyNineTh}</td>
                        <td >${webServerResult.nintyNineTh}</td>
                        <td >${diffBean.nintyNineThDiff}%</td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div style=" width: 30%; position: absolute; top: 530px;left: 600px;" id="table4">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <!--<div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:web搜索</strong> <a href="" style="float:right; display:none;">添加字段</a></div>-->

                <table class="table table-hover text-center">
                    <tr>
                        <th width="">无干扰 响应时间方差</th>
                        <th width="">干扰下 响应时间方差</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${webServerBaseResult.var}</td>
                        <td >${webServerResult.var}</td>
                        <td >${diffBean.varDiff}%</td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div style=" width: 30%; position: absolute; top: 660px;left: 150px;" id="table5">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <!--<div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:web搜索</strong> <a href="" style="float:right; display:none;">添加字段</a></div>-->

                <table class="table table-hover text-center">
                    <tr>
                        <th width="">无干扰 响应时间平均值</th>
                        <th width="">干扰下 响应时间平均值</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${webServerBaseResult.mean}</td>
                        <td >${webServerResult.mean}</td>
                        <td >${diffBean.meanDiff}%</td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div style=" width: 30%; position: absolute; top: 660px;left: 600px;" id="table6">
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
                        <td >${webServerBaseResult.min}</td>
                        <td >${webServerResult.min}</td>
                        <td >${diffBean.minDiff}%</td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div style=" width: 30%; position: absolute; top: 790px;left: 150px;" id="table7">
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
                        <td >${webServerBaseResult.max}</td>
                        <td >${webServerResult.max}</td>
                        <td >${diffBean.maxDiff}%</td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
        <div style=" width: 30%; position: absolute; top: 790px;left: 150px;" id="table7">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <!--<div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异:web搜索</strong> <a href="" style="float:right; display:none;">添加字段</a></div>-->

                <table class="table table-hover text-center">
                    <tr>
                        <th width="">无干扰 缺失率</th>
                        <th width="">干扰下 缺失率</th>
                        <th width="">变化率</th>
                    </tr>
                    <tr>
                        <td >${webServerBaseResult.missRate}</td>
                        <td >${webServerResult.missRate}</td>
                        <td >${diffBean.missRateDiff}%</td>
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
        Highcharts.chart('websearch2', {
/*            chart: {
                zoomType: 'x'
            },*/
            boost: {
                useGPUTranslations: true
            },
            title: {
                text: 'Highcharts drawing ' + n + ' points'
            },
            subtitle: {
                text: 'Using the Boost module'
            },
            tooltip: {
                valueDecimals: 2
            },
            series: [{
                data:[12,12,32,34,2424,53,3223,23],
            lineWidth: 0.5
            }]
        });
        console.timeEnd('line');

        Highcharts.chart('websearch1', {
            chart: {
                zoomType: 'x'
            },
            title: {
                text: 'cpu used(%)'
            },

            xAxis: {
                type: 'datetime',
                // maxZoom:24 * 3600 * 1000, // x轴总共显示的时间
                //min:<?=strtotime(date('Y-m-d'))?>,
                dateTimeLabelFormats: {
//                  minute: '%H:%M'
                    day: '%H:%M'
                }
            },
            yAxis: {
                title: {
                    text: 'used rate'
                },
                min:0

            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle'
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

            series: [{
                type: 'area',
                name:'测试一下',
                data:[100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,214,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,22,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
                ],
                pointInterval: 306000,
                pointStart: Date.UTC(2014, 6, 10,0,0,0),
                pointEnd:Date.UTC(2014,6,10,23,59,59),
                // pointEnd:Date.UTC(<?php date('Y , m, d',strtotime('-1 month'))?>, 0, 0, 0;?>)
            }]
        });


    });
</script>
</body>
</html>