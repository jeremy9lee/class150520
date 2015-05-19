package com.sds.icto.mysite.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class BulletinBoard {

	private int b_no;
	private int memberno;
	private String b_title;
	private String name;
	private String content;
	private Date reg_date;
	private int hit;
	
	
	public BulletinBoard(int b_no, int memberno,String b_title, String name, 
			String content, Date reg_date, int hit) {
		super();
		this.b_no = b_no;
		this.memberno = memberno;
		this.name = name;
		this.b_title = b_title;
		this.content = content;
		this.reg_date = reg_date;
		this.hit = hit;
	}
	public BulletinBoard() {
	}
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public int getMemberno() {
		return memberno;
	}
	public void setMemberno(int memberno) {
		this.memberno = memberno;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
