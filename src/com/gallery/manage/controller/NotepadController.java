/*
 * @(#)NotepadController.java 2017-4-9����1:10:14
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

import com.gallery.manage.entity.Notepad;
import com.gallery.manage.service.NotepadService;

/**
 * 
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-4-9����1:10:14 TODO</li>
 * </ul> 
 */
@Controller
public class NotepadController {

	@Resource
	private NotepadService service;
	
	// �б�
	@RequestMapping("notepad/list")
	public String list(HttpServletRequest request, ModelMap map) {
		List<Notepad> list = service.getList(request);
		map.put("list", list);
		
		return "notepadList";
	}
	// ��ӻ��޸�ҳ��
	@RequestMapping("notepad/add")
	public String addPage(String id, ModelMap map) {
		if (!StringUtils.isEmpty(id)) {
			Notepad entity = service.getById(Integer.valueOf(id));
			map.put("entity", entity);
		}
		
		return "notepadAdd";
	}
	// ��������
	@RequestMapping("notepad/save")
	public String saveOrUpdate(HttpServletRequest request, Notepad entity, ModelMap map) {
		if (StringUtils.isEmpty(entity.getId())) {	// ����
			service.save(request, entity);
		} else {
			service.update(entity);
		}
		List<Notepad> list = service.getList(request);
		map.put("list", list);
		
		return "notepadList";
	}
	// ɾ��
	@RequestMapping("notepad/delete")
	public String delete(HttpServletRequest request, int id, ModelMap map) {
		service.delete(id);
		
		List<Notepad> list = service.getList(request);
		map.put("list", list);
		
		return "notepadList";
	}
}
