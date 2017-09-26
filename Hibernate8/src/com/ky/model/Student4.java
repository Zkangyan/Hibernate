package com.ky.model;

import java.util.Map;

public class Student4 {

	/**
	 * Map ¼üÖµ¶Ô
	 */
	private long id;
	private String name;
	private Map<String,String> images;
	
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
	public Map<String, String> getImages() {
		return images;
	}
	public void setImages(Map<String, String> images) {
		this.images = images;
	}
	
}
