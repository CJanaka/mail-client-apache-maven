package com.mail_mvc_project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mail_mvc_project.dto.DraftDTO;
import com.mail_mvc_project.util.JDBCUtil;

public class DraftService {

	Connection connection = JDBCUtil.getConnection();
	PreparedStatement pst = null;
	ResultSet rs;
	
	public List<DraftDTO> addToDraft(DraftDTO draftDTO) {
		
		List<DraftDTO> draft = new ArrayList<>();
		try {
			String sql = "insert into draft (senderid,resaddress,mesg, subject) values (?,?,?,?)";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, draftDTO.getSenderId());
			pst.setString(2, draftDTO.getReceiveraddress());
			pst.setString(3, draftDTO.getMessage());
			pst.setString(4, draftDTO.getSubject());
			pst.executeUpdate();
			
			draft = getDraftMessages(draftDTO.getSenderId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return draft;
	}
	
	public List<DraftDTO> getDraftMessages(int sendId) {
		
		List<DraftDTO> draft = new ArrayList<>();
		try {
			String sql = "select * from draft where senderid = ?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, sendId);
			rs = pst.executeQuery();
			while (rs.next()) {
				DraftDTO draftDTO = new DraftDTO();
				draftDTO.setDraftId(rs.getInt("draftid"));
				draftDTO.setMessage(rs.getString("mesg"));
				draftDTO.setReceiveraddress(rs.getString("resaddress"));
				draftDTO.setSubject(rs.getString("subject"));
				draft.add(draftDTO);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return draft;
	}

}
