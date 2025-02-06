<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload CSV File</title>
</head>
<body>
	<div class="modal fade" id="fileUploadModal" tabindex="-1"
		aria-labelledby="fileUploadModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="fileUploadModalLabel">Select File</h5>
				</div>
				<div class="modal-body">
					<form enctype="multipart/form-data">
						<div class="input-group mb-3">
							<input type="file" class="form-control" id="inputGroupFile02"
								name="file"> <label class="input-group-text"
								for="inputGroupFile02">Upload</label>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<button class="btn btn-primary uploadCSV">Upload CSV</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script type="text/javascript"><%@include file="/WEB-INF/views/js/UploadFileModal.js" %></script>
</body>
</html>
