<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logged In User</title>
<jsp:include page="Libraries.jsp"></jsp:include>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">EMS</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Item2</a></li>
				</ul>
				<div class="d-flex">
					<div class="btn-group">
						<button type="button"
							class="btn btn-secondary dropdown-toggle d-flex align-items-center gap-2"
							data-bs-toggle="dropdown" aria-expanded="false">
							<span id="username">Hi, ${employee.firstName}
								${employee.lastName}!</span>
							<!-- Username -->
							<i class="bi bi-person-circle"></i>
							<!-- Bootstrap User Icon -->
						</button>
						<ul class="dropdown-menu dropdown-menu-end">
							<li><button class="dropdown-item" type="button">
									<i class="bi bi-person"></i> Profile
								</button></li>
							<li><button class="dropdown-item services" type="button">
									<i class="bi bi-list"></i> Services
								</button></li>
							<li><hr class="dropdown-divider"></li>
							<li><button class="dropdown-item text-danger logoutbutton"
									type="button">
									<i class="bi bi-box-arrow-right"></i> Logout
								</button></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</nav>

	<h1 class="text-success">Great , ${employee.firstName}
		${employee.lastName}! You are working as ${employee.roles} at The
		Basics</h1>
	<h3>Available Services</h3>
	<ul>
		<c:forEach var="service" items="${services}">
			<li>${service}</li>
		</c:forEach>
	</ul>
	<div class="item-container"></div>
	<script type="text/javascript"><%@include file="/WEB-INF/views/js/UserDashboard.js" %></script>
</body>
</html>