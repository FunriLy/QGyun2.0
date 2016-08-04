package com.qg.servlet;

import java.io.DataOutputStream;
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
=======
import java.io.File;
>>>>>>> fang rui add version 2.0
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
import com.qg.service.ResourceService;
/**
 * 
 * @author dragon
 * <pre>
 * 这是一个重写文件名的类
 * </pre>
 */
public class ResourceRename extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		//状态标志量
		Map<String, Boolean> state = new HashMap<String, Boolean>();
		try {
			//orderJosn----Json对应的参数
			String json = URLDecoder.decode(request.getParameter("orderJson"),"UTF-8"); 
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
			
=======
>>>>>>> fang rui add version 2.0
			Gson gson = new Gson();
			
			Map<String, String> ResourceMap=gson.fromJson(json, new TypeToken<Map<String, String>>(){}.getType());
			//resourceId----资源ID
			int resourceId =Integer.parseInt( ResourceMap.get("resourceId"));
			//newSourceName---资源名字
			String newSourceName = ResourceMap.get("newSourceName");
			
			ResourceService resourceService = new ResourceService();
			
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
			resourceService.resourceRename(resourceId, newSourceName);
			state.put("state", true);
		}catch(Exception e) {
			state.put("state", false);
		}finally {
			Gson gson = new Gson();
			DataOutputStream output=new DataOutputStream(resp.getOutputStream());
			output.writeUTF(gson.toJson(state));
=======
			File path = new File(getServletContext().getRealPath("/WEB-INF/resource"));
			boolean satate=resourceService.resourceRename(path,resourceId, newSourceName);
			state.put("rename", satate);
		}catch(Exception e) {
			state.put("rename", false);
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
