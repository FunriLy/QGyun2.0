package com.qg.servlet;

<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
=======
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
>>>>>>> fang rui add version 2.0
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
=======
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

>>>>>>> fang rui add version 2.0
import com.google.gson.Gson;
import com.qg.model.ResourceModel;
import com.qg.service.ResourceService;

/**
 * 
 * @author dragon
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
 * <pre>
 * 这是一个添加文件的类
 * </pre>
 */

//http://192.168.1.144:8080/Server/com/qg/servlet/ResourceAdd
public class ResourceAdd extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		//状态标志量
		Map<String, Boolean> state = new HashMap<String, Boolean>();
		try {
			
			//orderJosn----Json对应的参数
			String json = URLDecoder.decode(request.getParameter("orderJson"),"UTF-8"); 
			Gson gson = new Gson();
			ResourceModel Resource = gson.fromJson(json, ResourceModel.class);
			new ResourceService().addResource(Resource);
			state.put("state", true);
		} catch (Exception e) {
			state.put("state", false);
		}finally {
			Gson gson = new Gson();
			DataOutputStream output=new DataOutputStream(resp.getOutputStream());
			output.writeUTF(gson.toJson(state));
			output.close();
		}
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
			doPost(request, resp);
=======
 * 
 *         <pre>
 *         这是一个添加文件的类
 *         </pre>
 */

// http://192.168.1.144:8080/Server/ResourceAdd
public class ResourceAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// 状态标志量
		Map<String, Boolean> state = new HashMap<String, Boolean>();
		DataOutputStream output = new DataOutputStream(resp.getOutputStream());
		Gson gson = new Gson();

		try {
			//转码
			// orderJosn----Json对应的参数
			String json = URLDecoder.decode(request.getParameter("orderJson"), "UTF-8");
			ResourceModel resourceModel = gson.fromJson(json, ResourceModel.class);
//			ResourceModel resourceModel = new ResourceModel("123","456");
			// 步骤一：构造工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 步骤二：获得解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解决上传文件名 乱码问题
			upload.setHeaderEncoding("utf-8");
			// 步骤三：对请求内容进行解析
			List<FileItem> list = upload.parseRequest(request);
			// 遍历集合
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					String name = fileItem.getFieldName();
					String value = fileItem.getString("utf-8");
					System.out.println("普通上传项：" + name + "..." + value);
				} else {
					String filename = fileItem.getName();
					// 截取文件路径获取文件名
					if (filename.contains("\\")) {
						filename = filename.substring(filename.lastIndexOf("\\") + 1);
					}
					System.out.println("文件上传项：" + filename);
					InputStream in = new BufferedInputStream(fileItem.getInputStream());
					if (filename.equals("")) {
						state.put("upload", false);
						output.writeUTF(gson.toJson(state));
						return;
					}

					// 存入文件名
					resourceModel.setResourceName(filename);

					// 获取路径
					File path = new File(getServletContext().getRealPath("/WEB-INF/resource"));
					
					System.out.println(path);
					// 存入文件路径(未使用)
					resourceModel.setResourcePath(null);
					// 将文件写在服务器
					OutputStream out = new BufferedOutputStream(new FileOutputStream(path + "/" + filename));
					int temp;
					while ((temp = in.read()) != -1) {
						out.write(temp);
					}
					out.close();
					in.close();
					new ResourceService().addResource(resourceModel);
					state.put("upload", true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			state.put("upload", false);
		} finally {
			output.writeBytes(gson.toJson(state));
			output.close();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		doPost(request, resp);
>>>>>>> fang rui add version 2.0
	}
}