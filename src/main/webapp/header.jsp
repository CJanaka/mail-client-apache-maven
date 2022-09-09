<%@page import="java.util.Objects"%>
<%@ page import="com.mail_mvc_project.dto.UserDTO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">
<link
	href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<style>
.active {
	background-color: green;
	opacity: 0.4;
	font-size: 18px;
	font-weight: bold;
}
</style>
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
	response.sendRedirect("login.jsp");
}
%>

<nav class="navbar navbar-expand-lg navbar-dark"
	style="background-color: #3a3738;">
	<a class="navbar-brand" href="compose.jsp"
		style="color: green; font-family: 'Comfortaa', cursive">Mail+</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
			<li class="nav-item" data-active="inbox"><a class="nav-link"
				href="inbox.jsp">Inbox</a></li>
			<jsp:useBean id="userDto" scope="session"
				class="com.mail_mvc_project.dto.UserDTO"></jsp:useBean>

			<c:url value="" var="sentURL">
				<c:param name="myAddress" value="${sessionScope.userDto.getMail()}" />
			</c:url>
			<c:import url="${sentURL}" />
			<li class="nav-item" data-active="sent"><a class="nav-link"
				href="SentMessageController${sentURL}">Sentbox</a></li>

			<li class="nav-item" data-active="draft"><a class="nav-link"
				href="DraftController">Drafts</a></li>

			<c:url value="" var="newURL">
				<c:param name="status" value="0" />
				<c:param name="receiverId"
					value="${sessionScope.userDto.getUserId()}" />
			</c:url>
			<c:import url="${newURL}" />

			<li class="nav-item" data-active="trash"><a class="nav-link"
				href="TrashController${newURL}">Trash</a></li>

			<li class="nav-item" data-active="compose"><a class="nav-link"
				href="compose.jsp">Compose</a></li>
		</ul>
		<div class="dropdown">
			<button class="btn dropdown-toggle"
				style="background-color: #3a3738;" type="button"
				id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">
				<i class="fas fa-bell" style="color: white"></i>
			</button>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="width: 200px">
				<ul class="list-group">
					<c:choose>
						<c:when test="${not empty sessionScope.notifics}">
							<c:forEach items="${sessionScope.notifics}" var="message">
								<li class="dropdown-item">
								<c:out value="${message.getSenderAddress()}"></c:out> replied to you
								</li>
							</c:forEach>
						</c:when>
						<c:otherwise>
								<li class="dropdown-item">
									<h6>No new notifications</h6>
								</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		<p class="m-2" style="color: #9fffea">
			<i class="fas fa-user-circle"> <jsp:getProperty property="fName"
					name="userDto" /></i>
		</p>
		<a style="text-decoration: none;" href="LogoutController"><button
				class="btn btn-outline-success my-2 my-sm-0" type="button">
				<i class="fas fa-sign-out-alt"> Log Out</i>
			</button></a>
	</div>
</nav>

<script type="text/javascript">
let links = document.querySelectorAll(".navbar-nav li");
let bodyId = document.querySelector("body").id;
 
for(let link of links){
    if(link.dataset.active == bodyId){
        link.classList.add("active");
    }
}
</script>