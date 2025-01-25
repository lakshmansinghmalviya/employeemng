package com.ls.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ls.project.response.Message;
import com.ls.project.response.UnifiedResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<UnifiedResponse<Message>> handleRuntimeException(RuntimeException ex) {
		UnifiedResponse<Message> response = new UnifiedResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Something went wrong" + ex.getMessage(), new Message("error"));
		return ResponseEntity.ok(response);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleGeneralException(Exception ex) {
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("message", "An unexpected error occurred.");
		mav.addObject("details", ex.getMessage());
		return mav;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
		model.addAttribute("message", "Invalid request.");
		model.addAttribute("details", ex.getMessage());
		return "error";
	}
}
