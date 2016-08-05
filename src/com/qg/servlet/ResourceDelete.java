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
import com.qg.model.ResourceModel;
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
		boolean flag=false;
		try {
			//resourceId---资源ID参数
			int ID = Integer.parseInt(URLDecoder.decode(request.getParameter("resourceId"),"UTF-8"));  
			ResourceService resourceService = new ResourceService();
			ResourceModel resourceModel=resourceService.getResourceById(ID);
			String fileName = resourceModel.getResourceName();
//			String path =  getServletContext().getRealPath("/WEB-INF/resource" );
			String path =  getServletContext().getRealPath("/" );
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
			output.close();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
			doPost(request, resp);
	}
}