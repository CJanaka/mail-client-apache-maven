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

public class InboxService {

	Connection connection = JDBCUtil.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public List<ComposeDTO> getInboxMail(int receiverid) throws SQLException {
		List<ComposeDTO> messages = new ArrayList<>();
		
		try {
			String sql = "select receiverid, newmail, senderaddress, messageid, mesg, date, subject "
					+ "from message where receiverid = ? and trash = ? order by messageid desc";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, receiverid);
			pst.setBoolean(2, false);
			rs = pst.executeQuery();
			while (rs.next()) {
				ComposeDTO cDTO = new ComposeDTO();
				cDTO.setReceiverId(rs.getInt("receiverid"));
				cDTO.setNewMail(rs.getBoolean("newmail"));
				cDTO.setSenderAddress(rs.getString("senderaddress"));
				cDTO.setMessageId(rs.getInt("messageid"));
				cDTO.setMessage(rs.getString("mesg"));
				Date utilDate = new Date(rs.getDate("date").getTime());
				SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
				String stringDate = DateFor.format(utilDate);
				cDTO.setDate(stringDate);
				cDTO.setSubject(rs.getString("subject"));
				messages.add(cDTO);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			pst.close();
			connection.close();
		}
		return messages;
	}
	
	public List<ReplyDTO> getReplyDetails(String address) {
		List<ReplyDTO> reply = new ArrayList<>();
		
		try {
			String sql = "select senderaddress, messageid from reply where receiveraddress = ? and newreply = ?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, address);
			pst.setBoolean(2, true);
			rs = pst.executeQuery();
			while (rs.next()) {
				ReplyDTO rply = new ReplyDTO();
				rply.setSenderAddress(rs.getString("senderaddress"));
				rply.setMessageId(rs.getInt("messageid"));
				reply.add(rply);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return reply;
	}
}
