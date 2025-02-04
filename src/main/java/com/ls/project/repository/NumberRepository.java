package com.ls.project.repository;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ls.project.model.Number;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NumberRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Number createNumber(Number number) {
		// At repository layer
		System.out.println("The value of the number is at repo === " + number);

		String insertSql = "INSERT INTO numbers (senderId, mobile) " + "VALUES (?, ?)";

		System.out.println("Number===   " + number);
		String currentTime = (number.getTime() != null) ? number.getTime()
				: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		// Execute the insert query
		int rowsAffected = jdbcTemplate.update(insertSql, number.getSenderId(), number.getMobile());

		if (rowsAffected <= 0) {
			throw new RuntimeException("Number Not Created");
		}

		return number;
	}
}
