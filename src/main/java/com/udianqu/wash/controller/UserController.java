package com.udianqu.wash.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udianqu.wash.core.ListResult;
import com.udianqu.wash.core.Result;
import com.udianqu.wash.model.User; 
import com.udianqu.wash.service.LoginService;
import com.udianqu.wash.service.UserService;
import com.udianqu.wash.viewmodel.UserVM;

/**
 * 用户 包括（user_type) 1=系统管理员 2=职员 4=洗车工 8=用户
 * 
 * @author xml777
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	LoginService loginService;

	@RequestMapping("/saveUserObj.do")
	@ResponseBody
	public String saveUserObj(UserVM user, HttpServletRequest request) {

		try {
			HttpSession session = request.getSession();
			UserVM u = (UserVM) session.getAttribute("user");
			if (u == null) {
				Result<UserVM> s = new Result<UserVM>(null, true, false, false,
						"页面过期，请重新登录");
				return s.toJson();
			} else {

				if (user.getId() > 0) {
					user.setPsd(u.getPsd());
					userService.updateByPrimaryKey(user);
				} else {
					String psd = encryption(user.getPsd());
					user.setPsd(psd);
					userService.insert(user);
				}
				Result<UserVM> s = new Result<UserVM>(null, true, false, false,
						"保存成功");
				return s.toJson();
			}
		} catch (Exception ex) {
			Result<UserVM> s = new Result<UserVM>(null, false, false, false,
					"调用后台方法出错");
			return s.toJson();
		}
	}

	/*
	 * 获取注册用户列表，以列表形式呈现；
	 */
	@RequestMapping(value = "getUserList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getUserList(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "userType", required = false) String userType,
			HttpServletRequest request) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			page = page == 0 ? 1 : page;
			map.put("pageStart", (page - 1) * rows);
			map.put("pageSize", rows);
			List<Integer> ids = new ArrayList<Integer>();
			String[] str = userType.split(",");
			for (String s : str) {
				ids.add(Integer.parseInt(s));
			}
			map.put("userType", ids);
			ListResult<UserVM> rs = userService.loadUserlist(map);
			return rs.toJson();
		} catch (Exception ex) {
			ListResult<UserVM> rs = new ListResult<UserVM>(0, null);
			return rs.toJson();
		}
	}

	/*
	 * 
	 */
	@RequestMapping(value = "getAllUserList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getAllUserList(HttpServletRequest request) throws Exception {
		List<User> rs = userService.loadadminUserlist();
		JSONArray result = JSONArray.fromObject(rs);
		return result.toString();
	}

	@RequestMapping(value = "deleteUser.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String deleteUser(@RequestParam(value = "Id", required = true) Integer id,
			HttpServletRequest request) {
		try {
			userService.deleteUser(id);
			Result<UserVM> s = new Result<UserVM>(null, true, false,
					false, "删除成功");
			return s.toJson();
		} catch (Exception ex) {
			Result<UserVM> s = new Result<UserVM>(null, false, false, false,
					"调用后台方法出错");
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
