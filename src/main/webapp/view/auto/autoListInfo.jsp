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
<script src='<%=basePath%>script/auto/autoListInfo.js'
	type='text/javascript'></script>
</head>

<body>
	<div id="autoTb">
		<div>
			<p>
				<a id="showAuto" name="showAuto" href="javascript:void(0);" 
					class="easyui-linkbutton" iconcls="icon-udq-show" plain="true">查看</a> 
				<span>
				     <label>车牌号码:</label><input id="sch_autoPN" class="easyui-validatebox" style="width:120px" data-options="editable:false" />
				</span>
				<span style="padding-left:25px">
					<label>区域:</label><input id="txtcmbRegion" class="easyui-combotree" style="width:150px" />
            	</span>
				<a id="btnSearch" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" plain="true" onclick="AutoManage.doSearch();">查询</a>
            	<a id="btnclearUp" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-clear" plain="true" onclick="AutoManage.doClean();">清空</a>
			</p>
		</div>
	</div>
	<div id="autoListGrid" style="margin:10px"></div>
</body>
</html>
