<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户列表</title> 
	<script src='<%=basePath%>script/balance/rechargeList.js' 	type='text/javascript'></script>
  </head>
  
  <body>
     <div id="rechargeTb" >
        <div>
            <p> 
                <a id="recharge" name="recharge"
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-add"
                    plain="true">充值</a>
            	<span>
				     <label>用户名称:</label><input id="sch_name" class="easyui-validatebox" style="width:120px" data-options="editable:false" />
				</span>
				<span>
				     <label>电话号码:</label><input id="sch_mobile" class="easyui-validatebox" style="width:120px" data-options="editable:false" />
				</span>
            	<a id="btnSearch" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" plain="true" onclick="UserManage.doSearch();">查询</a>
            	<a id="btnclearUp" href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-clear" plain="true" onclick="UserManage.doClean();">清空</a>
            </p>  
        </div>
    </div>
    <div id="userListGrid"  style="margin:10px" ></div>
  </body>
</html>
