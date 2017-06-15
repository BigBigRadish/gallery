/*
 * @(#)FavouriteDao.java 2014-4-12下午5:04:19
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gallery.manage.entity.Favourite;

/**
 * 收藏夹
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-4-12下午5:04:19 TODO</li>
 * </ul> 
 */
@Repository
@SuppressWarnings("unchecked")
public class FavouriteDao {

	@Resource
	private BaseDao baseDao;
	
	// 列表
	public List<Favourite> getList(int userId, int id) {
		String sql = "select id,url,memo,time,userId from User_Favourite where 1=1";
		if (userId != 0) {
			sql += " and userId=" + userId;
		}
		if (id != 0) {
			sql += " and id=" + id;
		}
		
		 List<Favourite> list = baseDao.findList(sql, new RowMapper<Favourite>(){

			@Override
			public Favourite mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Favourite entity = new Favourite();
				entity.setId(rs.getString("id"));
				entity.setUrl(rs.getString("url"));
				entity.setMemo(rs.getString("memo"));
				entity.setTime(rs.getString("time"));
				entity.setUserId(rs.getInt("userId"));
				return entity;
			}});
		 return list;
	}
	// 保存
	public int save(Favourite entity) {
		String sql = "insert into User_Favourite(url,memo,time,userId) " +
				"values(?,?,getDate(),?)";
		Object[] param = new Object[]{entity.getUrl(), entity.getMemo(), entity.getUserId()};
		return (int) baseDao.insertIntoSqlServer(sql, param);
	}
	// 更新
	public int update(Favourite entity) {
		String sql = "update User_Favourite set url=?,memo=? where id=?";
		Object[] param = new Object[]{entity.getUrl(), entity.getMemo(), entity.getId()};
		return baseDao.executeSQL(sql, param);
	}
	// 删除
	public int delete(int id) {
		String sql = "delete from User_Favourite where id=" + id;
		return baseDao.executeSQL(sql);
	}
}
