package com.mail_mvc_project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mail_mvc_project.dto.ReplyDTO;
import com.mail_mvc_project.dto.UserDTO;
import com.mail_mvc_project.service.ReplyService;

@WebServlet("/ReplyController")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDTO uDTO = (UserDTO) session.getAttribute("userDto");
		String message = request.getParameter("message");
		String replyFrom = request.getParameter("replyFrom");
		int messageId = Integer.parseInt(request.getParameter("messageId"));
		String address = request.getParameter("receiverAddress");
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setMessage(message);
		replyDTO.setMessageId(messageId);
		replyDTO.setSenderAddress(uDTO.getMail());
		replyDTO.setReceiverAddress(address);
		
		ReplyService replyService = new ReplyService();
		int status = replyService.sendReply(replyDTO, replyFrom);
		
		response.sendRedirect("NewMailController?messageId="+messageId+"&fromReply="+replyFrom+"&status="+status);
	}

}
