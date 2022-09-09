package com.mail_mvc_project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mail_mvc_project.dto.ComposeDTO;
import com.mail_mvc_project.util.JDBCUtil;

public class TrashService {

	PreparedStatement pst = null;
	Connection connection = JDBCUtil.getConnection();
	ResultSet rs = null;

	public List<ComposeDTO> getTrashMessages(int receiverId) {

		List<ComposeDTO> cDTO = new ArrayList<>();
		try {
			//fetch all trash messags
			String sql = "select * from message where receiverid = ? and trash = ?";

			pst = connection.prepareStatement(sql);
			pst.setInt(1, receiverId);
			pst.setBoolean(2, true);
			rs = pst.executeQuery();
			while (rs.next()) {
				ComposeDTO getTrashMessages = new ComposeDTO();
				getTrashMessages.setMessageId(rs.getInt("messageid"));
				getTrashMessages.setSenderAddress(rs.getString("senderaddress"));
				getTrashMessages.setMessage("mesg");
				getTrashMessages.setDate(rs.getString("date"));
				getTrashMessages.setSubject(rs.getString("subject"));
				cDTO.add(getTrashMessages);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cDTO;
	}

	public List<ComposeDTO> moveToTrash(int receiverId, int messageId) {

		try {
			//move message to trash
			String sql = "update message set trash = ? where messageid = ?";
			pst = connection.prepareStatement(sql);
			pst.setBoolean(1, true);
			pst.setInt(2, messageId);
			int count = pst.executeUpdate();
			System.out.println(count);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		List<ComposeDTO> trashMsg = getTrashMessages(receiverId);
		return trashMsg;

	}

}
