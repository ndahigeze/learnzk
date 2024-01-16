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

@Component("sidebarPageConfigPagebase")
@Scope(value="request",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SidebarPageConfigPagebasedImpl implements SidebarPageConfig{
	
	HashMap<String, SidebarPage> pageMap = new LinkedHashMap<String,SidebarPage>();
	public SidebarPageConfigPagebasedImpl(){
//		pageMap.put("fn1",new SidebarPage("fn1","Add Todo","/imgs/fn.png","/chapter4/add-todo.zul"));
//		pageMap.put("fn2",new SidebarPage("fn2","Update Todo","/imgs/fn.png","/chapter4/add-update-todo.zul"));
//		pageMap.put("fn3",new SidebarPage("fn3","Todo List","/imgs/fn.png","/chapter4/todolist-mvvm.zul"));
	}
	
	public List<SidebarPage> getPages(){
		return new ArrayList<SidebarPage>(pageMap.values());
	}
	
	public SidebarPage getPage(String name){
		return pageMap.get(name);
	}
	
}