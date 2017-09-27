package com.ky.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Hibernate 多对多映射关系实现
 * 2，多对多双向实现
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
