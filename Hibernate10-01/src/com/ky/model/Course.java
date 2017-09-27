package com.ky.model;

/**
 * Hibernate 多对多映射关系实现
 * 1，多对多单向实现     只在一个类的一方设置集合
 * @author Administrator
 *
 */
public class Course {

	private int id;
	private String name;
	
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
	
	
}
