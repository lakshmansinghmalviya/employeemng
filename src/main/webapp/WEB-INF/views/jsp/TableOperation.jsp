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
				<!-- Age Filter -->
				<div class="dropdown me-2">
					<button class="btn btn-outline-primary dropdown-toggle"
						type="button" id="ageFilterDropdown" data-bs-toggle="dropdown"
						aria-expanded="false">Filter by Age</button>
					<div class="dropdown-menu" aria-labelledby="ageFilterDropdown">
						<a class="dropdown-item" href="#" data-age="18-25">18-25</a> <a
							class="dropdown-item" href="#" data-age="26-35">26-35</a> <a
							class="dropdown-item" href="#" data-age="36-45">36-45</a> <a
							class="dropdown-item" href="#" data-age="46+">46+</a>
					</div>
				</div>
				<!-- City Filter -->
				<div class="dropdown me-2">
					<button class="btn btn-outline-primary dropdown-toggle"
						type="button" id="cityFilterDropdown" data-bs-toggle="dropdown"
						aria-expanded="false">Filter by City</button>
					<div class="dropdown-menu" aria-labelledby="cityFilterDropdown">
						<a class="dropdown-item" href="#" data-city="New York">New
							York</a> <a class="dropdown-item" href="#" data-city="Los Angeles">Los
							Angeles</a> <a class="dropdown-item" href="#" data-city="Chicago">Chicago</a>
						<a class="dropdown-item" href="#" data-city="Houston">Houston</a>
					</div>
				</div>

				<!-- Search Input -->
				<input class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search">
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
