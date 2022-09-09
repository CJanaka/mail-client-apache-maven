package com.mail_mvc_project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mail_mvc_project.dto.ReplyDTO;
import com.mail_mvc_project.util.JDBCUtil;

public class ReplyService {

	Connection connection = JDBCUtil.getConnection();
	PreparedStatement pst = null;
	ResultSet rs;
	// Get current date and convert to string
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	String dat = dateFormat.format(date);


	public int sendReply(ReplyDTO replyDTO, String replyFrom) {

		int count = 0;
	
		try {
			String sql = "insert into reply (message, newreply, messageid, date, receiveraddress, senderaddress) values (?,?,?,?::date,?, ?)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, replyDTO.getMessage());
			pst.setBoolean(2, true);
			pst.setInt(3, replyDTO.getMessageId());
			pst.setString(4, dat);
			pst.setString(5, replyDTO.getReceiverAddress());
			pst.setString(6, replyDTO.getSenderAddress());
			count = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return count;
	}
}
