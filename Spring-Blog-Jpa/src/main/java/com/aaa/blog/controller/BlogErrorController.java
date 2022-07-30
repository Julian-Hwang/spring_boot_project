package com.aaa.blog.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogErrorController implements ErrorController {

	//@Override
	public String getErrorPath() {
		
		return "/errors";
	}
	
	@RequestMapping("/errors")
	public String errorHandle(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if(status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			
			if(statusCode.equals(HttpStatus.BAD_REQUEST.value())) {
				return "/errors/400";
			} else if(statusCode.equals(HttpStatus.NOT_FOUND.value())) {
				return "/errors/404";
			} else if(statusCode.equals(HttpStatus.FORBIDDEN.value())) {
				return "/errors/403";
			} else if(statusCode.equals(HttpStatus.INTERNAL_SERVER_ERROR.value())) {
				return "/errors/500";
			} 
		}
		return "errors/default";
	}
}
