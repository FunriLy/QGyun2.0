package com.qg.model;

<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
import java.util.Date;
=======
>>>>>>> fang rui add version 2.0
/***
 * 
 * @author dragon
 *<pre>
 *这是一个关于资料的类
 *</pre>
 */
public class ResourceModel {

	private int resourceId;
	private String uploaderName;
	private int uploaderId;
	private String resourceName;
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
	private Date resourceUploadTime;
	private String resourcePath;
	
	public ResourceModel(){}
	public ResourceModel(int resourceId,int uploaderId,String resourceName,Date resourceUploadTime,String resourcePath){
=======
	private String resourceUploadTime;
	private String resourcePath;
	
	public ResourceModel(){}
	public ResourceModel(int resourceId,int uploaderId,String resourceName,String resourceUploadTime,String resourcePath){
>>>>>>> fang rui add version 2.0
		this.resourceId=resourceId;
		this.uploaderId=uploaderId;
		this.resourceName=resourceName;
		this.resourcePath=resourcePath;
		this.resourceUploadTime=resourceUploadTime;
	}
//	public ResourceModel(int uploaderId,String resourceName,Date resourceUploadTime,String resourcePath){
//		this.uploaderId=uploaderId;
//		this.resourceName=resourceName;
//		this.resourcePath=resourcePath;
//		this.resourceUploadTime=resourceUploadTime;
//	}
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
//	public ResourceModel(String uploaderName,String resourceName){
//		this.uploaderName=uploaderName;
//		this.resourceName=resourceName;
//		this.resourcePath="";
//	}
=======
	public ResourceModel(String uploaderName,String resourceName){
		this.uploaderName=uploaderName;
		this.resourceName=resourceName;
		this.resourcePath="";
	}
>>>>>>> fang rui add version 2.0
	/**
	 * 这是一个设置上传者名字的方法
	 * @param uploaderName
	 */
	public void setUploaderName(String uploaderName){
		this.uploaderName=uploaderName;
	}
	/***
	 * 这是一个获得上传者名字的方法
	 * @return
	 */
	public String getUploaderName(){
		return uploaderName;
		}
	/***
	 * 这是一个设置资源ID的方法
	 * @param resourceId
	 */
	public void setResourceId(int resourceId){
		this.resourceId=resourceId;
	}
	
	/**
	 * 这是一个获取资源ID的的方法
	 * @return
	 */
	public int getResourceId() {
		return resourceId;
	}
	/**
	 * 这是一个获取上传者的方法
	 * @return 上传者的名字
	 */
	public int getUploaderId() {
		return uploaderId;
	}
	/**
	 * 这是一个获取资料名字的方法
	 * @return 文件名字
	 */
	public String getResourceName() {
		return resourceName;
	}
	/**
	 * 这是一个获取上传文件路径的方法
	 * @return 文件的路径
	 */
	public String getResourcePath() {
		return resourcePath;
	}
	/**
	 * 这是一个获取上传时间的方法
	 * @return 文件上传的时间
	 */
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
	public Date getResourceUploadTime(){
=======
	public String getResourceUploadTime(){
>>>>>>> fang rui add version 2.0
		return resourceUploadTime;
	}
	/**
	 * 这是一个设置文件上传者的方法
	 * @param uploader 文件上传者的名字
	 */
	public void setUploaderId(int uploaderId) {
		this.uploaderId = uploaderId;
	}
	/**
	 * 这是一个设置文件名字的方法
	 * @param resourceName 文件名字
	 */
	public void setResourceName(String resourceName){
		this.resourceName = resourceName;
	}
	/**
	 * 这是一个设置文件存储路径的方法
	 * @param resourcePath 文件存储的路径
	 */
	public void setResourcePath(String resourcePath){
		this.resourcePath = resourcePath;
	}
	/**
	 * 这是一个设置文件上传时间的方法
	 * @param resourceUploadTime 文件上传的时间
	 */
<<<<<<< a0a6b20c8a993bf00417359da73686b7ca4dabbe
	public void setResourceUploadTime(Date resourceUploadTime){
=======
	public void setResourceUploadTime(String resourceUploadTime){
>>>>>>> fang rui add version 2.0
		this.resourceUploadTime=resourceUploadTime;
	}
}