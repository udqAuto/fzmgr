<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script src='<%=basePath%>script/user/userBill.js'
	type='text/javascript'></script> 
<title>用户信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page"> 
</head>
  
  <body>
   <div id="div_userInfo" style="text-align:left;overflow:hidden;">
		 <p id="tb_operation" style="padding:2px;border-bottom:1px solid black;width:273px;">
		 	<a id="btnSaveUserInfo" doc="systemUser"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-add" plain="true">确定</a> 
			<a id="btnCancelSave"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-delete" plain="true">关闭</a> 
		 </p>
		 
		 <p><label>用户名称：</label><input id="txtUserName" class="easyui-validatebox" style="width:217px"  /></p>
		 <p name="p_pwd"><label>登录密码：</label><input id="txtUserPwd" type="password" style="width:217px"  class="easyui-validatebox"  /></p>
		 <p name="p_pwd"><label>登录密码：</label><input id="txtUserPwdAgain" type="password" style="width:217px"  class="easyui-validatebox"/></p>
		 <p><label>机构名称：</label><input id="txtUserOrgName" type="text" class="easyui-combotree" style="width:217px"  /></p>
		 <p><label>用户性别：</label><input id="txtUserSex" class="easyui-combobox" style="width:217px"  data-options="valueField:'id',textField:'name',data:[{id: 0,name: '男'},{id: 1,name: '女'},{id: 2,name: '保密'}]" /></p>
		 <p><label>用户电话：</label><input id="txtUserMobile" class="easyui-validatebox" style="width:217px" /></p>
		 <p><label>电子邮箱：</label><input id="txtUserEmail" class="easyui-validatebox"  style="width:217px" /></p>
	 </div>
  </body>
</html>
