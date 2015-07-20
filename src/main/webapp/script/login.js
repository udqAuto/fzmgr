$(function() {
	var args = getUrlArgs();
	if (args.optType == 0 || args.optType == "0") {
		$.messager.alert("系统提示","用户名、用户密码不能为空","error");
	} else if (args.optType == 1 || args.optType == "1") {
		$.messager.alert("系统提示","密码不能为空","error");
		$.messager.alert("系统提示");
	} else if (args.optType == 2 || args.optType == "2") {
		$.messager.alert("系统提示","用户已被锁定，如需要登录，请联系管理员","warning");
	} else if (args.optType == 3 || args.optType == "3") {
		$.messager.alert("系统提示","未注册用户","info");
	} else if (args.optType == 4 || args.optType == "4") {
		$.messager.alert("系统提示","系统登录错误，请联系网站管理员","info");
	}
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