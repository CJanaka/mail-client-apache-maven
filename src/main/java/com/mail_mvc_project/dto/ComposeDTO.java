package com.mail_mvc_project.dto;

public class ComposeDTO {

	private int receiverId;
	private int messageId;
	private String message;
	private String date;
	private boolean newMail;
	private String senderAddress;
	private String subject;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int senderId) {
		this.receiverId = senderId;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String stringDate) {
		this.date = stringDate;
	}
	public boolean isNewMail() {
		return newMail;
	}
	public void setNewMail(boolean newMail) {
		this.newMail = newMail;
	}
	public String getSenderAddress() {
		return senderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	@Override
	public String toString() {
		return "ComposeDTO [senderId=" + receiverId + ", messageId=" + messageId + ", message=" + message + ", date="
				+ date + ", newMail=" + newMail + ", sendToAddress=" + senderAddress + ", subject=" + subject + "]";
	}
}
