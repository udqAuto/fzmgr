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
 
import com.udianqu.wash.core.Result;
import com.udianqu.wash.model.Organization;
import com.udianqu.wash.model.Region; 
import com.udianqu.wash.service.RegionService; 
import com.udianqu.wash.viewmodel.OrganVM;
import com.udianqu.wash.viewmodel.RegionVM;

/**
 * 区域
 * 省，市，县
 * @author xml777
 *
 */
@Controller
@RequestMapping("/region")
public class RegionController {

	@Autowired RegionService regionService;
	
	
	@RequestMapping(value = "saveRegion.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String saveRegion(
			Region region,
			HttpServletRequest request, HttpServletResponse response
			){
		try {
			if (region.getId() > 0) {
				Region r = regionService.selectByPrimaryKey(region.getId());
				if(r!=null){ 
					region.setPid(r.getPid());
					region.setSort(r.getSort());
					region.setLevel(r.getLevel()); 
					region.setPath(r.getPath()); 
					regionService.updateByPrimaryKey(region);
				}
			} else {
				Region r = regionService.selectByPrimaryKey(region.getPid()); 
				region.setSort(1);
				region.setLevel(r.getLevel()+1);
				if(r.getPath()!=null&&r.getPath().length()>0){
					region.setPath(r.getPath()+"."+r.getId());
				}else{
					region.setPath(r.getId().toString());
				}
				regionService.insert(region);
			}
			Result<RegionVM> s = new Result<RegionVM>(null, true,
					false, false, "保存成功");
			return s.toJson();
	} catch (Exception ex) {
		Result<RegionVM> s = new Result<RegionVM>(null, false, false,
				false, "调用后台方法出错");
		return s.toJson();
	}
	}
	
	@RequestMapping(value = "deleteRegion.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String deleteRegion(
			@RequestParam(value = "Id", required = true) Integer id,
			HttpServletRequest request
			){
		try{
			regionService.deleteRegion(id);
			Result<RegionVM> s = new Result<RegionVM>(null, true, false,
					false, "调用后台方法出错");
			return s.toJson();
		}catch(Exception ex){
			Result<RegionVM> s = new Result<RegionVM>(null, false, false,
					false, "调用后台方法出错");
			return s.toJson();
		}
	}
	
	@RequestMapping(value = "updateRegion.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody ModelAndView updateRegion(
			HttpServletRequest request, HttpServletResponse response
			){
		return null;
	}
	
	
	@RequestMapping(value = "getRegionList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody String getRegionList(
			@RequestParam(value = "id", required = false) Integer hybrid_id,
			@RequestParam(value = "parentid", required = false) Integer parentid,
			HttpServletRequest request, HttpServletResponse response
			){
		Integer qid = null;  
		List<RegionVM> ls = new ArrayList<RegionVM>();
		List<RegionVM> list = new ArrayList<RegionVM>();
		if (hybrid_id != null) {
			qid = hybrid_id;  
		} else {
			qid = parentid;
		}   
		ls = regionService.getRegionList(qid); 
		list = getNodes(ls,qid);
		JSONArray  json = JSONArray.fromObject(list);
		String resutl  = json.toString();
		return resutl;
	}

	private List<RegionVM> getNodes(List<RegionVM> ls, Integer qid) {
		// TODO Auto-generated method stub
		List<RegionVM> list = new ArrayList<RegionVM>(); 
		for(RegionVM o :ls ){
			if(o.getPid() == qid){
				RegionVM v = new RegionVM();
				v.setId(o.getId());
				v.setPath(o.getPath());
				v.setPid(o.getPid()); 
				v.setLevel(o.getLevel());
				v.setName(o.getName());
				v.setText(o.getName()); 
				v.setSort(o.getSort());
				v.setAddress(o.getAddress());
				v.setIsEstate(o.getIsEstate());
				v.setShopId(o.getShopId());
				v.setShopName(o.getShopName());
				List<RegionVM> l = getItemByParentId(o.getId());
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

	private List<RegionVM> getItemByParentId(Integer id) {
		// TODO Auto-generated method stub
		List<RegionVM> ls = regionService.getRegionList(id); 
		return ls;
	}
}
