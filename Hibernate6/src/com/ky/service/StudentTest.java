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
		session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	}

	@After
	public void tearDown() throws Exception {
		 session.getTransaction().commit(); // 提交事务
		 session.close(); // 关闭session
	}

	@Test //1，save()方法将一个临时对象转变成持久化对象；
	public void testSaveClassAndStudent() {
		Class c=new Class(); //临时对象
	    c.setName("08计本");
	   
	    Student s1=new Student();
	    s1.setName("张三");
	    s1.setC(c);
	    
	    Student s2=new Student();
	    s2.setName("李四");
	    s2.setC(c);
	   
	    session.save(s1);
	    session.save(s2);
	    
	}
	
	/**
	 * 2，load()方法VS get()方法都是根据OID 从数据库中加载一个持久化对象。
	 * 区别1：假如数据库中不存在与OID 对应的记录，Load()方法会抛出异常，而get()方法返回null;
	 * 区别2：load 方法默认采用延迟加载策略，get 方法采用立即检索策略；
	 */
	@Test  
	public void testLoadClass(){
		// Class c=(Class)session.load(Class.class, Long.valueOf(2));  数据库没有数据会报错
		Class c=(Class)session.load(Class.class, Long.valueOf(1));
		//采用load 是延迟 用dug执行完上面一句都看不到值  当执行完下面时才会出现数据  在用的时候才会出现值
		//用于删除语句  只需要引用   并不需要取到值   
		System.out.println(c.getStudents());
	}
	
	@Test
	public void testGetClass(){
		// Class c=(Class)session.get(Class.class, Long.valueOf(2)); 数据库没有只会返回null
		Class c=(Class)session.get(Class.class, Long.valueOf(1));
		//采用get 是立即 用dug执行完上面一句就有值   一下子把所有数据获取到
		System.out.println(c.getStudents());
	}
	
	@Test //update()方法将一个游离对象转变为持久化对象；
	public void testUpdateClass(){
		Session session1=sessionFactory.openSession();
		session1.beginTransaction();
		Class c=(Class)session1.get(Class.class, Long.valueOf(1));
		session1.getTransaction().commit(); // 提交事务
		session1.close();  //c1提交之后就变成游离对象
		
		Session session2=sessionFactory.openSession();
		session2.beginTransaction();
		c.setName("08计算机本科2");
		session2.update(c);
		session2.getTransaction().commit(); // 提交事务
		session2.close(); //把游离对象变成持久化对象
	}
	
	@Test //saveOrUpdate()方法包含了save()(传来的是new出来的是临时对象执行)和update()(传进去的是游离的对象执行)方法；
	public void testSaveOrUpdateClass(){
		Session session1=sessionFactory.openSession();
		session1.beginTransaction();
		Class c=(Class)session1.get(Class.class, Long.valueOf(1));
		session1.getTransaction().commit(); // 提交事务
		session1.close();
		
		Session session2=sessionFactory.openSession();
		session2.beginTransaction();
		c.setName("08计算机本科3");
		
		Class c2=new Class();
		c2.setName("09计算机本科3");
		session2.saveOrUpdate(c); // update()
		session2.saveOrUpdate(c2); // save()
		session2.getTransaction().commit(); // 提交事务
		session2.close();
	}
	
	/**
	 * merge()方法，合并对象；
	 */
	@Test
	public void testMergeClass(){
		Session session1=sessionFactory.openSession();
		session1.beginTransaction();
		Class c=(Class)session1.get(Class.class, Long.valueOf(1));
		session1.getTransaction().commit(); // 提交事务
		session1.close();//先将游离对象 持久化
		
		Session session2=sessionFactory.openSession();
		session2.beginTransaction();
	
		//c2获取的是持久化对象
		Class c2=(Class)session2.get(Class.class, Long.valueOf(1));
		c.setName("08计算机本科4");
		//session2.update(c);  缓存中已经有一个持久化对象在执行时会报错要用merge
		session2.merge(c);//把两个对象的属性合并再更新

		session2.getTransaction().commit(); // 提交事务
		session2.close();
	}
	
	@Test //用 load获取
	public void testDeleteStudent(){
		Student student=(Student)session.load(Student.class, Long.valueOf(1));
		session.delete(student);
	}
}
