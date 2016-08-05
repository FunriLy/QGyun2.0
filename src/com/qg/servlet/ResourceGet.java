package com.qg.servlet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qg.model.ResourceModel;
import com.qg.service.ResourceService;
/**
 * 
 * @author dragon
 * <pre>
 * 这是一个获取全部文件的类
 * </pre>
 */
public class ResourceGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
	
		boolean state=true;
		DataOutputStream output=new DataOutputStream(resp.getOutputStream());
		Gson gson = new Gson();
		
		try {
				//page----页码参数
				String page=  URLDecoder.decode(request.getParameter("page"),"UTF-8");  
				
				int pageNumber = Integer.parseInt(page);
				
				ResourceService resourceService = new ResourceService();
				
				List<ResourceModel> Resources= resourceService.getResource(pageNumber);
				
				//Resources List<ResourceModel> 文件信息的集合 
				ObjectModel objectModel = new ObjectModel(Resources,state);
				output.writeBytes(gson.toJson(objectModel));

		}catch(Exception e){
			state=false;
			ObjectModel objectModel = new ObjectModel(state);
			output.writeBytes(gson.toJson(objectModel));
		}finally {
			output.close();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
			doPost(request, resp);
	}
	
	class ObjectModel {
		List<ResourceModel> Resources;
		boolean state;
		public ObjectModel(List<ResourceModel> Resources,boolean state){
			this.Resources=Resources;
			this.state=state;
		}
		public ObjectModel(boolean state){
			this.state=state;
		}
	}
}
