package com.bjsxt.album.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bjsxt.album.entity.Album;
import com.bjsxt.album.utils.DBUtils;

public class AlbumDAO {
	 private Session session = null;
	 public AlbumDAO(Session session){
		 this.session = session;
	 }

	 public void save(Album album){
		 session.save(album);
	 }
	 
	 public List findAlbum(){
		 String hql = "from Album a ,Photo p where a.albumId=p.albumId and photoId in (select min(photoId) from Photo group by albumId) ";
		 List<Object[]> list = session.createQuery(hql).list();
		 return list;
	 }
	 
	 public static void main(String[] args){
		 Session session =  DBUtils.getSession();
		 Transaction tx = session.beginTransaction();
		 AlbumDAO dao = new AlbumDAO(session);
		 for(int i = 0;i<10;i++){
			 
		 }
	 }
}
