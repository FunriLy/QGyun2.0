package com.qg.servlet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

import com.qg.model.*;
import com.qg.service.UserService;

public class DownloadPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user_name = request.getParameter("user_name");
		// 去数据库查询
		UserService userService = new UserService();
		UserModel userModel = userService.getUserModelByUsername(user_name);
		
		// 开始下载
		try {
		response.setContentType(getServletContext().getMimeType(
				userModel.getUserPicture()));
			String filename = userModel.getUserPicture();				
			
			String agent = request.getHeader("User-Agent");
			if (agent.contains("MSIE")) {
				// IE 浏览器 采用URL编码
				filename = URLEncoder.encode(filename, "utf-8");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + filename);
			} else if (agent.contains("Mozilla")) {
				// 火狐浏览器 采用Base64编码
				BASE64Encoder base64Encoder = new BASE64Encoder();
				filename = "=?UTF-8?B?"
						+ new String(base64Encoder.encode(filename
								.getBytes("UTF-8"))) + "?=";

				response.setHeader("Content-Disposition",
						"attachment;filename=" + filename);
			} else {
				// 默认 不编码
				response.setHeader("Content-Disposition",
						"attachment;filename=" + filename);
			}

			
			// 流拷贝
			OutputStream out = response.getOutputStream();
			InputStream in = new BufferedInputStream(new FileInputStream(
					getServletContext().getRealPath(
							"/jpg" +  "/"+userModel.getUserPicture())));
			int temp;
			while ((temp = in.read()) != -1) {
				out.write(temp);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
