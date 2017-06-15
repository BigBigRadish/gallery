/*
 * @(#)ContactService.java 2017-4-12下午5:39:42
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gallery.manage.dao.ContactDao;
import com.gallery.manage.entity.Contact;
import com.gallery.manage.entity.ContactGroup;
import com.gallery.manage.entity.UserBaseInfo;

/**
 * 联系人管理
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-4-12下午9:39:42 TODO</li>
 * </ul> 
 */

@Service
public class ContactService {

	@Resource
	private ContactDao dao;
	
	// 列表
	public List<Contact> getList(HttpServletRequest request) {
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		int userId = 0;
		if (!user.getIsSys()) {	// 系统管理员
			userId = Integer.valueOf(user.getId());
		}
		return dao.getContactsList(userId, 0);
	}
	// 根据id获得联系人
	public Contact getById(int id) {
		return dao.getContactsList(0, id).get(0);
	}
	// 保存
	public boolean save(HttpServletRequest request, Contact entity) {
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		entity.setUserId(Integer.valueOf(user.getId()));
		int id = dao.saveContacts(entity);
		if (id > 0) {
			return true;
		}
		return false;
	}
	// 更新
	public boolean update(Contact entity) {
		if (dao.update(entity) > 0) {
			return true;
		}
		return false;
	}
	// 删除
	public void delete(int id) {
		dao.delete(id);
	}
	// 联系人分组列表
	public List<ContactGroup> getGroupList() {
		return dao.getGroupList();
	}
}
