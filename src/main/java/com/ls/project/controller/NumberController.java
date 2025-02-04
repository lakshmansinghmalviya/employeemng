package com.ls.project.controller;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ls.project.response.UnifiedResponse;
import com.ls.project.service.NumberService;
import com.ls.project.model.Number;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class NumberController {
	Logger log = LoggerFactory.getLogger(NumberController.class);

	@Autowired
	private NumberService numberService;

	@PostMapping("/api/numbers")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<Number>> createNumber(@RequestBody Number number) {
		UnifiedResponse<Number> response = new UnifiedResponse<>(200, "Added Successfully",
				numberService.createNumber(number));
		return ResponseEntity.ok(response);
	}
}
