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
		session=sessionFactory.openSession(); // 生成一个session
	    session.beginTransaction(); // 开启事务
	}

	@After
	public void tearDown() throws Exception {
		 session.getTransaction().commit(); // 提交事务
		 session.close(); // 关闭session
	}

	/**
	 * 检索策略属性Lazy
	 * Lazy:true (默认) 延迟检索；set 端一对多
	 * Lazy:false 立即检索；set 端一对多
	 * Lazy:extra 增强延迟检索； set 端一对多
	 *
	 * 
	 * 
	 */
	@Test
	public void testLazy1() {
		//在用到的时候再去加载  延迟检索  Lazy:true (默认) 延迟检索
		//Lazy:false 立即检索  在查询的时候立马执行
		Class c=(Class)session.get(Class.class, Long.valueOf(1));
		Set<Student> studentList=(Set<Student>)c.getStudents();
		// Lazy:extra 增强延迟检索 对sql进行优化了  只执行了一条sql
		System.out.println(studentList.size());
		// studentList.iterator();
	}
	
	
	@Test
	public void testLazy2() {
		/**
		 *  Lazy:proxy(默认) 延迟检索；many-to-one 多对一
		 *  Lazy:no-proxy 无代理延迟检索；many-to-one 多对一(需要编译时字节码增强)
		 */
		Student student=(Student)session.get(Student.class, Long.valueOf(1));
		student.getC().getName();
	}
	
	/**
	 * 检索策略属性batch-size
	 * 1，批量延迟检索； 没有设置就是一条一条查
	 * batch-size="3" 加了数量直接一条查出
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
	
	@Test //2，批量立即检索；
	public void testBatch2(){
		List<Class> classList=session.createQuery("from Class").list();
		
	}
	
	/**
	 * 检索策略属性Fetch  获取方式  拿的意思
	 * 1，Fetch:select(默认) 查询方式；
	 * 2，Fetch:subselect 子查询方式；
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
	
	@Test //3，Fetch:join 迫切左外连接查询方式；
	public void testFetch2(){
		Class c=(Class)session.get(Class.class, Long.valueOf(1));
	}
}
