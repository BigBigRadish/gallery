/*
 * @(#)ContactGroup.java 2014-4-8下午7:21:57
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.entity;

import java.io.Serializable;

/**
 * 联系人分组
 * @modificationHistory.  
 * <ul>
 * <li>liqg 2017-4-8下午7:21:57 TODO</li>
 * </ul> 
 */

public class ContactGroup implements Serializable {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since v 1.1
	 */
	
	private static final long serialVersionUID = 1L;
	
	private int id;	
	private String name;
	private String time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}
