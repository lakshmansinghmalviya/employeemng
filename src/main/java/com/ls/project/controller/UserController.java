package com.ls.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ls.project.config.request.LoginRequest;
import com.ls.project.model.Employee;
import com.ls.project.response.LoginResponse;
import com.ls.project.response.PageResponse;
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

	@PostMapping("/api/employees")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<Employee>> createEmployee(@RequestBody Employee employee) {
		UnifiedResponse<Employee> response = new UnifiedResponse<>(200, "Added Successfully",
				userService.createEmployee(employee));
		return ResponseEntity.ok(response);
	}

	@PutMapping("/api/employees/{id}")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<Employee>> updateEmployee(@PathVariable Long id,
			@RequestBody Employee employee) {
		UnifiedResponse<Employee> response = new UnifiedResponse<>(200, "Updated Successfully",
				userService.updateEmployee(id, employee));
		return ResponseEntity.ok(response);
	}

	@PostMapping("/api/login")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
		LoginResponse loginResponse = userService.getEmployeeByEmailAndPassword(loginRequest);
		UnifiedResponse<LoginResponse> response = new UnifiedResponse<>(200, "Logged in successfully", loginResponse);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/api/employees/filters")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<PageResponse<Employee>>> findEmployeesByFilters(
			@RequestParam(name = "id", required = false) String id,
			@RequestParam(name = "city", required = false) String city,
			@RequestParam(name = "age", required = false) String age,
			@RequestParam(name = "pageNumber", defaultValue = "0") String pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") String pageSize,
			@RequestParam(name = "offset", defaultValue = "0") String offset,
			@RequestParam(name = "lastPage", defaultValue = "false") String lastPage,
			@RequestParam(name = "searchQuery", required = false) String searchQuery) {
		System.out.println("Received Parameters: in controller");
		System.out.println("ID: " + id);
		System.out.println("City: " + city);
		System.out.println("Age: " + age);

		UnifiedResponse<PageResponse<Employee>> response = new UnifiedResponse<>(200, "Filtered successfully",
				userService.findEmployeesByFilters(city, age, searchQuery, pageNo, pageSize, offset, lastPage, null,
						null, null, null, null, null));
		return ResponseEntity.ok(response);
	}

}
