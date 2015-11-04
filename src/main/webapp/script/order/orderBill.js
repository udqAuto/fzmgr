
var m_orderId ;
var m_userType;
$(function() { 
	getCurrentUser();
	var obj = getUrlArgs();
	m_userType = obj.userType;
	m_orderId = obj.orderId;
	if(m_userType != 1){
		$("#tb_operation a[doc='systemUser']").attr("style","display:none");
	}
	OrderManage.loadOrderInfo();
	$("#btnSave").bind("click", OrderManage.closeWindow);
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
			 $("#txtOrderState").combobox('setValue', data.state);
			 $("#txtOrderType").text(data.washTypeName);
			 $("#txtCustomerName").text(data.customerName);
			 $("#txtCustomerMobile").text(data.customerMobile);
			 $("#txtCustomerPN").text(data.autoPN);
			 $("#txtAutoPostion").text(data.autoPosition);
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