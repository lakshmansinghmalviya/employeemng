package com.ls.project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
 
	@GetMapping("/user/remove/session")
	public String removeUserSession(HttpSession session) {
		System.out.println("Removing session...");
		session.invalidate();
		return "redirect:/login"; 
	}

	@GetMapping("/user/dashboard")
	public ModelAndView userDashboardPage(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("UserDashboard");
		Employee employee = (Employee) session.getAttribute("loggedInUser");
		if (employee == null) {
			modelAndView.setViewName("redirect:/login");
			return modelAndView;
		}
		String[] services = employee.getServices().split(",");
		modelAndView.addObject("employee", employee);
		modelAndView.addObject("services", services);
		return modelAndView;
	}

	@PostMapping("/api/employees")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<Employee>> createEmployee(@RequestBody Employee employee) {
		UnifiedResponse<Employee> response = new UnifiedResponse<>(200, "Added Successfully",
				userService.createEmployee(employee));
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/api/employees/csv", consumes = { "multipart/form-data" })
	@ResponseBody
	public ResponseEntity<UnifiedResponse<List<Employee>>> createEmployeeUsingCsvFile(
			@RequestParam("file") MultipartFile file) throws IOException {
		UnifiedResponse<List<Employee>> response = new UnifiedResponse<>(200, "Added Successfully",
				userService.createEmployees(file));
		return ResponseEntity.ok(response);
	}

	@PutMapping("/api/employees/{id}")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<Employee>> updateEmployee(@PathVariable Long id,
			@RequestBody Employee employee) {
		System.out.println("Data is coming like on update  ===" + employee);
		UnifiedResponse<Employee> response = new UnifiedResponse<>(200, "Updated Successfully",
				userService.updateEmployeeInOneGo(id, employee));
		return ResponseEntity.ok(response);
	}

	@PostMapping("/api/login")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest,
			HttpSession session) {
		System.out.println("Login data be like ===" + loginRequest);
		Employee employee = userService.getEmployeeByEmailAndPassword(loginRequest);
		employee.setFirstName("🤩 " + employee.getFirstName());
		session.setAttribute("loggedInUser", employee);
		LoginResponse loginResponse = new LoginResponse(employee.getRoles());
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
