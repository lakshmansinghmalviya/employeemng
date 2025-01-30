$(document).ready(function() {

	var employeeList = [{}]

	const pagination = {
		pageNumber: 0,
		totalElements: 10,
		pageSize: 10,
		totalPages: 1,
		offset: 0,
		lastPage: false,
	}

	const setDataToPaginationObject = (response) => {
		pagination.pageNumber = response.data.pageNumber;
		pagination.pageSize = response.data.pageSize;
		pagination.totalElements = response.data.totalElements;
		pagination.totalPages = response.data.totalPages;
		pagination.lastPage = response.data.lastPage;
		console.log("Pagination objec now  === " + JSON.stringify(pagination));
		updatePaginationButtons();
	}

	const fetchFilteredEmployeeFromApi = (queryParams) => {
		const constructedUrl = "/Project/api/employees/filters?" + queryParams;
		$.ajax({
			url: constructedUrl,
			method: "GET",
			success: function(response) {
				setDataToPaginationObject(response)
				employeeList = response.data.content
				setEmployeeDataToUi(employeeList);
			},
			error: function(err) {
				console.error("Error fetching filtered data:", err);
			}
		});
	};

	fetchFilteredEmployeeFromApi("");

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
		$.ajax({
			url: "/Project/api/employees",
			method: "POST",
			data: JSON.stringify(formData),
			contentType: 'application/json',
			success: function(response) {
				$("#employeeModal").modal("hide");
				const filteredData = fetchAllFilteredDataFromUI();
				const queryParams = convertToParams(filteredData);
				fetchFilteredEmployeeFromApi(queryParams);
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
				const filteredData = fetchAllFilteredDataFromUI();
				const queryParams = convertToParams(filteredData);
				fetchFilteredEmployeeFromApi(queryParams);
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

	const setPaginationDataDefault = () => {
		pagination.pageNumber = 0;
		pagination.totalElements = 10;
		pagination.pageSize = 10;
		pagination.totalPages = 1;
		pagination.offset = 0;
		pagination.lastPage = false
	}

	$("#ageFilterSelect").change(function(e) {
		e.preventDefault();
		setPaginationDataDefault();
		const filteredData = fetchAllFilteredDataFromUI();
		const queryParams = convertToParams(filteredData);
		fetchFilteredEmployeeFromApi(queryParams);
	});

	$("#cityFilterSelect").change(function(e) {
		e.preventDefault();
		setPaginationDataDefault();
		const filteredData = fetchAllFilteredDataFromUI();
		const queryParams = convertToParams(filteredData);
		fetchFilteredEmployeeFromApi(queryParams);
	});

	$("#searchQuery").on("input", function(e) {
		e.preventDefault();
		setPaginationDataDefault();
		const filteredData = fetchAllFilteredDataFromUI();
		console.log(filteredData);
		const queryParams = convertToParams(filteredData);
		console.log(queryParams);
		fetchFilteredEmployeeFromApi(queryParams);
	});

	$("#nextPage").click(function(e) {
		e.preventDefault();
		pagination.pageNumber++;
		updatePaginationButtons();
		const queryParams = convertToParams(fetchAllFilteredDataFromUI());
		fetchFilteredEmployeeFromApi(queryParams);
	});

	$("#firstPage").click(function(e) {
		e.preventDefault();
		setPaginationDataDefault();
		updatePaginationButtons();
		const queryParams = convertToParams(fetchAllFilteredDataFromUI());
		fetchFilteredEmployeeFromApi(queryParams);
	});

	$("#goToPage").click(function(e) {
		e.preventDefault();
		const pageNo = $("#pageNumber").val() - 1;
		pagination.pageNumber = pageNo;
		const filteredData = fetchAllFilteredDataFromUI();
		const queryParams = convertToParams(filteredData);
		fetchFilteredEmployeeFromApi(queryParams);
	});

	$("#lastPage").click(function(e) {
		e.preventDefault();
		$("#nextPage").hide();
		const queryParams = convertToParams(fetchAllFilteredDataFromUIOnLastPageClicked());
		fetchFilteredEmployeeFromApi(queryParams);
	});

	$("#prevPage").click(function(e) {
		e.preventDefault();
		console.log("Before minus ===" + pagination.pageNumber);
		if (pagination.pageNumber >= 1) {
			pagination.pageNumber = pagination.pageNumber - 1;
			pagination.lastPage = false;
		}
		console.log("After minus ===" + pagination.pageNumber);
		const queryParams = convertToParams(fetchAllFilteredDataFromUI());
		console.log("The pagination params === " + queryParams);
		fetchFilteredEmployeeFromApi(queryParams);
	});

	function updatePaginationButtons() {
		console.log("Pagination data " + JSON.stringify(pagination));
		$("#pageNumber").val(pagination.pageNumber + 1)
		$("#prevPage").toggle(pagination.pageNumber > 0);
		$("#firstPage").toggle(pagination.pageNumber > 0)

		if (pagination.lastPage == true) {
			$("#nextPage").hide();
			$("#nextPage").hide();
			$("#lastPage").hide();
		}
		else {
			$("#nextPage").show();
			$("#lastPage").show();
		}
	}

	const fetchAllFilteredDataFromUI = () => {
		return {
			age: $("#ageFilterSelect").val(),
			city: $("#cityFilterSelect").val(),
			searchQuery: $("#searchQuery").val(),
			pageNumber: pagination.pageNumber,
			pageSize: pagination.pageSize,
			lastPage: pagination.lastPage,
		}
	}

	const fetchAllFilteredDataFromUIOnLastPageClicked = () => {
		return {
			...fetchAllFilteredDataFromUI(),
			lastPage: "true"
		}
	}

	const convertToParams = (data) => {
		const queryParams = $.param(data);
		console.log("Params" + queryParams)
		return queryParams;
	}

	const setEmployeeDataToUi = (employees) => {
		let rows = "";
		console.log("Employees i am getting to render" + JSON.stringify(employees));

		employees.forEach(function(employee) {
			rows +=
				"<tr class='table-success border border-success'>" +
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
	}

	$(".newByCSV").click(function(e) {
		e.preventDefault();
		$("#fileUploadModal").modal("show");
	});
});