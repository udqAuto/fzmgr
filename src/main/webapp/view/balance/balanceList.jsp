<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>车辆列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src='<%=basePath%>script/balance/balanceList.js'
	type='text/javascript'></script>
</head>

<body>
	<div id="balanceTb">
		<div>
			<p>
				<a id="showBalance" name="showBalance" href="javascript:void(0);" 
					class="easyui-linkbutton" iconcls="icon-udq-show" plain="true" style="display:none">查看</a> 
				<span>
				     <label>车主姓名:</label><input id="sch_userName" class="easyui-validatebox" style="width:120px" data-options="editable:false" />
				</span>
				<span>
				     <label>车主电话:</label><input id="sch_userMobile" class="easyui-validatebox" style="width:120px" data-options="editable:false" />
				</span>
				<a id="btnSearch" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" plain="true" onclick="BalanceManage.doSearch();">查询</a>
            	<a id="btnclearUp" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-clear" plain="true" onclick="BalanceManage.doClean();">清空</a>
			</p>
		</div>
	</div>
	<div id="balanceListGrid" style="margin:10px"></div>
</body>
</html>
