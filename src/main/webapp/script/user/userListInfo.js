$(function() { 
	UserManage.loadUserList();
	$("#addUser").bind("click", UserManage.addUser);
	$("#editUser").bind("click", UserManage.editUser);
	$("#delUser").bind("click", UserManage.delUser);
	$("#unlockUser").bind("click", UserManage.unLockUser);
	$("#lockUser").bind("click", UserManage.lockUser);
}); 
var m_userInfo_dlg = null;
var UserManage = { 
	loadUserList:function(){
		$('#userListGrid').datagrid({
			url : 'user/getUserList.do',
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
				formatter:function(value,rowData,index){
					if(value==1||value=="1"){
						return "男";
					}else if(value==2||value=="2"){
						return "女";
					}else{
						return "男";
					}
				}
			} ] ]
		});
	},
	addUser:function(){try {
		m_userInfo_dlg = art.dialog({
				id : 'dlgAddUser',
				title : '新增用户',
				content : "<iframe scrolling='yes' frameborder='0' src='view/user/userBill.jsp?type=0' style='width:600px;height:470px;overflow:hidden'/>",
				lock : true,
				initFn : function() {
				}
			});
		} catch (ex) {
			$.messager.alert("操作提示",ex.message,"error"); 
		}
	}, 
	editUser:function(){
		$.messager.alert("操作提示","编辑用户","info");
	},
	delUser:function(){ 
		$.messager.confirm("删除确认", "确认删除用户 ？", function(r) {
			if (r) {
				delUserAction(userId);
			}
		});
	},
	delUserAction:function(userId){
		
	},
	lockUser:function(){
		changeUserStateAction(userId,0);
	},
	unLockUser:function(){
		changeUserStateAction(userId,1);
	},
	changeUserStateAction:function(userId,states){
		
	}
};