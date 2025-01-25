$(document).ready(function() {
	// initial fetch the list of the employee
	var employeeList = [{}]
	const fetchEmployeeList = () => {
		$.ajax({
			url: "/Project/api/employees",
			method: "GET",
			success: function(employees) {
				employeeList = employees.data;
				let rows = "";
				employees.data.forEach(function(employee) {
					rows +=
						"<tr class='table-success'>" +
						"<th scope='row'>" + employee.id + "</th>" +
						"<td>" + employee.firstName + "</td>" +
						"<td>" + employee.lastName + "</td>" +
						"<td>" + employee.age + "</td>" +
						"<td>" + employee.doj + "</td>" +
						"<td>" + employee.dept + "</td>" +
						"<td>" + employee.email + "</td>" +
						"<td>" + employee.mobile + "</td>" +
						"<td>" + employee.street + ", " + employee.country + "</td>" +
						"<td>" + employee.city + "</td>" +
						"<td>" +
						"<button class='btn btn-primary btn-sm editEmployee' data-id='" + employee.id + "'>Edit</button> " +
						"<button class='btn btn-danger btn-sm ' data-id='" + employee.id + "'>Active/InActive</button>" +
						"</td>" +
						"</tr>";
				});
				$("table tbody").html(rows);
			},
			error: function(err) {
				console.error("Failed to fetch employees:", err);
			}
		});
	}
	fetchEmployeeList();

	$(document).on("click", ".new", function() {
		makeFormBlank();
		$("#employeeModal").modal("show");
		$("#email").removeAttr("readonly");
		$(".saveButton").addClass("add-employee");
		$(".saveButton").removeClass("save-employee-changes");
		$(".saveButton").html("Add");
	});

	$(document).on("click", ".editEmployee", function() {
		const employeeId = $(this).data("id")
		$("#email").attr("readonly", true);
		setDataToForm(findEmployeeById(employeeId));
		$(".saveButton").html("Save Changes");
		$(".saveButton").addClass("save-employee-changes");
		$(".saveButton").removeClass("add-employee");
		$("#employeeModal").modal("show");
	});

	$(document).on("click", ".add-employee", function(e) {
		e.preventDefault();
		const formData = getFormData();
		console.log(formData);
		$.ajax({
			url: "/Project/api/employees",
			method: "POST",
			data: JSON.stringify(formData),
			contentType: 'application/json',
			success: function(response) {
				console.log("resp===" + response.data);
				$("#employeeModal").modal("hide");
				fetchEmployeeList();
			},
			error: function(err) {
				alert("Error occurred while creating the employee. Please try again." + err.data.msg);
			}
		});
	});

	$(document).on("click", ".save-employee-changes", function(e) {
		e.preventDefault();
		const editedEmployee = getFormData();
		const id = $("#employeeId").val();
		$.ajax({
			url: "/Project/api/employees/" + id,
			method: "PUT",
			data: JSON.stringify(editedEmployee),
			contentType: 'application/json',
			success: function(response) {
				$("#employeeModal").modal("hide");
				fetchEmployeeList();
			},
			error: function(err) {
				alert("Error occurred while editing the employee. Please try again." + err.data.msg);
			}
		});
	});

	const getFormData = () => {
		const formData = {
			id: $("#employeeId").val(),
			firstName: $("#firstName").val(),
			lastName: $("#lastName").val(),
			age: $("#age").val(),
			email: $("#email").val(),
			mobile: $("#mobile").val(),
			country: $("#country").val(),
			city: $("#city").val(),
			street: $("#street").val(),
			dept: $("#dept").val(),
			doj: $("#doj").val(),
			role: $("#role").val(),
			password: $("#password").val()
		};
		return formData;
	};
	const setDataToForm = (employee) => {
		$("#employeeId").val(employee.id);
		$("#firstName").val(employee.firstName);
		$("#lastName").val(employee.lastName);
		$("#age").val(employee.age);
		$("#email").val(employee.email);
		$("#mobile").val(employee.mobile);
		$("#country").val(employee.country);
		$("#city").val(employee.city);
		$("#street").val(employee.street);
		$("#dept").val(employee.dept);
		$("#doj").val(employee.doj);
		$("#role").val(employee.role);
		$("#password").val(employee.password);
	};

	const makeFormBlank = () => {
		$("#employeeId").val("");
		$("#firstName").val("");
		$("#lastName").val("");
		$("#age").val("");
		$("#email").val("");
		$("#mobile").val("");
		$("#country").val("");
		$("#city").val("");
		$("#street").val("");
		$("#dept").val("");
		$("#doj").val("");
		$("#role").val("");
		$("#password").val("");
	};
	const findEmployeeById = (id) => {
		return employeeList.find(obj => obj.id == id);
	}
});