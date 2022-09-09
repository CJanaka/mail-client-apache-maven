package com.mail_mvc_project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mail_mvc_project.dto.UserDTO;
import com.mail_mvc_project.service.InboxService;

@WebServlet("/InboxController")
public class InboxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserDTO uDTO = (UserDTO) session.getAttribute("userDto");
		InboxService inbox = new InboxService();

		req.getSession().setAttribute("notifics", inbox.getReplyDetails(uDTO.getMail()));
		try {
			req.getSession().setAttribute("messageList", inbox.getInboxMail(uDTO.getUserId()));
			resp.sendRedirect("inbox.jsp");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


}
