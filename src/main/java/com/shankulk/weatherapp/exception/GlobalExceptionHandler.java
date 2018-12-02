package com.shankulk.weatherapp.exception;

import java.net.URISyntaxException;
import java.net.UnknownHostException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice(basePackages = {"com.shankulk.weatherapp.controller"})
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorInfo handleException(IllegalArgumentException e) {
		if(e.getMessage().equals("Missing City"))
			return new ErrorInfo("Please consider sending valid city query parameter in the request.");
		return new ErrorInfo(e.getMessage());
	}
	
	@ExceptionHandler(URISyntaxException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorInfo handleException(URISyntaxException e) {
		return new ErrorInfo(e.getMessage() + ", Ensure that URL has no spaces.");
	}
	
	@ExceptionHandler(UnknownHostException.class)
	@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
	public @ResponseBody ErrorInfo hendleException(UnknownHostException e) {
		return new ErrorInfo(e.getMessage() + ", Please check your internet connectivity.");
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorInfo handleException(HttpClientErrorException e) {
		return new ErrorInfo(e.getMessage() + ", City name that you have entered may not exist.");
	}
	
	//Generic Exception Handler
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorInfo handleException(Exception e) {
		return new ErrorInfo(e.getMessage());
	}
	
	
}
