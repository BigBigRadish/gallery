/*
 * @(#)NotepadDao.java 2014-4-9上午12:54:09
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gallery.manage.entity.Notepad;

/**
 * 
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-4-9上午12:54:09 TODO</li>
 * </ul> 
 */
@Repository
@SuppressWarnings("unchecked")
public class NotepadDao {

	@Resource
	private BaseDao baseDao;
	
	// 列表
	public List<Notepad> getList(int userId, int id) {
		String sql = "select id,title,content,time,userId from User_Notepad where 1=1";
		if (userId != 0) {
			sql += " and userId=" + userId;
		}
		if (id != 0) {
			sql += " and id=" + id;
		}
		List<Notepad> list = baseDao.findList(sql, new RowMapper<Notepad>(){

			@Override
			public Notepad mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Notepad entity = new Notepad();
				entity.setId(rs.getString("id"));
				entity.setContent(rs.getString("content"));
				entity.setTitle(rs.getString("title"));
				entity.setTime(rs.getString("time"));
				entity.setUserId(rs.getInt("userId"));
				return entity;
			}}
		);
		return list;
	}
	// 保存
	public int save(Notepad entity) {
		String sql = "insert into User_Notepad(title,content,time,userId) values(?,?,getDate(),?)";
		Object[] param = new Object[]{entity.getTitle(), entity.getContent(), entity.getUserId()};
		return (int) baseDao.insertIntoSqlServer(sql, param);
	}
	// 更新
	public int update(Notepad entity) {
		String sql = "update User_Notepad set title=?,content=? where id=?";
		Object[] param = new Object[]{entity.getTitle(), entity.getContent(), entity.getId()};
		return baseDao.executeSQL(sql, param);
	}
	// 删除
	public int delete(int id) {
		String sql = "delete from User_Notepad where id=" + id;
		return baseDao.executeSQL(sql);
	}
}
