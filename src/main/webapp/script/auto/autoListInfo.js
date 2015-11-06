var m_order_orgId;
var m_auto_dlg = null;
var m_auto_obj = {};
var m_auto_query={};
$(function() {
	getCurrentUser();
	var obj = getUrlArgs();
	m_order_orgId = obj.orgId;
	m_auto_query.defaultRegionId = 1;
	//AutoManage.initRegion();
	AutoManage.loadAutoList();
	$("#showAuto").bind("click", AutoManage.showAuto);
});

var AutoManage = {
//		initRegion:function(){
//			$("#txtcmbRegion").combotree({
//				url:'region/getRegionList.do?parentid=0',
//			});
//		},
		packageObject : function(row) {
			m_auto_obj.id = row.id;
			m_auto_obj.userId = row.userId;
			m_auto_obj.userName = row.userName;
			m_auto_obj.userMobile = row.userMobile;
			m_auto_obj.pn = row.pn;
			m_auto_obj.brand = row.brand;
			m_auto_obj.model = row.model;
			m_auto_obj.color = row.color;
			m_auto_obj.regionId = row.defaultRegionId;
			m_auto_obj.regionName = row.regionName;
			m_auto_obj.position = row.position;
			m_auto_obj.registerTime = row.registerTime;
			m_auto_obj.orgId = m_order_orgId;//为查询订单准备
		},	
		loadAutoList : function() {
			$('#autoListGrid').datagrid({
				url : 'auto/getAutoList.do',
				queryParams : {
					'autoInfo' : JSON.stringify(m_auto_query)
				},
				fitColumns : true,
				rownumbers : true,
				pagination : true,
				pageNumber : 1,
				pageSize : 10,
				nowrap : false,
				idField : 'id',
				singleSelect : true,
				onDblClickRow : AutoManage.showAuto,
				toolbar : "#autoTb",
				columns : [ [ {
					title : 'id',
					field : 'id',
					hidden : true
				}, {
					title : '车牌号码',
					field : 'pn',
					align : 'center',
					width : 100,
				}, {
					title : '区域',
					field : 'regionName',
					align : 'center',
					width : 100
				}, {
					title : '品牌',
					field : 'brand',
					align : 'center',
					width : 100
				}, {
					title : '车辆注册时间',
					field : 'registerTime',
					align : 'center',
					width : 100
				}, {
					title : '车主',
					field : 'userName',
					align : 'center',
					width : 100
				}, {
					title : '车主电话',
					field : 'userMobile',
					align : 'center',
					width : 100
				}, {
					title : '洗车次数',
					field : 'washCount',
					align : 'center',
					width : 100
				}, {
					title : '消费金额',
					field : 'totalAmount',
					align : 'center',
					width : 100
				} ] ]
			});
		},
		doSearch:function(){
//			if($("#txtcmbRegion").combotree("getValue") ==""|| $("#txtcmbRegion").combotree("getValue")==undefined){
//				m_auto_query.defaultRegionId = 1;
//			}else{
//				m_auto_query.defaultRegionId = $("#txtcmbRegion").combotree("getValue");
//			}
			m_auto_query.pn = $("#sch_autoPN").val();
			m_auto_query.userName = $("#sch_user").val();
			$('#autoListGrid').datagrid("reload",{'autoInfo' : JSON.stringify(m_auto_query)});
		},
		doClean:function(){
//			$("#txtcmbRegion").combotree("setValue","");
			$("#sch_autoPN").val("");
			$("#sch_user").val("");
		},
		showAuto : function(){
			try {
				var dataRows = $('#autoListGrid').datagrid('getRows');
				if (dataRows.length == 0) {
					$.messager.alert('操作提示', "没有可操作数据", "warning");
					return;
				}
				var target = $("#autoListGrid").datagrid("getChecked");
				if (!target || target.length == 0) {
					$.messager.alert('操作提示', "请选择操作项!", "warning");
					return;
				}
				AutoManage.packageObject(target[0]);
				m_auto_dlg = art
						.dialog({
							id : 'dlgShowAuto',
							title : '查看车辆信息',
							content : "<iframe scrolling='yes' frameborder='0' src='view/auto/autoBill.jsp' style='width:650px;height:550px;overflow:hidden'/>",
							lock : true,
							initFn : function() {
							}
						});
			} catch (ex) {
				$.messager.alert("操作提示", ex.message, "error");
			}
		}
};