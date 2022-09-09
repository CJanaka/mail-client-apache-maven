package com.mail_mvc_project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mail_mvc_project.dto.ComposeDTO;
import com.mail_mvc_project.dto.DraftDTO;
import com.mail_mvc_project.util.JDBCUtil;

public class ComposeService {

	Connection connection = JDBCUtil.getConnection();
	PreparedStatement pst = null;
	ResultSet rs;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	String dat = dateFormat.format(date);

	public int composeMail(ComposeDTO comDTO, String recevierAddress, boolean isDraft, int draftid) {
		int count = 0;
		try {
			String sql1 = "select userid from users where address = ?";
			pst = connection.prepareStatement(sql1);
			pst.setString(1, recevierAddress);
			rs = pst.executeQuery();
			int userId = 0;
			while (rs.next()) {
				userId = rs.getInt("userid");
			}
			if(userId != 0) {
				String sql = "INSERT INTO message(receiverid, newmail, senderaddress, mesg, date,subject,trash) VALUES (?, ?, ?, ?, ?::date,?,?)";
				pst = connection.prepareStatement(sql);
				pst.setInt(1, userId);
				pst.setBoolean(2, true);
				pst.setString(3, comDTO.getSenderAddress());
				pst.setString(4, comDTO.getMessage());
				pst.setString(5, dat);
				pst.setString(6, comDTO.getSubject());
				pst.setBoolean(7, false);
				count = pst.executeUpdate();			
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if (isDraft && count == 1) {
			try {
				String sql = "delete from draft where draftid = ?";
				pst = connection.prepareStatement(sql);
				pst.setInt(1, draftid);
				pst.execute();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return count;
	}

	public DraftDTO getdraftMessage(int draftid) {
		DraftDTO draftDTO = new DraftDTO();
		try {
			String sql = "select * from draft where draftid = ?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, draftid);
			rs = pst.executeQuery();
			while (rs.next()) {
				draftDTO.setDraftId(rs.getInt("draftid"));
				draftDTO.setMessage(rs.getString("mesg"));
				draftDTO.setReceiveraddress(rs.getString("resaddress"));
				draftDTO.setSubject(rs.getString("subject"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return draftDTO;
	}

}
