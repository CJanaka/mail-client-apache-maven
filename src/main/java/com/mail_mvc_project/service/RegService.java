package com.mail_mvc_project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mail_mvc_project.dto.RegUserDTO;
import com.mail_mvc_project.util.JDBCUtil;

public class RegService {

	public static boolean checkMailAddressExist(String mailAddress){
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement pst;
		ResultSet rs;
		boolean exist = false;
		try {
			String sql = "select address from users where address = ?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, mailAddress);
			rs = pst.executeQuery();
			if (rs.next()) {
				 exist = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return exist;
	}

	public static void regUser(RegUserDTO regDTO) {
		
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement pst;
		try {
			String sql = "INSERT INTO users (fname, lname, password, address, contact, dob) VALUES (?, ?, ?, ?, ?, ?::date)";
			pst = connection.prepareStatement(sql);
			pst.setString(1, regDTO.getfName());
			pst.setString(2, regDTO.getlName());
			pst.setString(3, regDTO.getPassword());
			pst.setString(4, regDTO.getMailAddress());
			pst.setInt(5, regDTO.getContact());
			pst.setString(6, regDTO.getDob());
			int count = pst.executeUpdate();
			System.out.println(count+" row affected");
											
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
