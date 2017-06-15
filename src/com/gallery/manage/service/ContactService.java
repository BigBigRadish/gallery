/*
 * @(#)ContactService.java 2017-4-12����5:39:42
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
 * ��ϵ�˹���
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-4-12����9:39:42 TODO</li>
 * </ul> 
 */

@Service
public class ContactService {

	@Resource
	private ContactDao dao;
	
	// �б�
	public List<Contact> getList(HttpServletRequest request) {
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		int userId = 0;
		if (!user.getIsSys()) {	// ϵͳ����Ա
			userId = Integer.valueOf(user.getId());
		}
		return dao.getContactsList(userId, 0);
	}
	// ����id�����ϵ��
	public Contact getById(int id) {
		return dao.getContactsList(0, id).get(0);
	}
	// ����
	public boolean save(HttpServletRequest request, Contact entity) {
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		entity.setUserId(Integer.valueOf(user.getId()));
		int id = dao.saveContacts(entity);
		if (id > 0) {
			return true;
		}
		return false;
	}
	// ����
	public boolean update(Contact entity) {
		if (dao.update(entity) > 0) {
			return true;
		}
		return false;
	}
	// ɾ��
	public void delete(int id) {
		dao.delete(id);
	}
	// ��ϵ�˷����б�
	public List<ContactGroup> getGroupList() {
		return dao.getGroupList();
	}
}
