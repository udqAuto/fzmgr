<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script src='<%=basePath%>script/order/orderBill.js'
	type='text/javascript'></script> 
<title>订单信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page"> 
</head>
  
  <body>
   <div id="div_orderInfo" style="text-align:left;overflow:hidden;">
		 <p id="tb_operation" style="padding:2px;border-bottom:1px solid black;">
			<a id="btnCancelSave"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-delete" plain="true">关闭</a> 
		 </p>
		 
		 <p><label>订单号：</label><input id="txtOrderNo" class="easyui-validatebox" readonly="readonly"/><input id="txtid" type="hidden"/></p>
		 <p><label>下单用户：</label><input id="txtUserName" class="easyui-validatebox"  readonly="readonly"/></p>
		 <p><label>用户备注：</label><input id="txtUserNote" class="easyui-validatebox" readonly="readonly"/></p>
		 <p><label>车牌号：</label><input id="txtAutoPN" class="easyui-validatebox" readonly="readonly"/></p>
		 <p><label>小区：</label><input id="txtRegionName" class="easyui-validatebox"  readonly="readonly"/></p>
		 <p><label>洗车店：</label><input id="txtShopName" class="easyui-validatebox"  readonly="readonly"/></p>
		 <p><label>支付类型：</label><input id="txtPayType" class="easyui-validatebox"  readonly="readonly"/></p>
		 <p><label>预约时间：</label><input id="txtOrderTime" class="easyui-validatebox" readonly="readonly"/></p>
		 <p><label>提交订单时间：</label><input id="txtBillTime" class="easyui-validatebox" readonly="readonly"/></p>
		 <p><label>管理员：</label><input id="txtWasherName" class="easyui-validatebox" readonly="readonly"/></p>
		 <p><label>管理员备注：</label><input id="txtWasherNote" class="easyui-validatebox" readonly="readonly"/></p>
		 <p><label>状态：</label><input id="txtStateNote" class="easyui-validatebox" readonly="readonly"/></p>
		 <p><label>接收订单时间：</label><input id="txtAcceptTime" class="easyui-validatebox" readonly="readonly"/></p>
		 <p><label>开始洗车时间：</label><input id="txtBeginTime" class="easyui-validatebox" readonly="readonly"/></p>
		 <p><label>完成时间：</label><input id="txtFinishTime" class="easyui-validatebox" readonly="readonly"/></p>
		 <p><label>客户评分：</label><input id="txtGradeUser" class="easyui-validatebox" readonly="readonly"/></p>
		 <p><label>洗车工队评分：</label><input id="txtGradeWasher" class="easyui-validatebox" readonly="readonly"/></p>
		 
	 </div>
  </body>
</html>
