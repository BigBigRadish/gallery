/*
 * @(#)NotepadService.java 2014-4-9����1:04:49
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
 * <li>radish 2017-4-9����1:04:49 TODO</li>
 * </ul> 
 */
@Service
public class NotepadService {

	@Resource
	private NotepadDao dao;
	
	// �б�
	public List<Notepad> getList(HttpServletRequest request) {
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		int userId = 0;
		if (!user.getIsSys()) {	// ϵͳ����Ա
			userId = Integer.valueOf(user.getId());
		}
		return dao.getList(userId, 0);
	}
	// ����id��ü��±�
	public Notepad getById(int id) {
		return dao.getList(0, id).get(0);
	}
	// ����
	public boolean save(HttpServletRequest request, Notepad entity) {
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		entity.setUserId(Integer.valueOf(user.getId()));
		int id = dao.save(entity);
		if (id > 0) {
			return true;
		}
		return false;
	}
	// ����
	public boolean update(Notepad entity) {
		if (dao.update(entity) > 0) {
			return true;
		}
		return false;
	}
	// ɾ��
	public void delete(int id) {
		dao.delete(id);
	}
	
}
