var m_type;
var m_orgId;
var m_parentId;
var m_parentName;
var score = parent.m_organ;
$(function() { 

	OrganManage.initBaseAttribute();
	var args = getUrlArgs();
	m_type = args.type;
	m_orgId = args.orgId;
	m_parentId = args.parentId;
	m_parentName = args.parentName;
	$("#txtParentName").val(m_parentName);
	if(m_type==1||m_type=="1"){
		
		$("#txtOrganName").val(score.name);
		if( score.bmId>0){
		$("#txtUserName").combobox('setValue', score.bmId);
		}
		if(score.isShop){
			$("#txtIsShop").attr("checked","true");
		}else{ 
			$("p[name='p_isShop']").attr("style","display:none");
		}
		$("#txtOrganDescription").val(score.note);
	}else{
		$("p[name='p_isShop']").attr("style","display:none");
	}
	$("#btnSaveOrganInfo").bind("click", OrganManage.saveOrgan);
	$("#btnCancelSave").bind("click", OrganManage.closeWindow);
	
});
//function a (){
//$("#txtUserName").combobox('setValue', score.bmId);
//}
var m_organ_obj = {};
var OrganManage = {
		initBaseAttribute:function(){
			$("#txtUserName").combobox({
				url:'user/getAllUserList.do',    
			    valueField:'id',    
			    textField:'name'
//			    onLoadSuccess:a
			});
		},
		onCheckShop:function(){
			var s = $("input[type='checkbox']:checked");
			if(s.length>0){  
				$("p[name='p_isShop']").removeAttr("style");
			}else{
				$("p[name='p_isShop']").attr("style","display:none");
			}
		},
		saveOrgan:function(){
			var organName = $.trim($("#txtOrganName").val());
			if (organName == "" || organName == undefined) {
				$.messager.alert("操作提示", "机构名称必须填写！", "error");
				return;
			}
			m_organ_obj.id = m_orgId;
			m_organ_obj.name = organName;
			m_organ_obj.pid = m_parentId;
			var bmId = $("#txtUserName").combobox("getValue");
			if (bmId == ""||bmId ==undefined) {
				m_organ_obj.bmId = null;
		    }else{
				m_organ_obj.bmId = bmId;
		    }
			var note = $("#txtOrganDescription").val();
			var s = $("input[type='checkbox']:checked");
			if(s.length>0){  
				m_organ_obj.isShop = true;
			}else{
				m_organ_obj.isShop = false; 
			}
			m_organ_obj.note = note;
			$.ajax({
				url : "organ/saveOrgan.do",
				type : "POST",
				dataType : "json",
				async : false,
				data : m_organ_obj,
				success : function(req) {
					if (req.isSuccess) {
						parent.OrganManage.closeDialog();
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
		closeWindow:function(){
			parent.m_OrganInfo_dlg.close();
		}
};
