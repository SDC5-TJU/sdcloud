<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<!--部署方案配置-->
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />


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
<div >
    <ul class="nav nav-tabs" >
        <li class="active" style="float: left;">
            <a  href="#home" id="onlineControl">在线服务</a>
        </li>
        <li class="" style="float: left;">
            <a  href="#" id="offlineControl">离线服务</a>
        </li>
    </ul>

</div>
<div>
    <div class="new-page-container" >
        <form action="" method="post" class="form form-horizontal" id="form-article-add">
           <!--在线-->
            <div id="online">
               <div class="row cl">
                   <label class="col-xs-4 col-sm-2 new-center"></label>
                   <label class="col-xs-4 col-sm-2 new-center" >请求总数</label>
                   <label class="col-xs-4 col-sm-2 new-center">负载策略</label>
                   <label class="col-xs-4 col-sm-2 new-center">单位/ms</label>
                   <label class="col-xs-4 col-sm-2 new-center">预热次数</label>
                   <label class="col-xs-4 col-sm-2 new-center">启用状态</label>
               </div>
               <div class="row cl">
                   <label class="form-label col-xs-4 col-sm-2">web搜索：</label>
                   <div class="formControls col-xs-8 col-sm-9">
                       <input class="input new-w50" type="text"  value="" placeholder="5000" id="config1-1" name="" >
                       <input class="input new-w50" type="text" value="" placeholder="随机/均匀" id="config1-2" name="">
                      <input  class="input new-w50" type="text"  value="" placeholder="10" id="config1-3" name="" >
                      <input  class="input new-w50" type="text"  value="" placeholder="2000" id="config1-4"name="">
                       <input style="float: right;" type="button" class="config" onclick="" value="禁用" id="config1">
                       <!--<input style="float: right;" type="button"  class="btn btn-primary radius " value="禁用" >-->
                   </div>

               </div>
               <div class="row cl">
                   <label class="form-label col-xs-4 col-sm-2">电商服务：</label>
                   <div class="formControls col-xs-8 col-sm-9">
                       <input class="input new-w50" type="text"  value="" placeholder="5000" id="config2-1" name="">
                       <input class="input new-w50" type="text" value="" placeholder="随机/均匀" id="config2-2" name="">
                       <input  class="input new-w50" type="text"  value="" placeholder="10" id="config2-3" name="" >
                       <input  class="input new-w50" type="text"  value="" placeholder="2000" id="config2-4" name="">
                       <input style="float: right;" type="button" class="config" onclick="" value="禁用" id="config2">
                   </div>
               </div>
               <div class="row cl">
                   <label class="col-xs-4 col-sm-2 new-center"></label>
                   <label class="col-xs-4 col-sm-2 new-center" >请求总数</label>
                   <label class="col-xs-4 col-sm-2 new-center">负载策略</label>
                   <label class="col-xs-4 col-sm-2 new-center">QPS</label>
                   <label class="col-xs-4 col-sm-2 new-center">预热次数</label>
                   <label class="col-xs-4 col-sm-2 new-center">启用状态</label>
               </div>
               <div class="row cl">
                   <label class="form-label col-xs-4 col-sm-2">memcached:</label>
                   <div class="formControls col-xs-8 col-sm-9">
                       <input class="input new-w50" type="text"  value="" placeholder="20000" id="config3-1" name="" >
                       <input class="input new-w50" type="text" value="均匀" placeholder="" id="" name="" disabled="disabled">
                       <input  class="input new-w50" type="text"  value="" placeholder="2000" id="config3-2" name="" >
                       <input  class="input new-w50" type="text"  value="" placeholder="2000" id="config3-3" name="">
                       <input style="float: right;" type="button" class="config" onclick="" value="禁用" id="config3">
                   </div>
               </div>
               <div class="row cl">
                   <label class="form-label col-xs-4 col-sm-2">silo:</label>
                   <div class="formControls col-xs-8 col-sm-9">
                       <input class="input new-w50" type="text"  value="" placeholder="20000" id="config4-1" name="">
                       <input class="input new-w50" type="text" value="均匀" placeholder="" id="" name="" disabled="disabled">
                       <input  class="input new-w50" type="text"  value="" placeholder="2000" id="config4-2" name="" >
                       <input  class="input new-w50" type="text"  value="" placeholder="2000" id="config4-3" name="">
                       <input style="float: right;" type="button" class="config" onclick="" value="禁用" id="config4">
                   </div>
               </div>
           </div>

            <!--离线-->
            <div id="offline" style="display:none;">
                <div class="row cl">
                    <label class="col-xs-4 col-sm-2 new-center"></label>
                    <label class="col-xs-4 col-sm-2 new-center">负载策略</label>
                    <label class="col-xs-4 col-sm-2 new-center">读写块大小</label>
                    <label class="col-xs-4 col-sm-2 new-center"></label>
                    <label class="col-xs-4 col-sm-2 new-center"></label>
                    <label class="col-xs-4 col-sm-2 new-center">启用状态</label>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-2">Bonnie：</label>
                    <div class="formControls col-xs-8 col-sm-9">
                        <input class="input new-w50" type="text"  value="循环" placeholder="" id="" name="" disabled="disabled">
                        <input class="input new-w50" type="text" value="" placeholder="4096" id="config5-1" name="" >
                        <input  class="input new-w50" type="text"  value="" placeholder="" id="" name="" disabled="disabled">
                        <input  class="input new-w50" type="text"  value="" placeholder="" id="" name="" disabled="disabled">
                        <input style="float: right;" type="button" class="config" onclick="" value="禁用" id="config5">
                    </div>
                </div>
                <div class="row cl">
                    <label class="col-xs-4 col-sm-2 new-center"></label>
                    <label class="col-xs-4 col-sm-2 new-center">负载策略</label>
                    <label class="col-xs-4 col-sm-2 new-center">线程数量</label>
                    <label class="col-xs-4 col-sm-2 new-center"></label>
                    <label class="col-xs-4 col-sm-2 new-center"></label>
                    <label class="col-xs-4 col-sm-2 new-center">启用状态</label>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-2">scimark：</label>
                    <div class="formControls col-xs-8 col-sm-9">
                        <input class="input new-w50" type="text"  value="循环" placeholder="" id="" name="" disabled="disabled">
                        <input class="input new-w50" type="text" value="" placeholder="4" id="config6-1" name="" >
                        <input  class="input new-w50" type="text"  value="" placeholder="" id="" name="" disabled="disabled">
                        <input  class="input new-w50" type="text"  value="" placeholder="" id="" name="" disabled="disabled">
                        <input style="float: right;" type="button" class="config" onclick="" value="禁用" id="config6">
                    </div>
                </div>
                <div class="row cl">
                    <label class="col-xs-4 col-sm-2 new-center"></label>
                    <label class="col-xs-4 col-sm-2 new-center">任务类型</label>
                    <label class="col-xs-4 col-sm-2 new-center">reduce数</label>
                    <label class="col-xs-4 col-sm-2 new-center"></label>
                    <label class="col-xs-4 col-sm-2 new-center"></label>
                    <label class="col-xs-4 col-sm-2 new-center">启用状态</label>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-2">Hadoop：</label>
                    <div class="formControls col-xs-8 col-sm-9">
                        <input class="input new-w50" type="text"  value="" placeholder="IO/CPU/综合" id="config7-1" name="" >
                        <input class="input new-w50" type="text" value="" placeholder="4" id="config7-2" name="" >
                        <input  class="input new-w50" type="text"  value="" placeholder="" id="" name="" disabled="disabled">
                        <input  class="input new-w50" type="text"  value="" placeholder="" id="" name="" disabled="disabled">
                        <input style="float: right;" type="button" class="config" onclick="" value="禁用" id="config7">
                    </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-4 col-sm-2">Cassandra：</label>
                    <div class="formControls col-xs-8 col-sm-9">
                        <input class="input new-w50" type="text"  value="" placeholder="IO/CPU/综合" id="config8-1" name="" >
                        <input class="input new-w50" type="text" value="" placeholder="4" id="config8-2" name="" >
                        <input  class="input new-w50" type="text"  value="" placeholder="" id="" name="" disabled="disabled">
                        <input  class="input new-w50" type="text"  value="" placeholder="" id="" name="" disabled="disabled">
                        <input style="float: right;" type="button" class="config" onclick="" value="禁用" id="config8">
                    </div>
                </div>
                <div class="row cl">
                    <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                        <button style="float: right;" onclick="save();" class="btn btn-primary radius" ><i class="Hui-iconfont">&#xe632;</i>保存</button>
                    </div>
                </div>
            </div>



        </form>
    </div>
</div>

<script src="statics/js/jquery.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#offlineControl').click(function(){
            $("#online").hide();   //隐藏
            $("#offline").show();
            })

            $("#onlineControl").click(function(){
                $("#online").show();   //隐藏
                $("#offline").hide();
            })

        //禁用与启动部分
        $("#config1").click(function(){
            if ($("#config1").val()=="禁用")
            {
                $("#config1-1").attr("disabled","disabled");
                $("#config1-2").attr("disabled","disabled");
                $("#config1-3").attr("disabled","disabled");
                $("#config1-4").attr("disabled","disabled");
                $("#config1").css("background","#61d38f");
                $("#config1").val("开启");
            }
            else if ($("#config1").val()=="开启")
            {
                $("#config1").css("background","#21A1D3");
                $("#config1").val("禁用");
                $("#config1-1").removeAttr("disabled");
                $("#config1-2").removeAttr("disabled");
                $("#config1-3").removeAttr("disabled");
                $("#config1-4").removeAttr("disabled");
            }
        })
        $("#config2").click(function(){
            if ($("#config2").val()=="禁用")
            {
                $("#config2-1").attr("disabled","disabled");
                $("#config2-2").attr("disabled","disabled");
                $("#config2-3").attr("disabled","disabled");
                $("#config2-4").attr("disabled","disabled");
                $("#config2").css("background","#61d38f");
                $("#config2").val("开启");
            }
            else if ($("#config2").val()=="开启")
            {
                $("#config2").css("background","#21A1D3");
                $("#config2").val("禁用");
                $("#config2-1").removeAttr("disabled");
                $("#config2-2").removeAttr("disabled");
                $("#config2-3").removeAttr("disabled");
                $("#config2-4").removeAttr("disabled");
            }
        })
        $("#config3").click(function(){
            if ($("#config3").val()=="禁用")
            {
                $("#config3-1").attr("disabled","disabled");
                $("#config3-2").attr("disabled","disabled");
                $("#config3-3").attr("disabled","disabled");
                $("#config3").css("background","#61d38f");
                $("#config3").val("开启");
            }
            else if ($("#config3").val()=="开启")
            {
                $("#config3").css("background","#21A1D3");
                $("#config3").val("禁用");
                $("#config3-1").removeAttr("disabled");
                $("#config3-2").removeAttr("disabled");
                $("#config3-3").removeAttr("disabled");
            }
        })
        $("#config4").click(function(){
            if ($("#config4").val()=="禁用")
            {
                $("#config4-1").attr("disabled","disabled");
                $("#config4-2").attr("disabled","disabled");
                $("#config4-3").attr("disabled","disabled");
                $("#config4").css("background","#61d38f");
                $("#config4").val("开启");
            }
            else if ($("#config4").val()=="开启")
            {
                $("#config4").css("background","#21A1D3");
                $("#config4").val("禁用");
                $("#config4-1").removeAttr("disabled");
                $("#config4-2").removeAttr("disabled");
                $("#config4-3").removeAttr("disabled");
            }
        })
        $("#config5").click(function(){
            if ($("#config5").val()=="禁用")
            {
                $("#config5-1").attr("disabled","disabled");
                $("#config5").css("background","#61d38f");
                $("#config5").val("开启");
            }
            else if ($("#config5").val()=="开启")
            {
                $("#config5").css("background","#21A1D3");
                $("#config5").val("禁用");
                $("#config5-1").removeAttr("disabled");
            }
        })
        $("#config6").click(function(){
            if ($("#config6").val()=="禁用")
            {
                $("#config6-1").attr("disabled","disabled");
                $("#config6").css("background","#61d38f");
                $("#config6").val("开启");
            }
            else if ($("#config6").val()=="开启")
            {
                $("#config6").css("background","#21A1D3");
                $("#config6").val("禁用");
                $("#config6-1").removeAttr("disabled");
            }
        })
        $("#config7").click(function(){
            if ($("#config7").val()=="禁用")
            {
                $("#config7-1").attr("disabled","disabled");
                $("#config7-2").attr("disabled","disabled");
                $("#config7").css("background","#61d38f");
                $("#config7").val("开启");
            }
            else if ($("#config7").val()=="开启")
            {
                $("#config7").css("background","#21A1D3");
                $("#config7").val("禁用");
                $("#config7-1").removeAttr("disabled");
                $("#config7-2").removeAttr("disabled");
            }
        })
        $("#config8").click(function(){
            if ($("#config8").val()=="禁用")
            {
                $("#config8-1").attr("disabled","disabled");
                $("#config8-2").attr("disabled","disabled");
                $("#config8").css("background","#61d38f");
                $("#config8").val("开启");
            }
            else if ($("#config8").val()=="开启")
            {
                $("#config8").css("background","#21A1D3");
                $("#config8").val("禁用");
                $("#config8-1").removeAttr("disabled");
                $("#config8-2").removeAttr("disabled");
            }
        })
    });
    function save(){
        alert("已保存");

    }
</script>

</body>
</html>