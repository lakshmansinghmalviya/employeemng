package com.ls.project.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	public List<Employee> createEmployees(List<Employee> employees) {
		List<Employee> createdEmployees = new ArrayList<>();
		for (Employee employee : employees) {
			String insertSql = "INSERT INTO employees (id,firstName, lastName, age, email, password, doj, mobile, country, city, street, dept, role) "
					+ "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			System.out.println("EMploye===   " + employee);
			int rowsAffected = jdbcTemplate.update(insertSql, employee.getId(), employee.getFirstName(),
					employee.getLastName(), employee.getAge(), employee.getEmail(), employee.getPassword(),
					employee.getDoj(), employee.getMobile(), employee.getCountry(), employee.getCity(),
					employee.getStreet(), employee.getDept(), employee.getRole().name());
			if (rowsAffected <= 0) {
				throw new RuntimeException("Employee Not Created");
			} else
				createdEmployees.add(employee);
		}
		return createdEmployees;
	}

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

	public Employee updateEmployeeFirstName(Long id, Employee employee) {
		String sql = "UPDATE employees SET firstName = ? WHERE id = ?";
		String hashedPwd = passwordEncoder.encode(employee.getPassword());
		int rowsAffected = jdbcTemplate.update(sql, employee.getFirstName(), id);
		if (rowsAffected == 0) {
			throw new RuntimeException("Employee not found with id: " + id);
		}
		employee.setPassword(hashedPwd);
		return employee;
	}

	public Employee updateEmployeeLastName(Long id, Employee employee) {
		String sql = "UPDATE employees SET lastName = ? WHERE id = ?";
		String hashedPwd = passwordEncoder.encode(employee.getPassword());
		int rowsAffected = jdbcTemplate.update(sql, employee.getLastName(), id);
		if (rowsAffected == 0) {
			throw new RuntimeException("Employee not found with id: " + id);
		}
		employee.setPassword(hashedPwd);
		return employee;
	}

	public Employee updateEmployeeOtherDetails(Long id, Employee employee) {
		String sql = "UPDATE employees SET  age = ?, email = ?, password = ?, doj = ?, mobile = ?, country = ?, city = ?, street = ?, dept = ?, role = ? WHERE id = ?";
		String hashedPwd = passwordEncoder.encode(employee.getPassword());
		int rowsAffected = jdbcTemplate.update(sql, employee.getAge(), employee.getEmail(), hashedPwd,
				employee.getDoj(), employee.getMobile(), employee.getCountry(), employee.getCity(),
				employee.getStreet(), employee.getDept(), employee.getRole().name(), id);

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
			String comingPageNo, String comingPageSize, String offset, String lastPage) {

		if (lastPage != null && !lastPage.isEmpty() && lastPage.equals("true")) {
			return findEmployeesByFiltersForLastPage(city, age, searchQuery, comingPageNo, comingPageSize, offset);
		} else {
			StringBuilder sqlQuery = new StringBuilder("SELECT * FROM employees WHERE 1=1");

			List<Object> params = new ArrayList<>();
			int pageNo = Integer.parseInt(comingPageNo);
			int pageSize = Integer.parseInt(comingPageSize);

			// Filter by age if provided
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

			// Filter by city if provided
			if (city != null && !city.isEmpty()) {
				sqlQuery.append(" AND (city = ?)");
				params.add(city);
			}

			// Apply search query if provided
			if (searchQuery != null && !searchQuery.isEmpty()) {
				sqlQuery.append(" AND (").append("(firstName LIKE CONCAT('%', ?, '%')) OR ")
						.append("(lastName LIKE CONCAT('%', ?, '%')) OR ")
						.append("(email LIKE CONCAT('%', ?, '%')) OR ").append("(doj LIKE CONCAT('%', ?, '%')) OR ")
						.append("(mobile LIKE CONCAT('%', ?, '%')) OR ")
						.append("(country LIKE CONCAT('%', ?, '%')) OR ")
						.append("(street LIKE CONCAT('%', ?, '%')) OR ").append("(city LIKE CONCAT('%', ?, '%')) OR ")
						.append("(dept LIKE CONCAT('%', ?, '%')) OR ").append("(role LIKE CONCAT('%', ?, '%')))");
				for (int i = 0; i < 10; i++) {
					params.add(searchQuery);
				}
			}

			// Add LIMIT and OFFSET for pagination
			int limit = pageSize + 1;
			int offsetValue = pageNo * limit;
			sqlQuery.append("LIMIT ? OFFSET ?");
			params.add(limit);
			params.add(offsetValue);

			System.out.println("The final query: " + sqlQuery.toString());
			System.out.println("Parameters: " + params);

			try {
				List<Employee> employees = jdbcTemplate.query(sqlQuery.toString(), params.toArray(),
						new EmployeeRowMapper());

				if (employees.isEmpty()) {
					return findEmployeesByFiltersForLastPage(city, age, searchQuery, comingPageNo, comingPageSize,
							offset);
				}

				if (employees.size() == 11) {
					return PageResponseBuilder.buildPageResponse(employees, employees.size() - 1, 1, pageNo, pageSize,
							false);
				}

				return PageResponseBuilder.buildPageResponse(employees, employees.size(), 1, pageNo, pageSize, true);
			} catch (Exception e) {
				System.out.println("Error during query execution: " + e.getMessage());
			}
			return null;
		}
	}

	public Integer findTotalRecordsOfEmployeesByFilters(StringBuilder sqlQuery, List<Object> paramsList) {
		List<Object> params = List.copyOf(paramsList);
		String queryString = sqlQuery.toString();
		System.out.println("The query coming to totalRecords to findTotalRecord ....." + queryString);

		// Replace the SELECT * with SELECT COUNT(1) to count records
		queryString = queryString.replaceFirst("(?i)^SELECT \\*", "SELECT COUNT(1)");
		System.out.println("The query after replacing and built for count ....." + queryString);

		try {
			return jdbcTemplate.queryForObject(queryString, params.toArray(), Integer.class);
		} catch (Exception e) {
			System.out.println("Error during query execution: " + e.getMessage());
		}
		return 0;
	}

	public PageResponse<Employee> findEmployeesByFiltersForLastPage(String city, String age, String searchQuery,
			String comingPageNo, String comingPageSize, String offset) {
		// Start building the SQL query
		StringBuilder sqlQuery = new StringBuilder("SELECT * FROM (");
		sqlQuery.append(" SELECT * FROM employees WHERE 1=1");

		List<Object> params = new ArrayList<>();
		int pageSize = Integer.parseInt(comingPageSize);

		// Filter by age if provided
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

		// Filter by city if provided
		if (city != null && !city.isEmpty()) {
			sqlQuery.append(" AND (city = ?)");
			params.add(city);
		}

		// Apply search query if provided
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
		// Log the query before counting total records
		System.out.println("The query before going to findTotalRecord ....." + sqlQuery);

		try {
			// Count total
			sqlQuery.append(") as lastest_employees");
			int totalRecords = findTotalRecordsOfEmployeesByFilters(sqlQuery, params);
			int totalPages = (totalRecords / pageSize) <= 0 ? 1
					: (totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1);

			int lastIndex = sqlQuery.lastIndexOf(") as lastest_employees");
			if (lastIndex != -1) {
				sqlQuery.replace(lastIndex, lastIndex + sqlQuery.length(), "");
			}
			sqlQuery.append(" ORDER BY id DESC LIMIT ?) AS latest_employees ORDER BY id ASC");
			params.add(pageSize);

			System.out.println("Final query before executing...." + sqlQuery.toString());
			System.out.println("Final params before executing...." + params);

			// Execute the query and fetch employees
			List<Employee> employees = jdbcTemplate.query(sqlQuery.toString(), params.toArray(),
					new EmployeeRowMapper());
			return PageResponseBuilder.buildPageResponse(employees, employees.size(), totalPages, totalPages, pageSize,
					true);
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
