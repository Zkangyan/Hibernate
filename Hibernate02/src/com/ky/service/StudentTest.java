package com.ky.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ky.model.Student;
import com.ky.util.HibernateUtil;

public class StudentTest {
	
	private SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
	
	private void add(){
	    Session session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	    
	    Student s=new Student();
	    s.setName("����");
	    session.save(s);
	    
	    session.getTransaction().commit(); // �ύ����
	    session.close(); // �ر�session
	}
	
	private void delete(){
		Session session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	    
	    Student student=(Student)session.get(Student.class, Long.valueOf(1));
	    session.delete(student);
	    
	    session.getTransaction().commit(); // �ύ����
	    session.close(); // �ر�session
	}
	
	private void update(){
		Session session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	    
	    Student student=(Student)session.get(Student.class, Long.valueOf(2));
	    student.setName("����2");
	    session.save(student);
	    
	    session.getTransaction().commit(); // �ύ����
	    session.close(); // �ر�session
	}
	
	private void getAllStudent(){
		Session session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	    
	    String hql="from Student";
	    Query query=session.createQuery(hql);
	    List<Student> studentList=query.list();
	    for(Student student:studentList){
	    	System.out.println(student);
	    }
	    
	    session.getTransaction().commit(); // �ύ����
	    session.close(); // �ر�session
	}

	public static void main(String[] args) {
		StudentTest studentTest=new StudentTest();
		// studentTest.add();
		// studentTest.delete();
		// studentTest.update();
		studentTest.getAllStudent();
	}
}
