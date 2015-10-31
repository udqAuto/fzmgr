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
		    <a id="btnSave" doc="systemUser"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-save" plain="true">保存</a> 
			<a id="btnCancelSave"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-close" plain="true">关闭</a> 
		 </p>
		 <p><h2>订单基本信息</h2></p>
		  <table style="font-size:12px;font-family:微软雅黑;width:100%;text-align:left;">
		 	<tr>
		 		<td>
		 			<label>订单编号：</label>
		 		</td>
		 		<td style="width:100px">
		 			<label id="txtOrderNo"></label>
		 		</td>
		 		<td>
		 			<label>订单状态：</label>
		 		</td>
		 		<td style="width:100px">
					<input id="txtOrderState" readonly="readonly" class="easyui-combobox" data-options="valueField:'id',textField:'name',data:[{id: 1,name: '已下单'},{id: 2,name: '已接收'},{id: 4,name: '已完成'},{id: 5,name: '已评价'}]"/>
		 		</td>
		 		<td>
		 			<label>洗车类型：</label>
		 		</td>
		 		<td>
		 			<label id="txtOrderType"></label>
		 		</td>
		 	</tr>
		 	<tr>
		 		<td><label>客户姓名：</label></td>
		 		<td><label id="txtCustomerName"></label></td>
		 		<td><label>联系电话：</label></td>
		 		<td><label id="txtCustomerMobile"></label></td>
		 		<td><label>车牌号码：</label></td>
		 		<td><label id="txtCustomerPN"></label></td>
		 	</tr>
		 	<tr>
		 		<td><label>所属小区：</label></td>
		 		<td><label id="txtCustomerRegion"></label></td>
		 		<td><label>下单时间：</label></td>
		 		<td><label id="txtBillTime"></label></td>
		 		<td><label>固定价格：</label></td>
		 		<td><label id="txtOrderPrice"></label></td> 
		 	</tr>
		 	<tr>
		 		<td><label>优惠券：</label></td>
		 		<td><label id="txtIsCoupon"></label></td>
		 		<td><label>优惠金额：</label></td>
		 		<td><label id="txtCouponAmount"></label></td>
		 		<td><label>结算价格：</label></td>
		 		<td colspan="4"><label id="txtOrderAmount"></label></td>
		 	</tr>
		 	<tr>
		 		<td><label>订单备注：</label></td>
		 		<td colspan="4"><label id="txtOrderNotes"></label></td>
		 	</tr> 
		 	<tr>
		 		<td><label>车辆位置：</label></td>
		 		<td colspan="4"><label id="txtAutoPostion"></label></td>
		 	</tr> 
		 </table>   
		 <p><h2>订单处理信息</h2></p>
		 <table style="font-size:12px;font-family:微软雅黑;width:100%;text-align:left;">
		 	<tr> 
		 		<td><label>接单员工：</label></td>
		 		<td style="width:100px"><label id="txtWasherName"></label></td> 
		 		<td><label>接单时间：</label></td>
		 		<td style="width:100px"><label id="txtAcceptTime"></label></td>
		 		<td></td>
		 		<td style="width:100px"></td>
		 	</tr>
		 	<tr>
		 		<td><label>开始时间：</label></td>
		 		<td><label id="txtBeginTime"></label></td>
		 		<td><label>结束时间：</label></td>
		 		<td><label id="txtEndTime"></label></td>
		 	</tr>
		 </table> 
		 <p><h2>订单评价信息</h2></p>
		 <table style="font-size:12px;font-family:微软雅黑;width:100%;text-align:left;">
		 	<tr>
		 		<td><label>客户评分：</label></td>
		 		<td style="width:100px"><label id="txtCustomerGrade"></label></td>
		 		<td><label>店员评分：</label></td>
		 		<td style="width:100px"><label id="txtWasherGrade"></label></td>
		 		<td></td>
		 		<td style="width:100px"></td>
		 	</tr>
		 </table> 
	 </div>
  </body>
</html>
