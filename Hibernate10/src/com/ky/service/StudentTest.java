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
		session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	}

	@After
	public void tearDown() throws Exception {
		 session.getTransaction().commit(); // �ύ����
		 session.close(); // �ر�session
	}

	/**
	 *һ��һӳ���ϵʵ��
	 *1����������ӳ�䣻
	 */
	@Test
	public void testSave1(){
		User user=new User();
		user.setName("����");
		
		Address address=new Address();
		address.setAddress("ĳ�ط�");
		address.setZipcode("43242");
		address.setUser(user);
		
		user.setAddress(address);
		session.save(user);
	}
	
	/**
	 * һ��һӳ���ϵʵ��
	 * 2���������ӳ�䣻
	 */
	@Test
	public void testSave2(){
		User2 user=new User2();
		user.setName("����");
		
		Address2 address=new Address2();
		address.setAddress("ĳ�ط�2");
		address.setZipcode("432422");
		address.setUser(user);
		
		user.setAddress(address);
		session.save(user);
	}
	

}
