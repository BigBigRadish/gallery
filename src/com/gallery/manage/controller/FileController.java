/*
 * @(#)FileController.java 2017-4-12下午5:57:26
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gallery.manage.entity.UserFile;
import com.gallery.manage.service.FileService;

/**
 * 
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-4-12下午5:57:26 TODO</li>
 * </ul> 
 */
@Controller
public class FileController {

	@Resource
	private FileService service;
	
	// 列表
	@RequestMapping("file/list")
	public String getList(HttpServletRequest request, ModelMap map) {
		List<UserFile> list = service.getList(request);
		map.put("list", list);

		return "fileList";
	}
	// 保存
	@RequestMapping("file/save")
	public String save(HttpServletRequest request, UserFile entity, ModelMap map) {
		try {
			service.save(request, entity);
			List<UserFile> list = service.getList(request);
			map.put("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fileList";
	}
	// 删除
	@RequestMapping("file/delete")
	public String delete(HttpServletRequest request, int id, ModelMap map) {
		service.delete(id);

		List<UserFile> list = service.getList(request);
		map.put("list", list);

		return "fileList";
	}
}
