var m_type;
var m_regionId;
var m_parentId;
var m_parentName;
var score = parent.region_obj;
$(function() { 

	RegionManage.initBaseAttribute();
	var args = getUrlArgs();
	m_type = args.type;
	m_regionId = args.regionId;
	m_parentId = args.parentId;
	m_parentName = args.parentName;
	$("#txtParentName").val(m_parentName);
if(m_type==1||m_type=="1"){
		
		$("#txtRegionName").val(score.name);
		if( score.shopId>0){
		$("#txtShopName").combobox('setValue', score.shopId);//设置的是name
		}
		if(score.isEstate){
			$("#txtIsEstate").attr("checked","true");
		}
		$("#txtRegionAddress").val(score.address);
	}
	
	$("#btnSaveRegionInfo").bind("click", RegionManage.saveRegion);
	$("#btnCancelSave").bind("click", RegionManage.closeWindow);
	
});
var m_region_obj = {};
var RegionManage = {
		initBaseAttribute:function(){
			$("#txtShopName").combobox({
				url:'organ/getAllShopList.do',    
			    valueField:'id',    
			    textField:'name'
			    //onLoadSuccess:a
			});
		},
		saveRegion:function(){
			m_region_obj.id = m_regionId;
			var regionName = $.trim($("#txtRegionName").val());
			if (regionName == "" || regionName == undefined) {
				$.messager.alert("操作提示", "区域名称必须填写！", "error");
				return;
			}
			m_region_obj.name = regionName;
			m_region_obj.pid = m_parentId;
			
			var shopId = $("#txtShopName").combobox("getValue");
			m_region_obj.shopId = shopId;
			
			var s = $("input[type='checkbox']:checked");
			var address = $.trim($("#txtRegionAddress").val());
			if(s.length>0){  
				m_region_obj.isEstate = true;
				m_region_obj.address = address;
			}else{
				m_region_obj.isEstate = false;
				if(address == ""||address == undefined){
					m_region_obj.address = "";
				}else{
					$.messager.alert("操作提示", "请先关联小区！", "error");
					return;
				}
			}
			$.ajax({
				url : "region/saveRegion.do",
				type : "POST",
				dataType : "json",
				async : false,
				data : m_region_obj,
				success : function(req) {
					if (req.isSuccess) {
						parent.RegionManage.closeDialog();
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
			parent.m_RegionInfo_dlg.close();
		}
};
