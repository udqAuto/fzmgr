<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>"> 
<title>用户信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page"> 
<script type="text/javascript">
function a (){
	$.ajax({
		url : "general4App/getNextBillSerialNo.do?billType="+4,
		type : "POST", 
		dataType : "json", 
		success : function(req) {
			if (req) {
				$.messager.alert("系统提示", req, "info");
				$("#s").val(req);
			} else {
				$.messager.alert("系统提示", req, "error");
			}
		},
		failer : function(a, b) {
			$.messager.alert("消息提示", a, "info");
		},
		error : function(a) {
			$.messager.alert("消息提示", a, "error");
		}
	});
} 
var m_time_interval;
var m_verif_time=120*1000;
function b(){
	$.ajax({
		url : "general4Apps/sendVerifCode.do?mobile=13568865179",
		type : "POST",
		dataType : "json", 
		success : function(req) {
			if (req.isSuccess) {
				$("#txtVerifCode").val(req.msg);
				verifCodeDelay();
			} else {
				$.messager.alert("系统提示", req.msg, "error");
			}
		},
		failer : function(a, b) {
			$.messager.alert("消息提示", a, "info");
		},
		error : function(a) {
			$.messager.alert("消息提示", a, "error");
		}
	});
}
function verifCodeDelay(){
	var cts=m_verif_time;
	$('#btnGetVerifCode').linkbutton({disabled:true});
	$('#btnGetVerifCode').text("还有"+cts/1000+"秒");
	cts-=1000;
	m_time_interval=setInterval(function(){
		$('#btnGetVerifCode').text("还有"+cts/1000+"秒");
		cts-=1000;
		if(cts<=0){
			clearTimeInterval();
		}
	},1000);
}

function clearTimeInterval(){
	clearInterval(m_time_interval);
	$('#btnGetVerifCode').linkbutton({disabled:false});
	$('#btnGetVerifCode').val("获取验证码");
}


function c(){
	var verifCode = $.trim($("#txtVerifCode").val());
	if(verifCode.length==0||verifCode == ""){
		return;
	}
	$.ajax({
		url : "general4Apps/confirmVerifCode.do?mobile=13568865179&verifCode="+verifCode,
		type : "POST",
		dataType : "json", 
		success : function(req) {
			if (req.isSuccess) {
				$.messager.alert("系统提示", req.msg, "info");
				clearTimeInterval();
			} else {
				$.messager.alert("系统提示", req.msg, "error");
			}
		}
	});
} 
var submit_obj={};
function sub (){
	submit_obj.washTypeIds = [1,2,3];
	submit_obj.couponIds = [];
	submit_obj.couponAmounts = [];
	submit_obj.fixedAmounts = [9.8,9.8,10];
	submit_obj.userId = 16;
	submit_obj.autoId = 5;
	submit_obj.regionId = 4;
	submit_obj.orgId = 4;
	submit_obj.userNote = "尽快";
	submit_obj.orderTime = "2015-07-31 14:34:34";
	$.ajax({
		url : "order/submitOrder4App.do",
		type : "POST",
		dataType : "json",
		data:{"orderInfo" : JSON.stringify(submit_obj)},
		success : function(req) {
			if (req) {
				$.messager.alert("系统提示", req.data.orderNo, "info");
				$("#s").val(req.data.orderNo);
			} else {
				$.messager.alert("系统提示", req, "error"); 
			}
		},
		failer : function(a, b) {
			$.messager.alert("消息提示", a, "info");
		},
		error : function(a) {
			$.messager.alert("消息提示", a, "error");
		}
	});
}
</script>
</head> 
  <body>  
  <input id="s" type=text" />
  <a onclick="a();">订单编号测试</a>
 
   <input id="txtVerifCode"  class="easyui-validatebox" data-options="required:false" />
   <a id="btnGetVerifCode" sytle="width:60px" href="javascript:void(0);" iconcls="icon-udq-show" class="easyui-linkbutton"  onclick="b()">获取验证码</input>
   <a sytle="width:60px" href="javascript:void(0);" iconcls="icon-udq-save" class="easyui-linkbutton"  onclick="c()">提交数据</input>
 
  <button onclick="sub();">提交订单测试</button>
 
  </body>
</html>
