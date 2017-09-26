package com.ky.model;
import java.util.Set;

//根类对应一个表
public class Student2 {

	private int id;
	private String name;
	private Set<Image2> images;
	
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
	public Set<Image2> getImages() {
		return images;
	}
	public void setImages(Set<Image2> images) {
		this.images = images;
	}
	
	
	
}
