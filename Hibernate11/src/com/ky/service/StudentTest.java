package com.ky.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.ky.model.Class;
import com.ky.model.Student;
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
	 * ������������Lazy
	 * Lazy:true (Ĭ��) �ӳټ�����set ��һ�Զ�
	 * Lazy:false ����������set ��һ�Զ�
	 * Lazy:extra ��ǿ�ӳټ����� set ��һ�Զ�
	 *
	 * 
	 * 
	 */
	@Test
	public void testLazy1() {
		//���õ���ʱ����ȥ����  �ӳټ���  Lazy:true (Ĭ��) �ӳټ���
		//Lazy:false ��������  �ڲ�ѯ��ʱ������ִ��
		Class c=(Class)session.get(Class.class, Long.valueOf(1));
		Set<Student> studentList=(Set<Student>)c.getStudents();
		// Lazy:extra ��ǿ�ӳټ��� ��sql�����Ż���  ִֻ����һ��sql
		System.out.println(studentList.size());
		// studentList.iterator();
	}
	
	
	@Test
	public void testLazy2() {
		/**
		 *  Lazy:proxy(Ĭ��) �ӳټ�����many-to-one ���һ
		 *  Lazy:no-proxy �޴����ӳټ�����many-to-one ���һ(��Ҫ����ʱ�ֽ�����ǿ)
		 */
		Student student=(Student)session.get(Student.class, Long.valueOf(1));
		student.getC().getName();
	}
	
	/**
	 * ������������batch-size
	 * 1�������ӳټ����� û�����þ���һ��һ����
	 * batch-size="3" ��������ֱ��һ�����
	 */
	@Test
	public void testBatch1(){
		List<Class> classList=session.createQuery("from Class").list();
		Iterator it=classList.iterator();
		Class c1=(Class)it.next();
		Class c2=(Class)it.next();
		Class c3=(Class)it.next();
		c1.getStudents().iterator();
		c2.getStudents().iterator();
		c3.getStudents().iterator();
	}
	
	@Test //2����������������
	public void testBatch2(){
		List<Class> classList=session.createQuery("from Class").list();
		
	}
	
	/**
	 * ������������Fetch  ��ȡ��ʽ  �õ���˼
	 * 1��Fetch:select(Ĭ��) ��ѯ��ʽ��
	 * 2��Fetch:subselect �Ӳ�ѯ��ʽ��
	 */
	@Test
	public void testFetch1(){
		List<Class> classList=session.createQuery("from Class").list();
		Iterator it=classList.iterator();
		Class c1=(Class)it.next();
		Class c2=(Class)it.next();
		Class c3=(Class)it.next();
		c1.getStudents().iterator();
		c2.getStudents().iterator();
		c3.getStudents().iterator();
	}
	
	@Test //3��Fetch:join �����������Ӳ�ѯ��ʽ��
	public void testFetch2(){
		Class c=(Class)session.get(Class.class, Long.valueOf(1));
	}
}
