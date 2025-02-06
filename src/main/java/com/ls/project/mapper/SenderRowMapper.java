package com.ls.project.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.ls.project.model.Sender;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SenderRowMapper implements RowMapper<Sender> {
	@Override
	public Sender mapRow(ResultSet rs, int rowNum) throws SQLException {
		Sender sender = new Sender();
		sender.setId(rs.getLong("id"));
		sender.setUserId(rs.getLong("userId"));
		sender.setContent(rs.getString("content"));
		sender.setMsgCount(rs.getLong("msgCount"));
		sender.setTime(rs.getString("time"));
		return sender;
	}
}
