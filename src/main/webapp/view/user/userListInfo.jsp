<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户列表</title> 
	<script src='<%=basePath%>script/user/userListInfo.js' 	type='text/javascript'></script>
  </head>
  
  <body>
     <div id="userTb" >
        <div>
            <p> 
                <a id="addUser" name="addUser" doc="systemUser"
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-add"
                    plain="true">新增</a>
                <a id="editUser" name="editUser" doc="systemUser"
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-edit"
                    plain="true">修改</a>
                <a id="delUser" name="delUser" doc="systemUser"
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-delete"
                    plain="true">删除</a>
                <a id="showUser" name="showUser" doc="autoUser"
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-show"
                    plain="true">查看</a>
                <a id="unlockUser" name="unlockUser" doc="systemUser"
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-unlock"
                    plain="true">启用</a>
                <a id="lockUser" name="lockUser" doc="systemUser"
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-lock"
                    plain="true">锁定</a>
                <!-- <span style="padding-left:25px">
					<label>组织机构:</label><input id="txtcmbOrgan" class="easyui-combotree" style="width:150px" />
            	</span> -->
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
