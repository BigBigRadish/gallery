/*
 * @(#)IndexController.java 2017-4-12下午3:27:25
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gallery.manage.entity.Contact;
import com.gallery.manage.entity.UserBaseInfo;
import com.gallery.manage.service.ContactService;
import com.gallery.manage.service.UserService;

/**
 * 首页
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-4-12下午3:27:25 TODO</li>
 * </ul> 
 */

@Controller
public class IndexController {
	
	@Resource
	private UserService userService;
	@Resource
	private ContactService contactService;
	/**
	 * 首页
	 * @author radish
	 * @creationDate. 2017-4-12 下午3:31:56 
	 * @return
	 */
	@RequestMapping("index")
	public String index() {
		return "login";
	}
	/**
	 * 登陆
	 * @author radish
	 * @creationDate. 2017-3-22 下午11:18:46 
	 * @param user
	 * @param passWd
	 * @return
	 */
	@RequestMapping("login")
	public String login(HttpServletRequest request, String name, String passWd, ModelMap modelMap) {
		boolean result = userService.login(request, name, passWd);
		if (!result) {
			modelMap.put("msg", "用户名或密码错误！");
			return "login";
		}
		UserBaseInfo entity = userService.getUser(request);
		modelMap.put("entity", entity);
		// 判断用户是否为系统用户 
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		if (user.getIsSys()) {
			List<UserBaseInfo> userList = userService.getList(0);
			modelMap.put("userList", userList);
			return "userList";
		}
		return "personalInfo";
	}
	/**
	 * 注册
	 * @author radish
	 * @creationDate. 2017-4-12下午10:31:20 
	 * @return
	 */
	@RequestMapping("register")
	public String register(HttpServletRequest request, String name, String passWd, ModelMap modelMap) {
		// 判断用户是否存在
		UserBaseInfo oldUser = userService.getByName(name);
		if (oldUser != null) {
			modelMap.put("msg", "注册失败，该用户已存在！");
			return "register";
		}
		boolean result = userService.register(request, name, passWd);
		if (!result) {
			modelMap.put("msg", "注册失败，请稍后重试！");
			return "register";
		}
		// 判断用户是否为系统用户 
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		if (user.getIsSys()) {
			List<UserBaseInfo> userList = userService.getList(0);
			modelMap.put("userList", userList);
			return "userList";
		}
		UserBaseInfo entity = userService.getUser(request);
		modelMap.put("entity", entity);
		return "personalInfo";
	}
	// 完善个人信息
	@RequestMapping("personalInfo")
	public String personalInfo(HttpServletRequest request, UserBaseInfo user, ModelMap map) {
		UserBaseInfo userEntity = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		user.setId(userEntity.getId());
		userService.update(request, user);
		// 联系人列表
		List<Contact> list = contactService.getList(request);
		map.put("list", list);
		return "contactsList";
	}
	// 个人信息
	@RequestMapping("personal")
	public String personal(HttpServletRequest request, ModelMap map) {
		UserBaseInfo entity = userService.getUser(request);
		map.put("entity", entity);
		return "personalInfo";
	}
	// 用户列表
	@RequestMapping("user/userList")
	public String userList(ModelMap map) {
		List<UserBaseInfo> userList = userService.getList(0);
		map.put("userList", userList);
		return "userList";
	}
	// 添加用户页面
	@RequestMapping("user/userAdd")
	public String addUserPage(String id, ModelMap map) {
		if (!StringUtils.isEmpty(id)) {
			UserBaseInfo entity = userService.getList(Integer.valueOf(id)).get(0);
			map.put("entity", entity);
		}
		return "userAdd";
	}
	// 保存用户
	@RequestMapping("user/saveUser")
	public String saveUser(HttpServletRequest request, UserBaseInfo user, ModelMap map) {
		if (StringUtils.isEmpty(user.getId())) {
			userService.save(user);
		} else {
			userService.update(request, user);
		}
		List<UserBaseInfo> userList = userService.getList(0);
		map.put("userList", userList);
		return "userList";
	}
	// 删除
	@RequestMapping("user/delete")
	public String deleteUser(int id, ModelMap map) {
		userService.delete(id);
		List<UserBaseInfo> userList = userService.getList(0);
		map.put("userList", userList);
		return "userList";
	}
}
