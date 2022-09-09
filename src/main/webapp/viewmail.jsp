<%@page import="java.util.List"%>
<%@page import="com.mail_mvc_project.dto.ReplyDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mail_mvc_project.dto.ComposeDTO"%>
<%@page import="java.util.Objects"%>
<%@ page import="com.mail_mvc_project.dto.UserDTO"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Mail</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
<link rel="stylesheet" href="/style.css">
</head>
<body>
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
	<!-- Reply confirmation -->
	<c:choose>
		<c:when test="${param.status != '' && param.status == 1}">
			<div class="alert alert-primary"  onclose="AutoRefresh()" role="alert">
				Message sent successfully..!
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:when>
		<c:when test="${ param.status != '' && param.status == 0}">
			<div class="alert alert-danger" onclose="AutoRefresh()" role="alert">
				Message not sent..!
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:when>
	</c:choose>

	<div class="container">
		<div class="row mt-3">
			<div class="col">
				<div class="card">
					<jsp:useBean id="message" scope="session"
						class="com.mail_mvc_project.dto.ComposeDTO"></jsp:useBean>
					<div class="card-header">
						<c:choose>
							<c:when test="${param.name == 'sent'}">
							To :
							</c:when>
							<c:otherwise>
							From :
							<c:set value="${false}" var="btnVisible"></c:set>
							</c:otherwise>
						</c:choose>

						<jsp:getProperty property="senderAddress" name="message" />
					</div>
					<div class="card-body">
						<h5 class="card-title">
							<jsp:getProperty property="subject" name="message" />
						</h5>
						<p class="card-text">
							<jsp:getProperty property="message" name="message" />
						</p>
					</div>

				</div>

			</div>
		</div>
		<hr style="border-top: 2px solid black;">
		<div class="row mt-3">
			<div class="col">

				<c:forEach items="${replyList}" var="reply">

					<c:choose>
						<c:when
							test="${reply.getSenderAddress() eq sessionScope.userDto.mail}">
							<c:set var="ms" scope="page" value="${false}" />
							<c:set var="msgType" scope="page" value="${'float-left'}" />
						</c:when>
						<c:otherwise>
							<c:set var="msgType" scope="page" value="${'float-right'}" />
						</c:otherwise>
					</c:choose>


					<c:set var="sendAddress" scope="page"
						value="${reply.getReceiverAddress()}" />
					<c:set value="${reply.getSenderAddress()}" var="add"></c:set>

					<div class="card mt-2 ${msgType}" style="width: 40rem">
						<div class="card-header">

							<c:out value="${reply.getSenderAddress()}" />

							<c:choose>
								<c:when test="${param.name == 'sent'}">

								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="card-body">
							<h6 class="card-subtitle mb-2 text-muted">
								<c:out value="${reply.getDate()}" />
							</h6>
							<p class="card-text">
								<c:out value="${reply.getMessage()}" />
							</p>

						</div>
					</div>

				</c:forEach>
			</div>
		</div>
		<hr style="border-top: 2px solid black;">

		<div class="row mt-3 ml-1">
			<c:if
				test="${not empty sessionScope.replyList || param.name == 'inbox'}">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#exampleModal">Reply</button>
			</c:if>
		</div>
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true" >
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">REPLY</h5>
						<button type="button" class="close btn btn-danger"
							data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form action="ReplyController" method="post">
						<div class="modal-body">
							<div class="card">
								<div class="card-body">
									<h6 class="card-title">Send your reply</h6>
									<div class="row">
										<div class="col">
											<c:choose>
												<c:when test="${param.name == 'sent'}">
													<c:set var="replyType" scope="page" value="${'sent'}" />
												</c:when>
												<c:otherwise>
													<c:set var="replyType" scope="page" value="${'inbox'}" />
												</c:otherwise>
											</c:choose>
											To :
											<c:out value="${sessionScope.message.getSenderAddress()}"></c:out>
											<div class="form-group">
												<textarea class="form-control" id="validationCustom01"
													name="message" id="myTextarea" required></textarea>
												<input type="hidden" name="messageId"
													value="${sessionScope.message.getMessageId()}" /> <input
													type="hidden" name="receiverAddress"
													value="${sessionScope.message.getSenderAddress()}" /> <input
													type="hidden" name="receiverId"
													value="${sessionScope.message.getReceiverId()}" /> <input
													type="hidden" name="replyFrom" value="${replyType}" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="reset" class="btn btn-secondary">Clear</button>
							<button type="submit" class="btn btn-primary">Send</button>
						</div>
					</form>
				</div>

			</div>
		</div>

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

	<script>
	$(document).ready(function() {
	    setTimeout(function() {
	        $(".alert").alert('close');
	    }, 2000);

	});

    function AutoRefresh( ) {
    	history.go(-1);
    	setTimeout("AutoRefresh();", 5000);
     }
	</script>
</body>
</html>