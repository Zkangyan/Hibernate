package com.ky.service;


import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ky.model.Student;
import com.ky.util.HibernateUtil;


/**
 * Hibernate 查询方式简介
	1，导航对象图查询方式(级联查询 学生和班级 通过查学生把班级查出来)；
2，OID 查询方式(就是根据id查询 session.get(Class.class, Long.valueOf(1)) session.Load())；
3，本地SQL 查询方式(与本地使用的数据库(oracle mysql)  不能夸数据库)；
4，HQL 查询方式(都是接口)；
5，QBC 查询方式；(Query By Criteria)
本地SQL 查询方式
	1,就是本地数据库的语句
HQL 查询方式
		HQL（Hibernate Query Language）是面向对象的查询语言；是使用最广的一种查询方式；
1，普通查询；
2，带条件查询；
3，使用别名；
4，对结果排序；
5，分页查询；
6，查询单个对象；
7，链式写法；

 * @author Administrator
 *
 */
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

	@Test//本地sql查询   必须进过addEntity(Student.class)  不然不能强转成student
	public void testSQLQuery() {
		String sql="select * from t_student";
		//都是使用接口   必须addEntity(Student.class) 对象化
		Query query=session.createSQLQuery(sql).addEntity(Student.class);
		List studentList=query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test //本地sql查询    必须进过addEntity(Student.class)  不然不能强转成student
	public void testSQLQuery2() {
		String sql="select * from t_student where stuName like :stuName and stuAge=:stuAge";
		Query query=session.createSQLQuery(sql).addEntity(Student.class);
		query.setString("stuName", "张%");
		query.setInteger("stuAge", 10);
		List studentList=query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test //1，普通查询；  HQL
	public void testHQLQuery() {
		String hql="from Student";
		Query query=session.createQuery(hql);
		List<Student> studentList=(List<Student>)query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test  //2，带条件查询；
	public void testHQLQuery2() {
		String hql="from Student where name like :stuName and age=:stuAge";
		Query query=session.createQuery(hql);
		query.setString("stuName", "张%");
		query.setInteger("stuAge", 10);
		List<Student> studentList=(List<Student>)query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test //3，使用别名；  as可省
	public void testHQLQuery3() {
		String hql="from Student as s where s.name like :stuName and s.age=:stuAge";
		Query query=session.createQuery(hql);
		query.setString("stuName", "张%");
		query.setInteger("stuAge", 10);
		List<Student> studentList=(List<Student>)query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test //4，对结果排序；
	public void testHQLQuery4() {
		String hql="from Student order by age desc";
		Query query=session.createQuery(hql);
		List<Student> studentList=(List<Student>)query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test //5，分页查询；
	public void testHQLQuery5() {
		String hql="from Student";
		Query query=session.createQuery(hql);
		query.setFirstResult(1); //起始页
		query.setMaxResults(2);  //最多返回的结果 相当于每页多少条
		List<Student> studentList=(List<Student>)query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test
	public void testHQLQuery6() {
		//6，查询单个对象； 确定只有一个对象  uniqueResult()   查总记录数
		String hql="from Student";
		Query query=session.createQuery(hql);
		query.setFirstResult(1);
		query.setMaxResults(1);
		Student student=(Student)query.uniqueResult();
		System.out.println(student);	
	}
	
	
	@Test 
	public void testHQLQuery7() {
		String hql="from Student as s where s.name like :stuName and s.age=:stuAge";
		Query query=session.createQuery(hql);
		//链式写法；
		List<Student> studentList=(List<Student>)query
				.setString("stuName", "张%")
				.setInteger("stuAge", 10)
				.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
}
