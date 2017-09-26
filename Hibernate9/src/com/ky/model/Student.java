package com.ky.model;
import java.util.Set;

//每个具体类对应一个表
public class Student {

	private int id;
	private String name;
	private Set<Image> images;
	
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
	public Set<Image> getImages() {
		return images;
	}
	public void setImages(Set<Image> images) {
		this.images = images;
	}
	
	
}
