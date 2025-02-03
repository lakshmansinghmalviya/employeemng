package com.ls.project.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ls.project.model.Employee;
import com.ls.project.model.WhatsappDetails;
import com.ls.project.response.UnifiedResponse;

@Controller
public class UserDataHolder {
	@PostMapping("/data")
	@ResponseBody
	public WhatsappDetails getEmployee(@RequestBody String json) {
		System.out.println("Json is coming like " + json);
		Gson gson = new Gson();
		JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
		JsonObject nestedJson = jsonObject.getAsJsonObject("json");
		WhatsappDetails whatsappDetails = gson.fromJson(nestedJson, WhatsappDetails.class);
		System.out.println("Json converted like === " + whatsappDetails);
		return whatsappDetails;
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
