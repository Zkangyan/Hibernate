package com.ky.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ky.model.Class;
import com.ky.model.Student;
import com.ky.util.HibernateUtil;

public class StudentTest {
	
	public static void main(String[] args) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	    
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
	   
	    session.getTransaction().commit(); // �ύ����
	    session.close(); // �ر�session
	}
}
