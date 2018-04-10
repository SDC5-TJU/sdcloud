<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<!--容器资源监控-->
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
<div>
    <div style="width: 60%; margin-top: 30px; margin-left: 60px;">
        <form method="post" action="" id="listform">
            <div class="panel admin-panel">
                <div class="panel-head"><h2 class="icon-reorder"> 容器资源监控</h2> </div>

                <table class="table table-hover text-center">
                    <tr>
                        <th width="20%">容器名称</th>
                        <th width="15%">CPU%</th>
                        <th width="20%">men usage</th>
                        <th width="15%">mem%</th>
                        <th width="15%">net I/O</th>
                        <th width="15%">block I/O</th>
                    </tr><tr>
                    <td width="20%">Hadoop1</td>
                    <td width="15%">50%</td>
                    <td width="20%">1.237MB/29.33GB</td>
                    <td width="15%">50%</td>
                    <td width="15%">0B/0B</td>
                    <td width="15%">0B/0B</td>
                </tr><tr>
                    <td width="20%">Hadoop2</td>
                    <td width="15%">20%</td>
                    <td width="20%">1.237MB/29.33GB</td>
                    <td width="15%">20%</td>
                    <td width="15%">0B/0B</td>
                    <td width="15%">0B/0B</td>
                </tr>
                    <tr>
                        <td width="20%">Hadoop3</td>
                        <td width="15%">5%</td>
                        <td width="20%">1.237MB/29.33GB</td>
                        <td width="15%">5%</td>
                        <td width="15%">0B/0B</td>
                        <td width="15%">0B/0B</td>
                    </tr><tr>
                    <td width="20%">Solr1</td>
                    <td width="15%">50%</td>
                    <td width="20%">1.237MB/29.33GB</td>
                    <td width="15%">50%</td>
                    <td width="15%">0B/0B</td>
                    <td width="15%">0B/0B</td>
                </tr><tr>
                    <td width="20%">Solr2</td>
                    <td width="15%">20%</td>
                    <td width="20%">1.237MB/29.33GB</td>
                    <td width="15%">20%</td>
                    <td width="15%">0B/0B</td>
                    <td width="15%">0B/0B</td>
                </tr><tr>
                    <td >......</td>
                </tr>
                </table>
            </div>
        </form>
    </div>


</div>

</body>
</html>