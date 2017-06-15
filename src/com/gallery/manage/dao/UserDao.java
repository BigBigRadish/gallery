/*
 * @(#)UserDao.java 2014-3-22下午11:20:12
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gallery.manage.entity.SysUserInfo;
import com.gallery.manage.entity.UserBaseInfo;

/**
 * 用户管理
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-3-22下午11:20:12 TODO</li>
 * </ul> 
 */

@Repository
@SuppressWarnings("unchecked")
public class UserDao {
	
	@Resource
	private BaseDao baseDao;
	
	/**
	 * 根据用户名获得系统用户
	 * @author radish
	 * @creationDate. 2017-3-22 下午11:31:15 
	 * @param mobile
	 * @return
	 */
	public SysUserInfo getSysUserByName(String name) {
		String sql = "select id,name,passWd from User_ManageInfo where name='" + name + "'";
		List<SysUserInfo> list = baseDao.findList(sql, new RowMapper<SysUserInfo>(){
			@Override
			public SysUserInfo mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				SysUserInfo entity = new SysUserInfo();
				entity.setId(rs.getInt("id"));
				entity.setName(rs.getString("name"));
				entity.setPassWd(rs.getString("passWd"));
				return entity;
			}
			
		});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	/**
	 * 根据用户名获得用户
	 * @author radish
	 * @creationDate. 2014-3-22 下午11:35:40 
	 * @param name
	 * @return
	 */
	public UserBaseInfo getUserByName(String name) {
		String sql = "select id,name,passWd,CreateTime,userName,unit,email from User_BaseInfo where name='" + name + "'";
		List<UserBaseInfo> list = baseDao.findList(sql, new RowMapper<UserBaseInfo>(){
			@Override
			public UserBaseInfo mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				UserBaseInfo entity = new UserBaseInfo();
				entity.setId(rs.getString("id"));
				entity.setName(rs.getString("name"));
				entity.setPassWd(rs.getString("passWd"));
				entity.setCreateTime(rs.getString("CreateTime"));
				entity.setUserName(rs.getString("userName"));
				entity.setUnit(rs.getString("unit"));
				entity.setEmail(rs.getString("email"));
				return entity;
			}
		});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	// 获得用户列表
	public List<UserBaseInfo> getUserList(int id) {
		String sql = "select id,name,passWd,CreateTime,Creator,userName,unit,email from User_BaseInfo ";
		if (id != 0) {
			sql += " where id=" + id;
		}
		List<UserBaseInfo> list = baseDao.findList(sql, new RowMapper<UserBaseInfo>(){
			@Override
			public UserBaseInfo mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				UserBaseInfo entity = new UserBaseInfo();
				entity.setId(rs.getString("id"));
				entity.setName(rs.getString("name"));
				entity.setPassWd(rs.getString("passWd"));
				entity.setCreateTime(rs.getString("CreateTime"));
				entity.setCreator(rs.getString("Creator"));
				entity.setUserName(rs.getString("userName"));
				entity.setUnit(rs.getString("unit"));
				entity.setEmail(rs.getString("email"));
				return entity;
			}
		});
		return list;
	}
	/**
	 * 保存用户
	 * @author radish
	 * @creationDate. 2017-3-22 下午11:43:53 
	 * @param entity
	 * @return
	 */
	public int save(UserBaseInfo entity) {
		String sql = "insert into User_BaseInfo(name,passWd,CreateTime,Creator,userName,unit,email) " +
				"values(?,?,getDate(),?,?,?,?)";
		Object[] params = new Object[]{entity.getName(), entity.getPassWd(), entity.getCreator(), 
				entity.getUserName(), entity.getUnit(), entity.getEmail()};
		
		return (int) baseDao.insertIntoSqlServer(sql, params);
	}
	/**
	 * 更新用户
	 * @author radish
	 * @creationDate. 2014-3-22 下午11:48:19 
	 * @param entity
	 * @return
	 */
	public int update(UserBaseInfo entity) {
		String sql = "update User_BaseInfo set name=?,passWd=?,UpdateTime=getDate(),Modifier=?,userName=?,unit=?,email=? " +
				"where id=?";
		Object[] params = new Object[]{entity.getName(), entity.getPassWd(), entity.getModifier(), 
				entity.getUserName(), entity.getUnit(), entity.getEmail(), entity.getId()};
		
		return baseDao.executeSQL(sql, params);
	}
	
	public void delete(int id) {
		String sql = "delete from User_BaseInfo where id=" + id;
		baseDao.executeSQL(sql);
	}

}
