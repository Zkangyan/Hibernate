package com.ky.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Hibernate ��Զ�ӳ���ϵʵ��
 * 2����Զ�˫��ʵ��
 * @author Administrator
 *
 */
public class Student2 {

	private int id;
	private String name;
	private Set<Course2> courses=new HashSet<Course2>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Course2> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course2> courses) {
		this.courses = courses;
	}
	
	
	
	
}
