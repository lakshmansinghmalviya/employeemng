<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Create and Update Modal</title>
</head>
<body>
	<!-- Modal for editing and creating the record -->
	<div class="modal fade" id="employeeModal" tabindex="-1"
		aria-labelledby="employeeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="employeeModalLabel">Employee
						Details</h5>
				</div>
				<div class="modal-body">
					<form id="employeeForm">
						<input type="hidden" id="employeeId" name="id">
						<!-- Hidden ID for editing -->
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="firstName" class="form-label">First Name</label> <input
									type="text" class="form-control" id="firstName"
									name="firstName" placeholder="Enter first name" required>
							</div>
							<div class="col-md-6 mb-3">
								<label for="lastName" class="form-label">Last Name</label> <input
									type="text" class="form-control" id="lastName" name="lastName"
									placeholder="Enter last name" required>
							</div>
						</div>

						<div class="row">
							<div class="col-md-4 mb-3">
								<label for="age" class="form-label">Age</label> <input
									type="number" class="form-control" id="age" name="age"
									placeholder="Enter age" required>
							</div>
							<div class="col-md-4 mb-3">
								<label for="email" class="form-label">Email</label> <input
									type="email" class="form-control" id="email" name="email"
									placeholder="Enter email" required readonly>
							</div>
							<div class="col-md-4 mb-3">
								<label for="mobile" class="form-label">Mobile</label> <input
									type="tel" class="form-control" id="mobile" name="mobile"
									placeholder="Enter mobile number" required>
							</div>
						</div>

						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="country" class="form-label">Country</label> <input
									type="text" class="form-control" id="country" name="country"
									placeholder="Enter country" required>
							</div>
							<div class="col-md-6 mb-3">
								<label for="city" class="form-label">City</label> <input
									type="text" class="form-control" id="city" name="city"
									placeholder="Enter city" required>
							</div>
						</div>

						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="street" class="form-label">Street</label> <input
									type="text" class="form-control" id="street" name="street"
									placeholder="Enter street" required>
							</div>
							<div class="col-md-6 mb-3">
								<label for="dept" class="form-label">Department</label> <input
									type="text" class="form-control" id="dept" name="dept"
									placeholder="Enter department" required>
							</div>
						</div>

						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="doj" class="form-label">Date of Joining</label> <input
									type="date" class="form-control" id="doj" name="doj" required>
							</div>
							<div class="col-md-6 mb-3">
								<label for="role" class="form-label">Role</label> <select
									class="form-select" id="role" name="role" required>
									<option value="" disabled selected>Select role</option>
									<option value="Manager">Manager</option>
									<option value="Developer">Developer</option>
								</select>
							</div>
						</div>

						<div class="mb-3">
							<label for="password" class="form-label">Password</label> <input
								type="password" class="form-control" id="password"
								name="password" placeholder="Enter password" required>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button form="employeeForm" class="btn btn-primary saveButton">Save
						Changes</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>