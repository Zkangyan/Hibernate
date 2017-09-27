package com.ky.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Hibernate 多对多映射关系实现
 * 1，多对多单向实现     只在一个类的一方设置集合
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
