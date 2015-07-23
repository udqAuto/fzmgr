<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script src='<%=basePath%>script/order/orderList.js'
	type='text/javascript'></script>
<title>订单列表</title>

</head>
<body>
	<div id="orderTb">
		<div>
			<p>
				<a id="showOrder" name="showOrder" href="javascript:void(0);"
					class="easyui-linkbutton" iconcls="icon-show" plain="true">查看</a>
			</p>
		</div>
	</div>
	<div id="orderListGrid" style="margin:10px"></div>
</body>
</html>