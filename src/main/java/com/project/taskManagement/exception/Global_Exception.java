package com.project.taskManagement.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.taskManagement.resHelper.API_Response;

public class Global_Exception {

	@ExceptionHandler(SourceNotFoundException.class)
	public ResponseEntity<API_Response> resourceNotFoundExceptionHandler(SourceNotFoundException ex) {
		String message = ex.getMessage();
		API_Response apiResponse = new API_Response(message, false);
		return new ResponseEntity<API_Response>(apiResponse, HttpStatus.NOT_FOUND);

	}

	

	@ExceptionHandler(API_Exception.class)
	public ResponseEntity<API_Response> handleApiException(API_Exception ex) {
		String message = ex.getMessage();
		API_Response apiResponse = new API_Response(message, true);
		return new ResponseEntity<API_Response>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<API_Response> handleUserException(UserException ex) {
		String message = ex.getMessage();
		API_Response apiResponse = new API_Response(message, true);
		return new ResponseEntity<API_Response>(apiResponse, HttpStatus.BAD_REQUEST);
	}
}
