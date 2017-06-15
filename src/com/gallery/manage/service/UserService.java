/*
 * @(#)UserService.java 2017-3-22下午10:48:37
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
 * <li>radish 2017-3-22下午11:48:37 TODO</li>
 * </ul> 
 */

@Service
public class UserService {

	@Resource
	private UserDao userDao;
	/**
	 * 登陆
	 * @author radish
	 * @creationDate. 2017-3-20 下午11:54:48
	 * @param request
	 * @param name	用户名
	 * @param passWd	密码
	 * @return
	 */
	public boolean login(HttpServletRequest request, String name, String passWd) {
		SysUserInfo sysUser = userDao.getSysUserByName(name);
		boolean isSys = false;	// 是否为系统用户
		String id = "";	// 用户id
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
		// 写入用户session
		HttpSession session = request.getSession();
		
		UserBaseInfo user = new UserBaseInfo();
		user.setId(id + "");
		user.setName(name);
		user.setIsSys(isSys);
		session.setAttribute("userEntity", user);
		return true;
	}
	// 注册
	public boolean register(HttpServletRequest request, String name, String passWd) {
		// 保存用户
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
	 * 保存用户
	 * @author radish
	 * @creationDate. 2017-3-22 下午11:57:53 
	 * @param request
	 * @param entity
	 * @return
	 */
	public boolean save(UserBaseInfo entity) {
		userDao.save(entity);
		return true;
	}
	/**
	 * 更新
	 * @author radish
	 * @creationDate. 2017-3-22 下午11:58:44 
	 * @param request
	 * @param entity
	 * @return
	 */
	public boolean update(HttpServletRequest request, UserBaseInfo entity) {
		userDao.update(entity);
		return true;
	}
	/**
	 * 用户列表
	 * @author radish
	 * @creationDate. 2017-3-23 上午12:00:09 
	 * @return
	 */
	public List<UserBaseInfo> getList(int id) {
		return userDao.getUserList(id);
	}
	// 获得用户
	public UserBaseInfo getUser(HttpServletRequest request) {
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		return userDao.getUserByName(user.getName());
	}
	// 删除
	public void delete(int id) {
		userDao.delete(id);
	}
	// 根据用户名获得用户
	public UserBaseInfo getByName(String name) {
		return userDao.getUserByName(name);
	}
}