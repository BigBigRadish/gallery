/*
 * @(#)Favourite.java 2014-4-12下午5:02:40
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.entity;

import java.io.Serializable;

/**
 * 收藏夹
 * @modificationHistory.  
 * <ul>
 * <li>liqg 2014-4-12下午5:02:40 TODO</li>
 * </ul> 
 */

public class Favourite implements Serializable {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since v 1.1
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String url;	// URL
	private String memo;	// 备注
	private String time;	// 时间
	private int userId;	// 用户id
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
