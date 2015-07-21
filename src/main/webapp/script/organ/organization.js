$(function() {  
	OrganManage.loadOrganList();
	$("#AddOrganization").bind("click", OrganManage.addOrganization);
	$("#EditOrganization").bind("click", OrganManage.EditOrganization);
	$("#DelOrganization").bind("click", OrganManage.DelOrganization); 
}); 

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