package com.ls.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.project.repository.NumberRepository;
import com.ls.project.model.Number;

@Service
public class NumberService {

	@Autowired
	private NumberRepository numberRepository;

	public Number createNumber(Number number) {
		return numberRepository.createNumber(number);
	}
}
