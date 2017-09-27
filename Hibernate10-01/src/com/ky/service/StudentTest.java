package com.ky.service;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ky.model.Course;
import com.ky.model.Course2;
import com.ky.model.Student;
import com.ky.model.Student2;
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

	@Test  //1，多对多单向实现；
	public void testSave1(){
		Course course1=new Course();
		course1.setName("语文");
		
		Course course2=new Course();
		course2.setName("数学");
		
		Student student1=new Student();
		student1.setName("张三");
		student1.getCourses().add(course1);
		student1.getCourses().add(course2);
		
		Student student2=new Student();
		student2.setName("李四");
		student2.getCourses().add(course1);
		student2.getCourses().add(course2);
		
		session.save(student1);
		session.save(student2);
	}
	
	@Test  //1，多对多单向实现；
	public void testLoad1(){
		Student student=(Student)session.get(Student.class, 1);
		Set<Course> courses=(Set<Course>)student.getCourses();
		Iterator it=courses.iterator();
		while(it.hasNext()){
			Course c=(Course)it.next();
			System.out.println(c.getName());
		}
	}
	
	
	@Test  //2，多对多双向实现；
	public void testSave2(){
		Course2 course1=new Course2();
		course1.setName("语文");
		
		Course2 course2=new Course2();
		course2.setName("数学");
		
		Student2 student1=new Student2();
		student1.setName("张三");
		student1.getCourses().add(course1);
		student1.getCourses().add(course2);
		
		Student2 student2=new Student2();
		student2.setName("李四");
		student2.getCourses().add(course1);
		student2.getCourses().add(course2);
		
		session.save(student1);
		session.save(student2);
	}
	
	@Test  //2，多对多双向实现；
	public void testLoad2(){
		Course2 course=(Course2)session.get(Course2.class, 1);
		Set<Student2> students=(Set<Student2>)course.getStudents();
		Iterator it=students.iterator();
		while(it.hasNext()){
			Student2 s=(Student2)it.next();
			System.out.println(s.getName());
		}
		
	}
	
	
}
