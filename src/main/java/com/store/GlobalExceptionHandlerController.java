package com.store;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.store.exceptions.UserAlreadyExistException;
import com.store.models.Response;
import com.store.utils.CustomHttpStatusBuilder;

import io.jsonwebtoken.ExpiredJwtException;

//@ControllerAdvice
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity<Object> handleNullPointerException(NullPointerException e,WebRequest request) {
		return CustomHttpStatusBuilder.buildResponseEntity(null, null);
	}
	
	@ResponseStatus(code = HttpStatus.FOUND)
	@ExceptionHandler(value = UserAlreadyExistException.class)
	public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException e,WebRequest request) {
		return CustomHttpStatusBuilder.buildResponseEntity(buildResponseObject(null, 200,e.getMessage()), null);
	}
	
	@ResponseStatus(code = HttpStatus.FOUND)
	@ExceptionHandler(value = ExpiredJwtException.class)
	public ResponseEntity<Object> handleJWTExpiredException(ExpiredJwtException e,WebRequest request) {
		return CustomHttpStatusBuilder.buildResponseEntity(buildResponseObject(null, 401,e.getMessage()), null);
	}
	
	public static Response buildResponseObject(Object obj,int status,String message) {
		return new Response(obj, status, message);
	}
}
