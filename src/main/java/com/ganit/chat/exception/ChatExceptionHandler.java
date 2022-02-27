package com.ganit.chat.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ganit.chat.util.ResponseMessage;


@ControllerAdvice
@RestController
public class ChatExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ResponseMessage> handleEntityNotFoundException(EntityNotFoundException exc,WebRequest req) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Entity not found!"));
	}
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ResponseMessage> handleUserAlreadyExistsException(UserAlreadyExistsException exc,WebRequest req) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Entity already exists!"));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessage> handleEntityNotFoundException(Exception exc,WebRequest req) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Internal error!"));
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(ex.getMessage()));
	}
	
}