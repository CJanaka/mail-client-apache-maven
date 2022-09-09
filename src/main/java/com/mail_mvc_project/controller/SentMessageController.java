package com.mail_mvc_project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mail_mvc_project.service.SentMessageService;

@WebServlet("/SentMessageController")
public class SentMessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String myAddress = request.getParameter("myAddress");
		
		SentMessageService sentMessage = new SentMessageService();
		request.getSession().setAttribute("sentMessage", sentMessage.getSentMessage(myAddress));
		response.sendRedirect("sent.jsp");
	}

}
