package com.ky.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ky.model.Student;
import com.ky.util.HibernateUtil;

public class StudentTest2 {
	
	public static void main(String[] args) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	    
	    Student s=new Student();
	    s.setName("����");
	    session.save(s);
	    
	    session.getTransaction().commit(); // �ύ����
	    session.close(); // �ر�session
	}
}
