package com.ky.service;
import com.ky.model.Class;
import com.ky.model.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ky.util.HibernateUtil;

public class StudentTest {
	/**
	 * 
	 * @param args
	 */
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

	@Test //1��save()������һ����ʱ����ת��ɳ־û�����
	public void testSaveClassAndStudent() {
		Class c=new Class(); //��ʱ����
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
	
	/**
	 * 2��load()����VS get()�������Ǹ���OID �����ݿ��м���һ���־û�����
	 * ����1���������ݿ��в�������OID ��Ӧ�ļ�¼��Load()�������׳��쳣����get()��������null;
	 * ����2��load ����Ĭ�ϲ����ӳټ��ز��ԣ�get �������������������ԣ�
	 */
	@Test  
	public void testLoadClass(){
		// Class c=(Class)session.load(Class.class, Long.valueOf(2));  ���ݿ�û�����ݻᱨ��
		Class c=(Class)session.load(Class.class, Long.valueOf(1));
		//����load ���ӳ� ��dugִ��������һ�䶼������ֵ  ��ִ��������ʱ�Ż��������  ���õ�ʱ��Ż����ֵ
		//����ɾ�����  ֻ��Ҫ����   ������Ҫȡ��ֵ   
		System.out.println(c.getStudents());
	}
	
	@Test
	public void testGetClass(){
		// Class c=(Class)session.get(Class.class, Long.valueOf(2)); ���ݿ�û��ֻ�᷵��null
		Class c=(Class)session.get(Class.class, Long.valueOf(1));
		//����get ������ ��dugִ��������һ�����ֵ   һ���Ӱ��������ݻ�ȡ��
		System.out.println(c.getStudents());
	}
	
	@Test //update()������һ���������ת��Ϊ�־û�����
	public void testUpdateClass(){
		Session session1=sessionFactory.openSession();
		session1.beginTransaction();
		Class c=(Class)session1.get(Class.class, Long.valueOf(1));
		session1.getTransaction().commit(); // �ύ����
		session1.close();  //c1�ύ֮��ͱ���������
		
		Session session2=sessionFactory.openSession();
		session2.beginTransaction();
		c.setName("08���������2");
		session2.update(c);
		session2.getTransaction().commit(); // �ύ����
		session2.close(); //����������ɳ־û�����
	}
	
	@Test //saveOrUpdate()����������save()(��������new����������ʱ����ִ��)��update()(����ȥ��������Ķ���ִ��)������
	public void testSaveOrUpdateClass(){
		Session session1=sessionFactory.openSession();
		session1.beginTransaction();
		Class c=(Class)session1.get(Class.class, Long.valueOf(1));
		session1.getTransaction().commit(); // �ύ����
		session1.close();
		
		Session session2=sessionFactory.openSession();
		session2.beginTransaction();
		c.setName("08���������3");
		
		Class c2=new Class();
		c2.setName("09���������3");
		session2.saveOrUpdate(c); // update()
		session2.saveOrUpdate(c2); // save()
		session2.getTransaction().commit(); // �ύ����
		session2.close();
	}
	
	/**
	 * merge()�������ϲ�����
	 */
	@Test
	public void testMergeClass(){
		Session session1=sessionFactory.openSession();
		session1.beginTransaction();
		Class c=(Class)session1.get(Class.class, Long.valueOf(1));
		session1.getTransaction().commit(); // �ύ����
		session1.close();//�Ƚ�������� �־û�
		
		Session session2=sessionFactory.openSession();
		session2.beginTransaction();
	
		//c2��ȡ���ǳ־û�����
		Class c2=(Class)session2.get(Class.class, Long.valueOf(1));
		c.setName("08���������4");
		//session2.update(c);  �������Ѿ���һ���־û�������ִ��ʱ�ᱨ��Ҫ��merge
		session2.merge(c);//��������������Ժϲ��ٸ���

		session2.getTransaction().commit(); // �ύ����
		session2.close();
	}
	
	@Test //�� load��ȡ
	public void testDeleteStudent(){
		Student student=(Student)session.load(Student.class, Long.valueOf(1));
		session.delete(student);
	}
}
