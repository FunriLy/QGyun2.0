package com.qg.util;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TestContextListerUtil implements ServletContextListener {

	public static List<String> onlineUsersList =new LinkedList<String>();
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("==============");
		System.out.println("服务器关闭");
		System.out.println("==============");

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("==============");
		System.out.println("服务器启动");
		System.out.println("==============");
	}

}
