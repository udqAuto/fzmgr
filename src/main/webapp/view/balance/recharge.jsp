<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script src='<%=basePath%>script/balance/recharge.js'
	type='text/javascript'></script> 
<title>余额充值</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page"> 
</head>
  
  <body>
   
		 <p id="tb_operation" style="padding:2px;border-bottom:1px solid black;width:94%;">
			<a id="btnSave"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-save" plain="true">确定</a> 
			<a id="btnCancelSave"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-close" plain="true">关闭</a> 
		 </p>
	<div id="div_recharge" style="text-align:left;overflow:hidden;">
		 <p><label>车主名称：</label><input id="txtUserName" style="width:210px"  class="easyui-validatebox" readonly="readonly"/></p>
		 <p><label>充值金额：</label><input id="txtAmount" type="number" style="width:210px"  class="easyui-validatebox"/></p>
	 </div>
  </body>
</html>
