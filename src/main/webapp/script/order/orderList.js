
var m_orderType;
var m_zType;
var m_order_orgId;
var m_order_query = {};
var m_userType;
$(function() {
	var user = getCurrentUser();
	m_userType = user.userType;
	var obj = getUrlArgs();
	m_orderType =  obj.orderState;
	m_order_orgId = obj.orgId;
	
	if(m_orderType == "1"){
		m_zType = 1;
		$("#orderTb a[doc='newOrder']").attr("style","display:inline");
	}else if(m_orderType == "2,3"){
		m_zType = 2; 
	}else if(m_orderType == "4"){
		m_zType = 4; 
	}else if(m_orderType == "5"){
		m_zType = 5;
	}else if(m_orderType == "11"){
		m_zType = 11;
	}else if(m_orderType == "1,2,3,4,5,10,11"){
		m_zType = 6;
	}
	if(m_zType != 6){
		$("#orderTb span[name='p_state']").hide();
	}
//	if(m_userType == 1){
//		$("#orderTb a[doc='autoUser']").attr("style","display:none");
//	} 
//	else{
//		$("#orderTb a[doc='systemUser']").attr("style","display:none");
//	}
	OrderManage.initControl();
	OrderManage.packageQuery();
	OrderManage.loadOrderList();
	$("#showOrder").bind("click", OrderManage.showOrder);
	$("#cancelOrder").bind("click", OrderManage.cancelOrder);
});
var order_obj = {};
var OrderManage = {
		initControl:function(){
			$("#txtcmbOrgan").combotree({
				url:'organ/getOrganList.do?parentid=0',
			});
		},
		packageQuery : function(){
			if($("#txtcmbOrgan").combotree("getValue") ==""|| $("#txtcmbOrgan").combotree("getValue")==undefined){
				m_order_query.orgId = m_order_orgId;
			}else{
				m_order_query.orgId = $("#txtcmbOrgan").combotree("getValue");
			}
			m_order_query.orderType = m_zType;
			var orderState = $("#txtOrderState").combobox("getValue");
			if(orderState != ""){
				m_order_query.orderType = orderState;
			}
			
//			if( $("#txtCancelType").combobox("getValue") ==""|| $("#txtCancelType").combobox("getValue")==undefined){
//				m_order_query.cancelType = 0;
//			}else{
//				m_order_query.cancelType = $("#txtCancelType").combobox("getValue");
//			}
			m_order_query.userName = $("#sch_userName").val();
			m_order_query.pn = $("#sch_autoPN").val();
			m_order_query.customerMobile = $("#sch_user").val();
			m_order_query.washtype = $("#sch_washtype").val();
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
				onDblClickRow : OrderManage.showOrder,
				toolbar : "#orderTb",
				columns : [ [ {
					title : 'id',
					field : 'id',
					hidden : true
				}, { 
					title : '评分',
					field : 'customerGrade',
					align : 'center',
					width : 40
				},{
					title : '订单号',
					field : 'orderNo',
					align : 'center',
					width : 80
				}, {
					title : '订单用户',
					field : 'customerName',
					align : 'center',
					width : 50,
				},{
					title : '车主电话',
					field : 'customerMobile',
					align : 'center',
					width : 100
				}, {
					title : '订单备注',
					field : 'userNote',
					align : 'center',
					width : 200
				}, {
					title : '车牌号',
					field : 'autoPN',
					align : 'center',
					width : 80
				}, {
					title : '车辆位置',
					field : 'autoPosition',
					align : 'center',
					width : 100
				},{ 
					title : '洗车类型',
					field : 'washTypeName',
					align : 'center',
					width : 100
				},{ 
					title : '支付方式',
					field : 'payType',
					align : 'center',
					width : 100,
					formatter : function(value, rowData, index) {
						if (value == 1 || value == "1") {
							return "支付宝";
						} else if (value == 2 || value == "2") {
							return "微信";
						} else if(value == 100 || value == "100"){
							return "余额支付";
						}
					}
				},{ 
					title : '下单时间',
					field : 'billTime',
					align : 'center',
					width : 100
				},{ 
					title : '接单时间',
					field : 'acceptTime',
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
			//$("#txtOrderState").combobox("setValue",0);
			//$("#txtCancelType").combobox("setValue",0);
			$("#sch_user").val("");
			$("#sch_userName").val("");
			$("#sch_washtype").val("");
			$("#sch_autoPN").val("");
			$("#sch_startTime").datebox("setValue","");
			$("#sch_endTime").combotree("setValue",""); 
		},
		cancelOrder : function(){
			try{
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
				var name = target[0].customerName;
				if(target != null){
					$.messager.confirm("取消确认", "确认取消用户 ["+name+"]的订单？", function(r) {
						if (r) {
							OrderManage.cancelOrderAction(orderId);
						}
					});
				}
			}catch (ex){
				$.messager.alert("操作提示", ex.message, "error");
			}
		},
		cancelOrderAction : function(orderId){
			$.ajax({
	    		url :  "order/cancelOrder.do?Id="+orderId,
	    		type : "POST",
	    		dataType : "json",
	    		async : false,
	    		success : function(req) {
	    			if (req.isSuccess) {
	    				$('#orderListGrid').datagrid("reload");
	    			} else {
	    				$.messager.alert('取消失败ʾ', req.msg, "warning");
	    			}
	    		}
	    	});
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
							content : "<iframe scrolling='yes' frameborder='0' src='view/order/orderBill.jsp?orderId="
								+orderId
								+"&userType="
								+m_userType
								+"' style='width:710px;height:450px;overflow:hidden'/>",
							lock : true,
							initFn : function() {
							}
						});
			} catch (ex) {
				$.messager.alert("操作提示", ex.message, "error");
			}
		}
};