package com.qg.service;

import java.io.File;
import java.util.List;

import com.qg.dao.ResourceDao;
import com.qg.model.ResourceModel;
/***
 * 该类具体处理业务逻辑
 * @author dragon
 *
 */
public class ResourceService {
	
	ResourceDao resourceDao = new ResourceDao();
	
	/**
	 * 根据id获取文件对象
	 * @param id 文件id
	 * @return 文件对象
	 */
	public ResourceModel getResourceById(int id){
		return resourceDao.getResourceById(id);
	}
	
	/**
	 * 这是一个将文件信息存入数据库的方法
	 * @param resourceModel 文件的类
	 */
	public void addResource(ResourceModel resourceModel) {
		/*判断数据库中是否会有相同的文件名*/
		if(findResource(resourceModel.getResourceName())){
//			String newName = resourceModel.getResourceName()+resourceModel.getUploaderId()+(int)(Math.random()*1000);
			String newName=resourceModel.getResourceName().substring(0,resourceModel.getResourceName().lastIndexOf("."))
					+resourceModel.getUploaderId()+(int)(Math.random()*100)+
					"."+resourceModel.getResourceName().substring(resourceModel.getResourceName().lastIndexOf(".")+1);
			while(findResource(newName)){
//			newName = resourceModel.getResourceName()+resourceModel.getUploaderId()+(int)(Math.random()*1000);
				newName=resourceModel.getResourceName().substring(0,resourceModel.getResourceName().lastIndexOf("."))
						+resourceModel.getUploaderId()+(int)(Math.random()*100)+
						"."+resourceModel.getResourceName().substring(resourceModel.getResourceName().lastIndexOf(".")+1);
			}
			resourceModel.setResourceName(newName);
			resourceDao.addResource(resourceModel);
		}
		else
		resourceDao.addResource(resourceModel);
	}
	/**
	 * 这是一个删除文件信息的方法
	 * @param resourceId 文件对应的ID
	 */
	public void deleteResource(int resourceId){
		resourceDao.deleteResource(resourceId);
	}
	/**
	 * 这是一个重新命名文件的方法
	 * @param resourceId 文件所对应的ID
	 * @param newName 文件的新名字
	 * @param path 文件路径
	 */
	public boolean resourceRename(File path,int resourceId,String newName){
		/*判断数据库中是否会有相同的文件名*/
		
			if(findResource(newName)){
					while(findResource(newName)){
						if(!newName.contains(".")){
							newName=newName+".";
						}
						newName=newName.substring(0,newName.lastIndexOf("."))+resourceId+(int)(Math.random()*100)+"."+newName.substring(newName.lastIndexOf(".")+1);
					}
				       File oldfile=new File(path+"/"+resourceDao.getResourceById(resourceId).getResourceName());  
				       System.out.println(oldfile);
				       File newfile=new File(path+"/"+newName);
				       System.out.println(newfile);
					   if(oldfile.renameTo(newfile)) {
								 resourceDao.resourceRename(resourceId, newName);
							    System.out.println("已重命名");
							    return true;
							 } else {
							    System.out.println("Error");
							    return false;
							 }
				}
				else
				{
					File oldfile=new File(path+"/"+resourceDao.getResourceById(resourceId).getResourceName());   
					System.out.println("旧名字"+oldfile);
			        File newfile=new File(path+"/"+newName); 
			        System.out.println("新名字"+newfile);
			        if(oldfile.renameTo(newfile)) {
			               	resourceDao.resourceRename(resourceId, newName);
						    System.out.println("已重命名");
						    return true;
						 } else {
						    System.out.println("Error");
						    return false;
						 }
	}
	}
	/**
	 * 这是一个查询文件名是否会重复的方法
	 * @param resourceName
	 * @return true or false
	 */
	public boolean findResource  (String resourceName){
		return resourceDao.findResource(resourceName);
	}
	/**
	 * 这是一个搜索文件的方法（模糊搜索）
	 * @param pageNumber 当前页码
	 * @param resourceName 文件名关键字
	 * @return 文件信息的集合
	 */
	public List<ResourceModel>searchResource(int pageNumber,String resourceName){
		return resourceDao.searchResource(pageNumber, resourceName);
	}
	/**
	 * 这是一个获得文件目录的方法
	 * @param pageNumber 当前页码
	 * @return 文件信息的集合
	 */
	public List<ResourceModel>getResource(int pageNumber){
		return resourceDao.getResource(pageNumber);
	}
	
	/**   
     * 删除单个文件   
     * @param   fileName    被删除文件的文件名   
     * @return 单个文件删除成功返回true,否则返回false   
     */    
    public  boolean deleteFile(String fileName){     
        File file = new File(fileName);     
        if(file.isFile() && file.exists()){     
            file.delete();     
            System.out.println("删除文件"+fileName+"成功！");     
            return true;     
        }else{     
            System.out.println("删除文件"+fileName+"失败！");     
            return false;     
        }     
    } 
   
}
