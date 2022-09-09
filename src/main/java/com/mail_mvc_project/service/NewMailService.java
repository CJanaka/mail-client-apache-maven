package com.mail_mvc_project.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mail_mvc_project.dto.ComposeDTO;
import com.mail_mvc_project.dto.ReplyDTO;
import com.mail_mvc_project.util.JDBCUtil;


public class NewMailService {

	Connection connection = JDBCUtil.getConnection();
	PreparedStatement pst;
	ResultSet rs;
	
	public List<ComposeDTO> updateNewMail(ComposeDTO message) throws SQLException {
		try {
			String sql = "update message set newmail = ? where messageid = ?";
			pst = connection.prepareStatement(sql);
			pst.setBoolean(1, false);
			pst.setInt(2, message.getMessageId());
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

		InboxService updatedMessageList = new InboxService();
		return updatedMessageList.getInboxMail(message.getReceiverId());
	}
	
	
	public ComposeDTO getRelatetMsgForSentBox(int messageId) {
		ComposeDTO comDTO = new ComposeDTO();
		try {
			String sql = "select m.receiverid ,u.address, m.date, m.messageid, m.mesg, m.subject from"
					+ " message m join users u on (m.receiverid = u.userid) where messageid = ?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, messageId);
			rs = pst.executeQuery();
			while (rs.next()) {
				comDTO.setReceiverId(rs.getInt("receiverid"));
				comDTO.setMessageId(rs.getInt("messageid"));
				comDTO.setSubject(rs.getString("subject"));
				comDTO.setSenderAddress(rs.getString("address"));
				comDTO.setMessage(rs.getString("mesg"));
				comDTO.setDate(sql);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return comDTO;
	}
	
	
	public ComposeDTO getRelatetMsgForInBox(int messageId) {
		ComposeDTO comDTO = new ComposeDTO();
		try {
			String sql = "select receiverid , senderaddress, date, messageid, mesg, subject from"
					+ " message where messageid = ?;";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, messageId);
			rs = pst.executeQuery();
			while (rs.next()) {
				comDTO.setMessageId(rs.getInt("messageid"));
				comDTO.setReceiverId(rs.getInt("receiverid"));
				comDTO.setSubject(rs.getString("subject"));
				comDTO.setSenderAddress(rs.getString("senderaddress"));
				comDTO.setMessage(rs.getString("mesg"));
				Date utilDate = new Date(rs.getDate("date").getTime());
				SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
				String stringDate = DateFor.format(utilDate);
				comDTO.setDate(stringDate);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return comDTO;
	}
	

	public List<ReplyDTO> getReply(int messageId, String userAddress ) {
		List<ReplyDTO> replyList = new ArrayList<>();
		try {
			String sql = "select * from reply where messageid = ?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, messageId);
			rs = pst.executeQuery();
			while (rs.next()) {
				ReplyDTO replyDTO = new ReplyDTO();
				Date utilDate = new Date(rs.getDate("date").getTime());
				SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
				String stringDate = DateFor.format(utilDate);

				replyDTO.setDate(stringDate);
				replyDTO.setMessage(rs.getString("message"));
				replyDTO.setMessageId(rs.getInt("messageid"));
				replyDTO.setNewReply(rs.getBoolean("newreply"));
				replyDTO.setSenderAddress(rs.getString("senderaddress"));
				replyDTO.setReceiverAddress(rs.getString("receiveraddress"));
				replyList.add(replyDTO);
			}
			updateReply(messageId, userAddress);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return replyList;
	}
	
	public void updateReply(int msgId, String recaddress) {
		try {
			String sql = "update reply set newreply = ? where messageid = ? and receiveraddress = ?";
			pst = connection.prepareStatement(sql);
			pst.setBoolean(1, false);
			pst.setInt(2, msgId);
			pst.setString(3, recaddress);
			pst.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}