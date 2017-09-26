package com.ky.service;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ky.model.Address;
import com.ky.model.Address2;
import com.ky.model.User;
import com.ky.model.User2;
import com.ky.util.HibernateUtil;



public class StudentTest {

	private SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
	private Session session;
	
	@Before
	public void setUp() throws Exception {
		session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	}

	@After
	public void tearDown() throws Exception {
		 session.getTransaction().commit(); // 提交事务
		 session.close(); // 关闭session
	}

	/**
	 *一对一映射关系实现
	 *1，按照主键映射；
	 */
	@Test
	public void testSave1(){
		User user=new User();
		user.setName("张三");
		
		Address address=new Address();
		address.setAddress("某地方");
		address.setZipcode("43242");
		address.setUser(user);
		
		user.setAddress(address);
		session.save(user);
	}
	
	/**
	 * 一对一映射关系实现
	 * 2，按照外键映射；
	 */
	@Test
	public void testSave2(){
		User2 user=new User2();
		user.setName("李四");
		
		Address2 address=new Address2();
		address.setAddress("某地方2");
		address.setZipcode("432422");
		address.setUser(user);
		
		user.setAddress(address);
		session.save(user);
	}
	

}
