<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
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
<link rel="stylesheet" href="/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300&display=swap"
	rel="stylesheet">
</head>
<body style="background-color: #3a3738;">
	<%
	//address checking
	String exist = request.getParameter("mailAlreadyExist");
	%>
	<%
	if (exist != null && exist.equals("0")) {
	%>
	<div class="alert alert-success alert-dismissible fade show"
		role="alert">
		<strong>Account Successfully Created..!.</strong>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	<%
	}
	%>
	<section class="vh-100">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col col-xl-10">
					<div class="card" style="border-radius: 1rem;">
						<div class="row g-0">
							<div class="col-md-6 col-lg-5 d-none d-md-block">
								<img
									src="https://wallpapertops.com/walldb/original/8/7/7/120389.jpg"
									alt="login form" class="img-fluid"
									style="border-radius: 1rem 0 0 1rem;" />
							</div>
							<div class="col-md-6 col-lg-7 d-flex align-items-center">
								<div class="card-body p-4 p-lg-5 text-black">

									<form action="LoginController" method="post">

										<div class="d-flex align-items-center mb-3 pb-1">
											<span class="h1 fw-bold mb-0"
												style="font-family: 'Comfortaa', cursive">Login here!</span>
										</div>

										<div class="form-outline mb-4">
											<div class="input-group mb-3">
												<input type="text" class="form-control"
													placeholder="Enter User Name"
													aria-label="Recipient's username"
													aria-describedby="basic-addon2" name="mail">
												<div class="input-group-append">
													<span class="input-group-text" id="basic-addon2">@mail.com</span>
												</div>
											</div>
										</div>

										<div class="form-outline mb-4">
											<input type="password" id="form2Example27"
												class="form-control form-control-lg" name="psw" /> <label
												class="form-label">Password</label>
										</div>

										<div class="pt-1 mb-4">
											<button class="btn btn-dark btn-lg btn-block" type="submit">Login</button>
										</div>

										<a class="small text-muted" href="#">Forgot password?</a>
										<p class="mb-5 pb-lg-2" style="color: #242850;">
											Don't have an account? <a
												href="register.jsp?mailAlreadyExist="
												style="color: #1823b8;">Register here </a>
										</p>
									</form>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>