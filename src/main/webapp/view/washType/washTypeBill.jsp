<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script src='<%=basePath%>script/washType/washTypeBill.js'
	type='text/javascript'></script> 
<title>洗车信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page"> 
</head>
  
  <body>
   <div id="div_washTypeInfo" style="text-align:left;overflow:hidden;">
		 <p id="tb_operation" style="padding:2px;border-bottom:1px solid black;">
			<a id="btnSaveWashTypeInfo"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-save" plain="true">保存</a> 
			<a id="btnCancelSave"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-close" plain="true">关闭</a> 
		 </p>
		 
		 <p><label>名称：</label><input id="txtWashTypeName" style="width:234px"  class="easyui-validatebox"/></p>
		 <p><label>价格：</label><input id="txtWashAmount" style="width:234px"  class="easyui-validatebox"/></p>
		 <p><label>描述：</label><input id="txtNote" style="width:234px"  class="easyui-validatebox"/></p>
		 <p><label>是否需要钥匙：</label><input id="txtIsNeedKey" type="checkbox"/></p>
	 </div>
  </body>
</html>
