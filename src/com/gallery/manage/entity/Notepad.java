/*
 * @(#)Notepad.java 2014-4-9����12:54:54
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.entity;

import java.io.Serializable;

/**
 * ���±�
 * @modificationHistory.  
 * <ul>
 * <li>liqg 2014-4-9����12:54:54 TODO</li>
 * </ul> 
 */

public class Notepad implements Serializable {

	/**
	 * serialVersionUID:TODO����һ�仰�������������ʾʲô��
	 *
	 * @since v 1.1
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String id;	
	private String title;	// ����
	private String content;	// ����
	private String time;	// ʱ��
	private int userId;	// �û�id
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	

}
