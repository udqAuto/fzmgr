<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"> 
	<script src='<%=basePath%>script/user/userListInfo.js' 	type='text/javascript'></script>
  </head>
  
  <body>
     <div id="userTb" >
        <div>
            <p> 
                <a id="addUser" name="addUser" doc="systemUser"
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-add"
                    plain="true">新增</a>
                <a id="editUser" name="editUser" doc="systemUser"
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-edit"
                    plain="true">修改</a>
                <a id="delUser" name="delUser" doc="systemUser"
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-remove"
                    plain="true">删除</a>
                <a id="showUser" name="showUser" 
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-show"
                    plain="true">查看</a>
                <a id="unlockUser" name="unlockUser" doc="systemUser"
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-unlock"
                    plain="true">启用</a>
                <a id="lockUser" name="lockUser" doc="systemUser"
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-lock"
                    plain="true">锁定</a>
            </p>  
        </div>
    </div>
    <div id="userListGrid"  style="margin:10px" ></div>
  </body>
</html>
