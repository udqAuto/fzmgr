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
  <button onclick="sub();">提交订单测试</button>
  </body>
</html>