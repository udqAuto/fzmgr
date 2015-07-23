var m_washTypeId;
var m_type;
var m_washType_obj = {};
var score = parent.washType_obj;
$(function() {
	var args = getUrlArgs();
	m_washTypeId = args.washTypeId;
	m_type = args.type;
	if(m_type==1||m_type=="1"){
		$("#txtWashTypeName").val(score.name);
		$("#txtWashAmount").val(score.amount);
		$("#txtNote").val(score.note);
		if(score.needKey){
			$("#txtIsNeedKey").attr("checked","true");
		}
	}
	$("#btnSaveWashTypeInfo").bind("click", WashTypeManage.saveWashType);
	$("#btnCancelSave").bind("click", WashTypeManage.closeWindow);
});
var WashTypeManage = {
		saveWashType : function(){
			m_washType_obj.id = m_washTypeId;
			var name = $.trim($("#txtWashTypeName").val());
			if (name == "" || name == undefined) {
				$.messager.alert("操作提示", "名称必须填写！", "error");
				return;
			}
			m_washType_obj.name = name;
			
			var amount = $.trim($("#txtWashAmount").val());
			if (amount == "" || amount == undefined) {
				$.messager.alert("操作提示", "价格必须填写！", "error");
				return;
			}
			m_washType_obj.amount = amount;
			
			var note = $.trim($("#txtNote").val());
			if (note == "" || note == undefined) {
				$.messager.alert("操作提示", "描述必须填写！", "error");
				return;
			}
			m_washType_obj.note = note;
			
			var s = $("input[type='checkbox']:checked");
			if(s.length>0){  
				m_washType_obj.needKey = true;
			}else{
				m_washType_obj.needKey = false;
			}
			
			$.ajax({
				url : "washType/saveWashType.do",
				type : "POST",
				dataType : "json",
				async : false,
				data : m_washType_obj,
				success : function(req) {
					if (req.isSuccess) {
						parent.WashTypeManage.closeDialog();
					} else {
						$.messager.alert("系统提示", req.msg, "error");
					}
				},
				failer : function(a, b) {
					$.messager.alert("消息提示", a, "info");
				},
				error : function(a) {
					$.messager.alert("消息提示", a, "error");
				}
			});
		},
		closeWindow : function(){
			parent.m_washTypeInfo_dlg.close();
		}
};
