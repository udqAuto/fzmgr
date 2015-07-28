
var m_orderType;
var m_zType;
var m_order_orgId;
var m_order_query = {};
$(function() {
	getCurrentUser();
	var obj = getUrlArgs();
	m_orderType =  obj.orderState;
	m_order_orgId = obj.orgId;
	if(m_orderType == 4||m_orderType == "4"){
		$("#sp_UnComplete").hide();
		$("#sp_Canceled").hide();
		m_zType = 1;
	}else if(m_orderType == "10,11"){
		$("#sp_UnComplete").hide();
		m_zType = 3; 
	}else {
		$("#sp_Canceled").hide();
		m_zType = 2; 
	}
	OrderManage.initControl();
	OrderManage.packageQuery();
	OrderManage.loadOrderList();
	$("#showOrder").bind("click", OrderManage.showOrder);
});
var order_obj = {};
var OrderManage = {
		initControl:function(){
			$("#txtcmbOrgan").combotree({
				url:'organ/getOrganList.do?parentid='+m_order_orgId,
			});
		},
		packageQuery : function(){
			if($("#txtcmbOrgan").combotree("getValue") ==""|| $("#txtcmbOrgan").combotree("getValue")==undefined){
				m_order_query.orgId = m_order_orgId;
			}else{
				m_order_query.orgId = $("#txtcmbOrgan").combotree("getValue");
			}
			m_order_query.orderType = m_zType;
			if( $("#txtOrderState").combobox("getValue") ==""|| $("#txtOrderState").combobox("getValue")==undefined){
				m_order_query.orderState = 0;
			}else{
				m_order_query.orderState = $("#txtOrderState").combobox("getValue");
			}
			if( $("#txtCancelType").combobox("getValue") ==""|| $("#txtCancelType").combobox("getValue")==undefined){
				m_order_query.cancelType = 0;
			}else{
				m_order_query.cancelType = $("#txtCancelType").combobox("getValue");
			} 
			m_order_query.startTime = $("#sch_startTime").datebox("getValue");
			m_order_query.endTime = $("#sch_endTime").datebox("getValue");
		}, 
		loadOrderList : function() {
			$('#orderListGrid').datagrid({
				url : 'order/getOrderList.do',
				queryParams : {
					'order_Query' : JSON.stringify(m_order_query)
				},
				fitColumns : true,
				rownumbers : true,
				pagination : true,
				pageNumber : 1,
				pageSize : 10,
				nowrap : false,
				idField : 'id',
				singleSelect : true, 
				toolbar : "#orderTb",
				columns : [ [ {
					title : 'id',
					field : 'id',
					hidden : true
				}, { 
					title : '状态',
					field : 'state',
					align : 'center',
					width : 100
				},{
					title : '订单号',
					field : 'orderNo',
					align : 'center',
					width : 100
				}, {
					title : '订单用户',
					field : 'customerName',
					align : 'center',
					width : 100,
				}, {
					title : '订单备注',
					field : 'userNote',
					align : 'center',
					width : 100
				}, {
					title : '车牌号',
					field : 'autoPN',
					align : 'center',
					width : 100
				},{ 
					title : '预约时间',
					field : 'orderTime',
					align : 'center',
					width : 100
				},{ 
					title : '订单时间',
					field : 'billTime',
					align : 'center',
					width : 100
				},{ 
					title : '接收时间',
					field : 'acceptTime',
					align : 'center',
					width : 100
				},{ 
					title : '开始洗车时间',
					field : 'beginTime',
					align : 'center',
					width : 100
				},{ 
					title : '完成时间',
					field : 'finishTime',
					align : 'center',
					width : 100
				} ] ]
			});
		},
		doSearch:function(){
			OrderManage.packageQuery();
			$('#orderListGrid').datagrid("reload",{'order_Query' : JSON.stringify(m_order_query)});
		},
		doClean:function(){
			$("#txtcmbOrgan").combotree("setValue","");
			$("#txtOrderState").combobox("setValue",0);
			$("#txtCancelType").combobox("setValue",0);
			$("#sch_startTime").datebox("setValue","");
			$("#sch_endTime").combotree("setValue",""); 
		},
		showOrder : function(){
			try {
				var dataRows = $('#orderListGrid').datagrid('getRows');
				if (dataRows.length == 0) {
					$.messager.alert('操作提示', "没有可操作数据", "warning");
					return;
				}
				var target = $("#orderListGrid").datagrid("getChecked");
				if (!target || target.length == 0) {
					$.messager.alert('操作提示', "请选择操作项!", "warning");
					return;
				} 
				var orderId = target[0].id;
				m_order_dlg = art
						.dialog({
							id : 'dlgShowOrder',
							title : '查看订单信息',
							content : "<iframe scrolling='yes' frameborder='0' src='view/order/orderBill.jsp?orderId="+orderId+"' style='width:710px;height:450px;overflow:hidden'/>",
							lock : true,
							initFn : function() {
							}
						});
			} catch (ex) {
				$.messager.alert("操作提示", ex.message, "error");
			}
		}
};