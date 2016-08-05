package com.qg.servlet;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
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
			//newSourceName---资源名字
			//resourceId----资源Id
			String newSourceName = URLDecoder.decode(request.getParameter("newSourceName"),"UTF-8"); 
			int resourceId =Integer.parseInt( URLDecoder.decode(request.getParameter("resourceId"),"UTF-8"));
			
			System.out.println(resourceId+"想改名");
			
			ResourceService resourceService = new ResourceService();
			
			File path = new File(getServletContext().getRealPath("/"));
//			File path = new File(getServletContext().getRealPath("/WEB-INF/resource"));
			boolean satate=resourceService.resourceRename(path,resourceId, newSourceName);
			state.put("rename", satate);
		}catch(Exception e) {
			state.put("rename", false);
		}finally {
			Gson gson = new Gson();
			DataOutputStream output=new DataOutputStream(resp.getOutputStream());
			output.writeBytes(gson.toJson(state));
			output.close();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
			doPost(request, resp);
	}
}
