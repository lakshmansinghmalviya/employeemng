package com.ls.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.project.model.Status;
import com.ls.project.repository.SenderRepository;
import com.ls.project.repository.StatusRepository;

@Service
public class StatusService {

	@Autowired
	private StatusRepository statusRepository;

	public Status createStatus(Status status) {
		return statusRepository.createStatus(status);
	}
}
