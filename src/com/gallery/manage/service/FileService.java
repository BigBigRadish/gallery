/*
 * @(#)FileService.java 2017-4-10����5:47:39
 * Copyright 2012 juncsoft, Inc. All rights reserved.
 */
package com.gallery.manage.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gallery.manage.dao.FileDao;
import com.gallery.manage.entity.UserBaseInfo;
import com.gallery.manage.entity.UserFile;

/**
 * �ļ�����
 * @modificationHistory.  
 * <ul>
 * <li>radish 2017-4-12����5:47:39 TODO</li>
 * </ul> 
 */
@Service
public class FileService {

	@Resource
	private FileDao dao;
	
	// �б�
	public List<UserFile> getList(HttpServletRequest request) {
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		int userId = 0;
		if (!user.getIsSys()) {	// ϵͳ����Ա
			userId = Integer.valueOf(user.getId());
		}
		return dao.getList(userId, 0);
	}
	// ����
	public boolean save(HttpServletRequest request, UserFile entity) throws Exception {
		UserBaseInfo user = (UserBaseInfo) request.getSession().getAttribute("userEntity");
		entity.setUserId(Integer.valueOf(user.getId()));
		
		// �����ļ�
		MultipartFile file = entity.getFile();
		String fileName = file.getOriginalFilename();	// �ļ���
		
		// ����ļ�������·��
		String localPath = "D:\\Tomcat\\webapps\\gallery\\file" ;
		//File localFile = new File(fileName);
		File localFile = new File(localPath + "_"+Calendar.getInstance().getTimeInMillis() +"_"+ fileName);
		int BUFFER_SIZE = 16 * 1024;
		InputStream in = new BufferedInputStream(file.getInputStream(), BUFFER_SIZE);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(localFile), BUFFER_SIZE);
		byte[] buffer = new byte[BUFFER_SIZE];
		int len = 0;
		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
		// ������·��
		String serverPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		//entity.setContent(serverPath + "/file/" + localFile.getName());
		entity.setContent(localFile.getName());
		int id = dao.save(entity);
		if (id > 0) {
			return true;
		}
		return false;
	}
	// ɾ��
	public void delete(int id) {
		dao.delete(id);
	}
	public static void main(String[] args) {
		try {
			File aafile = new File("D:\\data\\dspData\\logs\\track_info.log");
			System.out.println(aafile.getName());
//			String localPath = "D:\\data\\file\\track_info.log";
//			int BUFFER_SIZE = 16 * 1024;
//			InputStream in = new BufferedInputStream(new FileInputStream(aafile), BUFFER_SIZE);
//			OutputStream out = new BufferedOutputStream(new FileOutputStream(localPath), BUFFER_SIZE);
//			byte[] buffer = new byte[BUFFER_SIZE];
//			int len = 0;
//			while ((len = in.read(buffer)) > 0) {
//				out.write(buffer, 0, len);
//			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
