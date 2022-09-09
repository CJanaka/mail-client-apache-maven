package com.mail_mvc_project.dto;

public class UserDTO {
	
	private int userId;
	private String mail;
	private String password;	
	private String fName;
	private String lName;
	private int contact;
	private int dob;	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public int getContact() {
		return contact;
	}
	public void setContact(int contact) {
		this.contact = contact;
	}
	public int getDob() {
		return dob;
	}
	public void setDob(int dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", mail=" + mail + ", password=" + password + ", fName=" + fName
				+ ", lName=" + lName + ", contact=" + contact + ", dob=" + dob + "]";
	}	
}