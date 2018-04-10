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
    <script src="statics/js/jquery.js"></script>
    <script src="statics/js/pintuer.js"></script>
</head>
<body>
<form method="post" action="" id="listform">
    <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder"> 设备列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
        <div class="padding border-bottom">
            <ul class="search" style="padding-left:10px;">
                <li> <a class="button border-main icon-plus-square-o" href="deviceAdd.do"> 添加设备</a> </li>
                <li>
                    <input type="text" placeholder="请输入搜索关键字" name="keywords" class="input" style="width:250px; line-height:17px;display:inline-block" />
                    <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a>
                </li>
                <li style="padding-right:10px;"><span class="r">共有数据：<strong>4</strong> 条</span></li>
            </ul>
        </div>
        <table class="table table-hover text-center">
            <tr>
                <th width="10%">测试编号</th>
                <th width="30%x">描述</th>
                <th width="20%">开始时间</th>
                <th width="20%">截止时间</th>
                <th width="10%">操作</th>
                <th width="10%"></th>
            </tr><tr>
            <td >1</td>
            <td >FDA50693-A4E2-4FB1-AFCF-C6EB07647825</td>
            <td >2017-08-15 18:07:12</td>
            <td >2017-08-15 18:10:12</td>
            <td ><a href="jobControl.do"><input type="button" class="editButton" onclick="updateDevice();" value="配置"></a></td>
            <td><input type="button" class="viewButton" onclick="" value="查看"></td>
        </tr><tr>
            <td >2</td>
            <td >FDA50693-A4E2-4FB1-AFCF-C6EB07647825</td>
            <td >2017-08-15 18:07:12</td>
            <td >2017-08-15 18:10:12</td>
            <td ><a href="jobControl.do"><input type="button" class="editButton" onclick="updateDevice();" value="配置"></a></td>
            <td><input type="button" class="viewButton" onclick="" value="查看"></td>
        </tr>
            <tr>
                <td >3</td>
                <td >FDA50693-A4E2-4FB1-AFCF-C6EB07647825</td>
                <td >2017-08-15 18:07:12</td>
                <td >2017-08-15 18:10:12</td>
                <td ><a href="jobControl.do"><input type="button" class="editButton" onclick="updateDevice();" value="配置"></a></td>
                <td><input type="button" class="viewButton" onclick="" value="查看"></td>
            </tr><tr>
            <td >4</td>
            <td >FDA50693-A4E2-4FB1-AFCF-C6EB07647825</td>
            <td >2017-08-15 18:07:12</td>
            <td >2017-08-15 18:10:12</td>
            <td ><a href="jobControl.do"><input type="button" class="editButton" onclick="updateDevice();" value="配置"></a></td>
            <td><input type="button" class="viewButton" onclick="" value="查看"></td>
        </tr>
            <tr>
                <td colspan="8"><div class="pagelist"> <a href="">上一页</a>&nbsp;&nbsp;2/3&nbsp;&nbsp;<a href="">下一页</a><a href="">尾页</a> </div></td>
            </tr>
        </table>
    </div>
</form>
<script type="text/javascript">

    //搜索
    function changesearch(){

    }

    //单个删除
    function del(id,mid,iscid){
        if(confirm("您确定要删除吗?")){

        }
    }

    //全选
    $("#checkall").click(function(){
        $("input[name='id[]']").each(function(){
            if (this.checked) {
                this.checked = false;
            }
            else {
                this.checked = true;
            }
        });
    })

    //批量删除
    function DelSelect(){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){
            var t=confirm("您确认要删除选中的内容吗？");
            if (t==false) return false;
            $("#listform").submit();
        }
        else{
            alert("请选择您要删除的内容!");
            return false;
        }
    }

    //批量排序
    function sorts(){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){

            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");
            return false;
        }
    }


    //批量首页显示
    function changeishome(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){

            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量推荐
    function changeisvouch(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){


            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量置顶
    function changeistop(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){

            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");

            return false;
        }
    }


    //批量移动
    function changecate(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){

            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量复制
    function changecopy(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){
            var i = 0;
            $("input[name='id[]']").each(function(){
                if (this.checked==true) {
                    i++;
                }
            });
            if(i>1){
                alert("只能选择一条信息!");
                $(o).find("option:first").prop("selected","selected");
            }else{

                $("#listform").submit();
            }
        }
        else{
            alert("请选择要复制的内容!");
            $(o).find("option:first").prop("selected","selected");
            return false;
        }
    }

</script>
</body>
</html>