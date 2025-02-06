<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Senders States Table</title>
<jsp:include page="Libraries.jsp"></jsp:include>
</head>
<body>
	<div>
		<table class="table table-bordered color-primary">
			<thead class="table-dark">
				<tr>
					<th scope="col">Id</th>
					<th scope="col">UserId</th>
					<th scope="col">Content</th>
					<th scope="col">Count</th>
					<!--  	<th scope="col">Sent Messages</th> -->
					<th scope="col">Received Messages</th>
					<!--  	<th scope="col">SentPercentage</th> -->
					<th scope="col">Received Percentage</th>
					<th scope="col">Time</th>
					<th scope="col">Progress</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<jsp:include page="Pagination.jsp"></jsp:include>
	<script type="text/javascript"><%@include file="/WEB-INF/views/js/Senders.js" %></script>
</body>
</html>