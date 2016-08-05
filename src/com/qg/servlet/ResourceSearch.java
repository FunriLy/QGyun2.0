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
/***
 * 
 * @author dragon
 * <pre>
 * 该类用于搜索文件
 * </pre>
 */
public class ResourceSearch  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean State;
		State=true;
		//orderJosn----Json对应的参数
		System.out.println("搜索功能");
		String sourceName = URLDecoder.decode(request.getParameter("sourceName"),"UTF-8"); 
		String pageNumber = URLDecoder.decode(request.getParameter("page"),"UTF-8"); 
		System.out.println("关键字"+sourceName+"页码"+pageNumber);
		Gson gson = new Gson();
		DataOutputStream output=new DataOutputStream(resp.getOutputStream());
		try {
				
			int page =Integer.parseInt(pageNumber);
			ResourceService resourceService = new ResourceService();
			
			//Resources-- 文件信息的集合
			List<ResourceModel> Resources=resourceService.searchResource(page, sourceName);
			System.out.println(Resources.size());
			ObjectModel objectModel = new ObjectModel(Resources,State);
			output.writeBytes(gson.toJson(objectModel));
		}catch(Exception e){
			State=false;
			ObjectModel objectModel = new ObjectModel(State);
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
		Boolean state;
		public ObjectModel(List<ResourceModel> Resources,Boolean state){
			this.Resources=Resources;
			this.state=state;
		}
		public ObjectModel(boolean state){
			this.state=state;
		}
	}
}