/*
 * @(#)SysUserInfo.java 2014-3-22����11:23:51
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.entity;

import java.io.Serializable;

/**
 * ϵͳ�û���Ϣ
 * @modificationHistory.  
 * <ul>
 * <li>liqg 2017-3-22����11:23:51 TODO</li>
 * </ul> 
 */

public class SysUserInfo implements Serializable {

	/**
	 * serialVersionUID:TODO����һ�仰�������������ʾʲô��
	 *
	 * @since v 1.1
	 */
	
	private static final long serialVersionUID = 1L;
	
	private int id;	
	private String name;	// �û���
	private String passWd;	// ����
	private String createTime;	// ����ʱ��
	
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
