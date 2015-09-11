package com.udianqu.wash.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udianqu.wash.core.CCPRestSmsSDK;
import com.udianqu.wash.core.GeneralUtil;
import com.udianqu.wash.core.Result;
import com.udianqu.wash.core.SmsInfo;
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
			map.put("name", username);
			map.put("psd", nPwd);
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
	
	@RequestMapping(value = "login4App.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String login4App(
			@RequestParam(value = "userInfo", required = true) String userInfo,
			HttpServletRequest request, HttpServletResponse response) {
		JSONObject jObj = JSONObject.fromObject(userInfo);
		UserVM user = (UserVM) JSONObject.toBean(jObj,UserVM.class);
		Result<User> s = new Result<User>();
		try {
			if(user == null){
				s = new Result<User>(null, false, false, false, "传入后台参数为空");
				return s.toJson();
			}
//			if (user.getUsername().isEmpty()) {
//				s = new Result<User>(null, false, false, false, "请输入手机号");
//				return s.toJson();
//			}
//
//			if (user.getUsername().trim().length() == 0) {
//				s = new Result<User>(null, false, false, false, "请输入手机号");
//				return s.toJson();
//			}
			HttpSession session = request.getSession();
			User u = userService.selectByMobile(user.getMobile());
			if (u != null) {
				session.setAttribute("appUser", u);
				s = new Result<User>(u, true, false, false, "登录成功");
				return s.toJson();
			} else {
				s = new Result<User>(null, false, false, false, "用户不存在！");
				return s.toJson();
			}
		} catch (Exception ex) {
			s = new Result<User>(null, false, false, false, "系统登录错误，请联系网站管理员");
			return s.toJson();
		}
	}
	@RequestMapping(value="registUser4App.do",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String registUser(
			@RequestParam(value = "userInfo", required = true) String userInfo,
			@RequestParam(value = "verifCode", required = false) String verifCode,
			HttpServletRequest request) {

		Result<UserVM> result = new Result<UserVM>();
		try {
			JSONObject jObj = JSONObject.fromObject(userInfo);
			UserVM user = (UserVM) JSONObject.toBean(jObj,UserVM.class);
			String mobile = user.getMobile();
			if(mobile == null||mobile == ""){
				result = new Result<UserVM>(null, false, false, false,
						"请输入电话号码");
				return result.toJson();
			}
            //验证码验证
//			@SuppressWarnings("unchecked")
//			Map<String, SmsInfo> smss = (Map<String, SmsInfo>) request
//					.getSession().getAttribute("verifCodes");
//			if (smss == null) {
//				result = new Result<UserVM>(null, false, "没有产生相关验证码"); 
//				return result.toJson();
//			}  else {
//				SmsInfo smsInfo = smss.get(mobile);
//				//判断获取验证码时的电话与注册时传的电话是否相等？
//				if (!smsInfo.getVerifCode().equals(verifCode)) {
//					result = new Result<UserVM>(null, false, "验证码错误"); 
//					return result.toJson(); 
//				} else{
//					//验证码正确，处理其他业务逻辑，完成注册登录等逻辑
//					user.setUserType(8);
//					String psd = encryption("123456");
//					user.setPsd(psd);
//					User user2 = userService.insert(user);
//					Result<User> res = new Result<User>(user2, true, false, false,
//							"注册成功");
//					return res.toJson(); 
//				}
//			}
			if(user.getName() == null||"".equals(user.getName())){
				user.setName("车主用户");
			}
			user.setUserType(8);
			String psd = encryption("123456");
			user.setPsd(psd);
			User user2 = userService.insert(user);
			Result<User> res = new Result<User>(user2, true, false, false,
					"注册成功");
			return res.toJson(); 
		} catch (Exception ex) {
			result = new Result<UserVM>(null, false, false, false,
					"调用后台方法出错");
			return result.toJson();
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
	 * 发送验证码
	 * @param mobile   手机号码
	 * @param request   
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sendVerifCode4App.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String sendVerifCode(
			@RequestParam(value = "mobile", required = false) String mobile,
			HttpServletRequest request) throws Exception {
		Result<Object> result = null;
		try {
			if (mobile == null || "".equals(mobile)) {
				result = new Result<Object>(null, false, "请输入手机号码"); 
				return result.toJson();
			} 
//			User user = userService.selectByMobile(mobile);
//			if(user !=null){
//					result = new Result<Object>(null, false, false, false,
//							"电话号码已存在");
//					return result.toJson();
//			}
			HashMap hMap = null;
			// CCPRestSDK restAPI = new CCPRestSDK();
			CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
			restAPI.init("app.cloopen.com", "8883"); 
			//restAPI.init("sandboxapp.cloopen.com", "8883"); 

			// 初始化服务器地址和端口，沙盒环境配置成sandboxapp.cloopen.com，生产环境配置成app.cloopen.com，端口都是8883. 
			restAPI.setAccount("aaf98f894dd77eab014ddb6a41de0252","fc045501549c41bb8b44a8580865ef97"); 
			// 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在"控制台-应用"中看到开发者主账号ACCOUNT SID和
			// 主账号令牌AUTH TOKEN。
			restAPI.setAppId("aaf98f894dd77eab014ddc85adb403e9");
			// 初始化应用ID，如果是在沙盒环境开发，请配置"控制台-应用-测试DEMO"中的APPID。
			// 如切换到生产环境，请使用自己创建应用的APPID
			String verifCode = GeneralUtil.createVerifCode();
			hMap = restAPI.sendTemplateSMS(mobile,"22423", new String[] { verifCode, "2" });
			
			if ("000000".equals(hMap.get("statusCode"))) {
				@SuppressWarnings("unchecked")
				Map<String, SmsInfo> smss = (Map<String, SmsInfo>) request.getSession()
						.getAttribute("verifCodes");

				if (smss == null) {
					smss = new HashMap<String, SmsInfo>();
					request.getSession().setAttribute("verifCodes", smss);
				}
				
				SmsInfo smsInfo = new SmsInfo(); 
				smsInfo.setMobile(mobile); 
				smsInfo.setVerifCode(verifCode);
				smsInfo.setSendTime(new Date());
				smss.put(mobile, smsInfo);
				result = new Result<Object>(null, true, verifCode); 
			}else{
				String retMsg = hMap.get("statusMsg").toString();
				result = new Result<Object>(null, true, retMsg); 
			}
			return result.toJson();
		} catch (Exception ex) {
			result = new Result<Object>(null, false, "验证码发送失败");
			return result.toJson();
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
