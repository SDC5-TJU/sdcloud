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
        <li class="active" style="float: left;">
            <a  href="#home" id="chartOn">图</a>
        </li>
        <li class="" style="float: left;">
            <a  href="#" id="tableOn">表</a>
        </li>
    </ul>

</div>
<div>
    <!--图-->
    <div style="width: 900px;" id="chart">
        <div id="solrChart" style="width: 400px;height: 200px; position: absolute;top: 50px;left:100px;"></div>
        <div id="webChart" style="width: 400px;height: 200px;position: absolute; left:530px;top: 50px;"></div>
        <div id="reChart1" style="width: 400px;height: 200px; position: absolute; top: 300px;left:100px;"></div>
        <div id="reChart2" style="width: 400px;height: 200px; position: absolute; left:530px;top: 300px;"></div>
    </div>
    <!--表-->
    <div style=" width: 70%; position: absolute; top: 100px;left: 150px; display:none;" id="table">
        <form method="post" action="" id="">
            <div class="panel admin-panel">
                <div class="panel-head"><strong class="icon-reorder">计算同无干扰下的响应对比差异</strong> <a href="" style="float:right; display:none;">添加字段</a></div>

                <table class="table table-hover text-center">
                    <tr>
                        <th width="25%">应用名称</th>
                        <th width="25%">无干扰平均响应时间</th>
                        <th width="25%">干扰下平均响应时间</th>
                        <th width="25%">变化率</th>
                    </tr><tr>
                    <td width="20%">Web搜索</td>
                    <td width="15%">50%</td>
                    <td width="20%">1.237MB/29.33GB</td>
                    <td width="15%"><img src="statics/images/up.png">50%</td>
                </tr><tr>
                    <td width="20%">电商服务</td>
                    <td width="15%">20%</td>
                    <td width="20%">1.237MB/29.33GB</td>
                    <td width="15%"><img src="statics/images/up.png">20%</td>
                </tr><tr>
                    <td width="20%">silo</td>
                    <td width="15%">5%</td>
                    <td width="20%">1.237MB/29.33GB</td>
                    <td width="15%">5%</td>
                </tr><tr>
                    <td width="20%">memcached</td>
                    <td width="15%">50%</td>
                    <td width="20%">1.237MB/29.33GB</td>
                    <td width="15%"><img src="statics/images/down.png">50%</td>
                </tr>
                </table>
            </div>
        </form>
    </div>
    </div>
</div>

<script type="text/javascript" src="statics/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="statics/js/highcharts.js"></script>
<script type="text/javascript" src="statics/js/highcharts-more.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
            $('#tableOn').click(function () {
                $("#chart").hide();   //隐藏
                $("#table").show();
            })

            $("#chartOn").click(function () {
                $("#chart").show();   //隐藏
                $("#table").hide();
            })

        Highcharts.chart('solrChart', {
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

        Highcharts.chart('webChart', {
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

        Highcharts.chart('reChart1', {
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

        Highcharts.chart('reChart2', {
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