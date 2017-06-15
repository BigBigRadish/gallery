/*
 * @(#)UserFile.java 2014-4-12下午5:30:57
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.entity;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-4-12下午5:30:57 TODO</li>
 * </ul> 
 */

public class UserFile implements Serializable {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since v 1.1
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String title;	// 标题
	private String content;	// 内容
	private String time;	// 时间
	private int userId;	// 用户id
	
	private MultipartFile file;	// 文件
	
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
