$(function() {

	AutoManage.loadAutoList();
	$("#showAuto").bind("click", AutoManage.showAuto);
});
var m_auto_dlg = null;
var m_auto_obj = {};
var AutoManage = {
		packageObject : function(row) {
			m_auto_obj.id = row.id;
			m_auto_obj.userId = row.userId;
			m_auto_obj.userName = row.userName;
			m_auto_obj.pn = row.pn;
			m_auto_obj.brand = row.brand;
			m_auto_obj.model = row.model;
			m_auto_obj.color = row.color;
			m_auto_obj.regionId = row.defaultRegionId;
			m_auto_obj.regionName = row.regionName;
		},	
		loadAutoList : function() {
			$('#autoListGrid').datagrid({
				url : 'auto/getAutoList.do',
				fitColumns : true,
				rownumbers : true,
				pagination : true,
				pageNumber : 1,
				pageSize : 10,
				nowrap : false,
				idField : 'id',
				singleSelect : true,
				//onDblClickRow : ,
				toolbar : "#autoTb",
				columns : [ [ {
					title : 'id',
					field : 'id',
					hidden : true
				}, {
					title : '车主',
					field : 'userName',
					align : 'center',
					width : 100
				}, {
					title : '车牌号码',
					field : 'pn',
					align : 'center',
					width : 100,
				}, {
					title : '品牌',
					field : 'brand',
					align : 'center',
					width : 100
				}, {
					title : '型号',
					field : 'model',
					align : 'center',
					width : 100
				}, {
					title : '颜色',
					field : 'color',
					align : 'center',
					width : 100
				}, {
					title : '区域',
					field : 'regionName',
					align : 'center',
					width : 100
				} ] ]
			});
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
							content : "<iframe scrolling='yes' frameborder='0' src='view/auto/autoBill.jsp' style='width:600px;height:470px;overflow:hidden'/>",
							lock : true,
							initFn : function() {
							}
						});
			} catch (ex) {
				$.messager.alert("操作提示", ex.message, "error");
			}
		}
};