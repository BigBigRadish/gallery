/*
 * @(#)Contact.java 2014-3-29下午9:12:33
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.entity;

import java.io.Serializable;

/**
 * 联系人
 * @modificationHistory.  
 * <ul>
 * <li>liqg 2014-3-29下午9:12:33 TODO</li>
 * </ul> 
 */

public class Contact implements Serializable {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since v 1.1
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;	// 姓名
	private String birthday;	// 生日
	private String address;	// 地址
	private String mobile;	// 电话
	private String qq;	// QQ
	private String mail;	// 邮箱
	private int userId;	// 用户id
	private int groupId;	// 分组id
	private String groupName;	// 分组名称
	
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

}
