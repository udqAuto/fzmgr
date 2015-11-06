var m_report_query = {};
$(function() {
	m_report_query.date;
	ReportManage.loadReportList();
	//$("#showReport").bind("click", ReportManage.showReport);
});

var ReportManage = {	
		loadReportList : function() {
			$('#reportListGrid').datagrid({
				url : 'report/getReportList.do',
				queryParams : {
					'reportInfo' : JSON.stringify(m_report_query)
				},
				fitColumns : true,
				rownumbers : true,
				pagination : true,
				nowrap : false,
				idField : 'id',
				singleSelect : true,
				//onDblClickRow : ReportManage.showReport,
				toolbar : "#reportTb",
				columns : [ [ {
					title : 'id',
					field : 'id',
					hidden : true
				}, {
					title : '洗车店',
					field : 'orgName',
					align : 'center',
					width : 100,
				}, {
					title : '订单总数',
					field : 'orderCount',
					align : 'center',
					width : 100
				}, {
					title : '已下单',
					field : 'newOrder',
					align : 'center',
					width : 100
				}, {
					title : '已接单',
					field : 'receivedOrder',
					align : 'center',
					width : 100
				}, {
					title : '已完成',
					field : 'completedOrder',
					align : 'center',
					width : 100
				}, {
					title : '已评价',
					field : 'gradedOrder',
					align : 'center',
					width : 100
				}, {
					title : '已取消',
					field : 'cancelOrder',
					align : 'center',
					width : 100
				}, {
					title : '快洗',
					field : 'fastWash',
					align : 'center',
					width : 100
				}, {
					title : '内堂',
					field : 'insideWash',
					align : 'center',
					width : 100
				}, {
					title : '打蜡',
					field : 'waxed',
					align : 'center',
					width : 100
				}, {
					title : '支付宝',
					field : 'alipay',
					align : 'center',
					width : 100
				}, {
					title : '微信',
					field : 'weChat',
					align : 'center',
					width : 100
				}, {
					title : '余额支付',
					field : 'balancePay',
					align : 'center',
					width : 100
				}, {
					title : '总金额',
					field : 'totalAmount',
					align : 'center',
					width : 100
				} ] ]
			});
		},
		doSearch:function(){
//			m_auto_query.pn = $("#sch_autoPN").val();
//			m_auto_query.userName = $("#sch_user").val();
//			$('#autoListGrid').datagrid("reload",{'autoInfo' : JSON.stringify(m_auto_query)});
		},
		doClean:function(){
//			$("#sch_autoPN").val("");
//			$("#sch_user").val("");
		}
		
};