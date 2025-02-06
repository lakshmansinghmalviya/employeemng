package com.ls.project.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.ls.project.model.SenderStatusTableDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SenderStatesTableRowMapper implements RowMapper<SenderStatusTableDto> {

	@Override
	public SenderStatusTableDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		SenderStatusTableDto stats = new SenderStatusTableDto();
		stats.setId(rs.getLong("id"));
		stats.setUserId(rs.getLong("userId"));
		stats.setContent(rs.getString("content"));
		stats.setCount(rs.getLong("count"));
		stats.setSentCount(rs.getLong("sentCount"));
		stats.setReceivedCount(rs.getLong("receivedCount"));
		stats.setSentPercentage(rs.getDouble("sentPercentage"));
		stats.setReceivedPercentage(rs.getDouble("receivedPercentage"));
		stats.setTime(rs.getString("time"));
		return stats;
	}
}
