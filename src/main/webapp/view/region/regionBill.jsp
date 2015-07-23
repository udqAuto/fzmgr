<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script src='<%=basePath%>script/region/regionBill.js'
	type='text/javascript'></script> 
<title>机构区域信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page"> 
</head>
  
  <body>
   <div style="text-align:left;overflow:hidden;">
		 <p id="tb_operation" style="padding:2px;border-bottom:1px solid black;">
		 	<a id="btnSaveRegionInfo"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-save" plain="true">保存</a> 
			<a id="btnCancelSave"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-close" plain="true">关闭</a> 
		 </p> 
		 <p><label>上级区域：</label><input id="txtParentName" class="easyui-validatebox"  style="width:234px" readonly="readonly"/></p>
		 <p><label>区域名称：</label><input id="txtRegionName" class="easyui-validatebox"  style="width:234px" /></p>
		 <p><label>关联门店：</label><input id="txtShopName" type="text" class="easyui-combotree"  style="width:234px" /></p> 
		 <p><label>关联小区：</label><input id="txtIsEstate" type="checkbox" onclick="RegionManage.onCheckState();" /></p>
		 <p name='p_estate'><label>小区地址：</label><input id="txtRegionAddress" class="easyui-validatebox"  style="width:234px" /></p> 
	 </div>
  </body>
</html>
