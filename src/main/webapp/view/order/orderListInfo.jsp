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
				<a id="showOrder" name="showOrder" doc="autoUser" href="javascript:void(0);"
					class="easyui-linkbutton" iconcls="icon-udq-show" plain="true" style="display:none">查看</a> 
				<a id="cancelOrder" name="cancelOrder" doc="newOrder" href="javascript:void(0);"
					class="easyui-linkbutton" iconcls="icon-udq-delete" plain="true" style="display:none" >取消订单</a> 
					
				<span id="sp_UnComplete" name="p_state" style="padding-left:25px">
					<label>订单状态：</label><input id="txtOrderState" class="easyui-combobox" style="width:134px" data-options="editable:false,valueField:'id',textField:'name',data:[{id: 1,name: '新订单'},{id: 2,name: '已接收'},{id: 4,name: '已完成'},{id: 5,name: '已评价'},{id: 11,name: '已取消'}]" />
				</span>
				<span style="padding-left:25px">
					<label>组织机构:</label><input id="txtcmbOrgan" class="easyui-combotree" style="width:150px" />
            	</span>
				<span>
				     <label>车主:</label><input id="sch_userName" class="easyui-validatebox" style="width:120px" data-options="editable:false" />
				</span>
				<span>
				     <label>车主电话:</label><input id="sch_user" class="easyui-validatebox" style="width:120px" data-options="editable:false" />
				</span>
				<span>
				     <label>车牌:</label><input id="sch_autoPN" class="easyui-validatebox" style="width:120px" data-options="editable:false" />
				</span>
				<span>
				     <label>洗车类型:</label><input id="sch_washtype" class="easyui-validatebox" style="width:120px" data-options="editable:false" />
				</span>
				<span style="padding-left:25px">
					<label>开始时间:</label><input id="sch_startTime" class="easyui-datebox" style="width:120px" data-options="editable:false" />
            		<label>结束时间:</label><input id="sch_endTime" class="easyui-datebox" style="width:120px"  data-options="editable:false" />
            	</span>
            	<a id="btnSearch" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" plain="true" onclick="OrderManage.doSearch();">查询</a>
            	<a id="btnclearUp" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-clear" plain="true" onclick="OrderManage.doClean();">清空</a>
			</p>
			
		</div>
	</div>
	<div id="orderListGrid" style="margin:10px"></div>
</body>
</html>