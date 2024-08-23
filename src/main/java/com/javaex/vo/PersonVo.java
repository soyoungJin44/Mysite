package com.javaex.vo;

public class PersonVo {
	
	
	//필드
	private int no;
	private String id;
	private String password;
	private String name;
	private String gender;
	
	
	//생성자
	public PersonVo() {
		super();
	}
	
	
	public PersonVo(String id, String password, String name, String gender) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
	}
	
	public PersonVo(int no, String id, String password, String name, String gender) {
		super();
		this.no = no;
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
	}
	
	
	//메서드 gs


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	
	//메서드 일반
	@Override
	public String toString() {
		return "PersonVo [no=" + no + ", id=" + id + ", password=" + password + ", name=" + name + ", gender=" + gender
				+ "]";
	}
	
	
	

}
