package com.qg.servlet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
=======
import com.qg.model.ResourceModel;
>>>>>>> fang rui add version 2.0
import com.qg.service.ResourceService;
/**
 * 
 * @author dragon
 * <pre>
 * 这是一个删除文件的类
 * </pre>
 */
public class ResourceDelete extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		//状态标志量
		Map<String, Boolean> state = new HashMap<String, Boolean>();
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
		try {
			//orderJosn----Json对应的参数
			String json = URLDecoder.decode(request.getParameter("orderJson"),"UTF-8");  
			Gson gson = new Gson();
=======
		boolean flag=false;
		try {
			Gson gson = new Gson();
			//orderJosn----Json对应的参数
			String json = URLDecoder.decode(request.getParameter("orderJson"),"UTF-8");  
>>>>>>> fang rui add version 2.0
			//resourceId---资源ID参数
			//resourceMap---Map参数
			Map<String, Integer> resourceMap=gson.fromJson(json, new TypeToken<Map<String, Integer>>(){}.getType());
			int ID = resourceMap.get("resourceId");
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
			new ResourceService().deleteResource(ID);
			
			state.put("state", true);
		}catch(Exception e){
			state.put("state", false);
		}finally {
			Gson gson = new Gson();
			DataOutputStream output=new DataOutputStream(resp.getOutputStream());
			output.writeUTF(gson.toJson(state));
=======
			ResourceService resourceService = new ResourceService();
			ResourceModel resourceModel=resourceService.getResourceById(ID);
			String fileName = resourceModel.getResourceName();
			String path =  getServletContext().getRealPath("/WEB-INF/resource" );
			 flag = resourceService.deleteFile(path+"/"+fileName);
			//将数据库记录删除
			resourceService.deleteResource(resourceModel.getResourceId());
			state.put("delete", flag);
		}catch(Exception e){
			state.put("delete", flag);
		}finally {
			Gson gson = new Gson();
			DataOutputStream output=new DataOutputStream(resp.getOutputStream());
			output.writeBytes(gson.toJson(state));
>>>>>>> fang rui add version 2.0
			output.close();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
			doPost(request, resp);
	}
}