package com.mail_mvc_project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mail_mvc_project.util.JDBCUtil;

public class UpdateTrashService {

	Connection connection = JDBCUtil.getConnection();
	PreparedStatement pst = null;
	
	public void moveToInbox(int messageId) {
		try {
			String sql = "update message set trash = ? where messageid = ?";
			pst = connection.prepareStatement(sql);
			pst.setBoolean(1, false);
			pst.setInt(2, messageId);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void removeFromTrash(int messageId) {
		
		try {
			String sql = "delete from message where messageid = ?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, messageId);
			int a = pst.executeUpdate();
			System.out.println("delete "+a);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
