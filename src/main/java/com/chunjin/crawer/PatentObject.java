package com.chunjin.crawer;

public class PatentObject {

	private String patentNum;
	private String occurDate;
	private String type;
	private String comments;
	
	public PatentObject(String patentNum, String occurDate, String type, String comments) {
		super();
		this.patentNum = patentNum;
		this.occurDate = occurDate;
		this.type = type;
		this.comments = comments;
	}
	public String getPatentNum() {
		return patentNum;
	}
	public void setPatentNum(String patentNum) {
		this.patentNum = patentNum;
	}
	public String getOccurDate() {
		return occurDate;
	}
	public void setOccurDate(String occurDate) {
		this.occurDate = occurDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	

}
