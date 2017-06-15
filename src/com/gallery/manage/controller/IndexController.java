/*
 * @(#)IndexController.java 2017-4-12����3:27:25
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
 * ��ҳ
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-4-12����3:27:25 TODO</li>
 * </ul> 
 */

@Controller
public class IndexController {
	
	@Resource
	private UserService userService;
	@Resource
	private ContactService contactService;
	/**
	 * ��ҳ
	 * @author radish
	 * @creationDate. 2017-4-12 ����3:31:56 
	 * @return
	 */
	@RequestMapping("index")
	public String index() {
		return "login";
	}
	/**
	 * ��½
	 * @author radish
	 * @creationDate. 2017-3-22 ����11:18:46 
	 * @param user
	 * @param passWd
	 * @return
	 */
	@RequestMapping("login")
	public String login(HttpServletRequest request, String name, String passWd, ModelMap modelMap) {
		boolean result = userService.login(request, name, passWd);
		if (!result) {
			modelMap.put("msg", "�û������������");
			return "login";
		}
		UserBaseInfo entity = userService.getUser(request);
		modelMap.put("entity", entity);
		// �ж��û��Ƿ�Ϊϵͳ�û� 
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		if (user.getIsSys()) {
			List<UserBaseInfo> userList = userService.getList(0);
			modelMap.put("userList", userList);
			return "userList";
		}
		return "personalInfo";
	}
	/**
	 * ע��
	 * @author radish
	 * @creationDate. 2017-4-12����10:31:20 
	 * @return
	 */
	@RequestMapping("register")
	public String register(HttpServletRequest request, String name, String passWd, ModelMap modelMap) {
		// �ж��û��Ƿ����
		UserBaseInfo oldUser = userService.getByName(name);
		if (oldUser != null) {
			modelMap.put("msg", "ע��ʧ�ܣ����û��Ѵ��ڣ�");
			return "register";
		}
		boolean result = userService.register(request, name, passWd);
		if (!result) {
			modelMap.put("msg", "ע��ʧ�ܣ����Ժ����ԣ�");
			return "register";
		}
		// �ж��û��Ƿ�Ϊϵͳ�û� 
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
	// ���Ƹ�����Ϣ
	@RequestMapping("personalInfo")
	public String personalInfo(HttpServletRequest request, UserBaseInfo user, ModelMap map) {
		UserBaseInfo userEntity = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		user.setId(userEntity.getId());
		userService.update(request, user);
		// ��ϵ���б�
		List<Contact> list = contactService.getList(request);
		map.put("list", list);
		return "contactsList";
	}
	// ������Ϣ
	@RequestMapping("personal")
	public String personal(HttpServletRequest request, ModelMap map) {
		UserBaseInfo entity = userService.getUser(request);
		map.put("entity", entity);
		return "personalInfo";
	}
	// �û��б�
	@RequestMapping("user/userList")
	public String userList(ModelMap map) {
		List<UserBaseInfo> userList = userService.getList(0);
		map.put("userList", userList);
		return "userList";
	}
	// ����û�ҳ��
	@RequestMapping("user/userAdd")
	public String addUserPage(String id, ModelMap map) {
		if (!StringUtils.isEmpty(id)) {
			UserBaseInfo entity = userService.getList(Integer.valueOf(id)).get(0);
			map.put("entity", entity);
		}
		return "userAdd";
	}
	// �����û�
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
	// ɾ��
	@RequestMapping("user/delete")
	public String deleteUser(int id, ModelMap map) {
		userService.delete(id);
		List<UserBaseInfo> userList = userService.getList(0);
		map.put("userList", userList);
		return "userList";
	}
}
