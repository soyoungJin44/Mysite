package com.javaex.vo;

public class TboardVo {
	
	//필드
	private int no;
	private String title;
	private String content;
	private String hit;
	private String reg_date;
	private int userNo;
	private String name;
	
	//생성자
	public TboardVo() {
		super();
	}
	
	public TboardVo(int no, String title, String content, String hit, String reg_date, int userNo, String name) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.userNo = userNo;
		this.name = name;
	}
	//메서드 gs

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setreg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	//메서드 일반

	@Override
	public String toString() {
		return "TboardVo [no=" + no + ", title=" + title + ", content=" + content + ", hit=" + hit + ", regDate="
				+ reg_date + ", userNo=" + userNo + ", name=" + name + "]";
	}
	
	
}
