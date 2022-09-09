package com.mail_mvc_project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mail_mvc_project.dto.UserDTO;
import com.mail_mvc_project.service.loginService;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uName = request.getParameter("mail") + "@mail.com";
		System.out.println(uName);
		String pWord = request.getParameter("psw");			
		
		loginService lg = new loginService();
		UserDTO udto = lg.selectUser(pWord, uName);
		
		if (lg.getUserDetails()) {						
			request.getSession().setAttribute("userDto", udto);
			response.sendRedirect("InboxController");
		}else {
			response.sendRedirect("login.jsp");
		}
	}
}
