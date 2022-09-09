<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
	integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
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
<body>

	<%
	String exist = request.getParameter("mailAlreadyExist");
	%>
	<%
	if (!exist.isEmpty() && exist.equals("1")) {
	%>
	<div class="alert alert-danger alert-dismissible fade show"
		role="alert">
		<strong>The Username You Entered Has Already Taken.!, Use
			Another Username.</strong>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	<%
	}
	%>
	<div class="container">
		<div class="mt-5">
			<h2 class="ml-5 mb-5"><i class="fa fa-user-plus" aria-hidden="true"></i>
			 Sign Up As New User</h2>
		</div>
		<form action="RegUserController" method="post" novalidate>
			<div class="col mt-3">
				<div class="card" style="border-width: 2px; border-radius: 15px; border-color: black">
					<div class="card-body">
						<h5 class="card-title">Personal Details</h5>
						<div class="row">
							<div class="col mt-3">
								<div class="form-group">
									<label for="">First Name</label> <input type="text"
										class="form-control" name="fname"
										placeholder="Enter First Name" required>
									<div class="invalid-feedback">Please enter first Name.</div>
								</div>
							</div>
							<div class="col mt-3">
								<div class="form-group">
									<label for="">Last Name</label> <input type="text"
										class="form-control" name="lname"
										placeholder="Enter Last Name">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col mt-3">
								<div class="form-group">
									<label for="">Date Of Birth</label> <input type="date"
										class="form-control" name="dob" required>
									<div class="invalid-feedback">Enter birth date.</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col mt-3">
								<div class="form-group">
									<label for="">User name</label>
									<div class="input-group mb-3">
										<input type="text" class="form-control"
											placeholder="Enter User Name For Mail Address"
											aria-label="Recipient's username"
											aria-describedby="basic-addon2" name="uname" required>
										<div class="input-group-append">
											<span class="input-group-text" id="basic-addon2">@mail.com</span>
										</div>
										<div class="invalid-feedback">Enter a user name.</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col mt-3">
								<div class="form-group">
									<label for="">Enter Password</label> <input type="password"
										placeholder="Enter A Password" id="pWord" class="form-control"
										name="password" required>
									<div class="invalid-feedback">Enter a password .</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col mt-3">
								<div class="form-group">
									<label for="">Confirm Password</label> <input type="password"
										placeholder="Enter Password again" id="cPWord"
										class="form-control " name="password"
										onkeyup="checkPassword()" required>
									<div class="invalid-feedback">Password doesn't match.</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col mt-3">
								<div class="form-group">
									<label for="">Contact Number</label> <input type="number"
										placeholder="Enter A Contact Number" class="form-control"
										name="contact" required>
									<div class="invalid-feedback">Enter contact number.</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row mt-3">
					<div class="col-1">
						<button type="submit" class="btn btn-lg btn-primary">Submit</button>
					</div>
					<div class="col">
						<button type="reset" class="btn ml-2 btn-lg btn-secondary">Clear</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function checkPassword() {
			var password = document.getElementById("pWord").value;
			var confirm = document.getElementById("cPWord").value;
			if (password != confirm) {
				document.getElementById("cPWord").classList.add('is-invalid');
			} else {
				document.getElementById("cPWord").classList
						.remove('is-invalid');
			}
		}
	</script>
	<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
	%>

</body>
</html>