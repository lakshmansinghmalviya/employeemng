$(document).ready(function() {

	$(".newByCSV").click(function(e) {
		e.preventDefault();
		$("#fileUploadModal").modal("show");
	});


	$(".uploadCSV").click(function(event) {
		event.preventDefault();

		let formData = new FormData();
		let fileInput = $("#inputGroupFile02")[0].files[0];

		if (!fileInput) {
			alert("Please select a file to upload.");
			return;
		}

		formData.append("file", fileInput);

		$.ajax({
			url: "/Project/api/employees/csv",
			type: "POST",
			data: formData,
			processData: false,
			contentType: false,
			success: function(response) {
				if (response.code == 200) {
					$("#fileUploadModal").modal("hide");
					console.log("File uploaded successfully:", response);
					alert("File uploaded successfully!");
				} else {
					alert(response.msg);
				}
			},
			error: function(error) {
				console.error(error.msg);
				alert("Error uploading file. Please try again.");
			}
		});
	});
})