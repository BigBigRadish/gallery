/*
 * @(#)UserBaseInfo.java 2014-3-22����11:21:07
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.entity;

import java.io.Serializable;

/**
 * �û�������Ϣ
 * @modificationHistory.  
 * <ul>
 * <li>liqg 2017-3-22����11:21:07 TODO</li>
 * </ul> 
 */

public class UserBaseInfo implements Serializable{

	/**
	 * serialVersionUID:TODO����һ�仰�������������ʾʲô��
	 *
	 * @since v 1.1
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;	// �û���
	private String passWd;	// ����
	private String createTime;	// ����ʱ��
	private String creator;	// ������
	private String updateTime;	// ����ʱ��
	private String modifier;	// ������
	private String userName;	// ����
	private String unit;	// ��λ
	private String email;	// ����
	
	private boolean isSys;	// �Ƿ�Ϊϵͳ����Ա
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getIsSys() {
		return isSys;
	}
	public void setIsSys(boolean isSys) {
		this.isSys = isSys;
	}
	
	

}
