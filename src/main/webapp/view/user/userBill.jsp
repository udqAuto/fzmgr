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
   <div style="text-align:left;overflow:hidden;">
		 <p id="tb_operation" style="padding:2px;border-bottom:1px solid black;">
		 	<a id="btnSaveUserInfo"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-add" plain="true">确定</a> 
			<a id="btnCancelSave"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-delete" plain="true">关闭</a> 
		 </p>
		 
		 <p><label>用户名称：</label><input id="txtUserName" class="easyui-validatebox"  style="width:324px" /><input id="txtid" type="hidden"/></p>
		 <p><label>登录密码：</label><input id="txtUserPwd" type="text" class="easyui-validatebox"  style="width:324px" /></p>
		 <p><label>登录密码：</label><input id="txtUserPwdAgain" type="text" class="easyui-validatebox"  style="width:324px" /></p>
		 <p><label>用户性别：</label><input id="txtUserSex" class="easyui-combobox" data-options="valueField:'id',textField:'name',data:[{id: 0,name: '女'},{id: 1,name: '男'},{id: 2,name: '女'}]" style="width:324px" /></p>
		 <p><label>用户电话：</label><input id="txtUserMobile" class="easyui-validatebox" style="width:324px"/></p>
		 <p><label>用户类型：</label><input id="txtUserType" class="easyui-combobox" data-options="valueField:'id',textField:'name',data:[{id: 1,name: '系统管理员'},{id: 2,name: '公司员工'},{id: 4,name: '洗车工'},{id: 8,name: '车主'}]" style="width:324px" /></p>
		 <p><label>电子邮箱：</label><input id="txtUserEmail" class="easyui-validatebox" style="width:324px"/></p>
		 <!--  --><p><label>所属机构：</label><input id="txtUserOrg" class="easyui-validatebox" style="width:324px"/></p>
	 </div>
  </body>
</html>