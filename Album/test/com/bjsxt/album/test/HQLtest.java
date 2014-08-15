package com.bjsxt.album.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.bjsxt.album.entity.Album;
import com.bjsxt.album.entity.Photo;
import com.bjsxt.album.utils.DBUtils;


public class HQLtest {
	@Test
	public void testBasic(){
		Session session = DBUtils.getSession();
		Query q = session.createQuery("from Photo");
		List<Photo> list = q.list();
		for(Photo p:list){
			System.out.println(p.getFileName());
		}
		session.close();
	}
	
	@Test
	public void testFindByParm(){
		Session session = DBUtils.getSession();
		Query q = session.createQuery("from Photo where photoId= ? and fileName= ?");
		q.setInteger(0, 4);
		q.setString(1, "4.jpg");
		Photo p =(Photo)q.uniqueResult();
		System.out.println(p.getFileName());
		session.close();
	}
	
	
	@Test  //使用命名参数
	public void testFindByNamedParm(){
		Session session = DBUtils.getSession();
		Query q = session.createQuery("from Photo where photoId= :pid and fileName= :fn");
		q.setInteger("pid", 4);
		q.setString("fn", "4.jpg");
		Photo p =(Photo)q.uniqueResult();
		System.out.println(p.getFileName());
		session.close();
	}
	
	@Test
	public void testJoinFind(){
		System.out.println("testJoinFind()");
		Session session = DBUtils.getSession();
		//
		Query query = session.createQuery("from Photo b , Album a   where a.albumId = b.albumId order by b.photoId asc");
		List<Object[]> list = query.list();
		for(Object[] objs : list){
			Photo p = (Photo)objs[0];
			Album a = (Album)objs[1];
			System.out.println(a.getAlbumName() + ":" + p.getFileName());
		}
		session.close();		
	}
	
	
	@Test
	public void testJoinFindAnyField(){
		System.out.println("testJoinFindAnyField()");
		Session session = DBUtils.getSession();
		Query query = session.createQuery("select a.albumName , b.fileName from Photo b , Album a   where a.albumId = b.albumId order by b.photoId asc");
		List<Object[]> list = query.list();
		for(Object[] objs : list){
			String albumName = objs[0].toString();
			String fileName = objs[1].toString();
			System.out.println(albumName + ":" + fileName);
		}
		session.close();		
	}	
	
	
	@Test
	public void testJoinFindAnyFieldForMap(){
		System.out.println("testJoinFindAnyFieldForMap()");
		Session session = DBUtils.getSession();
		Query query = session.createQuery("select new Map( a.albumName  as albumname , b.fileName as filename) from Photo b , Album a   where a.albumId = b.albumId order by b.photoId asc");
		List<Map> list = query.list();
		for(Map rec : list){
			System.out.println(rec.get("albumname") + ":" + rec.get("filename"));
		}
		session.close();		
	}
	
	@Test  //使用分页查询
	public void testJoinFindPaging(){
		System.out.println("testJoinFindPaging()");
		Session session = DBUtils.getSession();
		Query query = session.createQuery("from Photo b , Album a   where a.albumId = b.albumId order by b.photoId asc");
		query.setFirstResult(1);
		query.setMaxResults(5);
		List<Object[]> list = query.list();
		for(Object[] objs : list){
			Photo p = (Photo)objs[0];
			Album a = (Album)objs[1];
			System.out.println(a.getAlbumName() + ":" + p.getFileName());
		}
		session.close();		
	}
	
	@Test  //使用原生SQL
	public void testNativeSQL(){
		System.out.println("testNativeSQL()");
		Session session = DBUtils.getSession();
		Query query = session.createSQLQuery("select filename , createdate from photo p where p.filename = ?");
		query.setString(0, "4.jpg");
		//查询到的每一条记录都是object数组
		List<Object[]> list = query.list();
		for(Object[] objs : list){
			String fileName = (String)objs[0];
			Date date = (Date)objs[1];
			System.out.println(fileName + ":" + date);
		}
		session.close();	
	}
	
	
	@Test
	public void batchUpdate(){
		System.out.println("batchDelete()");
		Session session = DBUtils.getSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("update Photo set createDate = :cd  where photoId = :id");
		q.setDate("cd", new Date());
		q.setInteger("id", 2);
		int count = q.executeUpdate();
		System.out.println("本次更新了" + count + "条数据");
		tx.commit();
		session.close();	
	}
	
}
