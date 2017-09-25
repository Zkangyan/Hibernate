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
		session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	}

	@After
	public void tearDown() throws Exception {
		 session.getTransaction().commit(); // 提交事务
		 session.close(); // 关闭session
	}

	//@Test
	public void testSaveClassAndStudent() {
		Class c=new Class();
	    c.setName("08计本");
	    
	    Student s1=new Student();
	    s1.setName("张三");
	    
	    Student s2=new Student();
	    s2.setName("李四");
	    
	    c.getStudents().add(s1);
	    c.getStudents().add(s2);
	    session.save(c);
	}
	
	/**
	 * //根据班级查学生
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
	
	//@Test 学生和班级各添加一条新的数据  并且没有关联
	public void testAdd() {
		Class c=new Class();
		c.setName("09几本");
		Student s1=new Student();
		s1.setName("王五");
		session.save(c);
		session.save(s1);
		
	}
	
	@Test  //使用这一的方法使的两个表进行关联  设置主外键一样的道理
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
