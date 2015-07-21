<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/view/lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script src='<%=basePath%>script/region/regionList.js' 	type='text/javascript'></script> 
    <title>组织结构</title>

  </head>
  
  <body>
    <div id="regionTb">  
                <a id="AddRegion" name="AddRegion" href="javascript:void(0);" class="easyui-linkbutton"
                    iconcls="icon_xfxg_add" plain="true">新增</a> 
                <a id="EditRegion" name="EditRegion"
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon_xfxg_edit"
                    plain="true">编辑</a>
                <a id="Delregion" name="Delregion"
                    href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon_xfxg_delete"
                    plain="true">删除</a> 
    </div>
    <div id="RegionTree"  style="margin:10px"></div>  
  </body>
</html>
