package com.ky.model;

//根类对应一个表
public class Image2 {

	private int id;
	private String imageName;
	private String imageType;  //父类加的属性  在配置文件中方便区分  
	private Student2 student;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Student2 getStudent() {
		return student;
	}
	public void setStudent(Student2 student) {
		this.student = student;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	
	
}
