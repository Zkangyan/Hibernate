package com.ky.service;
import com.ky.model.Class;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.ky.util.HibernateUtil;

public class StudentTest {
	/**
	 * ��ʱ״̬(transient)������new ��䴴������û�б��־û������Ҳ�����Sesssion �Ļ����С�������ʱ״̬
		��Java ���󱻳�Ϊ��ʱ����
		�־û�״̬(persistent)���Ѿ����־û������Ҽ��뵽Session �Ļ����С����ڳ־û�״̬��Java ���󱻳�Ϊ
		�־û�����(����session.save)��
		ɾ��״̬(removed)�����ٴ���Session �Ļ����У�����Session �Ѿ��ƻ���������ݿ���ɾ��������ɾ��״
		̬��Java ���󱻳�Ϊɾ������
		����״̬(detached)���Ѿ����־û��������ٴ���Session �Ļ����С���������״̬��Java ���󱻳�Ϊ�����
		��
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.openSession(); // ����һ��session
	    session.beginTransaction(); // ��������
	    Class c1=new Class();
	    c1.setName("08�Ʊ�");
	    Class c2=new Class();
	    c2.setName("09�Ʊ�");
	    
	    session.save(c1); // �־û�����
	    session.save(c2); // �־û�����
	    
	    session.delete(c2); // ɾ������
	    
	    session.getTransaction().commit(); // �ύ����
	    session.close(); // �ر�session
	    
	    System.out.println(c1.getName()); // �������
	    System.out.println(c2.getName()); // ɾ������
	}
}
