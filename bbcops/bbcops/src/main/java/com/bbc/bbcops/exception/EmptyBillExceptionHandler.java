package com.bbc.bbcops.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.bbc.bbcops.dto.ErrorResponse;

@ControllerAdvice
public class EmptyBillExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleEmptyBillException(EmptyBillException ex, WebRequest request){
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}

}
