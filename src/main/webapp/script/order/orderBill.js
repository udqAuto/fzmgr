
var m_orderId ;
$(function() { 
	getCurrentUser();
	var obj = getUrlArgs();
	m_orderId = obj.orderId;
	OrderManage.loadOrderInfo();
	$("#btnCancelSave").bind("click", OrderManage.closeWindow);
}); 
var OrderManage = { 
		loadOrderInfo:function(){
			$.ajax({
				url : "order/getOrderObjById.do?id="+m_orderId,
				type : "POST",
				dataType : "json", 
				success : function(req) {
					if (req.isSuccess) {
						OrderManage.fillOrderBill(req.data);
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
		},
		fillOrderBill:function(data){
			 $("#txtOrderNo").text(data.orderNo);
			 var orderState ="";
			 if(data.state==1){
				 orderState = "客户下单，门店未接受";
			 }else if(data.state==2){
				 orderState = "门店接受，未开始洗车";
			 }else if(data.state==3){
				 orderState = "正在洗车";
			 }else if(data.state==4){
				 orderState = "洗车完毕";
			 }else if(data.state==10){
				 orderState = "门店取消订单";
			 }else if(data.state==2){
				 orderState = "客户取消订单";
			 }
			 $("#txtOrderState").text(orderState);
			 $("#txtOrderType").text(data.washerTypeName);
			 $("#txtCustomerName").text(data.customerName);
			 $("#txtCustomerMobile").text(data.customerMobile);
			 $("#txtCustomerPN").text(data.autoPN);
			 $("#txtCustomerRegion").text(data.regionName);
			 $("#txtBillTime").text(data.billTime);
			 $("#txtOrderTime").text(data.orderTime);
			 $("#txtOrderPrice").text(data.fixedAmount);
			 if(data.couponName ==undefined ||data.couponName == ""){
				 $("#txtIsCoupon").text("未使用优惠券");
				 $("#txtCouponAmount").text("0");
			 }else{
				 $("#txtIsCoupon").text(data.couponName);
				 $("#txtCouponAmount").text(data.couponAmount);
			 }
			 $("#txtOrderAmount").text(data.finalAmount);
			 $("#txtOrderNotes").text(data.userNote);
			 $("#txtWasherName").text(data.washerName);
			 $("#txtAcceptTime").text(data.acceptTime);
			 $("#txtBeginTime").text(data.beginTime);
			 $("#txtEndTime").text(data.finishTime);
			 $("#txtCustomerGrade").text(data.customerGrade);
			 $("#txtWasherGrade").text(data.washerGrade);
		},
		closeWindow : function(){
			parent.m_order_dlg.close();
		}
};