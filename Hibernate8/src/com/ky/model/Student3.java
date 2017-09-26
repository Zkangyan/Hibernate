package com.ky.model;

import java.util.List;

public class Student3 {

	/**
	 * Bag 无序元素可重复  用list 来模拟的
	 */
	private long id;
	private String name;
	private List<String> images;
	
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
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	
	
	
	
	
}
