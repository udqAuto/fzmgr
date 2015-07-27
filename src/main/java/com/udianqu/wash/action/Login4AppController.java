package com.udianqu.wash.action;

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

import com.udianqu.wash.core.Result;
import com.udianqu.wash.service.UserService;
import com.udianqu.wash.viewmodel.UserVM;

@Controller
@RequestMapping("/login4App")
public class Login4AppController {
	
	@Autowired UserService userService;
	
	@RequestMapping(value = "login4App.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String login4App(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest request, HttpServletResponse response
			){
		Result<UserVM> s = new Result<UserVM>();
		try{
			if (username.isEmpty()) {
				s = new Result<UserVM>(null, false, false, false, "请输入用户名");
				return s.toJson();
			}
			if (password.isEmpty()) {
				s = new Result<UserVM>(null, false, false, false, "请输入密码");
				return s.toJson();
			}
			
			if (username.trim().length() == 0) {
				s = new Result<UserVM>(null, false, false, false, "请输入用户名");
				return s.toJson();
			}
			if (password.trim().length() == 0) {
				s = new Result<UserVM>(null, false, false, false, "请输入密码");
				return s.toJson();
			}
			HttpSession session = request.getSession();
			Map<String, Object> map = new HashMap<String, Object>();
			String nPwd = encryption(password);
			map.put("mobile", username);
			map.put("name", username);
			map.put("pwd", nPwd);
			UserVM u = userService.loadUserByNameAndPwd(map);
			if (u != null) {
				session.setAttribute("user", u);
				 s = new Result<UserVM>(u, true, false, false, "登录成功");
				return s.toJson();
			} else {
				s = new Result<UserVM>(null, false, false, false, "用户名或密码错误");
				return s.toJson();
			}
		}catch(Exception ex){
				s = new Result<UserVM>(null, false, false, false, "系统登录错误，请联系网站管理员");
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
