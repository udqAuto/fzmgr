<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script src='<%=basePath%>script/organ/organBill.js'
	type='text/javascript'></script> 
<title>组织机构信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page"> 
</head>
  
  <body>
   <div style="text-align:left;overflow:hidden;">
		 <p id="tb_operation" style="padding:2px;border-bottom:1px solid black;">
		 	<a id="btnSaveOrganInfo"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-add" plain="true">确定</a> 
			<a id="btnCancelSave"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-delete" plain="true">关闭</a> 
		 </p>
		 
		 <p><label>机构名称：</label><input id="txtOrganName" class="easyui-validatebox"  style="width:324px" /><input id="txtid" type="hidden"/></p>
		 <p><label>上级机构：</label><input id="txtParentName" class="easyui-validatebox"  style="width:324px" readonly="readonly" /></p>
		 <p><label>部门经理：</label><input id="txtUserName" type="text" class="easyui-combobox"  style="width:324px" /></p> 
		 <p><label>是否是洗车店：</label><input id="txtIsShop" type="checkbox" /></p> 
		 <p><label>描述：</label><input id="txtOrganDescription" class="easyui-validatebox"  style="width:324px"/></p>
	 </div>
  </body>
</html>
