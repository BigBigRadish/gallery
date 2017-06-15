/*
 * @(#)ContactDao.java 2017-3-29下午9:18:35
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gallery.manage.entity.Contact;
import com.gallery.manage.entity.ContactGroup;

/**
 * 通讯录管理
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-3-29下午9:18:35 TODO</li>
 * </ul> 
 */

@Repository
@SuppressWarnings("unchecked")
public class ContactDao {
	@Resource
	private BaseDao baseDao;

	// 通讯录列表
	public List<Contact> getContactsList(int userId, int id) {
		String sql = "select a.id,a.name,a.birthday,a.address,a.mobile,a.qq,a.mail,a.userId,b.name groupName from User_Contact a" +
				" left join User_ContactGroup b on a.groupId=b.id where 1=1";
		if (userId != 0) {
			sql += " and a.userId=" + userId;
		}
		if (id != 0) {
			sql += " and a.id=" + id;
		}
		List<Contact> list = baseDao.findList(sql, new RowMapper<Contact>(){
			@Override
			public Contact mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				Contact entity = new Contact();
				entity.setId(rs.getString("id"));
				entity.setName(rs.getString("name"));
				entity.setBirthday(rs.getString("birthday"));
				entity.setAddress(rs.getString("address"));
				entity.setMobile(rs.getString("mobile"));
				entity.setQq(rs.getString("qq"));
				entity.setMail(rs.getString("mail"));
				entity.setUserId(rs.getInt("userId"));
				entity.setGroupName(rs.getString("groupName"));
				return entity;
			}
		});
		return list;
	}
	// 保存
	public int saveContacts(Contact entity) {
		String sql = "insert into User_Contact(name,birthday,address,mobile,qq,mail,userId,groupId) " +
				"values(?,?,?,?,?,?,?,?)";
		Object[] params = new Object[]{entity.getName(), entity.getBirthday(), entity.getAddress(),
				entity.getMobile(), entity.getQq(), entity.getMail(), entity.getUserId(), entity.getGroupId()};
		return (int) baseDao.insertIntoSqlServer(sql, params);
	}
	// 更新
	public int update(Contact entity) {
		String sql = "update User_Contact set name=?,birthday=?,address=?,mobile=?,qq=?,mail=?,groupId=? " +
				"where id=?";
		Object[] params = new Object[]{entity.getName(), entity.getBirthday(), entity.getAddress(),
				entity.getMobile(), entity.getQq(), entity.getMail(), entity.getGroupId(), entity.getId()};
		return baseDao.executeSQL(sql, params);
	}
	// 删除
	public void delete(int id) {
		String sql = "delete from User_Contact where id=" + id;
		baseDao.executeSQL(sql);
	}
	
	public List<ContactGroup> getGroupList() {
		String sql = "select id,name,time from User_ContactGroup";
		
		List<ContactGroup> list = baseDao.findList(sql, new RowMapper<ContactGroup>(){

			@Override
			public ContactGroup mapRow(ResultSet rs, int arg1)
					throws SQLException {
				// TODO Auto-generated method stub
				ContactGroup entity = new ContactGroup();
				entity.setId(rs.getInt("id"));
				entity.setName(rs.getString("name"));
				entity.setTime(rs.getString("time"));
				return entity;
			}}
		);
		return list;
	}
}
