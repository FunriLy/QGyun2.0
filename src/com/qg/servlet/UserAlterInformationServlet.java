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

/**
 * Servlet implementation class AlterUserInformation
 */

public class UserAlterInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int success = 1;  
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAlterInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonString = URLDecoder.decode(request.getParameter("orderJson"),"UTF-8");
		Map<String, Boolean> feedback = new HashMap<String, Boolean>();
		UserService userService = new UserService();
		//解析Json
		Gson gson = new Gson();
		//获得UserModel对象
		UserModel user = gson.fromJson(jsonString, UserModel.class);
		//如果用户名存在，并且储存成功
		if((success == userService.isExist(user.getUserName())) && (success == userService.alterUserInformation(user))){
			feedback.put("alterInf", true);
		}
		else {
			feedback.put("alterInf", false);
		}
		//将map数据转化为Json后，返回移动端
		DataOutputStream output = new DataOutputStream(response.getOutputStream());
		output.writeBytes(gson.toJson(feedback));
		output.close();
	}

}
