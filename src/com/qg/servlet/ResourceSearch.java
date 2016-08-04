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
import com.qg.servlet.ResourceGet.ObjectModel;
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
		String json = URLDecoder.decode(request.getParameter("orderJson"),"UTF-8"); 
		Gson gson = new Gson();
		DataOutputStream output=new DataOutputStream(resp.getOutputStream());
		try {
				
			Map<String, String> SourceMap=gson.fromJson(json, new TypeToken<Map<String, String>>(){}.getType());
			//page----页码
			//sourceName-----搜索的文件名
			int page =Integer.parseInt( SourceMap.get("page"));
			String sourceName = SourceMap.get("sourceName");
			ResourceService resourceService = new ResourceService();
			
			//Resources-- 文件信息的集合
			List<ResourceModel> Resources=resourceService.searchResource(page, sourceName);
			ObjectModel objectModel = new ObjectModel(Resources,State);
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
			output.writeUTF(gson.toJson(objectModel));
		}catch(Exception e){
			State=false;
			ObjectModel objectModel = new ObjectModel(State);
			output.writeUTF(gson.toJson(objectModel));
=======
			output.writeBytes(gson.toJson(objectModel));
		}catch(Exception e){
			State=false;
			ObjectModel objectModel = new ObjectModel(State);
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
		Boolean State;
		public ObjectModel(List<ResourceModel> Resources,Boolean State){
			this.Resources=Resources;
			this.State=State;
		}
		public ObjectModel(boolean State){
			this.State=State;
=======
		Boolean state;
		public ObjectModel(List<ResourceModel> Resources,Boolean state){
			this.Resources=Resources;
			this.state=state;
		}
		public ObjectModel(boolean state){
			this.state=state;
>>>>>>> fang rui add version 2.0
		}
	}
}