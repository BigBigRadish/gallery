/*
 * @(#)UserService.java 2017-3-22����10:48:37
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.gallery.manage.dao.UserDao;
import com.gallery.manage.entity.SysUserInfo;
import com.gallery.manage.entity.UserBaseInfo;

/**
 * 
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-3-22����11:48:37 TODO</li>
 * </ul> 
 */

@Service
public class UserService {

	@Resource
	private UserDao userDao;
	/**
	 * ��½
	 * @author radish
	 * @creationDate. 2017-3-20 ����11:54:48
	 * @param request
	 * @param name	�û���
	 * @param passWd	����
	 * @return
	 */
	public boolean login(HttpServletRequest request, String name, String passWd) {
		SysUserInfo sysUser = userDao.getSysUserByName(name);
		boolean isSys = false;	// �Ƿ�Ϊϵͳ�û�
		String id = "";	// �û�id
		if (sysUser != null) {
			if (!passWd.equals(sysUser.getPassWd())) {
				return false;
			}
			id = sysUser.getId() + "";
			isSys = true;
		} else {
			UserBaseInfo user = userDao.getUserByName(name);
			if (user == null) {
				return false;
			} else {
				if (!passWd.equals(user.getPassWd())) {
					return false;
				}
			}
			id = user.getId();
		}
		// д���û�session
		HttpSession session = request.getSession();
		
		UserBaseInfo user = new UserBaseInfo();
		user.setId(id + "");
		user.setName(name);
		user.setIsSys(isSys);
		session.setAttribute("userEntity", user);
		return true;
	}
	// ע��
	public boolean register(HttpServletRequest request, String name, String passWd) {
		// �����û�
		UserBaseInfo entity = new UserBaseInfo();
		entity.setName(name);
		entity.setPassWd(passWd);
		int id = userDao.save(entity);
		
		entity.setId(id + "");
		entity.setIsSys(false);
		request.getSession().setAttribute("userEntity", entity);
		
		return true;
		
	}
	/**
	 * �����û�
	 * @author radish
	 * @creationDate. 2017-3-22 ����11:57:53 
	 * @param request
	 * @param entity
	 * @return
	 */
	public boolean save(UserBaseInfo entity) {
		userDao.save(entity);
		return true;
	}
	/**
	 * ����
	 * @author radish
	 * @creationDate. 2017-3-22 ����11:58:44 
	 * @param request
	 * @param entity
	 * @return
	 */
	public boolean update(HttpServletRequest request, UserBaseInfo entity) {
		userDao.update(entity);
		return true;
	}
	/**
	 * �û��б�
	 * @author radish
	 * @creationDate. 2017-3-23 ����12:00:09 
	 * @return
	 */
	public List<UserBaseInfo> getList(int id) {
		return userDao.getUserList(id);
	}
	// ����û�
	public UserBaseInfo getUser(HttpServletRequest request) {
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		return userDao.getUserByName(user.getName());
	}
	// ɾ��
	public void delete(int id) {
		userDao.delete(id);
	}
	// �����û�������û�
	public UserBaseInfo getByName(String name) {
		return userDao.getUserByName(name);
	}
}