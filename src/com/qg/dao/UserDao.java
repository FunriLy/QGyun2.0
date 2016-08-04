package com.qg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qg.model.UserModel;
import com.qg.util.SimpleConnectionPool;

/**
 * 
 * @author fangrui
 * <p>
 * UserModel对象数据库处理类
 *  * </p>
 */
public class UserDao {
	
	private Connection connection = null;
	private PreparedStatement pStatement = null;
	
	final static int success = 1;
	final static int fail =0;
	
	/**
	 * 数据库关闭方法
	 */
	private void userDaoClose(){
		try {
			if(pStatement != null){
				pStatement.close();
			}
			if(connection != null){
				SimpleConnectionPool.pushConnectionBackToPool(connection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 判断用户名是否存在
	 * @param user_name 用户名
	 * @return 若用户名存在返回success；否则返回fail。
	 */
	public int isExitst(String user_name){
		
		try {
			connection = SimpleConnectionPool.getConnection();
			String strSql = "select * from users where user_name = ?";
			pStatement = connection.prepareStatement(strSql);
			pStatement.setString(1, user_name);
			ResultSet rSet = pStatement.executeQuery();
			
			if(rSet.next()){
				return success;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			userDaoClose();
		}
		return fail;
	}
	
	/**
	 * 保存UserModel对象到数据库
	 * @param user UserModel对象
	 */
	public void saveUser(UserModel user){
		try {
			connection = SimpleConnectionPool.getConnection();
			String strSql = "insert into users(user_name, user_password, user_picture) value(?,?,?)";
			pStatement = connection.prepareStatement(strSql);
			pStatement.setString(1, user.getUserName());
			pStatement.setString(2, user.getUserPassword());
			/*
			 *  用户注册后，默认用户头像路径ַ
			 */
			pStatement.setString(3, "123");
			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			userDaoClose();
		}
	}
	
	/**
	 *  在用户名已经存在的情况下
	 *  请求用户头像路径
	 * @param user_name 用户名
	 * @return 用户头像路径
	 */
	public String getUserPictureByUsername(String user_name){
		String result = null;
		try {
			connection = SimpleConnectionPool.getConnection();
			String strSql = "select * from users where user_name = ?";
			pStatement = connection.prepareStatement(strSql);
			pStatement.setString( 1, user_name);
			ResultSet rSet = pStatement.executeQuery();
			
			if(rSet.next()){
				result = rSet.getString("user_picture");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			userDaoClose();
		}
		return result;
	}
	
	
	/**
	 * 在用户名已存在的状态下
	 *  通过用户名获得用户信息
	 * @param user_name 用户名
	 * @return 用户名对应的用户id
	 */
	public int getUseridByUsername(String user_name){
		int result = 0;
		try {
			connection = SimpleConnectionPool.getConnection();
			String strSql = "select * from users where user_name = ?";
			pStatement = connection.prepareStatement(strSql);
			pStatement.setString( 1, user_name);
			ResultSet rSet = pStatement.executeQuery();
			
			if(rSet.next()){
				result = rSet.getInt("user_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			userDaoClose();
		}
		return result;
	}
	
	/**
	 *  通过用户id返回用户名
	 * @param user_id 用户id
	 * @return 若存在返回对应的用户名，否则返回null。
	 */
	public String getUsernameByUserid(int user_id){
		String username = null;
		try {
			connection = SimpleConnectionPool.getConnection();
			String strSql = "select * from users where user_id = ?";
			pStatement = connection.prepareStatement(strSql);
			pStatement.setInt( 1, user_id);
			ResultSet rSet = pStatement.executeQuery();
			
			if(rSet.next()){
				username = rSet.getString("user_name");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			userDaoClose();
		}
		return username;
	}
	
	/**
	 * 在用户名存在的状态下
	 * ͨ通过用户名获得用户对象
	 * @param user_name 用户名
	 * @return 返回UserModel对象
	 */
	public UserModel getUserByUsername(String user_name){
		UserModel user = null;
		
		try {
			connection = SimpleConnectionPool.getConnection();
			String strSql = "select * from users where user_name=?";
			pStatement = connection.prepareStatement(strSql);
			pStatement.setString(1, user_name);
			ResultSet rSet = pStatement.executeQuery();
			
			if(rSet.next()){
				String name = rSet.getString("user_name");
				String password = rSet.getString("user_password");
				String picture = rSet.getString("user_picture");
				int id = rSet.getInt("user_id");
				
				user = new UserModel(name, password, picture);
				user.setUserId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			userDaoClose();
		}
		return user;
	}
	
	/**
	 *  通过用户名修改用户密码
	 * @param user_password 修改的用户密码
	 * @param user_name 用户名
	 */
	public void alterUserInformation(String user_password, String user_name){
		try {
			connection = SimpleConnectionPool.getConnection();
			String strSql = "update users set user_password=? where user_name=?";
			pStatement = connection.prepareStatement(strSql);
			pStatement.setString(1, user_password);
			pStatement.setString(2, user_name);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			userDaoClose();
		}
	}

	/**
	 *  修改用户头像路径
	 * @param user_name 用户名
	 * @param user_picture 头像路径
	 */
	public void alterUserPicture(String user_name, String user_picture){
		try {
			connection = SimpleConnectionPool.getConnection();
			String strSql = "update users set user_picture=? where user_name=?";
			pStatement = connection.prepareStatement(strSql);
			pStatement.setString(1, user_picture);
			pStatement.setString(2, user_name);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			userDaoClose();
		}
	}
}
