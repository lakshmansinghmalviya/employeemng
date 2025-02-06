package com.ls.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ls.project.model.Employee;
import com.ls.project.model.Sender;
import com.ls.project.model.SenderStatusTableDto;
import com.ls.project.response.UnifiedResponse;
import com.ls.project.service.SenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class SenderController {

	Logger log = LoggerFactory.getLogger(SenderController.class);

	@Autowired
	SenderService senderService;

	@PostMapping("/api/senders")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<Sender>> createSender(@RequestBody Sender sender) {
		System.out.println("The sender is coming like ===" + sender);
		UnifiedResponse<Sender> response = new UnifiedResponse<>(200, "Added Successfully",
				senderService.createSender(sender));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/senders")
	public String getSenders() {
		return "Senders";
	}

	@GetMapping("/api/senders/states/filters")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<List<SenderStatusTableDto>>> getSenderStates() {
		System.out.println("The sender states is coming like");
		UnifiedResponse<List<SenderStatusTableDto>> response = new UnifiedResponse<>(200, "Fetched Successfully",
				senderService.getSenderStats());
		return ResponseEntity.ok(response);
	}
}
