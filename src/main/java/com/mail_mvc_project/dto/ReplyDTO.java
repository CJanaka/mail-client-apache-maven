package com.mail_mvc_project.dto;

public class ReplyDTO {

	private int messageId;
	private int senderId;
	private String receiverAddress;
	private String senderAddress;
	private String message;
	private String date;
	private boolean newReply;
	
	
	public String getSenderAddress() {
		return senderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
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
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isNewReply() {
		return newReply;
	}
	public void setNewReply(boolean newReply) {
		this.newReply = newReply;
	}
	@Override
	public String toString() {
		return "ReplyDTO [messageId=" + messageId + ", senderId=" + senderId + ", receiverAddress=" + receiverAddress
				+ ", senderAddress=" + senderAddress + ", message=" + message + ", date=" + date + ", newReply="
				+ newReply + "]";
	}
	
	
}
