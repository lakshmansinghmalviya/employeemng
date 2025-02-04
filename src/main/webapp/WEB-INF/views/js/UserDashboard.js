$(document).ready(function() {

	$(".logoutbutton").click(function() {
		///user/remove/session
		const constructedUrl = "/Project/user/remove/session";
		$.ajax({
			url: constructedUrl,
			method: "GET",
			success: function(response) {
				// jsp will redirect no need;
				window.location.href = "/Project/login";
				console.log("Logged out the user details from the session")
			},
			error: function(err) {
				console.error("Error fetching filtered data:", err);
			}
		});
	})

	$(".services").click(function() { 
		const constructedUrl = "/Project/user/data";
		$.ajax({
			url: constructedUrl,
			method: "GET",
			success: function(response) {
				// jsp will redirect no need;
				//window.location.href = "/Project/login";
				console.log("will show the user's services in the div with good desgining")
			},
			error: function(err) {
				console.error("Error fetching filtered data:", err);
			}
		});
	})

})