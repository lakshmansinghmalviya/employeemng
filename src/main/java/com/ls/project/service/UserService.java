package com.ls.project.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.ls.project.config.request.LoginRequest;
import com.ls.project.model.Employee;
import com.ls.project.repository.UserRepository;
import com.ls.project.response.PageResponse;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	private final TransactionTemplate transactionTemplate;

	public UserService(PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public Employee getEmployeeByEmailAndPassword(LoginRequest loginRequest) {
		Employee employee = userRepository.getEmployeeByEmailAndPassword(loginRequest.getEmail(),
				loginRequest.getPassword());
		if (employee == null) {
			throw new RuntimeException("User not found with the details");
		}
		return employee;
	}

	public Employee createEmployee(Employee employee) {
		return userRepository.createEmployee(employee);
	}

	public List<Employee> createEmployees(MultipartFile file) throws IOException {
		List<Employee> employees = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line = reader.readLine();
			boolean firstLine = true;

			while (line != null) {
				if (firstLine) {
					firstLine = false;
					line = reader.readLine();
					continue;
				}
				String[] values = line.split(",,,,,");
				System.out.println("Array String are ===" + Arrays.toString(values));

				if (values.length < 13) {
					throw new IllegalArgumentException(
							"Invalid CSV format: Expected at least 13 columns but found " + values.length);
				}

				try {
					Employee employee = new Employee();
					employee.setId(Long.parseLong(values[0].trim().replaceAll("\"", "")));
					employee.setFirstName(values[1].trim().replaceAll("\"", ""));
					employee.setLastName(values[2].trim().replaceAll("\"", ""));
					employee.setAge(Integer.parseInt(values[3].trim().replaceAll("\"", "")));
					employee.setEmail(values[4].trim().replaceAll("\"", ""));
					employee.setPassword(values[5].trim().replaceAll("\"", ""));
					employee.setDoj(values[6].trim().replaceAll("\"", ""));
					employee.setMobile(values[7].trim().replaceAll("\"", ""));
					employee.setCountry(values[8].trim().replaceAll("\"", ""));
					employee.setCity(values[9].trim().replaceAll("\"", ""));
					employee.setStreet(values[10].trim().replaceAll("\"", ""));
					employee.setDept(values[11].trim().replaceAll("\"", ""));
					employee.setRoles(values[12].trim().replaceAll("\"", ""));

					employees.add(employee);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("Invalid number format in CSV: " + e.getMessage());
				}
				line = reader.readLine();
			}
		}
		try {
			return userRepository.createEmployees(employees);
		} catch (Exception e) {
			throw new RuntimeException("The file count't upload due to some errors ");
		}
	}

	public List<Employee> getAllEmployees() {
		return userRepository.getAllEmployees();
	}

	public Employee updateEmployee(Long id, Employee employee) {
		return transactionTemplate.execute(new TransactionCallback<Employee>() {
			@Override
			public Employee doInTransaction(TransactionStatus status) {
				try {
					// Perform the update operations within the transaction
					updateEmployeeFirstName(id, employee);
					updateEmployeeLastName(id, employee);
					return updateEmployeeOtherDetails(id, employee); // Return the final result
				} catch (RuntimeException e) {
					status.setRollbackOnly(); // Explicitly mark the transaction for rollback on exception
					throw e; // Rethrow the exception to ensure the transaction is rolled back
				}
			}
		});
	}

	public Employee updateEmployeeInOneGo(Long id, Employee employee) {
		return userRepository.updateEmployeeInOneGo(id, employee);
	}

	public Employee updateEmployeeFirstName(Long id, Employee employee) {
		return userRepository.updateEmployeeFirstName(id, employee);
	}

	public Employee updateEmployeeLastName(Long id, Employee employee) {
		return userRepository.updateEmployeeLastName(id, employee);
	}

	public Employee updateEmployeeOtherDetails(Long id, Employee employee) {
		if (id != null) {
		}
		// if we uncomment this line then we can test the transaction
		// throw new RuntimeException("Threw the exception to check...");
		return userRepository.updateEmployeeOtherDetails(id, employee);
	}

	public PageResponse<Employee> findEmployeesByFilters(String city, String age, String searchQuery, String pageNo,
			String pageSize, String offset, String lastPage, String doj, String mobile, String country, String street,
			String dept, String role) {
		System.out.println("Received Parameters: in service");
		System.out.println("City: " + city);
		System.out.println("Age: " + age);
		System.out.println("Query: " + searchQuery);
		return userRepository.findEmployeesByFilters(city, age, searchQuery, pageNo, pageSize, offset, lastPage);
	}
}
