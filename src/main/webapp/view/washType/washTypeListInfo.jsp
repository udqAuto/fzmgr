<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>洗车类型</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src='<%=basePath%>script/washType/washTypeListInfo.js'
	type='text/javascript'></script>
</head>

<body>
	<div id="washTypeTb">
		<div>
			<p>
				<a id="addWashType" name="addWashType" href="javascript:void(0);" 
					class="easyui-linkbutton" iconcls="icon-udq-add" plain="true">添加</a> 
				<a id="editWashType" name="editWashType" href="javascript:void(0);" 
					class="easyui-linkbutton" iconcls="icon-udq-edit" plain="true">修改</a> 
				<a id="delWashType" name="delWashType" href="javascript:void(0);" 
					class="easyui-linkbutton" iconcls="icon-udq-delete" plain="true">删除</a> 
			</p>
		</div>
	</div>
	<div id="washTypeListGrid" style="margin:10px"></div>
</body>
</html>
