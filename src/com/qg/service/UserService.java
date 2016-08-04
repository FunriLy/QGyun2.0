package com.qg.service;

import com.qg.dao.UserDao;
import com.qg.model.UserModel;

public class UserService {
	final static int success = 1;
	final static int fail = 0;
	
	UserDao userDao = new UserDao();
	
	/**
	 *  用户注册
	 * @param user UserModel对象
	 * @return 若注册成功返回success，否则返回fail。
	 */
	public int register(UserModel user){
		//用户名已经检验的情况下，将数据储存到数据库
		userDao.saveUser(user);
		//检查数据库中是否存在该数据
		if(success == isExist(user.getUserName())){
			return success;
		}
		return fail;
	}

	/**
	 *  用户登录
	 * @param user 登录的用户UserModel 对象
	 * @return 若成功返回success，否则返回fail。
	 */
	public int login(UserModel user){
		//获得数据库中的数据
		UserModel real_user = userDao.getUserByUsername(user.getUserName());
		//判断用户密码
		if(user.getUserPassword().equals(real_user.getUserPassword())){
			return success;
		}
		
		return fail;
	}
	
	/**
	 * 判断用户名是否存在
	 * @param user_name 用户名
	 * @return 存在返回1，否则返回0.
	 */
	public int isExist(String user_name){
		int result = 0;
		result = userDao.isExitst(user_name);
		return result;
	}
	

	/**
	 * 修改用户密码
	 * @param user 用户对象
	 * @return 修改成功返回success，否则返回fail。
	 */
	public int alterUserInformation(UserModel user){
		//将数据更新到数据库
		userDao.alterUserInformation(user.getUserPassword(), user.getUserName());
		//获得数据库的数据进行比较
		UserModel real_user = userDao.getUserByUsername(user.getUserName());
		if(user.getUserPassword().equals(real_user.getUserPassword())){
			//修改成功
			return success;
		}
		//修改失败
		return fail;
	}
	
	/**
	 * 获取用户头像路径
	 * @param user_name 用户名
	 * @return 用户头像路径
	 */
	public String getUserPictureByUsername(String user_name){
		//通过用户名获取用户头像路径
		return userDao.getUserPictureByUsername(user_name);
	}
	
	/**
	 *  修改用户头像路径
	 * @param user 用户对象
	 */
	public int alterUserPicture(UserModel user){
		userDao.alterUserPicture(user.getUserName(), user.getUserPicture());
		//获得数据库的数据进行比较
		UserModel real_user = userDao.getUserByUsername(user.getUserName());
		if(user.getUserPicture().equals(real_user.getUserPicture())){
			//修改成功
			return success;
		}
		//修改失败
		return fail;
	}
	
	/**
	 * 根据用户名字获得用户对象
	 * @param user_name  用户名
	 * @return 用户对象
	 */
	public UserModel getUserModelByUsername(String user_name){
		UserModel user = null;
		user = userDao.getUserByUsername(user_name);
		return user;
	}
}
