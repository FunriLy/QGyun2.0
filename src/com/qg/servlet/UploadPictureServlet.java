package com.qg.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.qg.model.UserModel;
import com.qg.service.UserService;

public class UploadPictureServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserService userService = new UserService();
		Gson gson = new Gson();
		String userName = URLDecoder.decode(request.getParameter("userName"),"UTF-8");
		
		Map<String, Boolean> feedback = new HashMap<String, Boolean>();
		
		UserModel user=userService.getUserModelByUsername(userName);
		
		
//		UserModel user = gson.fromJson(jsonString, UserModel.class);
		
		
		//步骤一：构造工厂
		DiskFileItemFactory factory= new DiskFileItemFactory();
		//步骤二：获得解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 解决上传文件名 乱码问题
		upload.setHeaderEncoding("utf-8");
		//步骤三：对请求内容进行解析
		try {
			List<FileItem> list = upload.parseRequest(request);
			//遍历集合
			for (FileItem fileItem : list) {
				if(fileItem.isFormField()){
					String name = fileItem.getFieldName();
					String value =fileItem.getString("utf-8");
					System.out.println("普通form项："+name+"..."+value);
				}
				else{
					String filename =fileItem.getName();
					InputStream in = new BufferedInputStream(fileItem.getInputStream());
					// 解决老版本浏览器IE6 文件路径存在问题
					if (filename.contains("\\")) {
						filename = filename.substring(filename
								.lastIndexOf("\\") + 1);
					}
					System.out.println("文件上传项："+filename);
					String regex = ".jpg";
					if(filename!=null&&!filename.endsWith(regex)){
						feedback.put("alterInf", false);
						DataOutputStream output = new DataOutputStream(response.getOutputStream());
						output.writeUTF(gson.toJson(feedback));
						return;
					}
					else{
						// 保证上传文件名唯一
						filename = user.getUserId()+".jpg";
					//存在服务器
						File path = new File(getServletContext().getRealPath(
						"/jpg" ));
					
					OutputStream out = new BufferedOutputStream(
							new FileOutputStream(path+"/"+filename));
					int temp;
					while((temp=in.read())!=-1){
						out.write(temp);
					}
					out.close();
					in.close();
					//将信息输入数据库
					user.setUserPicture(filename);
					userService.alterUserPicture(user);
					feedback.put("alterInf", true);
					DataOutputStream output = new DataOutputStream(response.getOutputStream());
					output.writeUTF(gson.toJson(feedback));
					return;
				
				}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
