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
<!DOCTYPE html>
<html lang="zh-cn">
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
<div id="mainDiv">
    <div id="container1"></div>
    <div id="container2"></div>

    <div id="container3"></div>
    <div id="container4"></div>
    <div id="container5"></div>
 <!--   <div id="container6"></div>
    <div id="container7"></div>-->
    <script type="text/javascript" src="statics/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="statics/js/highcharts.js"></script>
    <script type="text/javascript" src="statics/js/highcharts-more.js"></script>
    <script type="text/javascript">

     //   var flag=false;



        $(document).ready(function (){
            Highcharts.setOptions({
                global: {
                    useUTC: false
                }
            });
            Highcharts.chart('container1', {
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

          Highcharts.chart('container2', {
            chart: {
                zoomType: 'x'
            },
            title: {
                text: 'memery used(%)'
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
       
	   
	   Highcharts.chart('container3', {
            chart: {
                zoomType: 'x'
            },
            title: {
                text: 'IO used(%)'
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

		
		Highcharts.chart('container4', {
            chart: {
                zoomType: 'x'
            },
            title: {
                text: 'cpu used(%)'
            },
           
            xAxis: {
                type: 'net used(%)',
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
				Highcharts.chart('container5', {
			chart: {
				type: 'column'
			},
			title: {
				text: 'cpus'
			},
			xAxis: {
				/*categories: ['Apple', 'Oranges', 'Pears', 'Grapes', 'Bananas']*/
				categories: ['cpu1', 'cpu2', 'cpu3', 'cpu4', 'cpu5','cpu6','cpu7','cpu8', 'cpu9', 'cpu10',
				'cpu11', 'cpu12', 'cpu13', 'cpu14', 'cpu15','cpu16','cpu17','cpu18', 'cpu19', 'cpu20',
				'cpu21', 'cpu22', 'cpu23', 'cpu24', 'cpu25','cpu26','cpu27','cpu28', 'cpu29', 'cpu30',
				'cpu31', 'cpu32', 'cpu33', 'cpu34', 'cpu35','cpu36','cpu37','cpu38', 'cpu39', 'cpu40'
				]
			},
			yAxis: {
				min: 0,
				max:100,
				title: {
					text: 'CPU useInfo'
				}
			},
			tooltip: {
				pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
				shared: true
			},
			plotOptions: {
				column: {
					stacking: 'percent'
				}
			},
			series: [{
				name: '未使用',
				data: [20, 20, 20, 20, 20,20, 20, 20, 20, 20,
				      20, 20, 20, 20, 20,20, 20, 20, 20, 20,
					  20, 20, 20, 20, 20,20, 20, 20, 20, 20,
					  20, 20, 20, 20, 20,20, 20, 20, 20, 20]
			}, {
				name: '应用使用',
				data: [40, 40, 40, 40, 40,40, 40, 40, 40, 40,
				40, 40, 40, 40, 40,40, 40, 40, 40, 40,
				40, 40, 40, 40, 40,40, 40, 40, 40, 40,
				40, 40, 40, 40, 40,40, 40, 40, 40, 40,]
			}, {
				name: '系统使用',
				data: [40, 40, 40, 40, 40,40, 40, 40, 40, 40,
				40, 40, 40, 40, 40,40, 40, 40, 40, 40,
				40, 40, 40, 40, 40,40, 40, 40, 40, 40,
				40, 40, 40, 40, 40,40, 40, 40, 40, 40,]
			}]
		});

       });

    </script>
</div>
</body>

</html>
<body>
<div id="mainDiv">
    <div id="container1"></div>
    <div id="container2"></div>

    <div id="container3"></div>
    <div id="container4"></div>
<!--    <div id="container5"></div>
    <div id="container6"></div>
    <div id="container7"></div>-->
    <script type="text/javascript" src="statics/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="statics/js/highcharts.js"></script>
    <script type="text/javascript" src="statics/js/highcharts-more.js"></script>
    <script type="text/javascript">

     //   var flag=false;



        $(document).ready(function (){
            Highcharts.setOptions({
                global: {
                    useUTC: false
                }
            });
            chart = Highcharts.chart('container', {
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
        tooltip: {
            dateTimeLabelFormats: {
                millisecond: '%H:%M:%S.%L',
                second: '%H:%M:%S',
                minute: '%H:%M',
                hour: '%H:%M',
                day: '%Y-%m-%d',
                week: '%m-%d',
                month: '%Y-%m',
                year: '%Y'
            }
        },
        yAxis: {
            title: {
                text: 'used rate'
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
        series: [{
                    name:'测试一下',
                    data:[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,214,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,22,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
                    ],
                    pointInterval: 306000,
                    pointStart: Date.UTC(2014, 6, 10,0,0,0),
                    pointEnd:Date.UTC(2014,6,10,23,59,59),
                    // pointEnd:Date.UTC(<?php date('Y , m, d',strtotime('-1 month'))?>, 0, 0, 0;?>)
                }]
    });

            Highcharts.chart('container3', {
                title: {
                    text: 'IO used(%)'
                },
                yAxis: {
                    title: {
                        text: 'used rate'
                    }
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle'
                },
                plotOptions: {
                    spline: {
                        lineWidth: 2,
                        states: {
                            hover: {
                                lineWidth: 3
                            }
                        },
                        marker: {
                            enabled: false
                        },
                        // pointInterval: 300 * 1000, // 曲线每个点的间隔
                        // pointStart: Date.UTC(2014, 6, 10)
                    }
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
                series: [{
                    name:'测试一下',
                    data:[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,214,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,22,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
                    ],
                    pointInterval: 306000,
                    pointStart: Date.UTC(2014, 6, 10,0,0,0),
                    pointEnd:Date.UTC(2014,6,10,23,59,59),
                    // pointEnd:Date.UTC(<?php date('Y , m, d',strtotime('-1 month'))?>, 0, 0, 0;?>)
                }]
            });

            Highcharts.chart('container4', {
                title: {
                    text: 'net used(%)'
                },
                yAxis: {
                    title: {
                        text: 'used rate'
                    }
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle'
                },
                plotOptions: {
                    spline: {
                        lineWidth: 2,
                        states: {
                            hover: {
                                lineWidth: 3
                            }
                        },
                        marker: {
                            enabled: false
                        },
                        // pointInterval: 300 * 1000, // 曲线每个点的间隔
                        // pointStart: Date.UTC(2014, 6, 10)
                    }
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
                series: [{
                    name:'测试一下',
                    data:[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,214,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,22,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
                    ],
                    pointInterval: 306000,
                    pointStart: Date.UTC(2014, 6, 10,0,0,0),
                    pointEnd:Date.UTC(2014,6,10,23,59,59),
                    // pointEnd:Date.UTC(<?php date('Y , m, d',strtotime('-1 month'))?>, 0, 0, 0;?>)
                }]
            });
       });

    </script>
</div>
</body>

</html>