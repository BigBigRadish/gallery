/*
 * @(#)FavouriteService.java 2014-4-12����5:30:14
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gallery.manage.dao.FavouriteDao;
import com.gallery.manage.entity.Favourite;
import com.gallery.manage.entity.UserBaseInfo;

/**
 * �ղؼ�
 * @modificationHistory.  
 * <ul>
 * <li>liqg 2014-4-12����5:30:14 TODO</li>
 * </ul> 
 */
@Service
public class FavouriteService {

	@Resource
	private FavouriteDao dao;
	
	// ����б�
	public List<Favourite> getList(HttpServletRequest request) {
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		int userId = 0;
		if (!user.getIsSys()) {	// ϵͳ����Ա
			userId = Integer.valueOf(user.getId());
		}
		return dao.getList(userId, 0);
	}
	// ����id����ղؼ�
	public Favourite getById(int id) {
		return dao.getList(0, id).get(0);
	}
	// ����
	public boolean save(HttpServletRequest request, Favourite entity) {
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		entity.setUserId(Integer.valueOf(user.getId()));
		int id = dao.save(entity);
		if (id > 0) {
			return true;
		}
		return false;
	}
	// ����
	public boolean update(Favourite entity) {
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
