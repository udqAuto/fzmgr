$(function() { 
	OrganManage.initBaseAttribute();
	$("#btnSaveOrganInfo").bind("click", OrganManage.saveOrgan);
	$("#btnCancelSave").bind("click", OrganManage.closeWindow);
});
var OrganManage = {
		initBaseAttribute:function(){
			$("#txtOrganRegion").combotree({
				url:'region/getRegionList.do?parentid=0'
			});
		},
		saveOrgan:function(){
			$.messager.alert("操作提示","保存组织机构信息","info");
		},
		closeWindow:function(){
			parent.m_OrganInfo_dlg.close();
		}
};
