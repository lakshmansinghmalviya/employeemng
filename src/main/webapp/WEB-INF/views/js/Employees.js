$(document).ready(function() {

	$.ajax({
		url: "/Project/api/employees",
		method: "GET",
		success: function(data) {
			let rows = "";
			data.data.forEach(function(employee) {
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
					"<button class='btn btn-danger btn-sm ' data-id='" + employee.id + "'>Delete</button>" +
					"</td>" +
					"</tr>";
			});
			$("table tbody").html(rows);
		},
		error: function(err) {
			console.error("Failed to fetch employees:", err);
		}
	});

	$(document).on("click", ".new", function() {
		$("#employeeModal").modal("show");
		$(".saveButton").addClass("add-employee");
		$(".saveButton").removeClass("save-employee-changes");
		$(".saveButton").html("Add");
	});

	$(document).on("click", ".editEmployee", function() {
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
			},
			error: function(err) {
				console.error("Failed to create employee:", err);
			}
		});
		//$(".add-employee").html("Add");
		//	$("#employeeModal").modal("hide");
	});

	$(document).on("click", ".save-employee-changes", function() {
		// take the data here and edit employee
		console.log("Taking the data...edit")
		//	$(".add-employee").html("Add");
		//	$("#employeeModal").modal("show");
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
});