<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script src='<%=basePath%>script/auto/autoBill.js'
	type='text/javascript'></script> 
<title>车辆信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page"> 
</head>
  
  <body>
   <div id="div_autoInfo" style="text-align:left;overflow:hidden;">
		 <p id="tb_operation" style="padding:2px;border-bottom:1px solid black;">
			<a id="btnCancelSave"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-close" plain="true">关闭</a> 
		 </p>
		 
		 <p><label>车主名称：</label><input id="txtAutoName" style="width:234px"  class="easyui-validatebox"   readonly="readonly"/></p>
		 <p><label>车牌号码：</label><input id="txtAutoPN" style="width:234px"  class="easyui-validatebox" readonly="readonly"/></p>
		 <p><label>车辆品牌：</label><input id="txtAutoBrand" style="width:234px"  class="easyui-validatebox"  readonly="readonly"/></p>
		 <p><label>车辆型号：</label><input id="txtAutoModel" style="width:234px"  class="easyui-validatebox" readonly="readonly"/></p>
		 <p><label>特征颜色：</label><input id="txtAutoColor" style="width:234px"  class="easyui-validatebox" readonly="readonly"/></p>
		 <p><label>所属区域：</label><input id="txtAutoRegion" style="width:234px"  class="easyui-validatebox"  readonly="readonly"/></p>
	 </div>
  </body>
</html>
