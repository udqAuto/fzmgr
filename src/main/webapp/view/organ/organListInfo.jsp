<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script src='<%=basePath%>script/organ/organization.js' 	type='text/javascript'></script> 
    <title>组织结构</title>

  </head>
  
  <body>
    <div id="OrganizationTb">  
                <a id="AddOrganization" name="AddOrganization" href="javascript:void(0);" class="easyui-linkbutton"
                    iconcls="icon-udq-add" plain="true">新增</a> 
                <a id="EditOrganization" name="EditOrganization"
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-edit"
                    plain="true">编辑</a>
                <a id="DelOrganization" name="DelOrganization"
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-udq-delete"
                    plain="true">删除</a> 
    </div>
    <div id="OrganizationTree"  style="margin:10px"></div>  
  </body>
</html>
