package com.ls.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.project.config.request.LoginRequest;
import com.ls.project.enums.Role;
import com.ls.project.model.Employee;
import com.ls.project.repository.UserRepository;
import com.ls.project.response.LoginResponse;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public LoginResponse getEmployeeByEmailAndPassword(LoginRequest loginRequest) {
		Employee employee = userRepository.getEmployeeByEmailAndPassword(loginRequest.getEmail(),
				loginRequest.getPassword());
		if (employee == null) {
			throw new RuntimeException("User not found with the details");
		}
		LoginResponse response = new LoginResponse();
		response.setRole(employee.getRole());
		return response;
	}

	public Employee createEmployee(Employee employee) {
		return userRepository.createEmployee(employee);
	}

	public List<Employee> getAllEmployees() {
		return userRepository.getAllEmployees();
	}
}