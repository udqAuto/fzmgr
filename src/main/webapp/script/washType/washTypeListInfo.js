$(function() {
	getCurrentUser();
	WashTypeManage.loadWashTypeList();
	$("#addWashType").bind("click", WashTypeManage.addWashType);
	$("#editWashType").bind("click", WashTypeManage.editWashType);
	$("#delWashType").bind("click", WashTypeManage.delWashType);
});
var washType_obj = {};
var WashTypeManage = {
		packageObject : function(row){
			washType_obj.name = row.name;
			washType_obj.amount = row.amount;
			washType_obj.note = row.note;
			washType_obj.needKey = row.needKey;
		},
		loadWashTypeList : function(){
			$('#washTypeListGrid').datagrid({
				url : 'washType/getWashTypeList.do',
				fitColumns : true,
				rownumbers : true,
				pagination : true,
				pageNumber : 1,
				pageSize : 10,
				nowrap : false,
				idField : 'id',
				singleSelect : true,
				//onDblClickRow : ,
				toolbar : "#washTypeTb",
				columns : [ [ {
					title : 'id',
					field : 'id',
					hidden : true
				}, {
					title : '名称',
					field : 'name',
					align : 'center',
					width : 100
				}, {
					title : '价格',
					field : 'amount',
					align : 'center',
					width : 100,
				}, {
					title : '描述',
					field : 'note',
					align : 'center',
					width : 100
				}, {
					title : '是否需要钥匙',
					field : 'needKey',
					align : 'center',
					width : 100,formatter:function(value,rowData,index){
		            	   if(value){
		            		   return "是";
		            	   }else{
		            		   return "";
		            	   }
		               } 
				}] ]
			});
		},
		addWashType : function(){
			try{
			m_washTypeInfo_dlg = art.dialog({
				id : 'dlgAddWashType',
				title : '新增洗车类型',
				content : "<iframe scrolling='yes' frameborder='0' src='view/washType/washTypeBill.jsp?type=0&washTypeId=0' style='width:310px;height:350px;overflow:hidden'/>",
				lock : true,
				initFn : function() {
				}
				});
			} catch (ex) {
				$.messager.alert("操作提示",ex.message,"error"); 
			}
		},
		editWashType : function(){
			try{
				var target = $("#washTypeListGrid").datagrid("getChecked");
				if(!target || target.length == 0){
					$.messager.alert('操作提示', "请选择操作项!", "warning");
					return;
				}
				var washTypeId = target[0].id;
				WashTypeManage.packageObject(target[0]);
				m_washTypeInfo_dlg = art.dialog({
					id : 'dlgEditWashType',
					title : '编辑洗车类型',
					content : "<iframe scrolling='yes' frameborder='0' src='view/washType/washTypeBill.jsp?type=1&washTypeId="
						+washTypeId+"' style='width:310px;height:350px;overflow:hidden'/>",
					lock : true,
					initFn : function() {
					}
					});
				} catch (ex) {
					$.messager.alert("操作提示",ex.message,"error"); 
				}
		},
		delWashType : function(){
			try{
				var target = $("#washTypeListGrid").datagrid("getChecked");
				if(!target || target.length == 0){
					$.messager.alert('操作提示', "请选择操作项!", "warning");
					return;
				}
				var washTypeId = target[0].id;
				var washTypeName = target[0].name;
				if(target != null){
					$.messager.confirm("删除确认", "确认删除用户 ["+washTypeName+"]？", function(r) {
						if (r) {
							WashTypeManage.delWashTypeAction(washTypeId);
						}
					});
				}
			}catch(ex){
				$.messager.alert("操作提示",ex.message,"error");
			}
		},
		delWashTypeAction : function(washTypeId){
			$.ajax({
	    		url :  "washType/deleteWashType.do?Id="+washTypeId,
	    		type : "POST",
	    		dataType : "json",
	    		async : false,
	    		success : function(req) {
	    			if (req.isSuccess) {
	    				$('#washTypeListGrid').datagrid("reload");
	    			} else {
	    				$.messager.alert('删除失败ʾ', req.msg, "warning");
	    			}
	    		}
	    	});
		},
		closeDialog : function() {
			m_washTypeInfo_dlg.close();
			$('#washTypeListGrid').datagrid("reload");
		}
};