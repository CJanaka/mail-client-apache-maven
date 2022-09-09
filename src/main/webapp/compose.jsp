<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mail_mvc_project.dto.UserDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Compose</title>
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
<body id="compose">
	<jsp:include page="header.jsp" />
	<c:choose>
		<c:when test="${param.status != '' && param.status == 1}">
			<div class="alert alert-primary" role="alert">
				Message sent successfully..!
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:when>
		<c:when test="${ param.status != '' && param.status == 0}">
			<div class="alert alert-danger" role="alert">
				Message not sent..!
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:when>
	</c:choose>
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

	<jsp:useBean id="draft" scope="session"
		class="com.mail_mvc_project.dto.DraftDTO"></jsp:useBean>
	<div class="container">
		<div class="col mt-3">
			<form name="form" method="post">
				<div class="row">
					<div class="col">
						<h2>Compose</h2>
					</div>
				</div>
				<div class="card mt-5">
					<div class="card-body">
						<div class="card-title">To</div>
						<div class="row">
							<div class="col">
								<div class="form-group">
									<input type="email" class="form-control" name="address"
										value="<c:out value="${sessionScope.draft.getReceiveraddress()}"></c:out>">
									<c:choose>
										<c:when test="${param.isDraft == 'true'}">
											<input type="hidden" name="isDraft" value="${true}" />
										</c:when>
										<c:otherwise>
											<input type="hidden" name="isDraft" value="${false}" />
										</c:otherwise>
									</c:choose>
									<input type="hidden" name="draftId"
										value="${sessionScope.draft.getDraftId()}" />
								</div>
							</div>
						</div>
						<div class="card-title mt-3">Subject</div>
						<div class="row">
							<div class="col">
								<div class="form-group">
									<input type="text" class="form-control" name="subject"
										value="<c:out value="${sessionScope.draft.getSubject()}"></c:out>">
								</div>
							</div>
						</div>
						<div class="card-title mt-3">Message</div>
						<div class="row">
							<div class="col">
								<div class="form-group">
									<textarea class="form-control" name="message" id="myTextarea"
										rows="10">
										
									</textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row mt-3">
					<div class="col">
						<button class="btn btn-lg btn-primary m-2"
							onclick="javascript: form.action='ComposeController';"
							type="submit">Send</button>
						<button class="btn btn-lg btn-secondry m-2" type="reset">Clear</button>


						<a href="#">
							<button class="btn btn-lg btn-warning m-2" type="submit"
								role="button"
								onclick="javascript: form.action='DraftController';">Save
							</button>

						</a>
					</div>

				</div>
			</form>
		</div>
	</div>
	<script>
		window.onload = function() {
			document.getElementById("myTextarea").defaultValue = '<c:out value="${sessionScope.draft.getMessage()}"></c:out>';
		}

		$(document).ready(function() {
			setTimeout(function() {
				$(".alert").alert('close');
			}, 2000);

		});
	</script>
</body>
</html>