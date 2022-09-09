package com.mail_mvc_project.dto;

public class DraftDTO {

	private int senderId;
	private int draftId;
	private String subject;
	private String message;
	private String receiveraddress;
	
	
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getDraftId() {
		return draftId;
	}
	public void setDraftId(int draftId) {
		this.draftId = draftId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReceiveraddress() {
		return receiveraddress;
	}
	public void setReceiveraddress(String receiveraddress) {
		this.receiveraddress = receiveraddress;
	}
	
	@Override
	public String toString() {
		return "DraftDTO [senderId=" + senderId + ", draftId=" + draftId + ", subject=" + subject + ", message="
				+ message + ", receiveraddress=" + receiveraddress + "]";
	}
}
