package com.ky.model;

import java.util.HashSet;
import java.util.Set;

public class Class {

	private long id;
	private String name;
	//映射关系集合    并且学生是唯一的不能重复
	private Set<Student>students=new HashSet<Student>();
	
	
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
