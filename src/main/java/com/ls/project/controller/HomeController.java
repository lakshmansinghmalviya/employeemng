package com.ls.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "redirect:/login";
	}

	@RequestMapping("/login")
	public String login() {
		return "Login";
	}

	@RequestMapping("/test")
	public String testBootstrap() {
		return "Test";
	}
}
