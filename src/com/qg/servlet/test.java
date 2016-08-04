package com.qg.servlet;

import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.qg.dao.ResourceDao;
import com.qg.model.ResourceModel;
import com.qg.service.ResourceService;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		ResourceDao resourceDao= new ResourceDao();
		
		ResourceService resourceService = new ResourceService();
		
		List<ResourceModel> Resources= resourceDao.getResource(1);
		Map<String, Boolean> State = new HashMap<String, Boolean>();
		
		State.put("state", true);
		ObjectModel objectModel = new ObjectModel(Resources,State);
		
		System.out.println(gson.toJson(resourceDao.getResourceById(21)));
	}

}
class ObjectModel {
	List<ResourceModel> Resources;
	Map<String, Boolean> State;
	public ObjectModel(List<ResourceModel> Resources,Map<String, Boolean> State){
		this.Resources=Resources;
		this.State=State;
	}
}
