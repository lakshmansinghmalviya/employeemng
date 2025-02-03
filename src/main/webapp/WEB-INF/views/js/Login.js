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
				console.log("Data is coming like this == " + JSON.stringify(res));
				const role = res.data.roles;
				console.log("Role is coming...." + role);
				if (role == 'Manager' || role == 'Admin') {
					console.log("Came inside the role mang na dadmin ===" + role);
					window.location.href = '/Project/employees';
				}
				else if (role != null) {
					console.log("Role is coming.... inside the elif" + role);
					window.location.href = '/Project/user/dashboard';
				}
				else {
					console.log("Role is coming.... inside the elif" + role);
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
