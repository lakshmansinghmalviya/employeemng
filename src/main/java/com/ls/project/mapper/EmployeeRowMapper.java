package com.ls.project.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ls.project.model.Employee;

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
		employee.setRoles(rs.getString("roles"));
		employee.setServices(rs.getString("services"));
		employee.setActive(rs.getBoolean("active"));
		return employee;
	}
}