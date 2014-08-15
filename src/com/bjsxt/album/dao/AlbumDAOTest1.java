package com.bjsxt.album.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.bjsxt.album.utils.DBUtils;

public class AlbumDAOTest1 {

	@Test
	public void testFindAlbum() {
		Session session = DBUtils.getSession();
		AlbumDAO dao = new AlbumDAO(session);
		List<Object[]> list = dao.findAlbum();
		for(Object o :list){
			System.out.println("========");
		}
	}

}
