<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<script type="text/javascript">
	$(function() {
		$("#userGrid").empty();
		UserManage.loadData();
	});
	var UserManage = {
		loadData : function() {
			$('#UserGrid').datagrid({
				url : 'index/getUserList.do',
				queryParams : {
					'user_query' : JSON.stringify(m_user_query)
				},
				fitColumns : true,
				rownumbers : true,
				resizable : true,
				pagination : true,
				pageNumber : 1,
				pageSize : 10,
				nowrap : false,
				idField : 'id',
				singleSelect : true,
				//onDblClickRow : UserManage.ShowCustUserAction,
				//toolbar : "#custUserTb",
				columns : [ [ {
					title : 'id',
					field : 'id',
					width : 5,
					hidden : true
				}, {
					title : '姓名',
					field : 'username',
					width : 150
				}, {
					title : '性别',
					field : 'sex',
					width : 150,
					formatter : function(value, rowData, index) {
						if (value == 0 || value == "0") {
							return "男";
						} else {
							return "女";
						}
					}
				}, {
					title : '电话',
					field : 'tel',
					width : 150,
				}, {
					title : '地址',
					field : 'address',
					width : 150,
				} ]]
			});
		}
	}
</script>

<div id="userGrid">userListInfo</div>

