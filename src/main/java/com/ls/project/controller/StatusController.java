package com.ls.project.controller;

import com.ls.project.model.Status;
import com.ls.project.response.UnifiedResponse;
import com.ls.project.service.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class StatusController {
	Logger log = LoggerFactory.getLogger(StatusController.class);

	@Autowired
	StatusService statusService;

	@PostMapping("/api/allStatus")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<Status>> createStatus(@RequestBody Status status) {
		log.info("The status data received is: " + status);
		UnifiedResponse<Status> response = new UnifiedResponse<>(200, "Status Added Successfully",
				statusService.createStatus(status));
		return ResponseEntity.ok(response);
	}
}
