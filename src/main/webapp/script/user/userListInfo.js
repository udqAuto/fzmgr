$(function() {
	getCurrentUser();
	var obj = getUrlArgs();
	m_userType = obj.userType;
	if(m_userType == 0||m_userType =="0"){
		$("#userTb a[doc='systemUser']").attr("style","display:none");
	} 
	else{
		$("#userTb a[doc='autoUser']").attr("style","display:none");
	}
 
	
	UserManage.loadUserList();
	$("#addUser").bind("click", UserManage.addUser);
	$("#editUser").bind("click", UserManage.editUser);
	$("#delUser").bind("click", UserManage.delUser);
	$("#unlockUser").bind("click", UserManage.unLockUser);
	$("#lockUser").bind("click", UserManage.lockUser);
	$("#showUser").bind("click", UserManage.showUser);
});
var m_userType;
var m_userInfo_dlg = null;
var m_userInfo_Object = {};
var UserManage = {
	packageObject : function(row) {
		m_userInfo_Object.id = row.id;
		m_userInfo_Object.name = row.name;
		m_userInfo_Object.sex = row.sex;
		m_userInfo_Object.mobile = row.mobile;
		m_userInfo_Object.orgId = row.orgId;
		m_userInfo_Object.email = row.email;
	},
	loadUserList : function() {
		$('#userListGrid').datagrid({
			url : 'user/getUserList.do?userType=' + m_userType,
			fitColumns : true,
			rownumbers : true,
			pagination : true,
			pageNumber : 1,
			pageSize : 10,
			nowrap : false,
			idField : 'id',
			singleSelect : true,
			onDblClickRow : UserManage.editUser,
			toolbar : "#userTb",
			columns : [ [ {
				title : 'id',
				field : 'id',
				hidden : true
			}, {
				title : '用户名称',
				field : 'name',
				align : 'center',
				width : 150
			}, {
				title : '性别',
				field : 'sex',
				align : 'center',
				width : 150,
				formatter : function(value, rowData, index) {
					if (value == 0 || value == "0") {
						return "男";
					} else if (value == 1 || value == "1") {
						return "女";
					} else {
						return "保密";
					}
				}
			}, {
				title : '电话',
				field : 'mobile',
				align : 'center',
				width : 50
			}, {
				title : '机构名称',
				field : 'orgName',
				align : 'center',
				width : 50
			}, {
				title : '邮箱',
				field : 'email',
				align : 'center',
				width : 150
			} ] ]
		});
	},
	addUser : function() {
		try {
			m_userInfo_dlg = art
					.dialog({
						id : 'dlgAddUser',
						title : '新增用户',
						content : "<iframe scrolling='no' frameborder='0' src='view/user/userBill.jsp?type=0&userId=0&userType="
								+ m_userType
								+ "' style='width:310px;height:350px;overflow:hidden'/>",
						lock : true,
						initFn : function() {
						}
					});
		} catch (ex) {
			$.messager.alert("操作提示", ex.message, "error");
		}
	},
	showUser : function(){	
		try {
			var hasRows = $('#userListGrid').datagrid('getRows');
			if (hasRows.length == 0) {
				$.messager.alert('操作提示', "没有可操作数据", "warning");
				return;
			}
			var target = $("#userListGrid").datagrid("getChecked");
			if (!target || target.length == 0) {
				$.messager.alert('操作提示', "请选择操作项!", "warning");
				return;
			}
			if (target.length > 1) {
				$.messager.alert('操作提示', "只能选择单个操作项!", "warning");
				return;
			}
	
			UserManage.packageObject(target[0]);
	
			var userId = target[0].id;
	
			m_userInfo_dlg = art
					.dialog({
						id : 'dlgAddUser',
						title : '车主信息查看',
						content : "<iframe scrolling='no' frameborder='0' src='view/user/userBill.jsp?type=1&userId="
								+ userId
								+ "&userType="
								+ m_userType
								+ "' style='width:710px;height:270px;overflow:hidden'/>",
						lock : true,
						initFn : function() {
						}
					});
		} catch (ex) {
			$.messager.alert("操作提示", ex.message, "error");
		}
	},
	editUser : function() {
		if(m_userType == 0||m_userType=="0"){
			UserManage.showUser();
		}else{
			try {
				var hasRows = $('#userListGrid').datagrid('getRows');
				if (hasRows.length == 0) {
					$.messager.alert('操作提示', "没有可操作数据", "warning");
					return;
				}
				var target = $("#userListGrid").datagrid("getChecked");
				if (!target || target.length == 0) {
					$.messager.alert('操作提示', "请选择操作项!", "warning");
					return;
				}
				if (target.length > 1) {
					$.messager.alert('操作提示', "只能选择单个操作项!", "warning");
					return;
				}
				UserManage.packageObject(target[0]);
	
				var userId = target[0].id;
	
				m_userInfo_dlg = art
						.dialog({
							id : 'dlgAddUser',
							title : '编辑用户',
							content : "<iframe scrolling='no' frameborder='0' src='view/user/userBill.jsp?type=1&userId="
									+ userId
									+ "&userType="
									+ m_userType
									+ "' style='width:310px;height:350px;overflow:hidden'/>",
							lock : true,
							initFn : function() {
							}
						});
			} catch (ex) {
				$.messager.alert("操作提示", ex.message, "error");
			}
		}
	},

	delUser : function() {
		try{
			var dataRows = $('#userListGrid').datagrid('getRows');
			if (dataRows.length == 0) {
				$.messager.alert('操作提示', "没有可操作数据", "warning");
				return;
			}
			var dataTarget = $("#userListGrid").datagrid("getChecked");
			if (!dataTarget || dataTarget.length == 0) {
				$.messager.alert('操作提示', "请选择操作项!", "warning");
				return;
			}
			var userId = dataTarget[0].id;
			var userName = dataTarget[0].name;
			if(dataTarget != null){
				$.messager.confirm("删除确认", "确认删除用户 ["+userName+"]？", function(r) {
					if (r) {
						UserManage.delUserAction(userId);
					}
				});
			}
		}catch (ex){
			$.messager.alert("操作提示", ex.message, "error");
		}
	},
	delUserAction : function(userId) {
		$.ajax({
    		url :  "user/deleteUser.do?Id="+userId,
    		type : "POST",
    		dataType : "json",
    		async : false,
    		success : function(req) {
    			if (req.isSuccess) {
    				$('#userListGrid').datagrid("reload");
    			} else {
    				$.messager.alert('删除失败ʾ', req.msg, "warning");
    			}
    		}
    	});
	},
	lockUser : function() {
		changeUserStateAction(userId, 0);
	},
	unLockUser : function() {
		changeUserStateAction(userId, 1);
	},
	changeUserStateAction : function(userId, states) {

	},
	closeDialog : function() {
		m_userInfo_dlg.close();
		$('#userListGrid').datagrid("reload");
	}
};
