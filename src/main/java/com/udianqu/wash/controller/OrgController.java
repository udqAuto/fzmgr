package com.udianqu.wash.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.udianqu.wash.core.Result;
import com.udianqu.wash.model.Organization;
import com.udianqu.wash.model.User;
import com.udianqu.wash.service.OrganService;
import com.udianqu.wash.viewmodel.OrganVM;
import com.udianqu.wash.viewmodel.UserVM;
 

/**
 * 组织机构
 * @author xml777
 *
 */
@Controller
@RequestMapping("/organ")
public class OrgController {
	
	@Autowired OrganService organService;
	
	
	@RequestMapping(value = "saveOrgan.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String saveOrgan(
			Organization organ,
			HttpServletRequest request
			){
		try {
				if (organ.getId() > 0) {
					Organization o = organService.selectByPrimaryKey(organ.getId());
					if(o!=null){ 
						organ.setPid(o.getPid());
						organ.setIsUsed(o.getIsUsed());
						organ.setSort(o.getSort());
						organ.setLevel(o.getLevel()); 
						organ.setPath(o.getPath()); 
						organService.updateByPrimaryKey(organ);
					}
				} else {
					Organization o = organService.selectByPrimaryKey(organ.getPid()); 
					organ.setIsUsed(true);
					organ.setSort(1);
					organ.setLevel(o.getLevel()+1);
					if(o.getPath()!=null&&o.getPath().length()>0){
						organ.setPath(o.getPath()+"."+o.getId());
					}else{
						organ.setPath(o.getId().toString());
					}
					organService.insert(organ);
				}
				Result<OrganVM> s = new Result<OrganVM>(null, true,
						false, false, "保存成功");
				return s.toJson();
		} catch (Exception ex) {
			Result<OrganVM> s = new Result<OrganVM>(null, false, false,
					false, "调用后台方法出错");
			return s.toJson();
		}
	}
	
	@RequestMapping(value = "deleteOrgan.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String  deleteOrgan(
			@RequestParam(value = "Id", required = true) Integer id,
			HttpServletRequest request){
		try{
			organService.deleteOrgan(id);
			Result<OrganVM> s = new Result<OrganVM>(null, true, false,
					false, "调用后台方法出错");
			return s.toJson();
		}catch(Exception ex){
			Result<OrganVM> s = new Result<OrganVM>(null, false, false,
					false, "调用后台方法出错");
			return s.toJson();
		}
		
	}
	
	@RequestMapping(value = "updateOrg.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody ModelAndView updateOrg(
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}
	@RequestMapping(value = "getOrganList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String getOrganList(
			@RequestParam(value = "id", required = false) Integer hybrid_id,
			@RequestParam(value = "parentid", required = false) Integer parentid,
			HttpServletRequest request, HttpServletResponse response
			){
		Integer qid = null;  
		List<Organization> ls = new ArrayList<Organization>();
		List<OrganVM> list = new ArrayList<OrganVM>();
		if (hybrid_id != null) {
			qid = hybrid_id;  
		} else {
			qid = parentid;
		}   
		ls = organService.getOrganList(qid); 
		list = getNodes(ls,qid);
		JSONArray  json = JSONArray.fromObject(list);
		String resutl  = json.toString();
		return resutl;
	}
	
	@RequestMapping(value = "getAllShopList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getAllShopList( 
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) throws Exception { 
		List<Organization> rs = organService.loadAllShoplist(); 
		JSONArray result = JSONArray.fromObject(rs);
		return result.toString(); 
	}
	
	private List<OrganVM> getNodes(List<Organization> ls, Integer qid) {
		// TODO Auto-generated method stub
		List<OrganVM> list = new ArrayList<OrganVM>(); 
		for(Organization o :ls ){
			if(o.getPid() == qid){
				OrganVM v = new OrganVM();
				v.setId(o.getId());
				v.setPath(o.getPath());
				v.setPid(o.getPid());
				v.setIsShop(o.getIsShop());
				v.setIsUsed(o.getIsUsed());
				v.setLevel(o.getLevel());
				v.setName(o.getName());
				v.setText(o.getName());
				v.setNote(o.getNote());
				v.setSort(o.getSort());
				v.setBmId(o.getBmId());
				List<Organization> l = getItemByParentId(o.getId());
				if(l.size()>0){ 
					v.setChildren(getNodes(l,o.getId())); 
				}else{
					v.setChildren(null); 
				}
				v.setState("open");
				list.add(v);
			}
		}
		return list;
	}

	private List<Organization> getItemByParentId(Integer id) {
		// TODO Auto-generated method stub
		List<Organization> ls = organService.getOrganList(id); 
		return ls;
	}
 
}
