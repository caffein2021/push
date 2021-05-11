package com.caffein.client.member.vo;

import java.util.Date;

public class MemberVO {
	private int member_id;
	private String email;
	private String pw;
	private String name;
	private String tel;
	private int sex;
	private int age;
	private String salt;
	private Date rdate;
	
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	@Override
	public String toString() {
		return "MemberVO [member_id=" + member_id + ", email=" + email + ", pw=" + pw + ", name=" + name + ", tel="
				+ tel + ", sex=" + sex + ", age=" + age + ", salt=" + salt + ", rdate=" + rdate + "]";
	}
	
}
