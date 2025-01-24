package com.ls.project.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ls.project.enums.Role;
import com.ls.project.model.Employee;

@Repository
public class UserRepository {
	Logger logger = LoggerFactory.getLogger(UserRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Employee createEmployee(Employee employee) {
		String insertSql = "INSERT INTO employees (firstName, lastName, age, email, password, doj, mobile, country, city, street, dept, role) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println("EMploye===   " + employee);
		int rowsAffected = jdbcTemplate.update(insertSql, employee.getFirstName(), employee.getLastName(),
				employee.getAge(), employee.getEmail(), employee.getPassword(), employee.getDoj(), employee.getMobile(),
				employee.getCountry(), employee.getCity(), employee.getStreet(), employee.getDept(),
				employee.getRole().name());
		if (rowsAffected <= 0) {
			throw new RuntimeException("Employee Not Created");
		}
		return employee;
	}

	// Method to fetch all employees
	public List<Employee> getAllEmployees() {
		String sql = "SELECT * FROM employees";
		return jdbcTemplate.query(sql, new EmployeeRowMapper());
	}

	// Method to fetch employee by email and password
	public Employee getEmployeeByEmailAndPassword(String email, String password) {
		String sql = "SELECT * FROM employees WHERE email = ? and password =?";
		Employee employee = jdbcTemplate.queryForObject(sql, new Object[] { email, password }, new EmployeeRowMapper());
		if (employee == null) {
			throw new RuntimeException("User not found with the provided credentials");
		}
		return employee;
	}

	// Method to get employee by ID
	public Employee getEmployeeById(Long id) {
		String sql = "SELECT * FROM employees WHERE id = ?";
		List<Employee> employees = jdbcTemplate.query(sql, new Object[] { id }, new EmployeeRowMapper());
		if (employees.isEmpty()) {
			throw new RuntimeException("Employee not found with id: " + id);
		}
		return employees.get(0);
	}

	public class EmployeeRowMapper implements RowMapper<Employee> {
		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee employee = new Employee();
			employee.setId(rs.getLong("id"));
			employee.setFirstName(rs.getString("firstName"));
			employee.setLastName(rs.getString("lastName"));
			employee.setAge(rs.getInt("age"));
			employee.setEmail(rs.getString("email"));
			employee.setPassword(rs.getString("password"));
			employee.setDoj(rs.getString("doj"));
			employee.setMobile(rs.getString("mobile"));
			employee.setCountry(rs.getString("country"));
			employee.setCity(rs.getString("city"));
			employee.setStreet(rs.getString("street"));
			employee.setDept(rs.getString("dept"));
			employee.setRole(Role.valueOf(rs.getString("role")));
			return employee;
		}
	}
// Method to update employee details
//	public Employee updateEmployee(Long id, Employee employee) {
//		String sql = "UPDATE employees SET firstName = ?, lastName = ?, age = ?, email = ?, password = ?, doj = ?, mobile = ?, country = ?, city = ?, street = ?, dept = ?, role = ? WHERE id = ?";
//
//		int rowsAffected = jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getAge(),
//				employee.getEmail(), employee.getPassword(), employee.getDoj(), employee.getMobile(),
//				employee.getCountry(), employee.getCity(), employee.getStreet(), employee.getDept(), employee.getRole(),
//				id);
//
//		if (rowsAffected == 0) {
//			throw new RuntimeException("Employee not found with id: " + id);
//		}
//		return getEmployeeById(id);
//	}
}
