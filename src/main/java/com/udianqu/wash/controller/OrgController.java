package com.udianqu.wash.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.udianqu.wash.model.Organization;
import com.udianqu.wash.service.OrganService;
import com.udianqu.wash.viewModel.OrganVM; 
 

/**
 * 组织机构
 * @author xml777
 *
 */
@Controller
@RequestMapping("/organ")
public class OrgController {
	
	@Autowired OrganService organService;
	
	
	@RequestMapping(value = "addOrg.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody ModelAndView addOrg(
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}
	
	@RequestMapping(value = "deleteOrg.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody ModelAndView deleteOrg(
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
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
