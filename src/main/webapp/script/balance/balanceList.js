var m_balance_query={};
$(function() {
	BalanceManage.loadBalanceList();
	$("#showBalance").bind("click", BalanceManage.showBalance);
});

var BalanceManage = {	
		loadBalanceList : function() {
			$('#balanceListGrid').datagrid({
				url : 'userBalance/getBalanceList.do',
				queryParams : {
					'balanceInfo' : JSON.stringify(m_balance_query)
				},
				fitColumns : true,
				rownumbers : true,
				pagination : true,
				pageNumber : 1,
				pageSize : 10,
				nowrap : false,
				idField : 'id',
				singleSelect : true,
				//onDblClickRow : BalanceManage.showBalance,
				toolbar : "#balanceTb",
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
					title : '电话',
					field : 'userMobile',
					align : 'center',
					width : 100,
				},  {
					title : '消费或充值金额',
					field : 'amount',
					align : 'center',
					width : 100
				},{
					title : '订单号',
					field : 'orderNo',
					align : 'center',
					width : 100
				}, {
					title : '操作时间',
					field : 'recordTime',
					align : 'center',
					width : 100
				}, {
					title : '操作平台',
					field : 'type',
					align : 'center',
					width : 100,
					formatter : function(value, rowData, index) {
						if (value == 1 || value == "1") {
							return "手机端";
						} else if (value == 2 || value == "2") {
							return "后台";
						}
					}
				} ] ]
			});
		},
		doSearch:function(){
			m_balance_query.userName = $("#sch_userName").val();
			m_balance_query.userMobile = $("#sch_userMobile").val();
			$('#balanceListGrid').datagrid("reload",{'balanceInfo' : JSON.stringify(m_balance_query)});
		},
		doClean:function(){
			$("#sch_userName").val("");
			$("#sch_userMobile").val("");
		},
};