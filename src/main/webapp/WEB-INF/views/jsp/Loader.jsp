<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loader</title>
<jsp:include page="Libraries.jsp"></jsp:include>
</head>
<body>
	<div class="loader text-center mt-2">
		<div class="spinner-grow text-primary" role="status">
			<span class="visually-hidden">Loading...</span>
		</div>
		<div class="spinner-grow text-secondary" role="status">
			<span class="visually-hidden">Loading...</span>
		</div>
		<div class="spinner-grow text-success" role="status">
			<span class="visually-hidden">Loading...</span>
		</div>
	</div>
</body>
</html>