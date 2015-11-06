var m_recharge_info={};
$(function() { 
	var args = getUrlArgs();
	m_recharge_info.userId = args.userId;
	var userName = args.userName;
	$("#txtUserName").val(userName);
	$("#btnSave").bind("click", BalanceManage.recharge);
	$("#btnCancelSave").bind("click", BalanceManage.closeWindow);
});
var BalanceManage = {
		recharge : function(){
			var amount = $("#txtAmount").val();
			if(amount == ""||amount == undefined){
				$.messager.alert("操作提示", "请填写金额！", "error");
				return;
			}
			if(amount <= 0){
				$.messager.alert("操作提示", "充值金额不能小于0！", "error");
				return;
			}
			m_recharge_info.amount = amount;
			$.ajax({
				url : "userBalance/recharge.do",
				type : "POST",
				dataType : "json",
				async : false,
				data : m_recharge_info,
				success : function(req) {
					if (req.isSuccess) {
						//BalanceManage.closeWindow();
						//parent.UserManage.doSearch();
						parent.UserManage.closeDialog();
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
		closeWindow : function(){
			parent.m_recharge_dlg.close();
		}
};