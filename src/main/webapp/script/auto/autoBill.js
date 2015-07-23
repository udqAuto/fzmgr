$(function() { 
	AutoManage.showAutoInfo();
	$("#btnCancelSave").bind("click", AutoManage.closeWindow);
});
var score = parent.m_auto_obj;
var AutoManage = {
		showAutoInfo : function(){
			$("#txtAutoName").val(score.userName);
			$("#txtAutoPN").val(score.pn);
			$("#txtAutoBrand").val(score.brand);
			$("#txtAutoModel").val(score.model);
			$("#txtAutoColor").val(score.color);
			$("#txtAutoRegion").val(score.regionName);
		},
		closeWindow : function(){
			parent.m_auto_dlg.close();
		}
};