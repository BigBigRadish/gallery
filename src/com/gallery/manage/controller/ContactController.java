/*
 * @(#)ContactController.java 2017-3-29下午5:56:34
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
import com.gallery.manage.entity.ContactGroup;
import com.gallery.manage.service.ContactService;

/**
 * 联系人
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-3-29下午5:56:34 TODO</li>
 * </ul> 
 */
@Controller
public class ContactController {
	@Resource
	private ContactService contactService;
	
	//联系人列表
	@RequestMapping("contacts/list")
	public String list(HttpServletRequest request, ModelMap map) {
		List<Contact> list = contactService.getList(request);
		map.put("list", list);
		return "contactsList";
	}
	// 添加或更新页面
	@RequestMapping("contacts/updatePage")
	public String updatePage(String id, ModelMap map) {
		if (!StringUtils.isEmpty(id)) {
			Contact entity = contactService.getById(Integer.valueOf(id));
			map.put("entity", entity);
		}
		List<ContactGroup> groupList = contactService.getGroupList();
		map.put("groupList", groupList);
		return "contactsAdd";
	}
	// 保存
	@RequestMapping("contacts/save")
	public String save(HttpServletRequest request, Contact entity, ModelMap map) {
		if (StringUtils.isEmpty(entity.getId())) {	// 保存
			contactService.save(request, entity);
		} else {	// 更新
			contactService.update(entity);
		}
		List<Contact> list = contactService.getList(request);
		map.put("list", list);
		return "contactsList";
	}
	// 删除
	@RequestMapping("contacts/delete")
	public String delete(HttpServletRequest request, int id, ModelMap map) {
		contactService.delete(id);
		List<Contact> list = contactService.getList(request);
		map.put("list", list);
		return "contactsList";
	}
	// 联系人分组列表
	@RequestMapping("contacts/groupList")
	public String ContactGroupList(ModelMap map) {
		List<ContactGroup> groupList = contactService.getGroupList();
		map.put("list", groupList);
		return "contactsGroupList";
	}
}
