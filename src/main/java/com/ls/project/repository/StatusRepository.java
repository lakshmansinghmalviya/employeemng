package com.ls.project.repository;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ls.project.model.Status;

@Repository
public class StatusRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Status createStatus(Status status) {
		String insertSql = "INSERT INTO allStatus (mobile, msgId, status) " + "VALUES (?, ?, ?)";

		System.out.println("Status===   " + status);

		String currentTime = (status.getTime() != null) ? status.getTime()
				: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		int rowsAffected = jdbcTemplate.update(insertSql, status.getMobile(), status.getMsgId(), status.getStatus());

		if (rowsAffected <= 0) {
			throw new RuntimeException("Status Not Created");
		}
		return status;
	}
}
