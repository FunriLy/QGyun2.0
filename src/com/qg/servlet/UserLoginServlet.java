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
import com.qg.model.UserModel;
import com.qg.service.UserService;
import com.qg.util.TestContextListerUtil;

/**
 * Servlet implementation class UserloginServlet
 */

public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final int success = 1;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		/*
		 * 测试
		 */
		String jsonString = URLDecoder.decode(request.getParameter("orderJson"),"UTF-8");
		Map<String, Boolean> feedback = new HashMap<String, Boolean>();
		UserService userService = new UserService();
		//解析Jsong
		Gson gson = new Gson();
		UserModel user = gson.fromJson(jsonString, UserModel.class);
		
		
		int result = 1;
		//遍历在线用户，防止重复登陆
		for(String t_user_name : TestContextListerUtil.onlineUsersList){
			//如果存在相同的用户名
			if(user.getUserName().equals(t_user_name)){
				result = 0;
			}
		}
		//判断用户是否存在，密码是否正确，是否已经登录
		if( (success == userService.isExist(user.getUserName())) && (success == userService.login(user)) &&  success==result){
			//将用户头像路径返回
			feedback.put("login", true);
			//往在线用户中添加
			TestContextListerUtil.onlineUsersList.add(user.getUserName());
			
			System.out.println("在线用户列表："+TestContextListerUtil.onlineUsersList.toString());
		}
		else {
			/*
			 * 用户已经登录
			 * 用户密码错误
			 * 用户不存在
			 */
			feedback.put("login", false);
		}
		//将数据转化并发送到移动端
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		DataOutputStream outrput = new DataOutputStream(response.getOutputStream());
		
		outrput.writeBytes(gson.toJson(feedback));
		outrput.flush();
		outrput.close();
	}

}
