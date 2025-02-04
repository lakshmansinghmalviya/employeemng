$(document).ready(function() {
	var senderStatesList = [{}]
	console.log("Okay fine....")
	const fetchFilteredSendersFromApi = (queryParams) => {
		const constructedUrl = "/Project/api/senders/states/filters?" + queryParams;
		$.ajax({
			url: constructedUrl,
			method: "GET",
			success: function(response) {
				console.log("Data===" + JSON.stringify(response));
				//setDataToPaginationObject(response)
				senderStatesList = response.data
				console.log("The list of the senders is " + senderStatesList + "       " + response.data.content)
				setSenderStatesDataToUi(senderStatesList);
				console.log("data here " + response.data);
			},
			error: function(err) {
				console.error("Error fetching filtered data:", err);
			}
		});
	};
	fetchFilteredSendersFromApi("")

	const setSenderStatesDataToUi = (senders) => {
		let rows = "";
		console.log("Employees i am getting to render" + JSON.stringify(senders));

		senders.forEach(function(sender) {
			rows +=
				"<tr class='table-success border border-dark editEmployee' data-id='" + sender.id + "'>" +
				"<th scope='row'>" + sender.id + "</th>" +
				"<td>" + sender.userId + "</td>" +
				"<td>" + sender.content + "</td>" +
				"<td>" + sender.count + "</td>" +
				//	"<td>" + sender.sentCount + "</td>" +
				"<td>" + sender.receivedCount + "</td>" +
				//	"<td>" + sender.sentPercentage + "</td>" +
				"<td>" + sender.receivedPercentage + "</td>" +
				"<td>" + sender.time + "</td>" +
				"<td>" +
				"<div class='progress' role='progressbar' aria-valuenow='" + sender.receivedPercentage + "' aria-valuemin='0' aria-valuemax='100'>" +
				"<div class='progress-bar bg-success' style='width: " + sender.receivedPercentage + "%'>" + sender.receivedPercentage + "%</div>" +
				"</div>" +
				"</td>" +
				"</tr>";
		});
		$("table tbody").html(rows);
	}
})