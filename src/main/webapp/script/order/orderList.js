$(function() {
	getCurrentUser();
	OrderManage.loadOrderList();
	$("#showOrder").bind("click", OrderManage.showOrder);
});
var order_obj = {};
var OrderManage = {
		packageObject : function(row) {
			order_obj.id = row.id;
			order_obj.orderNo = row.orderNo;
			order_obj.userName = row.userName;
			order_obj.userNote = row.userNote;
			order_obj.autoPN = row.autoPN;
			order_obj.regionName = row.regionName;
			order_obj.shopName = row.shopName;
			order_obj.payType = row.payType;
			order_obj.orderTime = row.orderTime;
			order_obj.billTime = row.billTime;
			order_obj.washerName = row.washerName;
			order_obj.washerNote = row.washerNote;
			order_obj.stateNote = row.stateNote;
			order_obj.acceptTime = row.acceptTime;
			order_obj.beginTime = row.beginTime;
			order_obj.finishTime = row.finishTime;
			order_obj.gradeUser = row.gradeUser;
			order_obj.gradeWasher = row.gradeWasher;
		},
		loadOrderList : function() {
			$('#orderListGrid').datagrid({
				url : 'order/getOrderList.do',
				fitColumns : true,
				rownumbers : true,
				pagination : true,
				pageNumber : 1,
				pageSize : 10,
				nowrap : false,
				idField : 'id',
				singleSelect : true,
				//onDblClickRow : ,
				toolbar : "#orderTb",
				columns : [ [ {
					title : 'id',
					field : 'id',
					hidden : true
				}, {
					title : '订单号',
					field : 'orderNo',
					align : 'center',
					width : 100
				}, {
					title : '下单用户',
					field : 'userName',
					align : 'center',
					width : 100,
				}, {
					title : '用户备注',
					field : 'userNote',
					align : 'center',
					width : 100
				}, {
					title : '小区',
					field : 'regionName',
					align : 'center',
					width : 100
				}, {
					title : '车牌号',
					field : 'autoPN',
					align : 'center',
					width : 100
				}, {
					title : '洗车店',
					field : 'shopName',
					align : 'center',
					width : 100
				},{ 
					title : '支付类型',
					field : 'payType',
					align : 'center',
					width : 100
				},{ 
					title : '预约时间',
					field : 'orderTime',
					align : 'center',
					width : 100
				},{ 
					title : '提交订单时间',
					field : 'billTime',
					align : 'center',
					width : 100
				},{ 
					title : '管理员',
					field : 'washerName',
					align : 'center',
					width : 100
				},{ 
					title : '管理员备注',
					field : 'washerNote',
					align : 'center',
					width : 100
				},{ 
					title : '状态',
					field : 'stateNote',
					align : 'center',
					width : 100
				},{ 
					title : '接收订单时间',
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
				},{ 
					title : '客户评分',
					field : 'gradeUser',
					align : 'center',
					width : 100
				},{ 
					title : '洗车工队评分',
					field : 'gradeWasher',
					align : 'center',
					width : 100
				}
				] ]
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
				OrderManage.packageObject(target[0]);
				m_order_dlg = art
						.dialog({
							id : 'dlgShowOrder',
							title : '查看订单信息',
							content : "<iframe scrolling='yes' frameborder='0' src='view/order/orderBill.jsp' style='width:310px;height:350px;overflow:hidden'/>",
							lock : true,
							initFn : function() {
							}
						});
			} catch (ex) {
				$.messager.alert("操作提示", ex.message, "error");
			}
		}
};