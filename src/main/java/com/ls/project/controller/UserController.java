package com.ls.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ls.project.config.request.LoginRequest;
import com.ls.project.model.Employee;
import com.ls.project.response.LoginResponse;
import com.ls.project.response.UnifiedResponse;
import com.ls.project.service.UserService;

@Controller
public class UserController {
	Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@GetMapping("/employees")
	public String employeesPage() {
		return "Employees";
	}

//	@PostMapping("/api/employees")
//	@ResponseBody
//	public ResponseEntity<UnifiedResponse<Employee>> createEmployee(@RequestBody Employee employee) {
//		UnifiedResponse<Employee> response = new UnifiedResponse<>(200, "Added Successfully", null);
////		userService.createEmployee(employee);
//		return ResponseEntity.ok(response);
//	}

	@PostMapping("/api/employees")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<Employee>> createEmployee(@RequestBody Employee employee) {
		UnifiedResponse<Employee> response = new UnifiedResponse<>(200, "Added Successfully",
				userService.createEmployee(employee));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/api/employees")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<List<Employee>>> getAllEmployee() {
		UnifiedResponse<List<Employee>> response = new UnifiedResponse<>(200, "Logged in successfully",
				userService.getAllEmployees());
		return ResponseEntity.ok(response);
	}

	@PostMapping("/api/login")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
		LoginResponse loginResponse = userService.getEmployeeByEmailAndPassword(loginRequest);
		UnifiedResponse<LoginResponse> response = new UnifiedResponse<>(200, "Logged in successfully", loginResponse);
		return ResponseEntity.ok(response);
	}
}
