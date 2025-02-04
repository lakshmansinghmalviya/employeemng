package com.ls.project.repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ls.project.mapper.SenderStatesTableRowMapper;
import com.ls.project.model.Sender;
import com.ls.project.model.SenderStatusTableDto;

@Repository
public class SenderRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Sender createSender(Sender sender) {
		System.out.println("The value of the sender is at repo === " + sender);

		String insertSql = "INSERT INTO senders (userId, content, msgCount, time) " + "VALUES (?, ?, ?, ?)";

		String currentTime = (sender.getTime() != null) ? sender.getTime()
				: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		int rowsAffected = jdbcTemplate.update(insertSql, sender.getUserId(), sender.getContent(), sender.getMsgCount(),
				currentTime);

		if (rowsAffected <= 0) {
			throw new RuntimeException("Sender Not Created");
		}
		return sender;
	}

	public List<SenderStatusTableDto> getSenderStats() {
		String sql = """
					SELECT
				    s.id AS id,
				    s.userId AS userId,
				    s.content AS content,
				    s.msgCount AS count,
				    COUNT(DISTINCT n.msgId) AS sentCount,
				    COUNT(DISTINCT a.mobile) AS receivedCount,
				    ROUND((COUNT(DISTINCT n.msgId) * 100.0) / NULLIF(s.msgCount, 0), 2) AS sentPercentage,
				    ROUND((COUNT(DISTINCT a.mobile) * 100.0) / NULLIF(s.msgCount, 0), 2) AS receivedPercentage,
				    s.time AS time
				FROM senders s
				LEFT JOIN numbers n ON s.id = n.senderId
				LEFT JOIN allStatus a ON n.mobile = a.mobile
				GROUP BY s.id, s.userId, s.content, s.msgCount, s.time
				ORDER BY s.id
				""";
		return jdbcTemplate.query(sql, new SenderStatesTableRowMapper());
	}
}
