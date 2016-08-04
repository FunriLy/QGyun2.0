package com.qg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
=======
import java.text.SimpleDateFormat;
>>>>>>> fang rui add version 2.0
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qg.model.ResourceModel;
import com.qg.util.SimpleConnectionPool;

/***
 * 
 * @author dragon
 * <pre>
 * 该类与数据库进行交互
 * </pre>
 */
public class ResourceDao {
	/**
	 * 根据id查找文件信息
	 * @param id
	 * @return 文件的对象
	 */
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
=======
	 SimpleDateFormat formatter; 
>>>>>>> fang rui add version 2.0
	public ResourceModel getResourceById(int id){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ResourceModel resource = new ResourceModel();
		String sql="SELECT * FROM resource WHERE resource_id=?";
		 try {
			UserDao userDao = new UserDao();
			conn = SimpleConnectionPool.getConnection();
			stmt=(PreparedStatement) conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			while(rs.next()){
				resource.setResourceName(rs.getString(2));
				resource.setResourceId(id);
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
				resource.setResourceUploadTime(rs.getTimestamp(3));
=======
				resource.setResourceUploadTime(new SimpleDateFormat ("yyyy-MM-dd HH:mm").format(rs.getTimestamp(3)));
>>>>>>> fang rui add version 2.0
				resource.setUploaderId(rs.getInt(4));
				resource.setUploaderName(userDao.getUsernameByUserid(rs.getInt(4)));
				resource.setResourcePath(rs.getString(5));
			}
		 }catch (SQLException e) {
				e.printStackTrace();
				System.out.println("失败");
			}finally{
				close(rs, stmt, conn);
			}
			return resource;
	}
	
	/**
	 * 这是一个将文件信息存入数据库的方法
	 * @param resourceModel 文件的类
	 */
	public void addResource(ResourceModel resourceModel){
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO resource"
				+ "(resource_name,resource_upload_time,user_id,resource_path)"
				+ "VALUES(?,?,?,?)";
		try {
			conn = SimpleConnectionPool.getConnection();
			stmt=(PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, resourceModel.getResourceName());
			stmt.setTimestamp(2,new Timestamp(new Date().getTime()));
			UserDao userDao = new UserDao();
			stmt.setInt(3, userDao.getUseridByUsername(resourceModel.getUploaderName()));
			stmt.setString(4, resourceModel.getResourcePath());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("失败");
		}finally{
			close(null, stmt, conn);
		}
	}
	
	/**
	 * 这是一个删除文件信息的方法
	 * @param resourceId 文件对应的ID
	 */
	public void deleteResource(int resourceId){
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM resource WHERE resource_id=?";
		try {
			conn = SimpleConnectionPool.getConnection();
			stmt=(PreparedStatement) conn.prepareStatement(sql);
			stmt.setInt(1, resourceId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("失败");
		}finally{
			close(null, stmt, conn);
		}
	}
	/**
	 * 这是一个从数据库中查询文件信息的方法
	 * @param pageNumber 当前页码
	 * @return Resource的集合
	 */
	public List<ResourceModel>getResource(int pageNumber){
		Connection conn=null;
		PreparedStatement stmt = null;
		 ResultSet rs = null;
		 List<ResourceModel> resources = new ArrayList<ResourceModel>();
		 int number=(pageNumber-1)*8;
		 String sql = "SELECT * FROM resource ORDER BY resource_id DESC LIMIT ?,16";
		 try {
			 conn = SimpleConnectionPool.getConnection();
				
			stmt=(PreparedStatement) conn.prepareStatement(sql);
			stmt.setInt(1, number);
			rs=stmt.executeQuery();
			while(rs.next()){
				UserDao userDao = new UserDao();
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
				ResourceModel resourceModel = new ResourceModel(rs.getInt(1),rs.getInt(4),rs.getString(2),rs.getTimestamp(3),rs.getString(5));
=======
				ResourceModel resourceModel = new ResourceModel(rs.getInt(1),rs.getInt(4),rs.getString(2),new SimpleDateFormat ("yyyy-MM-dd HH:mm").format(rs.getTimestamp(3)),rs.getString(5));
				rs.getTimestamp(3);
				
				
>>>>>>> fang rui add version 2.0
				resourceModel.setUploaderName(userDao.getUsernameByUserid(rs.getInt(4)));
				resources.add(resourceModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("失败");
		}finally{
			close(rs, stmt, conn);
		}
		return resources;
	}
	/**
	 * 这是一个重新命名文件的方法
	 * @param resourceId 文件所对应的ID
	 * @param newName 文件的新名字
	 */
	public void resourceRename(int resourceId,String newName) {
		Connection conn=null;
		PreparedStatement stmt = null;
		String sql = "UPDATE resource SET resource_name=? WHERE resource_id=?";
		try {
			conn = SimpleConnectionPool.getConnection();
			stmt=(PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, newName);
			stmt.setInt(2, resourceId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("失败");
		}finally{
			close(null, stmt, conn);
		}
	}
	/**
	 * 这是一个查询文件名是否会重复的方法
	 * @param resourceName
	 * @return true or false
	 */
    public    boolean  findResource  (String resourceName){
	Connection conn=null;
	PreparedStatement stmt = null;
	 ResultSet rs = null;
	String sql="SELECT COUNT(1) FROM resource WHERE resource_name=?";
	boolean existed=false;
	try{
		conn = SimpleConnectionPool.getConnection();
		stmt=(PreparedStatement) conn.prepareStatement(sql);
		stmt.setString(1, resourceName);
		 rs = stmt.executeQuery();
		if(rs.next()){
			existed=(rs.getInt(1)==1);
			}
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("失败");
		}finally{
			close(rs, stmt, conn);
		}
	return existed;
	}
    /**
     * 这是一个模糊搜索的方法
     * @param pageNumber 当前页码
     * @param resourceName 想搜索的资源名字
     * @return  Resource的集合
     */
    public List<ResourceModel>searchResource(int pageNumber,String resourceName){
		Connection conn=null;
		PreparedStatement stmt = null;
		 ResultSet rs = null;
		 List<ResourceModel> resources = new ArrayList<ResourceModel>();
		 int number=(pageNumber-1)*8;
		 String sql = "SELECT * FROM resource WHERE resource_name LIKE ? ORDER BY resource_id DESC LIMIT ?,16";
		 try {
			conn = SimpleConnectionPool.getConnection();
			stmt=(PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, "%"+resourceName+ "%");
			stmt.setInt(2, number);
			rs=stmt.executeQuery();
			while(rs.next()){
				UserDao userDao = new UserDao();
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
				ResourceModel resourceModel = new ResourceModel(rs.getInt(1),rs.getInt(4),rs.getString(2),rs.getTimestamp(3),rs.getString(5));
=======
				ResourceModel resourceModel = new ResourceModel(rs.getInt(1),rs.getInt(4),rs.getString(2),new SimpleDateFormat ("yyyy-MM-dd HH:mm").format(rs.getTimestamp(3)),rs.getString(5));
>>>>>>> fang rui add version 2.0
				resourceModel.setUploaderName(userDao.getUsernameByUserid(rs.getInt(4)));
				resources.add(resourceModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("失败");
		}finally{
			close(rs, stmt, conn);
		}
		return resources;
	}
    
	/**
	 * 这是一个关闭ResultSet，Statement，Connection的方法
	 * @param rs  ResultSet
	 * @param stat Statement
	 * @param conn  Connection
	 */
    public static void close(ResultSet rs,Statement stat,Connection conn){
        try {
            if(rs!=null)rs.close();
            if(stat!=null)stat.close();
            if(conn!=null)SimpleConnectionPool.pushConnectionBackToPool(conn);
        } catch (SQLException e) {
            e.printStackTrace();
       }
}
	
}
