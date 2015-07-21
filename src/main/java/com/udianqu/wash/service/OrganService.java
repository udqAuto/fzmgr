package com.udianqu.wash.service;

import java.util.List;

import com.udianqu.wash.model.Organization;

public interface OrganService {

	List<Organization> getOrganList(Integer qid);

	List<Organization> getParentIdItems(Integer parentId);

	List<Organization> getItemsByParentId(Integer qid);

}
