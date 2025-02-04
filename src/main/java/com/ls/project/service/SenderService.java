package com.ls.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.project.model.Sender;
import com.ls.project.model.SenderStatusTableDto;
import com.ls.project.repository.SenderRepository;

@Service
public class SenderService {

	@Autowired
	private SenderRepository senderRepository;

	public Sender createSender(Sender sender) {
		return senderRepository.createSender(sender);
	}

	public List<SenderStatusTableDto> getSenderStats() {
		return senderRepository.getSenderStats();
	}
}
