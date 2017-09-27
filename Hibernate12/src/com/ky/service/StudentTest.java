package com.ky.service;


import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ky.model.Student;
import com.ky.util.HibernateUtil;


/**
 *1�� Hibernate ��ѯ��ʽ���
 * 	1����������ͼ��ѯ��ʽ(������ѯ ѧ���Ͱ༶ ͨ����ѧ���Ѱ༶�����)��
 * 	2��OID ��ѯ��ʽ(���Ǹ���id��ѯ session.get(Class.class, Long.valueOf(1)) session.Load())��
 * 	3������SQL ��ѯ��ʽ(�뱾��ʹ�õ����ݿ�(oracle mysql)  ���ܿ����ݿ�)��
 * 	4��HQL ��ѯ��ʽ(���ǽӿ�)��
 * 	5��QBC ��ѯ��ʽ��(Query By Criteria)
 *2������SQL ��ѯ��ʽ
 *	 1,���Ǳ������ݿ�����
 *3�� HQL ��ѯ��ʽ
 * HQL��Hibernate Query Language�����������Ĳ�ѯ���ԣ���ʹ������һ�ֲ�ѯ��ʽ��
 * 	1����ͨ��ѯ��
 * 	2����������ѯ��
 * 	3��ʹ�ñ�����
 * 	4���Խ������
 * 	5����ҳ��ѯ��
 * 	6����ѯ��������
 * 	7����ʽд����
 *4�� QBC ��ѯ��ʽ
 * QBC ��ѯ��ʽ(Query By Criteria)����һ�׽ӿ���ʵ�ֵĲ�ѯ��ʽ��
 * 	1����ͨ��ѯ��
 * 	2����������ѯ��
 *	 3���Խ������
 * 	4����ҳ��ѯ��
 * 	5����ѯ��������
 * 	6����ʽд����
 * @author Administrator
 *
 */
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
	 * ����SQL ��ѯ��ʽ
	 */
	@Test//����sql��ѯ   �������addEntity(Student.class)  ��Ȼ����ǿת��student
	public void testSQLQuery() {
		String sql="select * from t_student";
		//����ʹ�ýӿ�   ����addEntity(Student.class) ����
		Query query=session.createSQLQuery(sql).addEntity(Student.class);
		List studentList=query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test //����sql��ѯ    �������addEntity(Student.class)  ��Ȼ����ǿת��student
	public void testSQLQuery2() {
		String sql="select * from t_student where stuName like :stuName and stuAge=:stuAge";
		Query query=session.createSQLQuery(sql).addEntity(Student.class);
		query.setString("stuName", "��%");
		query.setInteger("stuAge", 10);
		List studentList=query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	/**
	 *  HQL ��ѯ��ʽ
	 *  QBC ��ѯ��ʽ(Query By Criteria)����һ�׽ӿ���ʵ�ֵĲ�ѯ��ʽ
	 * 
	 */
	
	@Test //1����ͨ��ѯ��  HQL
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
	
	@Test  //2����������ѯ��
	public void testHQLQuery2() {
		String hql="from Student where name like :stuName and age=:stuAge";
		Query query=session.createQuery(hql);
		query.setString("stuName", "��%");
		query.setInteger("stuAge", 10);
		List<Student> studentList=(List<Student>)query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test //3��ʹ�ñ�����  as��ʡ
	public void testHQLQuery3() {
		String hql="from Student as s where s.name like :stuName and s.age=:stuAge";
		Query query=session.createQuery(hql);
		query.setString("stuName", "��%");
		query.setInteger("stuAge", 10);
		List<Student> studentList=(List<Student>)query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test //4���Խ������
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
	
	@Test //5����ҳ��ѯ��
	public void testHQLQuery5() {
		String hql="from Student";
		Query query=session.createQuery(hql);
		query.setFirstResult(1); //��ʼҳ
		query.setMaxResults(2);  //��෵�صĽ�� �൱��ÿҳ������
		List<Student> studentList=(List<Student>)query.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	@Test
	public void testHQLQuery6() {
		//6����ѯ�������� ȷ��ֻ��һ������  uniqueResult()   ���ܼ�¼��
		String hql="from Student";
		Query query=session.createQuery(hql);
		query.setFirstResult(1);
		query.setMaxResults(1);
		Student student=(Student)query.uniqueResult();
		System.out.println(student);	
	}
	
	
	@Test  //��ʽд����
	public void testHQLQuery7() {
		String hql="from Student as s where s.name like :stuName and s.age=:stuAge";
		Query query=session.createQuery(hql);
		//��ʽд����
		List<Student> studentList=(List<Student>)query
				.setString("stuName", "��%")
				.setInteger("stuAge", 10)
				.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}		
	}
	
	/**
	 * QBC ��ѯ��ʽ
	 * QBC ��ѯ��ʽ(Query By Criteria)����һ�׽ӿ���ʵ�ֵĲ�ѯ��ʽ��
	 * 1����ͨ��ѯ��
	 * 2����������ѯ��
	 * 3���Խ������
	 * 4����ҳ��ѯ��
	 * 5����ѯ��������
	 * 6����ʽд����
	 */
	
	@Test  //1����ͨ��ѯ��
	public void testQBCQuery1(){
		Criteria criteria=session.createCriteria(Student.class);
		List<Student> studentList=criteria.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}	
	}
	
	@Test  //2����������ѯ��
	public void testQBCQuery2(){
		Criteria criteria=session.createCriteria(Student.class);
		Criterion c1=Restrictions.like("name", "��%");
		Criterion c2=Restrictions.eq("age", 10);
		criteria.add(c1);
		criteria.add(c2);
		List<Student> studentList=criteria.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}	
	}
	
	@Test //3���Խ������
	public void testQBCQuery3(){
		Criteria criteria=session.createCriteria(Student.class);
		criteria.addOrder(Order.desc("age"));
		List<Student> studentList=criteria.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}	
	}
	
	@Test //4����ҳ��ѯ��
	public void testQBCQuery4(){
		Criteria criteria=session.createCriteria(Student.class);
		criteria.setFirstResult(2);
		criteria.setMaxResults(2);
		List<Student> studentList=criteria.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}	
	}
	
	@Test //5����ѯ��������
	public void testQBCQuery5(){
		Criteria criteria=session.createCriteria(Student.class);
		criteria.setFirstResult(2);
		criteria.setMaxResults(1);
		Student student=(Student)criteria.uniqueResult();
		System.out.println(student);
	}
	
	
	@Test //6����ʽд����
	public void testQBCQuery6(){
		Criteria criteria=session.createCriteria(Student.class);
		List<Student> studentList=criteria
				.setFirstResult(0)
				.setMaxResults(2)
				.list();
		Iterator it=studentList.iterator();
		while(it.hasNext()){
			Student s=(Student)it.next();
			System.out.println(s);
		}	
	}
	
	
	
	
}
