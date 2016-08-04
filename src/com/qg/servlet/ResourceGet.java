package com.qg.servlet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
	
		Map<String, Boolean> State = new HashMap<String, Boolean>();
		boolean state=true;
		DataOutputStream output=new DataOutputStream(resp.getOutputStream());
		Gson gson = new Gson();
		
		try {
				//orderJosn----Json对应的参数
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
				String json = URLDecoder.decode(request.getParameter("orderJson"),"UTF-8");  
				
				Map<String, Integer> pageMap=gson.fromJson(json, new TypeToken<Map<String, Integer>>(){}.getType());
				//page----页码参数
				int pageNumber = pageMap.get("page");
=======
//				String json = URLDecoder.decode(request.getParameter("orderJson"),"UTF-8");  
//				Map<String, Integer> pageMap=gson.fromJson(json, new TypeToken<Map<String, Integer>>(){}.getType());
				//page----页码参数
				String page=  URLDecoder.decode(request.getParameter("page"),"UTF-8");  
				
				int pageNumber = Integer.parseInt(page);
>>>>>>> fang rui add version 2.0
				
				ResourceService resourceService = new ResourceService();
				
				List<ResourceModel> Resources= resourceService.getResource(pageNumber);
				
				//Resources List<ResourceModel> 文件信息的集合 
				ObjectModel objectModel = new ObjectModel(Resources,state);
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
				output.writeUTF(gson.toJson(objectModel));
=======
				output.writeBytes(gson.toJson(objectModel));
>>>>>>> fang rui add version 2.0

		}catch(Exception e){
			state=false;
			ObjectModel objectModel = new ObjectModel(state);
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
			output.writeUTF(gson.toJson(objectModel));
=======
			output.writeBytes(gson.toJson(objectModel));
>>>>>>> fang rui add version 2.0
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
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
		boolean State;
		public ObjectModel(List<ResourceModel> Resources,boolean State){
			this.Resources=Resources;
			this.State=State;
		}
		public ObjectModel(boolean State){
			this.State=State;
=======
		boolean state;
		public ObjectModel(List<ResourceModel> Resources,boolean state){
			this.Resources=Resources;
			this.state=state;
		}
		public ObjectModel(boolean state){
			this.state=state;
>>>>>>> fang rui add version 2.0
		}
	}
}
