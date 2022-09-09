<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.Objects"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.mail_mvc_project.dto.UserDTO"%>
<%@ page import="com.mail_mvc_project.dto.ComposeDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>In-box</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
</head>
<body id="inbox">
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
	if (Objects.isNull(userDTO)) {
	%>
	<c:redirect url="login.jsp" />
	<%
	}
	%>
	<jsp:include page="header.jsp" />

	<div class="container mt-3">
		<table class="table table-striped">
			<thead style="background-color: #37a6ad">
				<tr>
					<th scope="col">Received From</th>
					<th scope="col">Message Subject</th>
					<th scope="col">Message</th>
					<th scope="col">Received Date</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${messageList}" var="message">
					<c:choose>
						<c:when test="${message.isNewMail()}">
							<tr class="table-success">
						</c:when>
						<c:otherwise>
							<tr>
						</c:otherwise>
					</c:choose>

					<td><c:out value="${message.getSenderAddress()}" /></td>
					<td><c:out value="${message.getSubject()}" /></td>
					<td><c:out
							value="${fn:substring(message.getMessage(), 0, 25)}" /></td>
					<td><c:out value="${message.getDate()}" /></td>

					<c:url value="" var="myURL">
						<c:param name="messageId" value="${message.getMessageId()}" />
						<c:param name="newmail" value="${message.isNewMail()}" />
					</c:url>
					<c:import url="${myURL}" />
					<td>
						<div class="btn-group">
							<a href="NewMailController${myURL}">
								<button type="button" class="btn btn-danger">Read</button>
							</a>
							<button type="button"
								class="btn btn-danger dropdown-toggle dropdown-toggle-split"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"></button>
							<div class="dropdown-menu">
								<c:url value="" var="trashURL">
									<c:param name="status" value="1" />
									<c:param name="receiverId" value="${message.getReceiverId()}" />
									<c:param name="messageId" value="${message.getMessageId()}" />
								</c:url>
								<c:import url="${trashURL}" />
								<a class="dropdown-item" href="TrashController${trashURL}">Move
									To Trash</a>
							</div>
						</div>
					</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
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
	<script type="text/javascript">
		$(document).ready(function() {
			$('.navbar-nav').on('click', 'a', function() {
				$(this).addClass(' active');
			});
		});
	</script>
</body>
</html>