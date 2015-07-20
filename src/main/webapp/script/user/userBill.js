$(function() { 
	$("#btnSaveUserInfo").bind("click", UserManage.saveUser);
	$("#btnCancelSave").bind("click", UserManage.closeWindow);
});
var UserManage = {
		saveUser:function(){
			$.messager.alert("操作提示","保存用户信息","info");
		},
		closeWindow:function(){
			parent.m_userInfo_dlg.close();
		}
};
