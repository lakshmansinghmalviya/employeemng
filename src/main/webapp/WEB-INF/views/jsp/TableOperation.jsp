<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Table Operation</title>
<jsp:include page="Libraries.jsp"></jsp:include>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-light bg-light justify-content-end">
			<div class="d-flex align-items-center">
				<div class="me-2">
					<select class="form-select" id="ageFilterSelect"
						aria-label="Age Filter" style="min-width: 150px">
						<option selected value="">Filter by Age</option>
						<option value="18-25">18-25</option>
						<option value="26-35">26-35</option>
						<option value="36-45">36-45</option>
						<option value="46-150">46+</option>
					</select>
				</div>

				<div class="me-2">
					<select class="form-select" id="cityFilterSelect"
						aria-label="City Filter" style="min-width: 150px">
						<option selected value="">Filter by City</option>
						<option value="Indore">Indore</option>
						<option value="Bhopal">Bhopal</option>
						<option value="Chicago">Chicago</option>
						<option value="Houston">Houston</option>
					</select>
				</div>

				<input class="form-control me-2" type="search" placeholder="Search"
					id="searchQuery" aria-label="Search">
				<button class="btn btn-outline-success me-2" type="submit">Search</button>
				<button class="btn btn-outline-primary me-2 new">New</button>
			</div>
		</nav>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>
