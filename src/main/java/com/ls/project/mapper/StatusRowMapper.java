package com.ls.project.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.ls.project.model.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusRowMapper implements RowMapper<Status> {
	@Override
	public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
		Status status = new Status();
		status.setId(rs.getLong("id"));
		status.setMobile(rs.getString("mobile"));
		status.setMsgId(rs.getString("msgId"));
		status.setStatus(rs.getString("status"));
		status.setTime(rs.getString("time"));
		return status;
	}
}
