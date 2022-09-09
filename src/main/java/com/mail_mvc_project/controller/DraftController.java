package com.mail_mvc_project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mail_mvc_project.dto.DraftDTO;
import com.mail_mvc_project.dto.UserDTO;
import com.mail_mvc_project.service.DraftService;

@WebServlet("/DraftController")
public class DraftController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserDTO uDTO = (UserDTO) session.getAttribute("userDto");
		int senderId = uDTO.getUserId();
		DraftService drafts = new DraftService();
		request.getSession().setAttribute("draftMessage", drafts.getDraftMessages(senderId));
		response.sendRedirect("draft.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserDTO uDTO = (UserDTO) session.getAttribute("userDto");
		
		String receiverAddress = request.getParameter("address");
		String message = request.getParameter("message");
		String subject = request.getParameter("subject");
		
		
		int senderId = uDTO.getUserId();
		DraftDTO draftDTO = new DraftDTO();
		draftDTO.setSenderId(senderId);
		draftDTO.setMessage(message);
		draftDTO.setReceiveraddress(receiverAddress);
		draftDTO.setSubject(subject);
		
		DraftService drafts = new DraftService();
		request.getSession().setAttribute("draftMessage", drafts.addToDraft(draftDTO));
		response.sendRedirect("draft.jsp");
		
	}
}
