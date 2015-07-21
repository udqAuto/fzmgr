$(function() {  
	OrganManage.loadOrganList();
	$("#AddOrganization").bind("click", OrganManage.addOrganization);
	$("#EditOrganization").bind("click", OrganManage.EditOrganization);
	$("#DelOrganization").bind("click", OrganManage.DelOrganization); 
}); 
var m_OrganInfo_dlg = null;
var OrganManage = {
		loadOrganList:function(){
			$('#OrganizationTree').treegrid({
		    	url: 'organ/getOrganList.do?parentid=0', 
		        rownumbers: true, 
		        idField: 'id',
		        treeField: 'name',
		        toolbar:'#OrganizationTb',
		        columns: [[
		               { title: 'id', field: 'id', align: 'left', width: 200,hidden:true } ,
		               { title: '组织机构名称', field: 'name', align: 'left', width: 300 } 
		        ]]
		    });
		},
		addOrganization:function(){
			try{
			m_OrganInfo_dlg = art.dialog({
				id : 'dlgAddOrgan',
				title : '新增组织机构',
				content : "<iframe scrolling='yes' frameborder='0' src='view/organ/organBill.jsp?type=0' style='width:600px;height:470px;overflow:hidden'/>",
				lock : true,
				initFn : function() {
				}
				});
			} catch (ex) {
				$.messager.alert("操作提示",ex.message,"error"); 
			}
		},
		editOrganization:function(){
			
		},
		delOrganization:function(){
			$.messager.confirm("删除确认", "确认删除该组织机构 ？", function(r) {
				if (r) {
					$.messager.confirm("删除确认", "确认删除当前组织结构 ？", function(r) {
						if (r) {
							OrganManage.delOrganizationAction(orgId);
						}
					}); 
				}
			});
		},
		delOrganizationAction:function(orgId){
			
		}
};