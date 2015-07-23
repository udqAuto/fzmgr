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
		 	<a id="btnSaveOrganInfo"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-save" plain="true">保存</a> 
			<a id="btnCancelSave"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-close" plain="true">关闭</a> 
		 </p> 
		 <p><label>上级机构：</label><input id="txtParentName" style="width:234px"  class="easyui-validatebox"    readonly="readonly" /></p>
		 <p><label>机构名称：</label><input id="txtOrganName" style="width:234px"  class="easyui-validatebox" /></p>
		 <p><label>是否门店：</label><input id="txtIsShop" style="width:234px"  type="checkbox" onclick="OrganManage.onCheckShop();" /></p> 
		 <p name='p_isShop'><label>门店经理：</label><input style="width:234px"  id="txtUserName" type="text" class="easyui-combobox"    /></p> 
		 <p><label>描述说明：</label><input id="txtOrganDescription"  style="width:234px" class="easyui-validatebox" /></p>
	 </div>
  </body>
</html>
