package com.qg.servlet;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.qg.model.ResourceModel;
import com.qg.service.ResourceService;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		ResourceService resourceService = new ResourceService();
		
		//添加数据
//		int a= 100;
//		while(a-->0)
//		{
//			resourceService.addResource(new ResourceModel("123","456.txt"));;
//		}
		//添加数据
		
		//删除数据
		int b=1237;
				while(b++<1430){
		resourceService.deleteResource(b);
				}
		//删除数据

		//		resourceService.resourceRename(new File("C:\\Users\\dragon\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\QGYun\\WEB-INF\\resource"),1034 , "qQ");
		System.out.println(123);
		
		
//		Map<String, Integer>aa = new HashMap<String, Integer>() ;
//		aa.put("resourceId", 1033);
		System.out.println(11);
		
	}

}
