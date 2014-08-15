package com.bjsxt.album.service;

import java.util.List;

import org.hibernate.Session;

import com.bjsxt.album.dao.AlbumDAO;
import com.bjsxt.album.utils.DBUtils;

public class AlbumService {
	Session session = DBUtils.getSession();
	AlbumDAO dao = new AlbumDAO(session);
	
	public List findAlbum(){
		return dao.findAlbum();
		
	}

}
