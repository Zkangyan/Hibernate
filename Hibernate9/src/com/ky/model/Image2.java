package com.ky.model;

//�����Ӧһ����
public class Image2 {

	private int id;
	private String imageName;
	private String imageType;  //����ӵ�����  �������ļ��з�������  
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
