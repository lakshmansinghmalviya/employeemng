<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagination</title>
<jsp:include page="Libraries.jsp"></jsp:include>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-end">
			<li class="page-item"><a class="page-link" id="totalPages"
				value="99">Total Page: 99</a></li>
			<li class="page-item"><a class="page-link btn" id="prevPage">Prev</a></li>
			<li class="page-item"><a class="page-link btn" id="firstPage">First</a></li>
			<li class="page-item">
				<div class="input-group">
					<input id="pageNumber" class="form-control text-center"
						type="number" style="width: 100px;" placeholder="Page No" min="1"
						aria-label="Search Page Number">
					<button class="btn btn-primary" id="goToPage" type="button">Go</button>
				</div>
			</li>
			<li class="page-item"><a class="page-link btn" id="lastPage">Last</a></li>
			<li class="page-item"><a class="page-link btn" id="nextPage">Next</a></li>
		</ul>
	</nav>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>

</body>
</html>