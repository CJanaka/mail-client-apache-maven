package com.mail_mvc_project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mail_mvc_project.dto.RegUserDTO;
import com.mail_mvc_project.service.RegService;

@WebServlet("/RegUserController")
public class RegUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		RegUserDTO regDTO = new RegUserDTO();

		String contact = request.getParameter("contact");
		regDTO.setfName(request.getParameter("fname"));
		regDTO.setlName(request.getParameter("lname"));
		regDTO.setDob(request.getParameter("dob"));
		String addrs = request.getParameter("uname") + "@mail.com";
		regDTO.setMailAddress(addrs);
		regDTO.setPassword(request.getParameter("password"));
		regDTO.setContact(Integer.parseInt(contact));

		if (RegService.checkMailAddressExist(addrs)) {
			response.sendRedirect("register.jsp?mailAlreadyExist="+1);
		}else {
			RegService.regUser(regDTO);
			response.sendRedirect("login.jsp?mailAlreadyExist="+0);		
		}		
	}
}
