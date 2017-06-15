/*
 * @(#)FavouriteService.java 2014-4-12下午5:30:14
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
 * 收藏夹
 * @modificationHistory.  
 * <ul>
 * <li>liqg 2014-4-12下午5:30:14 TODO</li>
 * </ul> 
 */
@Service
public class FavouriteService {

	@Resource
	private FavouriteDao dao;
	
	// 获得列表
	public List<Favourite> getList(HttpServletRequest request) {
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		int userId = 0;
		if (!user.getIsSys()) {	// 系统管理员
			userId = Integer.valueOf(user.getId());
		}
		return dao.getList(userId, 0);
	}
	// 根据id获得收藏夹
	public Favourite getById(int id) {
		return dao.getList(0, id).get(0);
	}
	// 保存
	public boolean save(HttpServletRequest request, Favourite entity) {
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		entity.setUserId(Integer.valueOf(user.getId()));
		int id = dao.save(entity);
		if (id > 0) {
			return true;
		}
		return false;
	}
	// 更新
	public boolean update(Favourite entity) {
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
