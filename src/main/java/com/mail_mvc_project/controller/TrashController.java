package com.mail_mvc_project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mail_mvc_project.service.TrashService;

@WebServlet("/TrashController")
public class TrashController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("trachController");
		int headreStatus = Integer.parseInt(request.getParameter("status"));
		int headerReceiverId = Integer.parseInt(request.getParameter("receiverId"));
//		int msgId = Integer.parseInt(request.getParameter("messageId"));
//		int receiverId = Integer.parseInt(request.getParameter("recevierId"));
//		String subject = request.getParameter("messageSub");
//		String mesg = request.getParameter("message");
//		String address = request.getParameter("address");
//		String date = request.getParameter("date");
		
//		ComposeDTO message = new ComposeDTO();
//		message.setReceiverId(receiverId);
//		message.setMessageId(msgId);
//		message.setSubject(subject);
//		message.setMessage(mesg);
//		message.setSenderAddress(address);
//		message.setDate(date);
		
		
		TrashService trashService = new TrashService();
		if (headreStatus == 0) {		
			request.getSession().setAttribute("trashMessage", trashService.getTrashMessages(headerReceiverId));
			response.sendRedirect("trash.jsp");
		}else if (headreStatus == 1){
			int msgId = Integer.parseInt(request.getParameter("messageId"));
			request.getSession().setAttribute("trashMessage", trashService.moveToTrash(headerReceiverId, msgId));
			response.sendRedirect("trash.jsp");
		}
		
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
