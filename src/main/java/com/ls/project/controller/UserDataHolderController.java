package com.ls.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ls.project.config.util.PageResponseBuilder;
import com.ls.project.model.Employee;
import com.ls.project.model.WhatsappDetails;
import com.ls.project.model.WhatsappDetails2;
import com.ls.project.response.UnifiedResponse;
import com.ls.project.service.UserService;

@Controller
public class UserDataHolderController {

	private List<Employee> employeesList = new ArrayList<>();

	@Autowired
	UserService userService;

	@GetMapping("/employees/{id}")
	public ResponseEntity<UnifiedResponse<Employee>> getEmployeeById(@PathVariable Long id) {
		Optional<Employee> employee = employeesList.stream().filter(object -> object.getId() == id).findFirst();
		if (employee.isPresent())
			return ResponseEntity.ok(new UnifiedResponse<>(200, "Employee is fetched from the cache ", employee.get()));
		else {
			Employee fetchedEmployee = userService.getEmployeeById(id);
			employeesList.add(fetchedEmployee);
			return ResponseEntity.ok(new UnifiedResponse<>(200, "Employee is fetched from the db", fetchedEmployee));
		}
	}

	@PostMapping("/data")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<WhatsappDetails>> getEmployee(@RequestBody String json) {
		System.out.println("Json is coming like " + json);
		Gson gson = new Gson();
		JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
		JsonObject nestedJson = jsonObject.getAsJsonObject("json");
		WhatsappDetails whatsappDetails = gson.fromJson(nestedJson, WhatsappDetails.class);
		System.out.println("Json converted like === " + whatsappDetails);
		return ResponseEntity.ok(new UnifiedResponse<WhatsappDetails>(200, "Okay parsed", whatsappDetails));
	}

	@PostMapping("/data2")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<WhatsappDetails2>> parsedJson(@RequestBody String json) {
		System.out.println("Json is coming like " + json);
		Gson gson = new Gson();
		JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
		JsonObject nestedJson = jsonObject.getAsJsonObject("json");
		WhatsappDetails2 whatsappDetails = gson.fromJson(nestedJson, WhatsappDetails2.class);
		System.out.println("Json converted like === " + whatsappDetails);
		return ResponseEntity.ok(new UnifiedResponse<WhatsappDetails2>(200, "Okay parsed", whatsappDetails));
	}

	@GetMapping("/user/data")
	@ResponseBody
	public ResponseEntity<UnifiedResponse<Employee>> userDashboardPage(HttpSession session) {
		Employee employee = (Employee) session.getAttribute("loggedInUser");
		if (employee == null) {
			throw new RuntimeException("Session has been expired");
		}
		UnifiedResponse<Employee> response = new UnifiedResponse<>(200, "Fetched Successfully", employee);
		return ResponseEntity.ok(response);
	}
}
