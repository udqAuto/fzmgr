package com.udianqu.wash.controller;  
  
import java.io.File;  
import java.text.SimpleDateFormat;
import java.util.Date;  
import java.util.HashMap;
import java.util.Map;
  
import javax.servlet.http.HttpServletRequest;  
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.ModelMap;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestParam;  
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;  
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.udianqu.wash.core.GeneralUtil;
import com.udianqu.wash.core.Result;
import com.udianqu.wash.dao.WashOrderMapper;
import com.udianqu.wash.model.WashOrder;
import com.udianqu.wash.viewmodel.UserVM;
  
@Controller
@RequestMapping("/upload")
public class UploadController {  
	
	@Autowired WashOrderMapper washOrderMapper;
  
    @RequestMapping(value = "addWashPhoto4App.do", produces = "application/json;charset=UTF-8")  
    public @ResponseBody String upload(
    		@RequestParam MultipartFile washPhoto,
    		HttpServletRequest request) { 
    	//SimpleDateFormat sdfFileName = new SimpleDateFormat("yyyyMMddHHmmss");   
		//MultipartHttpServletRequest   multipartRequest = (MultipartHttpServletRequest) request;
        //MultipartFile washPhoto = multipartRequest.getFile("washPhoto");
        try { 
        	//String path = request.getSession().getServletContext().getRealPath("washPhoto"); 
        	String path = getClass().getResource("/").getFile().toString();
			path = path.substring(0, (path.length() - 16))+"washPhoto";
        	String fileName1 = washPhoto.getOriginalFilename();//接收到的Name是没有格式的
        	String fileName = fileName1+".jpg";                //加上jpg格式
        	String orderNo[] = fileName1.split("_");           //分割出订单号作文件夹名
	        //String fileUrl = path+"/"+fileName;
	        //String photoUrl = "washPhoto/"+fileName;
	        Map<String,Object> m = GeneralUtil.getCurrentDate();
	        String date = (String) m.get("currentDate");
	        String cpath = path+"/"+date+"/"+orderNo[0];//照片最终路径
	        String photoUrl = "washPhoto/"+date+"/"+orderNo[0]+"/"+fileName; 
	        File targetFile = new File(cpath, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        washPhoto.transferTo(targetFile); //保存
        //保存photoUrl到数据库
        Map<String,Object> map = new HashMap<String, Object>();
        if("1".equals(orderNo[1])){
        	map.put("photoUrl1", photoUrl);
        }
        if("2".equals(orderNo[1])){
        	map.put("photoUrl2", photoUrl);
        }
        if("3".equals(orderNo[1])){
        	map.put("photoUrl3", photoUrl);
        }
        
            map.put("orderNo", orderNo[0]);
            washOrderMapper.updateByOrderNo(map);
            Result<WashOrder> result = new Result<WashOrder>(null, true, false, false,
    				"上传成功！");
    		return result.toJson(); 
        } catch (Exception ex) {  
        	Result<WashOrder> result = new Result<WashOrder>(null, false, false, false,
        			ex.getMessage());
    		return result.toJson();  
        }  
    }  
  
}  