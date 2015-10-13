$(function() { 
	getCurrentUser();
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
				fitColumns : true,
		        idField: 'id',
		        treeField: 'name',
		        onDblClickRow : RegionManage.editRegion,
		        toolbar:'#regionTb',
		        columns: [[
		               { title: 'id', field: 'id', align: 'left', width: 200,hidden:true } ,
		               { title: '区域名称', field: 'name', align: 'left', width: 300 }, 
		               { title: '是否小区', field: 'isEstate', align: 'center', width: 100,formatter:function(value,rowData,index){
		            	   if(value){
		            		   return "是";
		            	   }else{
		            		   return "";
		            	   }
		               } } ,
		               { title: '小区地址', field: 'address', align: 'center', width: 200 } ,
		               { title: '门店名称', field: 'shopName', align: 'center', width: 150 }  
		        ]]
		    });
		},
		addRegion:function(){
			try{
				var target = $("#RegionTree").treegrid("getSelected");
				if (!target || target.length == 0) {
					$.messager.alert('操作提示', "请选择区域作为父级节点!", "warning");
					return;
				}
				var parentId = target.id;
				var parentName = target.name;
			m_RegionInfo_dlg = art.dialog({
				id : 'dlgAddRegion',
				title : '新增机构区域',
				content : "<iframe scrolling='no' frameborder='0' src='view/region/regionBill.jsp?type=0&parentId="
					+parentId+"&parentName="
					+parentName+"&regionId=0' style='width:310px;height:350px;overflow:hidden'/>",
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
					$.messager.alert('操作提示', "请选择要操作的区域节点!", "warning");
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
				content : "<iframe scrolling='no' frameborder='0' src='view/region/regionBill.jsp?type=1&parentId=0&parentName="
					+parentName+"&regionId="+regionId+"' style='width:310px;height:350px;overflow:hidden'/>",
				lock : true,
				initFn : function() {
				}
				});
			} catch (ex) {
				$.messager.alert("操作提示",ex.message,"error"); 
			}
		},
		delRegion:function(){
			var target = $("#RegionTree").treegrid("getSelected");
			if (!target || target.length == 0) {
				$.messager.alert('操作提示', "请选择机构作为父级节点!", "warning");
				return;
			}
			var regionId = target.id;
			$.messager.confirm("删除确认", "确认删除该区域？", function(r) {
				if (r) {
					RegionManage.delRegionAction(regionId);
				}
			});
		},
		delRegionAction:function(regionId){
			$.ajax({
	    		url :  "region/deleteRegion.do?Id="+regionId,
	    		type : "POST",
	    		dataType : "json",
	    		async : false,
	    		success : function(req) {
	    			if (req.isSuccess) {
	    				$('#RegionTree').treegrid("reload");
	    			} else {
	    				$.messager.alert('删除失败ʾ', req.msg, "warning");
	    			}
	    		}
	    	});
		},
		closeDialog : function() {
			m_RegionInfo_dlg.close();
			$('#RegionTree').treegrid("reload");
		}
};