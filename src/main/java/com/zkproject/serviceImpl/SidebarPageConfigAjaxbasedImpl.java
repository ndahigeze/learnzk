/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package com.zkproject.serviceImpl;

import com.zkproject.services.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Component("sidebarPageConfigAjaxbased")
@Scope(value="request",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SidebarPageConfigAjaxbasedImpl implements SidebarPageConfig{
	
	HashMap<String, SidebarPage> pageMap = new LinkedHashMap<String,SidebarPage>();
	public SidebarPageConfigAjaxbasedImpl(){
		pageMap.put("home",new SidebarPage("Home","Home","/imgs/fn.png","/home.zul"));
		pageMap.put("fn1",new SidebarPage("fn1","Todos","/imgs/fn.png","/add-todo.zul"));
		pageMap.put("fn4",new SidebarPage("fn4","Add User","/imgs/fn.png","/add-user.zul"));
		pageMap.put("fn5", new SidebarPage("fn5","Users","/imgs/fn.png","/user-list.zul"));
	}
	
	public List<SidebarPage> getPages(){
		return new ArrayList<SidebarPage>(pageMap.values());
	}
	
	public SidebarPage getPage(String name){
		return pageMap.get(name);
	}
	
}