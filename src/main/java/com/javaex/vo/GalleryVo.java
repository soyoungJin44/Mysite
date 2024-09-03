package com.javaex.vo;

public class GalleryVo {
	
	//필드
	private int no;
	private String orgName;
	private String saveName;
	private String filePath;
	private long fileSize;
	
	//생성자
	
	public GalleryVo() {
		super();
	}
	
	public GalleryVo(int no, String orgName, String saveName, String filePath, long fileSize) {
		super();
		this.no = no;
		this.orgName = orgName;
		this.saveName = saveName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

	
	//메서드 gs
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	
	
	//메서드 일반
	
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", orgName=" + orgName + ", saveName=" + saveName + ", filePath=" + filePath
				+ ", fileSize=" + fileSize + "]";
	}
	

}
