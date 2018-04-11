<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />-->

<link rel="stylesheet" href="statics/css/pintuer.css">
<link rel="stylesheet" href="statics/css/admin.css">

<link rel="stylesheet" type="text/css" href="statics/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="statics/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="statics/css/iconfont.css" />

<link rel="stylesheet" type="text/css" href="statics/css/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="statics/css/style.css" />

<link href="statics/css/webuploader.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div class="page-container">
	<form action="addRecord.do" method="post" class="form form-horizontal" id="form-article-add">
	


		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>测试描述：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input style="width:80%;height:300px;" type="text" class="input w50" value="" placeholder="请输入测试描述" id="" name="">
			</div>
		</div>

		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onclick="goback();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i>确认添加</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</div>


<script src="statics/js/jquery.js"></script>
<script type="text/javascript">
 
function goback(){
    alert("添加成功");

}
</script>
</body>
</html>