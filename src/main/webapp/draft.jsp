<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.util.Objects"%>
<%@ page import="com.mail_mvc_project.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Drafts</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
</head>
<body id="draft">

	<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
	%>
	<%
		UserDTO userDTO = (UserDTO) session.getAttribute("userDto");
	%>
	<%
	if (Objects.isNull(userDTO)) {%>
		<c:redirect url = "login.jsp"/>
	<%}
	%>
	
	<jsp:include page="header.jsp" />
	<div class="container mt-3">
		<table class="table table-striped">
			<thead style="background-color: #3749ad">
				<tr>
					<th scope="col">Address</th>
					<th scope="col">Subject</th>
					<th scope="col">Message</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${draftMessage}" var="message">
					<tr>
						<td><c:out value="${message.getReceiveraddress()}" /></td>
						<td><c:out value="${message. getSubject()}" /></td>
						<td><c:out value="${fn:substring(message.getMessage(), 0, 25)}" /></td>
						<td>
							<div class="btn-group">
								<c:url value="" var="newURL">
									<!--<c:param name="address" value="${message.getReceiveraddress()}" />
									<c:param name="subject" value="${message. getSubject()}" />
									<c:param name="message" value="${message.getMessage()}" />	-->					
									<c:param name="draftId" value="${message.getDraftId()}" />						
									<c:param name="isDraft" value="${true}" />						
								</c:url>
								<c:import url="${newURL}" />
								<a href="ComposeController${newURL}">
									<button type="button" class="btn btn-secondary">Edit</button>
								</a>
							</div>
						</td>
					</tr>
					
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>