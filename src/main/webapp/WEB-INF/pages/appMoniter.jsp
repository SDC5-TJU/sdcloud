<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="statics/css/pintuer.css">
    <link rel="stylesheet" href="statics/css/admin.css">
    <link rel="stylesheet" href="statics/css/physical.css">
    <script src="statics/js/jquery.js"></script>
    <script src="statics/js/pintuer.js"></script>
</head>
<body>
<div>
    <div style="width: 50%;margin-top: 30px;margin-left: 30px;">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <div class="panel-head"><h2 class="icon-reorder"> 应用资源监控</h2></div>

                <table class="table table-hover text-center">
                    <tr>
                        <th width="20%">应用名称</th>
                        <th width="15%">CPU%</th>
                        <th width="20%">men usage</th>
                        <th width="15%">mem%</th>
                        <th width="15%">net I/O</th>
                        <th width="15%">block I/O</th>
                    </tr><tr>
                    <td width="20%">Web搜索</td>
                    <td width="15%">50%</td>
                    <td width="20%">1.237MB/29.33GB</td>
                    <td width="15%">50%</td>
                    <td width="15%">0B/0B</td>
                    <td width="15%">0B/0B</td>
                </tr><tr>
                    <td width="20%">电商服务</td>
                    <td width="15%">20%</td>
                    <td width="20%">1.237MB/29.33GB</td>
                    <td width="15%">20%</td>
                    <td width="15%">0B/0B</td>
                    <td width="15%">0B/0B</td>
                </tr><tr>
                    <td width="20%">silo</td>
                    <td width="15%">5%</td>
                    <td width="20%">1.237MB/29.33GB</td>
                    <td width="15%">5%</td>
                    <td width="15%">0B/0B</td>
                    <td width="15%">0B/0B</td>
                </tr><tr>
                    <td width="20%">memcached</td>
                    <td width="15%">50%</td>
                    <td width="20%">1.237MB/29.33GB</td>
                    <td width="15%">50%</td>
                    <td width="15%">0B/0B</td>
                    <td width="15%">0B/0B</td>
                </tr><tr>
                    <td >......</td>
                </tr>
                </table>
            </div>
        </form>
    </div>

    <div id="CPUchart" style="position: absolute; top:30px;right: 30px; width: 550px; height: 300px; "></div>
    <div id="MEMchart" style="position: absolute; top:370px;right: 30px; width: 550px; height: 300px; "></div>
</div>

<script type="text/javascript" src="statics/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="statics/js/highcharts.js"></script>
<script type="text/javascript" src="statics/js/highcharts-more.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });
        Highcharts.chart('CPUchart', {
            chart: {
                zoomType: 'x'
            },
            title: {
                text: 'CPU占用图'
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

                name:'应用1',
                data:[100,120,160,170,130,150,180,103,100,150,100,110,100,140,70,180,100,100,214,30,20,10,40,20,30,40,50,70,60,60,50,80,60,22,30,0,60,20,30,60,50,60,30,20,23,99,70,60,80
                ],

                pointInterval: 306000,
                pointStart: Date.UTC(2014, 6, 10,0,0,0),
                pointEnd:Date.UTC(2014,6,10,23,59,59),
                // pointEnd:Date.UTC(<?php date('Y , m, d',strtotime('-1 month'))?>, 0, 0, 0;?>)
            },
                {
                    name:'应用2',
                    data:[140,160,130,150,170,180,80,183,160,100,130,130,190,40,170,120,190,160,314,130,320,210,240,120,130,60,70,20,50,80,20,90,100,122,230,120,50,60,90,30,20,90,130,320,123,59,89,63,40
                    ],
                    pointInterval: 306000,
                    pointStart: Date.UTC(2014, 6, 10,0,0,0),
                    pointEnd:Date.UTC(2014,6,10,23,59,59),
                }]
        });

        Highcharts.chart('MEMchart', {
            chart: {
                type: 'area'
            },
            title: {
                text: '内存占用堆叠图'
            },
            xAxis: {
                categories: ['10:01', '10:02', '10:03', '10:04', '10:05', '10:06'],
                tickmarkPlacement: 'on',
                title: {
                    enabled: false
                }
            },
            yAxis: {
                title: {
                    text: 'MB'
                },
                min:0

            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle'
            },
            tooltip: {
                split: true,
                valueSuffix: ' MB'
            },
            plotOptions: {
                area: {
                    stacking: 'normal',
                    lineColor: '#666666',
                    lineWidth: 1,
                    marker: {
                        lineWidth: 1,
                        lineColor: '#666666'
                    }
                }
            },

            series: [{
                type: 'area',
                name:'应用1',
                data:[30,40,50,70,100,400],
            },
                {
                    name:'应用2',
                    data:[70,90,150,200,190,500],
                },
            {
                name:'应用3',
                data:[100,190,250,290,390,600],
            }]
        });
    });
</script>
</body>
</html>