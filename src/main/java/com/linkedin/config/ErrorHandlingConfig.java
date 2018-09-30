package com.linkedin.config;

import com.linkedin.errors.HttpError;
import com.linkedin.errors.NotAuthorizedException;
import com.linkedin.errors.ObjectNotFoundException;
import com.linkedin.errors.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.MessageFormat;

@ControllerAdvice
public class ErrorHandlingConfig {

  @ExceptionHandler(value = {WrongPasswordException.class})
  public ResponseEntity wrongPassword(WrongPasswordException ex) {
	String message = "Wrong password";
	HttpError error = new HttpError(message, 1);
	return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {ObjectNotFoundException.class})
  public ResponseEntity objectNotFound(ObjectNotFoundException ex) {
	String message = MessageFormat.format("Object of class {0} with id {1} not found", ex.getTargetClass().getName(), ex.getId().toString());
	HttpError error = new HttpError(message, 1);
	return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {NotAuthorizedException.class})
  public ResponseEntity NotAuthorized(NotAuthorizedException ex) {
	String message = MessageFormat.format("Not Authorized to make changes or delete this Object of class {0}", ex.getTargetClass().getName());
	HttpError error = new HttpError(message, 1);
	return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
  }
}