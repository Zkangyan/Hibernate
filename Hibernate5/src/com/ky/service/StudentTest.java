package com.ky.service;
import com.ky.model.Class;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.ky.util.HibernateUtil;

public class StudentTest {
	/**
	 * 临时状态(transient)：刚用new 语句创建，还没有被持久化，并且不处于Sesssion 的缓存中。处于临时状态
		的Java 对象被称为临时对象。
		持久化状态(persistent)：已经被持久化，并且加入到Session 的缓存中。处于持久化状态的Java 对象被称为
		持久化对象(比如session.save)。
		删除状态(removed)：不再处于Session 的缓存中，并且Session 已经计划将其从数据库中删除。处于删除状
		态的Java 对象被称为删除对象。
		游离状态(detached)：已经被持久化，但不再处于Session 的缓存中。处于游离状态的Java 对象被称为游离对
		象。
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	    Class c1=new Class();
	    c1.setName("08计本");
	    Class c2=new Class();
	    c2.setName("09计本");
	    
	    session.save(c1); // 持久化对象
	    session.save(c2); // 持久化对象
	    
	    session.delete(c2); // 删除对象
	    
	    session.getTransaction().commit(); // 提交事务
	    session.close(); // 关闭session
	    
	    System.out.println(c1.getName()); // 游离对象
	    System.out.println(c2.getName()); // 删除对象
	}
}
