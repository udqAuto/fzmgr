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
	if(m_type==1||m_type=="1"){
		$("#txtUserName").val(score.name);
		$("#txtUserSex").combobox('setValue', score.sex);
		$("#txtUserIdcard").val(score.idcard);
		$("#txtUserMobile").val(score.mobile);
		$("#txtUserOrgName").combotree('setValue', score.orgId);
		$("#txtUserEmail").val(score.email);
		$("#txtUserPhoto").attr("src",score.photoUrl);
		$("#txtWashCount").val(score.washCount);
		$("#txtTotalAmount").val(score.totalAmount);
		$("#txtUserBalance").val(score.balance);
		$("#txtRegisterTime").val(score.registerTime);
		$("#txtNote").val(score.note);
		$("#div_userInfo p[name='p_total']").hide();
//		var pho = document.getElementById("txtUserPhoto");
//		pho.src = score.photoUrl;
		$("#div_userInfo p[name='p_pwd']").hide();
		if(m_userType=="8"||m_userType==8){
			$("#div_userInfo p[name='p_utype']").hide();
			$("#txtUserSex").combobox('disable');
			$("#txtUserOrgName").combotree('disable');
			$("#txtUserName").attr("readonly","readonly");
			$("#txtUserIdcard").attr("readonly","readonly");
			$("#txtUserMobile").attr("readonly","readonly");
			$("#txtUserEmail").attr("readonly","readonly");
			$("#txtRegisterTime").attr("readonly","readonly");
			$("#userAutoInfo").removeClass("displaynone");
			$("#div_userInfo").attr("style","width:45%;float:left;");
			//$("#btnSaveUserInfo").attr("style","display:none");
			$("#div_userInfo p[name='p_img']").hide();
			$("#div_userInfo p[name='p_upload']").hide();
			$("#div_userInfo p[name='p_info']").hide();
			$("#div_userInfo p[name='p_total']").show();
			UserManage.loadAutoByUserId(m_userId);
		}else{
			$("#txtUserType").combobox('setValue', score.userType);
			$("#div_userInfo p[name='p_time']").hide();
		}
	}else{
		$("#div_userInfo p[name='p_pwd']").hide();//不需要密码
		$("#div_userInfo p[name='p_img']").hide();
		$("#div_userInfo p[name='p_total']").hide();
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
		loadAutoByUserId:function(uId){
			$('#dgAuto').datagrid({
				url : 'auto/getAutoListByUserId.do?userId='+uId,
				fitColumns : true,
				rownumbers : true,
				pagination : false,  
				singleSelect : true,
				idField : 'id',  
				title :"拥有车辆",
				columns : [ [ {
					title : '车牌号码',
					field : 'pn',
					align : 'center',
					width : 100,
				},{
					title : '品牌',
					field : 'brand',
					align : 'center',
					width : 100
				}, {
					title : '型号',
					field : 'model',
					align : 'center',
					width : 100
				}, {
					title : '颜色',
					field : 'color',
					align : 'center',
					width : 100
				}, {
					title : '区域',
					field : 'regionName',
					align : 'center',
					width : 100
				} ] ]
			});
		},
		saveUser:function(){
			u_obj.id = m_userId;
			//u_obj.userType = m_userType;
			var username = $.trim($("#txtUserName").val());//需要验证是否重复
			if (username == "" || username == undefined) {
				$.messager.alert("操作提示", "用户姓名必须填写！", "error");
				return;
			}
//			if(m_type==0||m_type=="0"){
//				var password = $.trim($("#txtUserPwd").val());
//				var password2 = $.trim($("#txtUserPwdAgain").val());
//				if (password == "" || password == undefined) {
//					$.messager.alert("操作提示", "登录密码必须填写！", "error");
//					return;
//				}
//				if (password2 == "" || password2 == undefined) {
//					$.messager.alert("操作提示", "请再次输入密码！", "error");
//					return;
//				}
//				if(password2 != password){
//					$.messager.alert("操作提示", "两次输入的密码不同！", "error");
//					return;
//				}
//			}
			if ($("#txtUserSex").combobox("getValue") == ""
				|| $("#txtUserSex").combobox("getValue") == undefined) {
			    u_obj.sex = 0;
		    } else {
			    u_obj.sex = $("#txtUserSex").combobox("getValue");
		    }
			
			if(m_userType != 8){
				if ($("#txtUserOrgName").combotree("getValue") == ""
					|| $("#txtUserOrgName").combotree("getValue") == undefined) {
					$.messager.alert("操作提示", "机构不能为空！", "error");
					return;
			    } else {
				    u_obj.orgId = $("#txtUserOrgName").combotree("getValue");
			    }
				if ($("#txtUserType").combobox("getValue") == ""
					|| $("#txtUserType").combobox("getValue") == undefined) {
					$.messager.alert("操作提示", "请选择用户类型！", "error");
					return;
			    } else {
				    u_obj.userType = $("#txtUserType").combobox("getValue");
			    }
			}

			if ($("#txtUserMobile").val() == ""
				|| $("#txtUserMobile").val() == undefined) {
				$.messager.alert("操作提示", "请填写电话号码！", "error");
				return;
		    } else {
		    	u_obj.mobile = $.trim($("#txtUserMobile").val());
		    }
			u_obj.name = username;
			//u_obj.psd = password;
			u_obj.idcard = $.trim($("#txtUserIdcard").val());
			u_obj.email = $.trim($("#txtUserEmail").val());
			u_obj.note = $.trim($("#txtNote").val());
			
			var f = $("#file").val();  
			var extname = f.substring(f.lastIndexOf(".")+1,f.length);  
			extname = extname.toLowerCase();//处理了大小写   
		    if(m_type == 1||m_type =="1"){
				u_obj.photoUrl = score.photoUrl;
			}
//				var pho = document.getElementById("txtUserPhoto");
//				u_obj.photoUrl = pho.src;
			
//		        if(extname!= "jpeg"&&extname!= "jpg"&&extname!= "gif"&&extname!= "png"){  
//		        	$.messager.alert("操作提示", "图片格式不正确！", "error");  
//		           } 
//		    var file = document.getElementById("f").files;    
//		    var size = file[0].size;  
//		    if(size>2097152){ 
//		    	$.messager.alert("操作提示", "所选择的图片太大，图片大小最多支持2M！", "error"); 
//		      }  
			
//		    $("#loading").ajaxStart(function(){
//                $(this).show();
//            }).ajaxComplete(function(){
//                $(this).hide();
//            });
			$.ajaxFileUpload({
				url : "user/saveUserObj.do",
				secureuri:false,                       //是否启用安全提交,默认为false
				fileElementId:'file',
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
