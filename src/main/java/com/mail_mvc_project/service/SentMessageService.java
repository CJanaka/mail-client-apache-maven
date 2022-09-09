package com.mail_mvc_project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mail_mvc_project.dto.ComposeDTO;
import com.mail_mvc_project.util.JDBCUtil;

public class SentMessageService {

	Connection connection = JDBCUtil.getConnection();
	PreparedStatement pst = null;
	ResultSet rs;
	
	public List<ComposeDTO> getSentMessage(String myAddress) {
		List<ComposeDTO> sendMsg = new ArrayList<>();

		try {
			String sql = "select m.mesg, u.userid, u.address, m.date, m.messageid from message m  join users u on "
					+ "(m.receiverid = u.userid)  where m.senderaddress = ?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, myAddress);
			rs = pst.executeQuery();
			while (rs.next()) {
				ComposeDTO comDto = new ComposeDTO();
				
//				
//					String sql2 = "select address from users where userid = ?";
//					PreparedStatement pst2 = connection.prepareStatement(sql2);
//					pst2.setInt(1, rs.getInt("receiverid"));
//					ResultSet rs2 = pst2.executeQuery();
//					while (rs2.next()) {
//						receiverAddress = rs2.getString("address");
//					}
					comDto.setReceiverId(rs.getInt("userid"));
					comDto.setSenderAddress(rs.getString("address"));
					Date utilDate = new Date(rs.getDate("date").getTime());
					SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
					String stringDate= DateFor.format(utilDate);
					comDto.setDate(stringDate);
					comDto.setMessageId(rs.getInt("messageid"));
					comDto.setMessage(rs.getString("mesg"));
					sendMsg.add(comDto);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sendMsg;
	}
}
