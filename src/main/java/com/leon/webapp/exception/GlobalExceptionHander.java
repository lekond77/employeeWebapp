package com.leon.webapp.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class GlobalExceptionHander {

	@ExceptionHandler(TokenNotFoundException.class)
	public ModelAndView handleTokenNotFoundException(TokenNotFoundException ex) {

		ModelAndView modelAndView = new ModelAndView("redirect:/login");

		modelAndView.addObject("error", ex.getMessage());
		return modelAndView;
	}

}
