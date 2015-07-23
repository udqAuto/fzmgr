$(function() {  
	OrganManage.loadOrganList();
	$("#AddOrganization").bind("click", OrganManage.addOrganization);
	$("#EditOrganization").bind("click", OrganManage.editOrganization);
	$("#DelOrganization").bind("click", OrganManage.delOrganization); 
}); 
var m_OrganInfo_dlg = null;
var m_organ={};
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
				var target = $("#OrganizationTree").treegrid("getSelected");
				if (!target || target.length == 0) {
					$.messager.alert('操作提示', "请选择机构作为父级节点!", "warning");
					return;
				}
				var parentId = target.id;
				var parentName = target.name;
			m_OrganInfo_dlg = art.dialog({
				id : 'dlgAddOrgan',
				title : '新增组织机构',
				content : "<iframe scrolling='yes' frameborder='0' src='view/organ/organBill.jsp?type=0&parentId="
					+parentId+"&parentName="
					+parentName+"&orgId=0' style='width:600px;height:470px;overflow:hidden'/>",
				lock : true,
				initFn : function() {
				}
				});
			} catch (ex) {
				$.messager.alert("操作提示",ex.message,"error"); 
			}
		},
		editOrganization:function(){
			try{
				var node = $("#OrganizationTree").treegrid("getSelected");
				var nodes = $('#OrganizationTree').treegrid('getParent', node.id)

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
				var orgId = node.id;
				
				m_organ.name = node.name;
				m_organ.note = node.note;
				m_organ.isShop = node.isShop;
				m_organ.bmId = node.bmId;
				
				
			m_OrganInfo_dlg = art.dialog({
				id : 'dlgEditOrgan',
				title : '编辑组织机构',
				content : "<iframe scrolling='yes' frameborder='0' src='view/organ/organBill.jsp?type=1&parentId=0&parentName="
					+parentName+"&orgId="+orgId+"' style='width:600px;height:470px;overflow:hidden'/>",
				lock : true,
				initFn : function() {
				}
				});
			} catch (ex) {
				$.messager.alert("操作提示",ex.message,"error"); 
			}
		},
		delOrganization:function(){
			var node = $("#OrganizationTree").treegrid("getSelected");
			if (!node || node.length == 0) {
				$.messager.alert('操作提示', "请选择要操作的机构节点!", "warning");
				return;
			}
			var orgId = node.id;
			$.messager.confirm("删除确认", "确认删除该组织机构 ？", function(r) {
				if (r) {
					OrganManage.delOrganizationAction(orgId);
				}
			});
		},
		delOrganizationAction:function(orgId){
			$.ajax({
	    		url :  "organ/deleteOrgan.do?Id="+orgId,
	    		type : "POST",
	    		dataType : "json",
	    		async : false,
	    		success : function(req) {
	    			if (req.isSuccess) {
	    				$('#OrganizationTree').treegrid("reload");
	    			} else {
	    				$.messager.alert('删除失败ʾ', req.msg, "warning");
	    			}
	    		}
	    	});
		},
		
		closeDialog : function() {
			m_OrganInfo_dlg.close();
			$('#OrganizationTree').treegrid("reload");
		}
};