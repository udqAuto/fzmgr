<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>   
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/"; 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">  
	<script src='<%=basePath%>asset/script/jquery-1.11.1.min.js'	type='text/javascript'></script> 
	<script src='<%=basePath%>asset/script/easyui/jquery.easyui.1.4.1.min.js'	type='text/javascript'></script>
	<script src='<%=basePath%>script/common.js' type='text/javascript'></script>
	<link href='<%=basePath%>asset/css/easyui/metro/easyui.css'	media='all' rel='stylesheet' type='text/css' />
	<script src='<%=basePath%>script/login.js'	type='text/javascript'></script>
	<script src='<%=basePath%>script/MD5.js'	type='text/javascript'></script>
      <style type="text/css">
        * {
            padding: 0;
            margin: 0;
        }

        body {
            text-align: center;
            width:100%; 
            background-color:#ffffff;
        }

        #login {
            width: 740px;
            margin: 0 auto;
            font-size: 12px;
        }

        #loginlogo {
            width: 799px;
            height: 100px;
            overflow: hidden;
            background: url('resource/icon/login/logo1.png') no-repeat;
            margin-top: 50px;
        }

        #loginpanel {
            width: 729px;
            position: relative;
            height: 300px;
        }

        .panel-h {
            width: 729px;
            height: 20px;
            background: url('resource/icon/login/panel-h.gif') no-repeat;
            position: absolute;
            top: 0px;
            left: 0px;
            z-index: 3;
        }

        .panel-f {
            width: 729px;
            height: 13px;
            background: url('resource/icon/login/panel-f.gif') no-repeat;
            position: absolute;
            bottom: 0px;
            left: 0px;
            z-index: 3;
        }

        .panel-c {
            z-index: 2;
            background: url('resource/icon/login/panel-c.gif') repeat-y;
            width: 729px;
            height: 300px; 
        }

        .panel-c-l {
            position: absolute;
            left: 60px;
            top: 40px;
        }

        .panel-c-r {
            position: absolute;
            right: 20px;
            top: 50px;
            width: 222px;
            line-height: 200%;
            text-align: left;
        }

        .panel-c-l h3 {
            color: #556A85;
            margin-bottom: 10px;
        }

        .panel-c-l td {
            padding: 7px;
        }


        .login-text {
            height: 24px;
            left: 24px;
            border: 1px solid #e9e9e9;
            background: #f9f9f9;
        }

        .login-text-focus {
            border: 1px solid #E6BF73;
        }

        .login-btn {
            width: 114px;
            height: 29px;
            color: #E9FFFF;
            line-height: 29px;
            background: url('resource/icon/login/login-btn.gif') no-repeat;
            border: none;
            overflow: hidden;
            cursor: pointer;
        }

        #txtUsername, #txtPassword {
            width: 191px;
        }

        #logincopyright {
            text-align: center;
            color: White;
            margin-top: 50px;
        }
    </style>
   
</head>
  
  <body>
     <div id="login">
        <div id="loginlogo"></div>
        <div id="loginpanel">
            <div class="panel-h"></div>
            <div class="panel-c">
                <div class="panel-c-l">
                <form id="submitform" style="boder:1px solid wheat" action="index/loginAction.do" method="POST">
                         <table cellpadding="0" cellspacing="0">
                            <tbody>
                                <tr>
                                    <td align="left" colspan="2">
                                        <h3>　</h3>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right"><label>用户名：</label></td>
                                    <td align="left"><input name="username" id="txtusername" type="text" style="height:30px" class="easyui-validatebox" /></td>
                                </tr>
                                <tr>
                                    <td align="right"><label>密码：</label></td>
                                    <td align="left"><input name="password" id="txtpassword" type="password" style="height:30px"  class="easyui-validatebox" /></td>
                                </tr>
                                <tr>
                                    <td align="right" colspan="2">
                                        <input   type="submit" id="btnLogin" value="登陆" class="login-btn" onclick="$('#submitform').submit()"  />
                                    </td>
                                </tr>
                            </tbody>
                        </table>  
                     </form>
                </div>
                <div class="panel-c-r">
                    <p>请从左侧输入登录账号和密码登录</p>
                    <p>如果遇到系统问题，请联系网络管理员。</p>
                    <p>如果没有账号，请联系网站管理员。 </p>
                    <p>......</p>
                </div>
            </div>
            <div class="panel-f"></div>
        </div>
        <div id="logincopyright">Copy Right ®2014-2016 </div>
    </div>
  </body>
</html>
