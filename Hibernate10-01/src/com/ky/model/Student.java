package com.ky.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Hibernate ��Զ�ӳ���ϵʵ��
 * 1����Զ൥��ʵ��     ֻ��һ�����һ�����ü���
 * @author Administrator
 *
 */
public class Student {

	private int id;
	private String name;
	private Set<Course> courses=new HashSet<Course>();
	
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
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	
}
