package com.ls.project.mapper;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ls.project.model.Number;

public class NumberRowMapper implements RowMapper<Number> {
	@Override
	public Number mapRow(ResultSet rs, int rowNum) throws SQLException {
		Number number = new Number();
		number.setId(rs.getLong("id"));
		number.setSenderId(rs.getLong("senderId"));
		number.setMobile(rs.getString("mobile"));
		number.setMsgId(rs.getString("msgId"));
		number.setTime(rs.getString("time"));
		return number;
	}
}
