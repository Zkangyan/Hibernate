package com.ky.service;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ky.model.Class;
import com.ky.model.Student;
import com.ky.util.HibernateUtil;

public class StudentTest3 {

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

	@Test
	public void testSaveClassAndStudent() {
		Class c=new Class();
	    c.setName("08�Ʊ�");
	    session.save(c);
	    
	    Student s1=new Student();
	    s1.setName("����");
	    s1.setC(c);
	    
	    Student s2=new Student();
	    s2.setName("����");
	    s2.setC(c);
	    
	    session.save(s1);
	    session.save(s2);
	}
	
	@Test
	public void testSaveClassAndStudentWithCascade() {
		//��ʱ����   û��save  
		/**
		 * ���Ҫʵ�����ַ�������Ҫ�������ļ���
		 * <many-to-one name="c" column="classId" class="com.java1234.model.Class" cascade="save-update"></many-to-one>
		 */
		Class c=new Class();
	    c.setName("08�Ʊ�");
	    
	    Student s1=new Student();
	    s1.setName("����");
	    s1.setC(c);
	    
	    Student s2=new Student();
	    s2.setName("����");
	    s2.setC(c);
	    
	    session.save(s1);
	    session.save(s2);
	}

}
