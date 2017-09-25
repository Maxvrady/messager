<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/bootstrap.css' />">
	<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/css.css' />">
	<title>Login</title>
</head>
<body>
	<div class="container-fluid">
		<!-- Header -->
		<header>
				<div class="row">
					<div id="header-btn">
						<a class="header-btn" href="<c:url value='registration' />">
							<button type="button" class="btn btn-primary btn-lg">Registration</button>
						</a>
						<a class="header-btn" href="<c:url value='/login' />">
							<button type="button" class="btn btn-primary btn-lg">Login</button>
						</a>
					</div>
				</div>
		</header>
		<!-- Main -->
		<main>
			<div class="row" id="main">
			<br>
				<div class="col-lg-4 log-md-4 col-sm-4"></div>
				<div class="col-lg-4 log-md-4 col-sm-4" id="main-form">
					<img class="img-responsive" src="<c:url value='/static/img/mail-512.png' />"></img>
					<form  method="POST">
						<div class="form-group">
							<label for="username">Username</label>
							<input name="username" class="form-control input-lg" placeholder="Username" id="username">
						</div>
						<div class="form-group">
							<label for="password">Password</label>
							<input type="password" name="password" class="form-control input-lg" placeholder="Password" id="password">
						</div>
						<button type="submit" class="btn btn-lg btn-primary">Sing In</button>
					</form>
				</div>
				<div class="col-lg-4 log-md-4 col-sm-4"></div>
			</div>
		</main>
		<!-- Footer -->
		<footer>
			
		</footer>
	</div>
</body>
</html>