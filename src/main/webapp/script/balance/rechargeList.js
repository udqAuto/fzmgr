var m_userType;
var m_user_query={};
$(function() {
	m_user_query.orgId = 1;
	m_user_query.regionId=1;
	UserManage.loadCustomerList();
	
	$("#recharge").bind("click", UserManage.toRecharge);
});
var UserManage = {
	loadCustomerList : function() {
		$('#userListGrid').datagrid({
			url : 'user/getCustomerList.do?userType=8',
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
			onDblClickRow : UserManage.toRecharge,
			toolbar : "#rechargeTb",
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
	toRecharge : function(){
		try {
			var hasRows = $('#userListGrid').datagrid('getRows');
			if (hasRows.length == 0) {
				$.messager.alert('操作提示', "没有可操作数据", "warning");
				return;
			}
			var target = $("#userListGrid").datagrid("getChecked");
			if (!target || target.length == 0) {
				$.messager.alert('操作提示', "请选择充值用户!", "warning");
				return;
			}
			var userId = target[0].id;
			var userName = target[0].name;
			m_recharge_dlg = art
					.dialog({
						id : 'dlgRecharge',
						title : '余额充值',
						content : "<iframe scrolling='no' frameborder='0' src='view/balance/recharge.jsp?userId="+userId+
						"&userName="+userName+
						"' style='width:310px;height:300px;overflow:hidden'/>",
						lock : true,
						initFn : function() {
						}
					});
		} catch (ex) {
			$.messager.alert("操作提示", ex.message, "error");
		}
	},
	doSearch:function(){
		m_user_query.name = $('#sch_name').val();
		m_user_query.mobile = $('#sch_mobile').val();
		$('#userListGrid').datagrid("reload",{'userInfo' : JSON.stringify(m_user_query)});
	},
	doClean:function(){
		$('#sch_name').val("");
		$('#sch_mobile').val("");
	},
	
	closeDialog : function() {
		m_recharge_dlg.close();
		$('#userListGrid').datagrid("reload");
	}
};
