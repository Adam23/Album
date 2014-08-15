package com.bjsxt.album.utils;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class DBUtils {
	public static Session getSession(){
		Configuration config = new AnnotationConfiguration().configure();
		Session session = config.buildSessionFactory().openSession();
		return session;
	}

}
