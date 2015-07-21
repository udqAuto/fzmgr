var u_obj={};
$(function() { 
	$("#btnSaveUserInfo").bind("click", UserManage.saveUser);
	$("#btnCancelSave").bind("click", UserManage.closeWindow);
});
var UserManage = {
		saveUser:function(){
			$.messager.alert("操作提示","保存用户信息","info");
			var username = $.trim($("#txtUserName").val());//需要验证是否重复
			if (username == "" || username == undefined) {
				$.messager.alert("操作提示", "用户姓名必须填写！", "error");
				return;
			}
			
			var password = $.trim($("#txtUserPwd").val());
			if (password == "" || password == undefined) {
				$.messager.alert("操作提示", "登录密码必须填写！", "error");
				return;
			}
			
			var password2 = $.trim($("#txtUserPwdAgain").val());
			if (password2 == "" || password2 == undefined) {
				$.messager.alert("操作提示", "请再次输入密码！", "error");
				return;
			}
			if(password2 != password){
				$.messager.alert("操作提示", "两次输入的密码不同！", "error");
				return;
			}

			if ($("#txtUserSex").combobox("getValue") == ""
				|| $("#txtUserSex").combobox("getValue") == undefined) {
			    u_obj.sex = 1;
		    } else {
			    u_obj.sex = $("#txtUserSex").combobox("getValue");
		    }
			
			if ($("#txtUserType").combobox("getValue") == ""
				|| $("#txtUserSex").combobox("getValue") == undefined) {
			    u_obj.type = 1;
		    } else {
			    u_obj.type = $("#txtUserType").combobox("getValue");
		    }
			
			u_obj.name = username;
			u_obj.pwd = password;
			u_obj.mobile = $.trim($("#txtUserMobile").val());
			u_obj.email = $.trim($("#txtUserEmail").val());
			//u_obj.org = $.trim($("#txtUserOrg").val());
			
			$.ajax({
				url : "user/saveUserObj.do",
				type : "POST",
				dataType : "json",
				async : false,
				data : u_obj,
				success : function(req) {
					if (req.isSuccess) {
						parent.CustUserManage.SearchAction();
						parent.m_custuser_dlg.close();
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
		closeWindow:function(){
			parent.m_userInfo_dlg.close();
		}
};
