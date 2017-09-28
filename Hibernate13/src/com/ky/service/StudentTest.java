package com.ky.service;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.ky.util.HibernateUtil;


public class StudentTest {

	private SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
	private Session session;
	
	/*@Before
	public void setUp() throws Exception {
		session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	}

	@After
	public void tearDown() throws Exception {
		 session.getTransaction().commit(); // �ύ����
		 session.close(); // �ر�session
	}*/

	/**
	 * sessionһ������
	 */
	@Test
	public void testCache1() {
		Class c=(Class)session.get(Class.class, Long.valueOf(1));
		System.out.println(c.getName());
		//���β�ѯֻ��һ��sql
		Class c2=(Class)session.get(Class.class, Long.valueOf(1));
		System.out.println(c2.getName());
		System.out.println(c==c2); //true  ��ͬһ�����ﵱ��
	}
	
	/**
	 * ��ͬ������
	 */
	@Test
	public void testCache2(){
		Session session1=sessionFactory.openSession();
		session1.beginTransaction();
		Class c=(Class)session1.get(Class.class, Long.valueOf(1));
		System.out.println(c.getName());
		session1.getTransaction().commit();
		session1.close();
		//��������������ѯ���������
		Session session2=sessionFactory.openSession();
		session2.beginTransaction();
		Class c2=(Class)session2.get(Class.class, Long.valueOf(1));
		System.out.println(c2.getName());
		session2.getTransaction().commit();
		session2.close();
		
		System.out.println(c==c2); //false
	}
	
	
	

}
