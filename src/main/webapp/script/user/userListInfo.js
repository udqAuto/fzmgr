$(function() {
	getCurrentUser();
	var obj = getUrlArgs();
	m_userType = obj.userType;
	m_user_query.orgId = 1;
	m_user_query.regionId = 1;
	if(m_userType == 8||m_userType =="8"){
		$("#userTb a[doc='systemUser']").attr("style","display:none");
		UserManage.loadCustomerList();
	} 
	else{
		$("#userTb a[doc='autoUser']").attr("style","display:none");
		UserManage.loadUserList();
	}
	UserManage.initOrgan();
	UserManage.initRegion();
	
	$("#addUser").bind("click", UserManage.addUser);
	$("#editUser").bind("click", UserManage.editUser);
	$("#delUser").bind("click", UserManage.delUser);
	$("#unlockUser").bind("click", UserManage.unLockUser);
	$("#lockUser").bind("click", UserManage.lockUser);
	$("#showUser").bind("click", UserManage.showUser);
});
var m_user_query={};
var m_userType;
var m_userInfo_dlg = null;
var m_userInfo_Object = {};
var UserManage = {
		initOrgan:function(){
			$("#txtcmbOrgan").combotree({
				url:'organ/getOrganList.do?parentid=0',
			});
		},
		initRegion:function(){
		$("#txtcmbRegion").combotree({
			url:'region/getRegionList.do?parentid=0',
		});
	},
	packageObject : function(row) {
		m_userInfo_Object.id = row.id;
		m_userInfo_Object.name = row.name;
		m_userInfo_Object.sex = row.sex;
		m_userInfo_Object.mobile = row.mobile;
		m_userInfo_Object.orgId = row.orgId;
		m_userInfo_Object.email = row.email;
		m_userInfo_Object.userType = row.userType;
		m_userInfo_Object.idcard = row.idcard;
		m_userInfo_Object.photoUrl = row.photoUrl;
		m_userInfo_Object.washCount = row.washCount;
		m_userInfo_Object.totalAmount = row.totalAmount;
		m_userInfo_Object.balance = row.balance;
		m_userInfo_Object.registerTime = row.registerTime;
		m_userInfo_Object.note = row.note;
	},
	loadUserList : function() {
		$('#userListGrid').datagrid({
			url : 'user/getUserList.do?userType='+m_userType,
			queryParams : {
				'userInfo' : JSON.stringify(m_user_query)
			},
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
				title : '照片',
				field : 'photoUrl',
				align : 'center',
				//width : 150,
				formatter : function(value, rowData, index) {
					return "<img style='width:30px;height:30px;' src='"+rowData.photoUrl+"'/>";
				}
			},{
				title : '用户名称',
				field : 'name',
				align : 'center',
				width : 150
			}, {
				title : '机构名称',
				field : 'orgName',
				align : 'center',
				width : 150
			}, {
				title : '用户类型',
				field : 'userType',
				align : 'center',
				width : 150,
				formatter : function(value, rowData, index) {
					if (value == 1 || value == "1") {
						return "系统管理员";
					} else if (value == 2 || value == "2") {
						return "公司员工";
					} else if (value == 4 || value == "4"){
						return "洗车工";
					} else{
						return "车主";
					}
				}
			}, {
				title : '性别',
				field : 'sex',
				align : 'center',
				width : 150,
				formatter : function(value, rowData, index) {
					if (value == 1 || value == "1") {
						return "男";
					} else if (value == 2 || value == "2") {
						return "女";
					} else {
						return "保密";
					}
				}
			}, {
				title : '注册时间',
				field : 'registerTime',
				align : 'center',
				width : 150
			}, {
				title : '身份证',
				field : 'idcard',
				align : 'center',
				width : 100
			}, {
				title : '电话',
				field : 'mobile',
				align : 'center',
				width : 100
			}, {
				title : '余额',
				field : 'balance',
				align : 'center',
				width : 100
			}] ]
		});
	},
	loadCustomerList : function() {
		$('#userListGrid').datagrid({
			url : 'user/getCustomerList.do?userType='+m_userType,
			queryParams : {
				'userInfo' : JSON.stringify(m_user_query)
			},
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
				width : 100
			}, {
				title : '电话',
				field : 'mobile',
				align : 'center',
				width : 100
			}, {
				title : '车辆数',
				field : 'autoCount',
				align : 'center',
				width : 100
			}, {
				title : '车辆所在小区',
				field : 'regionName',
				align : 'center',
				width : 150
			}, {
				title : '洗车次数',
				field : 'washCount',
				align : 'center',
				width : 100
			}, {
				title : '消费金额',
				field : 'totalAmount',
				align : 'center',
				width : 100
			}, {
				title : '余额',
				field : 'balance',
				align : 'center',
				width : 100
			}, {
				title : '注册时间',
				field : 'registerTime',
				align : 'center',
				width : 150
			}, {
				title : '备注',
				field : 'note',
				align : 'center',
				width : 100
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
								+ "' style='width:310px;height:400px;overflow:hidden'/>",
						lock : true,
						initFn : function() {
						}
					});
		} catch (ex) {
			$.messager.alert("操作提示", ex.message, "error");
		}
	},
	doSearch:function(){
//		if($("#txtcmbOrgan").combotree("getValue") ==""|| $("#txtcmbOrgan").combotree("getValue")==undefined){
//			m_user_query.orgId = 1;
//		}else{
//			m_user_query.orgId = $("#txtcmbOrgan").combotree("getValue");
//		}
		if($("#txtcmbRegion").combotree("getValue") ==""|| $("#txtcmbRegion").combotree("getValue")==undefined){
		m_user_query.regionId = 1;
	}else{
		m_user_query.regionId = $("#txtcmbRegion").combotree("getValue");
	}
		m_user_query.name = $('#sch_name').val();
		m_user_query.mobile = $('#sch_mobile').val();
		m_user_query.startTime = $("#sch_startTime").datebox("getValue");
		m_user_query.endTime = $("#sch_endTime").datebox("getValue");
		$('#userListGrid').datagrid("reload",{'userInfo' : JSON.stringify(m_user_query)});
	},
	doClean:function(){
		$('#sch_name').val("");
		$('#sch_mobile').val("");
		$("#sch_startTime").datebox("setValue","");
		$("#sch_endTime").datebox("setValue","");
		$("#txtcmbRegion").combotree("setValue","");
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
								+ "' style='width:710px;height:350px;overflow:hidden'/>",
						lock : true,
						initFn : function() {
						}
					});
		} catch (ex) {
			$.messager.alert("操作提示", ex.message, "error");
		}
	},
	editUser : function() {
		if(m_userType == 8||m_userType=="8"){
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
							id : 'dlgEditUser',
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
