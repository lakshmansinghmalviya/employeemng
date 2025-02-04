package com.ls.project.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SessionFilter implements Filter {
	private Logger logger = LoggerFactory.getLogger(SessionFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("Hello from: session filter lets check the session here of the user == " + request.getLocalAddr());
		System.out.println("Hello from: session filter lets check the session here of the user");
		chain.doFilter(request, response);
	}
}
