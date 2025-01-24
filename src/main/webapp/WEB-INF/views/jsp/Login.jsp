<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<jsp:include page="Libraries.jsp" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<jsp:include page="WelcomeMessage.jsp" />
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<form class="p-4 border rounded bg-light shadow">
					<div class="mb-3">
						<label for="email" class="form-label">Email:</label> <input
							type="email" id="email" name="email" class="form-control"
							placeholder="Enter email" required>
					</div>
					<div class="mb-3">
						<label for="password" class="form-label">Password:</label> <input
							type="password" id="password" name="password"
							class="form-control" placeholder="Enter password" required>
					</div>
					<div class="d-grid">
						<button class="btn btn-primary login">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript"><%@include file="/WEB-INF/views/js/Login.js" %></script>
</body>
</html>
