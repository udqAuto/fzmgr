<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script src='<%=basePath%>script/user/userBill.js'
	type='text/javascript'></script> 
<script src='<%=basePath%>script/user/ajaxfileupload.js'
	type='text/javascript'></script> 
<title>用户信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page"> 
</head>
  
  <body>
		 <p id="tb_operation" style="padding:2px;border-bottom:1px solid black;width:94%;">
		 	<a id="btnSaveUserInfo"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-save" plain="true">保存</a> 
			<a id="btnCancelSave"  href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-close" plain="true">关闭</a> 
		 </p>
   <div id="div_userInfo" style="text-align:left;overflow:hidden;">
         <p name="p_img"><img id="txtUserPhoto" style='width:40px;height:50px;float:right;' src="" class="easyui-validatebox" style="width:217px"/></p>
		 <p><label>用户名称：</label><input id="txtUserName" class="easyui-validatebox"/></p>
		 <p name="p_upload"><label>选择照片：</label><input type="file" id="file" name="file" class="easyui-validatebox" style="width:170px;"/></p>
		 <p name="p_pwd"><label>登录密码：</label><input id="txtUserPwd" type="password" style="width:217px"  class="easyui-validatebox"  /></p>
		 <p name="p_pwd"><label>登录密码：</label><input id="txtUserPwdAgain" type="password" style="width:217px"  class="easyui-validatebox"/></p>
		 <p><label>用户电话：</label><input id="txtUserMobile" class="easyui-validatebox" style="width:217px" /></p>
		 <p name="p_info"><label>机构名称：</label><input id="txtUserOrgName" type="text" class="easyui-combotree" style="width:217px"  /></p>
		 <p name="p_utype"><label>用户类型：</label><input id="txtUserType" type="text" class="easyui-combobox" style="width:217px"   data-options="valueField:'id',textField:'name',data:[{id: 1,name: '系统管理员'},{id: 2,name: '公司员工'},{id: 4,name: '洗车工'}]" /></p>
		 <p><label>用户性别：</label><input id="txtUserSex" class="easyui-combobox" style="width:217px"  data-options="valueField:'id',textField:'name',data:[{id: 0,name: '保密'},{id: 1,name: '男'},{id: 2,name: '女'}]" /></p>
		 <p name="p_info"><label>身份证号：</label><input id="txtUserIdcard" class="easyui-validatebox" style="width:217px"/></p>
		 <p name="p_info"><label>电子邮箱：</label><input id="txtUserEmail" class="easyui-validatebox"  style="width:217px"/></p>
		 <p name="p_total"><label>洗车次数：</label><input id="txtWashCount" class="easyui-validatebox" readonly="readonly"  style="width:217px" /></p>
		 <p name="p_total"><label>消费金额：</label><input id="txtTotalAmount" class="easyui-validatebox" readonly="readonly"  style="width:217px" /></p>
		 <p name="p_total"><label>用户余额：</label><input id="txtUserBalance" class="easyui-validatebox" readonly="readonly"  style="width:217px" /></p>
		 <p name="p_time"><label>注册时间：</label><input id="txtRegisterTime" class="easyui-validatebox"  style="width:217px"/></p>
		 <p name="p_time"><label>备&nbsp;&nbsp;注：</label><input id="txtNote" class="easyui-validatebox" placeholder="可修改备注" style="width:217px"/></p>
	 </div>
	 <div id="userAutoInfo" class="displaynone" style="width:50%;float:left;padding-top:12px;">
	 	<div id="dgAuto"  style="margin:10px;height:155px" ></div>
	 </div>
	
  </body>
</html>
