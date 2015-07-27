package com.udianqu.wash.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.udianqu.wash.core.Result;
import com.udianqu.wash.model.User;
import com.udianqu.wash.service.LoginService;
import com.udianqu.wash.service.UserService;
import com.udianqu.wash.viewmodel.UserVM; 

/**
 * 登录、注册
 * @author xml777
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	/**
	 * 登录，注册都用这个控制器
	 * 移动端专用的接口后面都加上4App，移动端接口只能返回String,不能返回modelAndView
	 * web接口不需要加后缀
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@Autowired LoginService loginService;
	@Autowired UserService userService;
	
	@RequestMapping(value = "login4App.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String login4App(
			/*@RequestParam(value = "identity", required = true) String username,
			@RequestParam(value = "password", required = true) String password,*/
			@RequestParam(value = "identity", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			HttpServletRequest request, HttpServletResponse response
			){
		
		String info;
		boolean b = loginService.login(username, password);
			if (b) {
				info = "angular.callbacks._0({'status':'success'})";
			} else {
				info = "Error";
			}
		
		return info;
	}
	
	@RequestMapping(value = "login.do", produces = "application/json;charset=UTF-8")
	 @ResponseBody
	public void login(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest request, HttpServletResponse response
			) throws Exception{ 
		try {
			if (username.isEmpty()) {
				response.sendRedirect("../login.jsp?optType=0");
				return;
			}
			if (password.isEmpty()) {
				response.sendRedirect("../login.jsp?optType=1");
				return;
			}
			HttpSession session = request.getSession();
			if (username.trim().length() == 0) {
				response.sendRedirect("../login.jsp?optType=0");
				return;
			}
			if (password.trim().length() == 0) {
				response.sendRedirect("../login.jsp?optType=1");
				return;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			String nPwd = encryption(password);
			map.put("mobile",username);
			map.put("name", username);
			map.put("pwd", nPwd);
			UserVM u = userService.loadUserByNameAndPwd(map);
			if (u != null) {
				session.setAttribute("user", u);
				response.sendRedirect("../index.jsp");
			} else {
				response.sendRedirect("../login.jsp?optType=3");
				return;
			}
		}catch(Exception ex){ 
			response.sendRedirect("../login.jsp?optType=4");
			return;
		}
	}
	/**
	 * 退出主页，返回登录页面
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "onExit.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	void onExit(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.getSession().invalidate();// 清除当前用户相关的session对象
		response.sendRedirect("../login.jsp"); 
	}
	/**
	 * 判断用户是否登录，若session过期，返回登录页面
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "isSignIn.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String isSignIn(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			HttpSession session = request.getSession();
			UserVM u = (UserVM) session.getAttribute("user");
			if (u == null) {
				Result<UserVM> s = new Result<UserVM>(null, false, false,
						false, "登录过期，请重新登录");
				return s.toJson();
			} else {
				Result<UserVM> s = new Result<UserVM>(u, true, false, false, "");
				return s.toJson();
			}
		} catch (Exception ex) {
			Result<UserVM> s = new Result<UserVM>(null, false, false, false,
					"获取登录用户信息失败，请联系网站管理员");
			return s.toJson();
		}
	}
	
	/**
	 * 
	 * @param plainText
	 *            明文
	 * @return 32位密文
	 */
	public String encryption(String plainText) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			re_md5 = buf.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re_md5;
	}
}
