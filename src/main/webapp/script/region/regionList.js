$(function() {  
	RegionManage.loadOrganList();
	$("#addRegion").bind("click", RegionManage.addRegion);
	$("#EditRegion").bind("click", RegionManage.EditRegion);
	$("#DelRegion").bind("click", RegionManage.DelRegion); 
}); 

var RegionManage = {
		loadOrganList:function(){
			$('#RegionTree').treegrid({
		    	url: 'region/getRegionList.do?parentid=0', 
		        rownumbers: true, 
		        idField: 'id',
		        treeField: 'name',
		        toolbar:'#regionTb',
		        columns: [[
		               { title: 'id', field: 'id', align: 'left', width: 200,hidden:true } ,
		               { title: '区域名称', field: 'name', align: 'left', width: 300 } 
		        ]]
		    });
		},
		addRegion:function(){
			
		},
		EditRegion:function(){
			
		},
		DelRegion:function(){
			$.messager.confirm("删除确认", "确认删除该区域？", function(r) {
				if (r) {
					$.messager.confirm("删除确认", "确认删除当前区域 ？", function(r) {
						if (r) {
							RegionManage.delRegionAction(orgId);
						}
					}); 
				}
			});
		},
		delRegionAction:function(orgId){
			
		}
};