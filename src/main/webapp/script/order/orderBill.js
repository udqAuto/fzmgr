$(function() { 
	OrderManage.showOrderInfo();
	$("#btnCancelSave").bind("click", OrderManage.closeWindow);
});
var score = parent.order_obj;
var OrderManage = {
		showOrderInfo : function(){
			$("#txtOrderNo").val(score.orderNo);
			$("#txtUserName").val(score.customerName);
			$("#txtUserNote").val(score.userNote);
			$("#txtAutoPN").val(score.autoPN);
			$("#txtRegionName").val(score.regionName);
			$("#txtShopName").val(score.shopName);
			$("#txtPayType").val(score.payType);
			$("#txtOrderTime").val(score.orderTime);
			$("#txtBillTime").val(score.billTime);
			$("#txtWasherName").val(score.washerName);
			$("#txtWasherNote").val(score.washerNote);
			$("#txtStateNote").val(score.stateNote);
			$("#txtAcceptTime").val(score.acceptTime);
			$("#txtBeginTime").val(score.beginTime);
			$("#txtFinishTime").val(score.finishTime);
			$("#txtGradeUser").val(score.gradeUser);
			$("#txtGradeWasher").val(score.gradeWasher);
		},
		closeWindow : function(){
			parent.m_order_dlg.close();
		}
};