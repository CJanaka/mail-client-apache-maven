package com.mail_mvc_project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mail_mvc_project.service.UpdateTrashService;

@WebServlet("/UpdateTrashControler")
public class UpdateTrashControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int messageId = Integer.parseInt(request.getParameter("messageId"));
		int removeStatus = Integer.parseInt(request.getParameter("removeStatus"));

		UpdateTrashService updateTrash = new UpdateTrashService();
		
		if (removeStatus == 0) {			
			updateTrash.moveToInbox(messageId);
			response.sendRedirect("InboxController");
		}else if (removeStatus == 1){
			updateTrash.removeFromTrash(messageId);
			response.sendRedirect("InboxController");
		}

	}

}
