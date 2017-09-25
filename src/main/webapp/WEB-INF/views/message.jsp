<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">

	<!-- <link rel="stylesheet" type="text/css" href="bootstrap.css"> -->
	<title>Messager</title>
    <script src="<c:url value='/static/js/JQuery.js' />"></script>
    <script src="<c:url value='/static/js/JSON.js' />"></script>
	<script src="<c:url value='/static/js/socket.js' />"></script>

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/css.css'/>">
	<!-- <link rel="stylesheet" type="text/css" href="css.css"> -->
</head>
<body>
	<div class="container-fluid" id="wrapper">
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
		<!-- End header -->

		<!-- Messager -->
		<div class="row" id="friend">
			<!-- Friend Panel -->
			<div class="container-fluid">
				<div class="row">
					<div id="friend-panel" class="col-lg-12">
						<h4>
							<a href="#">
								<span id="open-add-panel" class="glyphicon glyphicon-plus"  aria-hidden="true" title="Add friend"></span>
							</a>
						</h4>
					</div>
				</div>
			</div>
			<!-- End friend panel -->

			<!-- Add user panel -->
			<div id="add-friend-panel" class="hidden">
				<div class="container-fluid">
					<diw class="row">
						<h5>
						<span calss="glyphicon glyphicon-remove" aria-hidden="true"></span>
						</h5>
						<p>Please enter username:</p>
						<input type="text" name="" id="add-friend-input" placeholder="Enter username" class="form-control">
						<button class="btn btn-primary" id="add-friend-button">Add friend</button>
					</diw>
				</div>
			</div>
			<!-- End add user panel -->

			<!-- Friend list -->
			<div class="col-lg-3 col-md-3 col-sm-3" id="friend-list">

				<div class="friend">

					<div class="row">
						<div class="col-lg-3">
							<img src="img11.jpg" class="img-rounded">
						</div>

						<div class="col-lg-9">
							<p>Maxouni</p>
						</div>
					</div>

				</div>

				<div class="friend">

					<div class="row">
						<div class="col-lg-3">
							<img src="img11.jpg" class="img-circle">
						</div>

						<div class="col-lg-9">
							<p>Maxouni</p>
						</div>
					</div>

				</div>

			</div>

			<div class="col-lg-9 col-md-9 col-sm-9" id="message-window">

				<div class="container-fluid">
					<div class="row">

						<div class="message">
							<p>Author</p>
							<p>text message</p>
						</div>

						<div class="message">
							<p>Author</p>
							<p>text message</p>
						</div>

						<div class="message">
							<p>Author</p>
							<p>text message</p>
						</div>

					</div>
				</div>

				<div class="container">
					<div class="row" id="input-message">
						<div class="col-lg-11 col-md-11 col-sm-11">
							<input type="type" name="message" placeholder="Enter message" class="form-control" id="send-input">
						</div>
						<div class="col-lg-1 col-md-1 col-sm-1">
							<button class="btn btn-primary" id="send-button">Send</button>
						</div>
					</div>
				</div>

			</div>

		</div>
		<!-- End -->
	</div>

</body>
</html>