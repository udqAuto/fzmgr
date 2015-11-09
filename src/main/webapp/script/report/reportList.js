var m_report_query = {};
$(function() {
	ReportManage.packageQuery();
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
				//pagination : true,
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
					field : 'shopName',
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
		packageQuery : function(){
			m_report_query.startTime = $("#sch_startTime").datebox("getValue");
			m_report_query.endTime = $("#sch_endTime").datebox("getValue");
			m_report_query.date = $("#sch_date").datebox("getValue");
		},
		doSearch:function(){
			ReportManage.packageQuery();
			$('#reportListGrid').datagrid("reload",{'reportInfo' : JSON.stringify(m_report_query)});
		},
		doClean:function(){
			m_report_query.startTime = $("#sch_startTime").datebox("setValue","");
			m_report_query.endTime = $("#sch_endTime").datebox("setValue","");
			m_report_query.date = $("#sch_date").datebox("setValue","");
		}
		
};