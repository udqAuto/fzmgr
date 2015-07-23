$(function() {  
	RegionManage.loadRegionList();
	$("#addRegion").bind("click", RegionManage.addRegion);
	$("#editRegion").bind("click", RegionManage.editRegion);
	$("#delRegion").bind("click", RegionManage.delRegion); 
}); 
var region_obj={};
var RegionManage = {
		loadRegionList:function(){
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
			try{
				var target = $("#RegionTree").treegrid("getSelected");
				if (!target || target.length == 0) {
					$.messager.alert('操作提示', "请选择机构作为父级节点!", "warning");
					return;
				}
				var parentId = target.id;
				var parentName = target.name;
			m_RegionInfo_dlg = art.dialog({
				id : 'dlgAddRegion',
				title : '新增机构区域',
				content : "<iframe scrolling='yes' frameborder='0' src='view/region/regionBill.jsp?type=0&parentId="
					+parentId+"&parentName="
					+parentName+"&regionId=0' style='width:600px;height:470px;overflow:hidden'/>",
				lock : true,
				initFn : function() {
				}
				});
			} catch (ex) {
				$.messager.alert("操作提示",ex.message,"error"); 
			}
		},
		editRegion:function(){
			try{
				var node = $("#RegionTree").treegrid("getSelected");
				var nodes = $('#RegionTree').treegrid('getParent', node.id);

				if (!node || node.length == 0) {
					$.messager.alert('操作提示', "请选择要操作的机构节点!", "warning");
					return;
				}
				var parentName ="";
				if(nodes==undefined){
					parentName = "";
				}else{
					parentName =nodes.name;
				}
				var regionId = node.id;
				
				region_obj.name = node.name;
				region_obj.address = node.address;
				region_obj.isEstate = node.isEstate;
				region_obj.shopId = node.shopId;
				
				
			m_RegionInfo_dlg = art.dialog({
				id : 'dlgEditRegion',
				title : '编辑机构区域',
				content : "<iframe scrolling='yes' frameborder='0' src='view/region/regionBill.jsp?type=1&parentId=0&parentName="
					+parentName+"&regionId="+regionId+"' style='width:600px;height:470px;overflow:hidden'/>",
				lock : true,
				initFn : function() {
				}
				});
			} catch (ex) {
				$.messager.alert("操作提示",ex.message,"error"); 
			}
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
			
		},
		closeDialog : function() {
			m_RegionInfo_dlg.close();
			$('#RegionTree').treegrid("reload");
		}
};