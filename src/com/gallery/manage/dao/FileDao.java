/*
 * @(#)FileDao.java 2014-4-12下午5:30:28
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gallery.manage.entity.UserFile;

/**
 * 文件管理
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-4-12下午5:30:28 TODO</li>
 * </ul> 
 */
@Repository
@SuppressWarnings("unchecked")
public class FileDao {

	@Resource
	private BaseDao baseDao;
	
	// 列表
	public List<UserFile> getList(int userId, int id) {
		String sql = "select id,title,content,time,userId from User_file where 1=1";
		if (userId != 0) {
			sql += " and userId=" + userId;
		}
		if (id != 0) {
			sql += " and id=" + id;
		}
		List<UserFile> list = baseDao.findList(sql, new RowMapper<UserFile>() {

			@Override
			public UserFile mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				UserFile entity = new UserFile();
				entity.setId(rs.getString("id"));
				entity.setTitle(rs.getString("title"));
				entity.setContent(rs.getString("content"));
				entity.setTime(rs.getString("time"));
				entity.setUserId(rs.getInt("userId"));
				return entity;
			}
		});
		return list;
	}
	// 保存
	public int save(UserFile entity) {
		String sql = "insert into User_file(title,content,time,userId) " +
				"values(?,?,getDate(),?)";
		Object[] param = new Object[]{entity.getTitle(), entity.getContent(), entity.getUserId()};
		return (int) baseDao.insertIntoSqlServer(sql, param);
	}
	// 删除
	public int delete(int id) {
		String sql = "delete from User_file where id=" + id;
		return baseDao.executeSQL(sql);
	}
}
