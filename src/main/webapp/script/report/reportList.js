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
				showFooter:true,
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
					field : 'name',
					align : 'center',
					width : 100,
				}, {
					title : '所有用户',
					field : 'all_total',
					align : 'center',
					width : 100,
				},{
					title : '新注册用户',
					field : 'reg_total',
					align : 'center',
					width : 100,
				},{
					title : '订单总数',
					field : 's0',
					align : 'center',
					width : 100
				}, {
					title : '已下单',
					field : 's1',
					align : 'center',
					width : 100
				}, {
					title : '已接单',
					field : 's2',
					align : 'center',
					width : 100
				}, {
					title : '已完成',
					field : 's4',
					align : 'center',
					width : 100
				}, {
					title : '已评价',
					field : 's5',
					align : 'center',
					width : 100
				}, {
					title : '已取消',
					field : 's11',
					align : 'center',
					width : 100
				}, {
					title : '快洗',
					field : 't1',
					align : 'center',
					width : 100
				}, {
					title : '内堂',
					field : 't2',
					align : 'center',
					width : 100
				}, {
					title : '打蜡',
					field : 't3',
					align : 'center',
					width : 100
				}, {
					title : '支付宝',
					field : 'p1',
					align : 'center',
					width : 100
				}, {
					title : '微信',
					field : 'p2',
					align : 'center',
					width : 100
				}, {
					title : '余额支付',
					field : 'p100',
					align : 'center',
					width : 100
				}, {
					title : '总金额',
					field : 'amount',
					align : 'right',
					width : 100,
					formatter : function(value, rowData, index){
						return value.toFixed(2);
					}
				} ] ]
			});
		},
		packageQuery : function(){
			m_report_query.startTime = $("#sch_startTime").datebox("getValue");
			m_report_query.endTime = $("#sch_endTime").datebox("getValue");
		},
		doSearch:function(){
			ReportManage.packageQuery();
			$('#reportListGrid').datagrid("reload",{'reportInfo' : JSON.stringify(m_report_query)});
		},
		doClean:function(){
			m_report_query.startTime = $("#sch_startTime").datebox("setValue","");
			m_report_query.endTime = $("#sch_endTime").datebox("setValue","");
		}
		
};