package com.mail_mvc_project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mail_mvc_project.dto.ComposeDTO;
import com.mail_mvc_project.dto.UserDTO;
import com.mail_mvc_project.service.ComposeService;

@WebServlet("/ComposeController")
public class ComposeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComposeService comService = new ComposeService();
		int draftid = Integer.parseInt(request.getParameter("draftId"));
		
		request.getSession().setAttribute("draft", comService.getdraftMessage(draftid));
		response.sendRedirect("compose.jsp?isDraft="+true);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserDTO userDTO = (UserDTO) session.getAttribute("userDto");
		String recevierAddress = request.getParameter("address");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		boolean isDraft = Boolean.parseBoolean(request.getParameter("isDraft"));
		int draftid = Integer.parseInt(request.getParameter("draftId"));
		String senderAddress = userDTO.getMail();
		ComposeService comService = new ComposeService();

		ComposeDTO comDTO = new ComposeDTO();
		comDTO.setMessage(message);
		comDTO.setSubject(subject);
		comDTO.setNewMail(true);
		comDTO.setSenderAddress(senderAddress);

		int status = comService.composeMail(comDTO, recevierAddress, isDraft, draftid);
		response.sendRedirect("compose.jsp?status=" + status);

	}
}
