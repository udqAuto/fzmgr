package com.udianqu.wash.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.udianqu.wash.core.CCPRestSmsSDK;
import com.udianqu.wash.core.GeneralUtil;
import com.udianqu.wash.core.Result;
import com.udianqu.wash.core.SmsInfo;
import com.udianqu.wash.service.BillSerialNoService;
import com.udianqu.wash.viewmodel.WashOrderVM;  

@Controller
@RequestMapping("/general4Apps")
public class General4AppsController {

	@Autowired
	BillSerialNoService billSerialNoService;

	@RequestMapping(value = "getNextBillSerialNo.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getNextBillSerialNo(
			@RequestParam(value = "billType", required = false) Integer billType,
			HttpServletRequest request) {
		try {
			Map<String, Object> map = GeneralUtil.getSerialNoPars(billType);
			String s = billSerialNoService.getNextBillSerialNo(map);
			return s;
		} catch (Exception ex) {
			return ex.getMessage();
		}
	}

	/**
	 * 发送验证码
	 * @param mobile   手机号码
	 * @param request   
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sendVerifCode.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String sendVerifCode(
			@RequestParam(value = "mobile", required = false) String mobile,
			HttpServletRequest request) throws Exception {
		try {
			Result<Object> result = null;
			if (mobile == null || "".equals(mobile)) {
				result = new Result<Object>(null, true, "手机号码"); 
				return result.toJson();
			} 
			HashMap hMap = null;
			// CCPRestSDK restAPI = new CCPRestSDK();
			CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
			//restAPI.init("app.cloopen.com", "8883"); 
			restAPI.init("sandboxapp.cloopen.com", "8883"); 

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
			Result<Object> s = new Result<Object>(null, false, "后台调用验证码发送出错");
			return s.toJson();
		}
	}
	/**
	 * 验证发送 的验证码是否是接收的验证码
	 * @param verifCode  验证码内容
	 * @param mobile     手机号码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "confirmVerifCode.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String confirmVerifCode( 
			@RequestParam(value = "verifCode", required = false) String verifCode, 
			@RequestParam(value = "mobile", required = false) String mobile, 
			HttpServletRequest request) {
		Result<Object> result = null;
		@SuppressWarnings("unchecked")
		Map<String, SmsInfo> smss = (Map<String, SmsInfo>) request
				.getSession().getAttribute("verifCodes");
		if (smss == null) {
			result = new Result<Object>(null, false, "没有产生相关验证码"); 
			return result.toJson();
		}  else {
			SmsInfo smsInfo = smss.get(mobile);
			if (!smsInfo.getVerifCode().equals(verifCode)) {
				result = new Result<Object>(null, false, "验证码错误"); 
				return result.toJson(); 
			} else{
				
				//验证码正确，处理其他业务逻辑，完成注册登录等逻辑
				result = new Result<Object>(null, true, "注册成功"); 
			}
		}
		return result.toJson();
	}
	
	
}
