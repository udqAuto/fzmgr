package com.udianqu.wash.service;

import java.util.List;

import com.udianqu.wash.model.Organization;
import com.udianqu.wash.viewmodel.OrganVM;

public interface OrganService {

	List<OrganVM> getOrganList(Integer qid);

	List<Organization> getParentIdItems(Integer parentId);

	List<Organization> getItemsByParentId(Integer qid);

	void insert(Organization organ);

	void updateByPrimaryKey(Organization organ);

	Organization selectByPrimaryKey(Integer id);

	void deleteByPrimaryKey(Integer id);

	void deleteOrgan(Integer id);

	List<Organization> loadAllShoplist();

}
