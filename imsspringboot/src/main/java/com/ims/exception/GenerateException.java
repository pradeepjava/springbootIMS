package com.ims.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenerateException {

	@ExceptionHandler(ObjectAlreadyExistsInDBException.class)
	public ResponseEntity<Object> getAlreadyExistsExceptionResponse(ObjectAlreadyExistsInDBException exception) {
		ErrorMessage errorMessage=new ErrorMessage(new Date(), exception.getMessage());
		return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
	}
}
