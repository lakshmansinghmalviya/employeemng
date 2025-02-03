<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ls.project.model.Employee"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employees</title>
<jsp:include page="Libraries.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/views/jsp/TableOperation.jsp" />
	<div>
		<table class="table table-bordered color-primary">
			<thead class="table-dark">
				<tr>
					<th scope="col">Id</th>
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th scope="col">Age</th>
					<th scope="col">DOJ</th>
					<th scope="col">Dept</th>
					<th scope="col">Email</th>
					<th scope="col">Mobile</th>
					<th scope="col">Address</th>
					<th scope="col">City</th>
					<th scope="col">Actions</th>
					<th scope="col">Active</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<jsp:include page="Pagination.jsp"></jsp:include>
	<jsp:include page="EmployeeModal.jsp"></jsp:include>
	<jsp:include page="UploadFileModal.jsp"></jsp:include>
	<script type="text/javascript"><%@include file="/WEB-INF/views/js/Employees.js" %></script>
</body>
</html>
