/*
 * @(#)SysUserInfo.java 2014-3-22下午11:23:51
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.entity;

import java.io.Serializable;

/**
 * 系统用户信息
 * @modificationHistory.  
 * <ul>
 * <li>liqg 2017-3-22下午11:23:51 TODO</li>
 * </ul> 
 */

public class SysUserInfo implements Serializable {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since v 1.1
	 */
	
	private static final long serialVersionUID = 1L;
	
	private int id;	
	private String name;	// 用户名
	private String passWd;	// 密码
	private String createTime;	// 创建时间
	
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
	public String getPassWd() {
		return passWd;
	}
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
