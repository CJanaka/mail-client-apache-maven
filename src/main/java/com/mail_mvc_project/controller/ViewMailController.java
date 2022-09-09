package com.mail_mvc_project.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mail_mvc_project.dto.ComposeDTO;
import com.mail_mvc_project.dto.UserDTO;
import com.mail_mvc_project.service.NewMailService;

@WebServlet("/NewMailController")
public class ViewMailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int msgId = Integer.parseInt(request.getParameter("messageId"));
		boolean newmail = false;
		String clickFrom = null;
		String status = "";
		NewMailService readMail = new NewMailService();

		if (request.getParameter("newmail") != null) {
			newmail = Boolean.parseBoolean(request.getParameter("newmail"));
			clickFrom = "inbox";
			request.getSession().setAttribute("message", readMail.getRelatetMsgForInBox(msgId));
		} else if (request.getParameter("fromReply") != null) {
			status = request.getParameter("status");
			System.out.println(status);
			if (request.getParameter("fromReply").equals("inbox")) {
				clickFrom = "inbox";
				request.getSession().setAttribute("message", readMail.getRelatetMsgForInBox(msgId));
			} else {
				clickFrom = "sent";
				request.getSession().setAttribute("message", readMail.getRelatetMsgForSentBox(msgId));
			}

		} else {
			clickFrom = "sent";
			request.getSession().setAttribute("message", readMail.getRelatetMsgForSentBox(msgId));
		}

		HttpSession session = request.getSession();
		UserDTO uDTO = (UserDTO) session.getAttribute("userDto");	
		request.getSession().setAttribute("replyList", readMail.getReply(msgId, uDTO.getMail()));


		if (newmail) {	
			ComposeDTO comDTO = new ComposeDTO();
			comDTO.setMessageId(msgId);
			comDTO.setReceiverId(uDTO.getUserId());
			
			try {
				session.removeAttribute("messageList");
				request.getSession().setAttribute("messageList", readMail.updateNewMail(comDTO));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("viewmail.jsp?name=" + clickFrom + "&status=" + status);
			
		} else {
			response.sendRedirect("viewmail.jsp?name=" + clickFrom + "&status=" + status);
		}
	}
}
