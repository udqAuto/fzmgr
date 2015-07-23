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
		if(score.isEstate){
			$("#txtIsEstate").attr("checked","true");
			$("p[name='p_estate']").removeAttr("style");
		}else{
			$("p[name='p_estate']").attr("style","display:none");
		}
		$("#txtRegionAddress").val(score.address);
	}else{
		$("p[name='p_estate']").attr("style","display:none");
	}
	
	$("#btnSaveRegionInfo").bind("click", RegionManage.saveRegion);
	$("#btnCancelSave").bind("click", RegionManage.closeWindow);
	
});
function a (){
	$("#txtShopName").combotree('setValue', score.shopId);
}
var m_region_obj = {};
var RegionManage = {
		onCheckState:function(){			
			var s = $("input[type='checkbox']:checked"); 
			if(s.length>0){ 
				$("p[name='p_estate']").removeAttr("style");
			}else{
				$("p[name='p_estate']").attr("style","display:none");
			}
		},
		initBaseAttribute:function(){
			$("#txtShopName").combotree({ 
				url:'organ/getOrganList.do?parentid=0' ,
				onLoadSuccess:a
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
			
			var shopId = $("#txtShopName").combotree("getValue");
			m_region_obj.shopId = shopId;
			
			var s = $("input[type='checkbox']:checked");
			var address = $.trim($("#txtRegionAddress").val());
			if(s.length>0){  
				m_region_obj.isEstate = true;
				m_region_obj.address = address;
			}else{
				m_region_obj.isEstate = false;
				m_region_obj.address = "";
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
