<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<title>Registration</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/css.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/bootstrap.css'/>">
</head>
<body>
	<div class="container-fluid">
		<!-- Header -->
		<header>
				<div class="row">
					<div id="header-btn">
						<a class="header-btn" href="<c:url value='/registration' />">
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
			<div class="row">
				<div class="col-lg-4 log-md-4 col-sm-4"></div>

				<div class="log-lg-4 col-md-4 col-sm-4">
					<img src="<c:url value='/static/img/Registration.png' />" class="img-responsive">
					<form:form method="POST" modelAttribute="registrationForm">
						<div class="form-group">
							<form:label path="name">First name</form:label>
							<form:input path="name" class="form-control input-lg" placeholder="Name" id="name"/>
						</div>
						<div class="form-group">
							<form:label path="username">Username</form:label>
							<form:input path="username" class="form-control input-lg" placeholder="Username" id="username"/>
						</div>
						<div class="form-group">
							<form:label path="email">Email</form:label>
							<form:input path="email" class="form-control input-lg" placeholder="Email" id="email"/>
						</div>
						<div class="form-group">
							<form:label path="password">Password</form:label>
							<form:input path="password" type="password" class="form-control input-lg" placeholder="Password" id="password"/>
						</div>
						<button type="submit" class="btn btn-primary btn-lg" >Registration</button>
					</form:form>
				</div>
				<div class="log-lg-4 col-md-4 col-sm-4"></div>
			</div>
		</main>

		<!-- Footer -->
		<footer>
			
		</footer>
	</div>
</body>
</html>