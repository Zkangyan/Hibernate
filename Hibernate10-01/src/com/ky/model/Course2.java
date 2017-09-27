package com.ky.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Hibernate 多对多映射关系实现
 * 2，多对多双向实现
 * @author Administrator
 *
 */
public class Course2 {

	private int id;
	private String name;
	private Set<Student2> students=new HashSet<Student2>();
	
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
	public Set<Student2> getStudents() {
		return students;
	}
	public void setStudents(Set<Student2> students) {
		this.students = students;
	}
	
	
	
	
}
