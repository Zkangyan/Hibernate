package com.ky.service;


import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ky.model.Class;
import com.ky.model.Student;
import com.ky.util.HibernateUtil;

public class StudentTest4 {

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

	//@Test
	public void testSaveClassAndStudent() {
		Class c=new Class();
	    c.setName("08�Ʊ�");
	    
	    Student s1=new Student();
	    s1.setName("����");
	    
	    Student s2=new Student();
	    s2.setName("����");
	    
	    c.getStudents().add(s1);
	    c.getStudents().add(s2);
	    session.save(c);
	}
	
	/**
	 * //���ݰ༶��ѧ��
	 */
	 // @Test 
	public void getStudentByClass() {
		Class c=(Class) session.get(Class.class, Long.valueOf(1));
		Set<Student>students=c.getStudents();
		Iterator it=students.iterator();
		while(it.hasNext()) {
			Student s=(Student) it.next();
			System.out.println(s);
		}
		
	}
	
	//@Test ѧ���Ͱ༶�����һ���µ�����  ����û�й���
	public void testAdd() {
		Class c=new Class();
		c.setName("09����");
		Student s1=new Student();
		s1.setName("����");
		session.save(c);
		session.save(s1);
		
	}
	
	@Test  //ʹ����һ�ķ���ʹ����������й���  ���������һ���ĵ���
	public void testInverse(){
		Class c=(Class)session.get(Class.class, Long.valueOf(2));
		Student s=(Student)session.get(Student.class, Long.valueOf(3));
		s.setC(c);
		c.getStudents().add(s);
	}
	
	@Test
	public void testDeleteClassCascade(){
		Class c=(Class)session.get(Class.class, Long.valueOf(2));
		session.delete(c);
	}

}
