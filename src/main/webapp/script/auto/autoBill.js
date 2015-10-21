var m_order_query={};//加载订单打包参数
var score = parent.m_auto_obj;
$(function() { 
	AutoManage.showAutoInfo();
	$("#btnCancelSave").bind("click", AutoManage.closeWindow);
	m_order_query.orgId = score.orgId;
	m_order_query.autoId = score.id;
	AutoManage.loadOrderByAutoId();
});
var AutoManage = {
		showAutoInfo : function(){
			$("#txtAutoName").val(score.userName);
			$("#txtAutoPN").val(score.pn);
			$("#txtAutoBrand").val(score.brand);
			$("#txtAutoModel").val(score.model);
			$("#txtAutoColor").val(score.color);
			$("#txtAutoRegion").val(score.regionName);
			$("#txtAutoPosition").val(score.position);
		},
		loadOrderByAutoId:function(){
			$('#dgOrder').datagrid({
				url : 'order/getOrderListByAutoId.do',
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
				title :"该车辆的订单",
				columns : [ [ {
					title : '订单号',
					field : 'orderNo',
					align : 'center',
					width : 130
				}, {
					title : '订单状态',
					field : 'stateNote',
					align : 'center',
					width : 100,
				}, {
					title : '车主电话',
					field : 'customerMobile',
					align : 'center',
					width : 100
				}, {
					title : '结算金额',
					field : 'finalAmount',
					align : 'center',
					width : 100
				}, {
					title : '用户需求',
					field : 'userNote',
					align : 'center',
					width : 100
				},{
					title : '洗车工',
					field : 'washerName',
					align : 'center',
					width : 100
				},{
					title : '下单时间',
					field : 'billTime',
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
		closeWindow : function(){
			parent.m_auto_dlg.close();
		}
};