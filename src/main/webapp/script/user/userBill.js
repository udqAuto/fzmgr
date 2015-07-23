var u_obj={};
var m_type;
var m_userId;
var m_userType;
var score = parent.m_userInfo_Object;
$(function() { 
	
	var args = getUrlArgs();
	m_userId = args.userId;
	m_type = args.type;
	m_userType = args.userType;
	UserManage.initOrgan();
	
	//编辑
	if(m_type==1||m_type=="1"){
		$("#txtUserName").val(score.name);
		$("#txtUserSex").combobox('setValue', score.sex);
		$("#txtUserMobile").val(score.mobile);
		$("#txtUserOrgName").combotree('setValue', score.orgId);
		$("#txtUserEmail").val(score.email);
		$("#div_userInfo p[name='p_pwd']").hide();
		if(m_userType=="0"||m_userType==0){
			$("#div_userInfo a[doc='systemUser']").attr("style","display:none");
			$("#txtUserSex").combobox('disable');
			$("#txtUserOrgName").combotree('disable');
			$("#txtUserName").attr("readonly","readonly");
			$("#txtUserMobile").attr("readonly","readonly");
			$("#txtUserEmail").attr("readonly","readonly");
		}
	}
	
	
	$("#btnSaveUserInfo").bind("click", UserManage.saveUser);
	$("#btnCancelSave").bind("click", UserManage.closeWindow);
});
function a (){
	$("#txtUserOrgName").combotree('setValue', score.orgId);
}
var UserManage = {
		initOrgan:function(){
			$("#txtUserOrgName").combotree({
				url:'organ/getOrganList.do?parentid=0',
				onLoadSuccess:a
			});
		},
		
		saveUser:function(){
			u_obj.id = m_userId;
			u_obj.userType = m_userType;
			var username = $.trim($("#txtUserName").val());//需要验证是否重复
			if (username == "" || username == undefined) {
				$.messager.alert("操作提示", "用户姓名必须填写！", "error");
				return;
			}
			if(m_type==0||m_type=="0"){
				var password = $.trim($("#txtUserPwd").val());
				var password2 = $.trim($("#txtUserPwdAgain").val());
				if (password == "" || password == undefined) {
					$.messager.alert("操作提示", "登录密码必须填写！", "error");
					return;
				}
				if (password2 == "" || password2 == undefined) {
					$.messager.alert("操作提示", "请再次输入密码！", "error");
					return;
				}
				if(password2 != password){
					$.messager.alert("操作提示", "两次输入的密码不同！", "error");
					return;
				}
			}
			if ($("#txtUserSex").combobox("getValue") == ""
				|| $("#txtUserSex").combobox("getValue") == undefined) {
			    u_obj.sex = 1;
		    } else {
			    u_obj.sex = $("#txtUserSex").combobox("getValue");
		    }
			
			if ($("#txtUserOrgName").combotree("getValue") == ""
				|| $("#txtUserOrgName").combotree("getValue") == undefined) {
				$.messager.alert("操作提示", "机构不能为空！", "error");
				return;
		    } else {
			    u_obj.orgId = $("#txtUserOrgName").combotree("getValue");
		    }
			
			u_obj.name = username;
			u_obj.psd = password;
			u_obj.mobile = $.trim($("#txtUserMobile").val());
			u_obj.email = $.trim($("#txtUserEmail").val());
			
			$.ajax({
				url : "user/saveUserObj.do",
				type : "POST",
				dataType : "json",
				async : false,
				data : u_obj,
				success : function(req) {
					if (req.isSuccess) {
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
		closeWindow:function(){
			parent.m_userInfo_dlg.close();
		}
};
