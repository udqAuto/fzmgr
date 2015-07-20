$(function() { 
	$("#txtusername").keydown(function(e) {
		if (e.keyCode == 13) {
			var username = $("#txtusername").val();
			var password = $("#txtpassword").val();
			if(username==""||username.length==0){
				$.messager.alert("系统提示","请输入用户名和密码","error")
				return;
			}
			if(password==""||password.length==0){
				$.messager.alert("系统提示","请输入用户密码","error")
				return;
			}
			$("#submitform").submit();
		}
	});
	$("#txtpassword").keydown(function(e) {
		if (e.keyCode == 13) {
			var username = $("#txtusername").val();
			var password = $("#txtpassword").val();
			if(username==""||username.length==0){
				$.messager.alert("系统提示","请输入用户名和密码","error")
				return;
			}
			if(password==""||password.length==0){
				$.messager.alert("系统提示","请输入用户密码","error")
				return;
			}
			$("#submitform").submit();
		}
	});
});