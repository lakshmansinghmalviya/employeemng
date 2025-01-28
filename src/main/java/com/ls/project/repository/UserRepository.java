package com.ls.project.repository;

import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.InterfaceMaker;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ls.project.config.util.PageResponseBuilder;
import com.ls.project.enums.Role;
import com.ls.project.model.Employee;
import com.ls.project.response.PageResponse;

@Repository
public class UserRepository {
	Logger logger = LoggerFactory.getLogger(UserRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Employee createEmployee(Employee employee) {
		String insertSql = "INSERT INTO employees (firstName, lastName, age, email, password, doj, mobile, country, city, street, dept, role) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println("EMploye===   " + employee);
		String hashedPwd = passwordEncoder.encode(employee.getPassword());
		int rowsAffected = jdbcTemplate.update(insertSql, employee.getFirstName(), employee.getLastName(),
				employee.getAge(), employee.getEmail(), hashedPwd, employee.getDoj(), employee.getMobile(),
				employee.getCountry(), employee.getCity(), employee.getStreet(), employee.getDept(),
				employee.getRole().name());
		if (rowsAffected <= 0) {
			throw new RuntimeException("Employee Not Created");
		}
		return employee;
	}

	// Method to update employee details
	public Employee updateEmployee(Long id, Employee employee) {
		String sql = "UPDATE employees SET firstName = ?, lastName = ?, age = ?, email = ?, password = ?, doj = ?, mobile = ?, country = ?, city = ?, street = ?, dept = ?, role = ? WHERE id = ?";
		String hashedPwd = passwordEncoder.encode(employee.getPassword());
		int rowsAffected = jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getAge(),
				employee.getEmail(), hashedPwd, employee.getDoj(), employee.getMobile(), employee.getCountry(),
				employee.getCity(), employee.getStreet(), employee.getDept(), employee.getRole().name(), id);

		if (rowsAffected == 0) {
			throw new RuntimeException("Employee not found with id: " + id);
		}
		employee.setPassword(hashedPwd);
		return employee;
	}

	// Method to fetch all employees
	public List<Employee> getAllEmployees() {
		String sql = "SELECT * FROM employees";
		return jdbcTemplate.query(sql, new EmployeeRowMapper());
	}

	// Method to fetch employee by email and password
	public Employee getEmployeeByEmailAndPassword(String email, String password) {
		String sql = "SELECT * FROM employees WHERE email = ?";
		Employee employee = jdbcTemplate.queryForObject(sql, new Object[] { email }, new EmployeeRowMapper());

		if (passwordEncoder.matches(password, employee.getPassword())) {
			return employee;
		} else {
			throw new RuntimeException("User not found with the provided credentials");
		}
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

	public PageResponse<Employee> findEmployeesByFilters(String city, String age, String searchQuery,
			String comingpageNo, String comingpageSize) {
		StringBuilder sqlQuery = new StringBuilder("SELECT * FROM employees WHERE 1=1 ");
		List<Object> params = new ArrayList<>();
		int camePageNo = Integer.parseInt(comingpageNo);
		int camePageSize = Integer.parseInt(comingpageSize);

		if (age != null && !age.isEmpty()) {
			String[] ages = age.split("-");
			if (ages.length == 2) {
				Integer minAge = Integer.parseInt(ages[0]);
				Integer maxAge = Integer.parseInt(ages[1]);
				sqlQuery.append(" AND (age BETWEEN ? AND ?)");
				params.add(minAge);
				params.add(maxAge);
			}
		} else {
			sqlQuery.append(" AND (age IS NULL OR age IS NOT NULL)");
		}

		if (city != null && !city.isEmpty()) {
			sqlQuery.append(" AND (city = ?)");
			params.add(city);
		}

		if (searchQuery != null && !searchQuery.isEmpty()) {
			sqlQuery.append(" AND (").append("(firstName LIKE CONCAT('%', ?, '%')) OR ")
					.append("(lastName LIKE CONCAT('%', ?, '%')) OR ").append("(email LIKE CONCAT('%', ?, '%')) OR ")
					.append("(doj LIKE CONCAT('%', ?, '%')) OR ").append("(mobile LIKE CONCAT('%', ?, '%')) OR ")
					.append("(country LIKE CONCAT('%', ?, '%')) OR ").append("(street LIKE CONCAT('%', ?, '%')) OR ")
					.append("(city LIKE CONCAT('%', ?, '%')) OR ").append("(dept LIKE CONCAT('%', ?, '%')) OR ")
					.append("(role LIKE CONCAT('%', ?, '%')))");
			for (int i = 0; i < 10; i++) {
				params.add(searchQuery);
			}
		}
		// here we are getting the total records and then we will do our work
		int totalRecords = findTotalRecordsOfEmployeesByFilters(sqlQuery, params);
		int totalPages = (totalRecords / camePageSize) <= 0 ? 1
				: (totalRecords % camePageSize == 0 ? totalRecords / camePageSize : totalRecords / camePageSize + 1);

		System.out.println("Total records ==" + totalRecords);
		int limit = camePageSize;
		int offset = camePageNo * limit;
		sqlQuery.append(" LIMIT ? OFFSET ?");
		params.add(limit);
		params.add(offset);

		System.out.println("The final query: " + sqlQuery.toString());
		System.out.println("Parameters: " + params);

		try {
			List<Employee> employees = jdbcTemplate.query(sqlQuery.toString(), params.toArray(),
					new EmployeeRowMapper());
			return PageResponseBuilder.buildPageResponse(employees, totalRecords, totalPages, camePageNo, camePageSize);
		} catch (Exception e) {
			System.out.println("Error during query execution: " + e.getMessage());
		}
		return null;
	}

	public Integer findTotalRecordsOfEmployeesByFilters(StringBuilder sqlQuery, List<Object> params) {
		String queryString = new String(sqlQuery.toString());
		queryString = queryString.replaceFirst("(?i)^SELECT \\*", "SELECT COUNT(1)");

		try {
			return jdbcTemplate.queryForObject(queryString, params.toArray(), Integer.class);
		} catch (Exception e) {
			System.out.println("Error during query execution: " + e.getMessage());
		}
		return null;
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
}
