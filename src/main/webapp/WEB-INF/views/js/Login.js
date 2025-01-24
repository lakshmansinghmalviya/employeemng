$(document).ready(function() {
	console.log("Hello login page");

	$(document).on("click", ".login", function(e) {
		e.preventDefault();

		const email = $("#email").val();
		const pwd = $("#password").val();
		console.log("Email:", email);
		console.log("Password:", pwd);

		$.ajax({
			url: '/Project/api/login',
			method: 'POST',
			data: JSON.stringify({
				email: email,
				password: pwd
			}),
			contentType: 'application/json',
			success: function(res) {
				console.log(res);
				const role = res.data.role;
				if (role == 'Manager') {
					window.location.href = '/Project/employees';
				}
				else if (role == 'Developer') {
					window.location.href = '/Project/login';
				}
			},
			error: function(xhr, status, error) {
				console.log("AJAX Error: ", status, error);
				console.log("Response Text: ", xhr.responseText);
			}
		});
	});
});
