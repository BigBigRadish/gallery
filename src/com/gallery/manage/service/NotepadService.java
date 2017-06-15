/*
 * @(#)NotepadService.java 2014-4-9上午1:04:49
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gallery.manage.dao.NotepadDao;
import com.gallery.manage.entity.Notepad;
import com.gallery.manage.entity.UserBaseInfo;

/**
 * 
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-4-9上午1:04:49 TODO</li>
 * </ul> 
 */
@Service
public class NotepadService {

	@Resource
	private NotepadDao dao;
	
	// 列表
	public List<Notepad> getList(HttpServletRequest request) {
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		int userId = 0;
		if (!user.getIsSys()) {	// 系统管理员
			userId = Integer.valueOf(user.getId());
		}
		return dao.getList(userId, 0);
	}
	// 根据id获得记事本
	public Notepad getById(int id) {
		return dao.getList(0, id).get(0);
	}
	// 保存
	public boolean save(HttpServletRequest request, Notepad entity) {
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		entity.setUserId(Integer.valueOf(user.getId()));
		int id = dao.save(entity);
		if (id > 0) {
			return true;
		}
		return false;
	}
	// 更新
	public boolean update(Notepad entity) {
		if (dao.update(entity) > 0) {
			return true;
		}
		return false;
	}
	// 删除
	public void delete(int id) {
		dao.delete(id);
	}
	
}
