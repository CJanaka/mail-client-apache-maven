package com.mail_mvc_project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mail_mvc_project.dto.UserDTO;
import com.mail_mvc_project.util.JDBCUtil;

public class loginService {
	Connection connection = JDBCUtil.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	UserDTO udto = new UserDTO();
	boolean valid = false;
	int uId;
	
	public UserDTO selectUser(String password, String mail) {

		try {
			String sql = "SELECT * FROM users WHERE password = ? and address = ?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, password);
			pst.setString(2, mail);
			rs = pst.executeQuery();
			if (rs.next()) {
				valid = true;
				udto.setfName(rs.getString("fname"));
				udto.setMail(rs.getString("address"));
				udto.setUserId(rs.getInt("userid"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
		return udto;
	}

	public boolean getUserDetails() {
		return valid;
	}
	
	
	
//	public UserDTO inbox() {
//		List <UserDTO> userList = new ArrayList<>();
//		try {
//			String sql = "SELECT * FROM message m join users u on (m.senderid = u.userid) WHERE senderid = ?";
//			pst = connection.prepareStatement(sql);
//			pst.setInt(1, uId);
//			rs = pst.executeQuery();
//			while (rs.next()) {
//				valid = true;
//				udto.setfName(rs.getString("fname"));
//				udto.setlName(rs.getString("lname"));
//				userList.add(udto);
//			}
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}		
//		return udto;
//	}

}
