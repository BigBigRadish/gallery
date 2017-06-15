/*
 * @(#)FavouriteController.java 2017-4-12下午5:52:19
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

import com.gallery.manage.entity.Favourite;
import com.gallery.manage.service.FavouriteService;

/**
 * 
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-4-12下午5:52:19 TODO</li>
 * </ul> 
 */
@Controller
public class FavouriteController {

	@Resource
	private FavouriteService service;

	// 列表
	@RequestMapping("favourite/list")
	public String list(HttpServletRequest request, ModelMap map) {
		List<Favourite> list = service.getList(request);
		map.put("list", list);

		return "favouriteList";
	}
	
	@RequestMapping("favourite/add")
	public String addPage(String id, ModelMap map) {
		if (!StringUtils.isEmpty(id)) {
			Favourite entity = service.getById(Integer.valueOf(id));
			map.put("entity", entity);
		}

		return "favouriteAdd";
	}
	// 保存或更新
	@RequestMapping("favourite/save")
	public String saveOrUpdate(HttpServletRequest request, Favourite entity, ModelMap map) {
		if (StringUtils.isEmpty(entity.getId())) {	// 保存
			service.save(request, entity);
		} else {
			service.update(entity);
		}
		List<Favourite> list = service.getList(request);
		map.put("list", list);

		return "favouriteList";
	}
	// 删除
	@RequestMapping("favourite/delete")
	public String delete(HttpServletRequest request, int id, ModelMap map) {
		service.delete(id);

		List<Favourite> list = service.getList(request);
		map.put("list", list);

		return "favouriteList";
	}
}
