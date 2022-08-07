package com.store.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.store.models.Response;

public class CustomHttpStatusBuilder {
	
	public static ResponseEntity<Object> buildResponseEntity(Response response, MultiValueMap<String, String> headers){
		switch(response.getStatus()) {
		case 100:
			return new ResponseEntity<Object>(response, headers, HttpStatus.CONTINUE);
		case 101:
			return new ResponseEntity<Object>(response, headers, HttpStatus.SWITCHING_PROTOCOLS);
		case 102:
			return new ResponseEntity<Object>(response, headers, HttpStatus.PROCESSING);
		case 103:
			return new ResponseEntity<Object>(response, headers, HttpStatus.TOO_EARLY);
		case 200:
			return new ResponseEntity<Object>(response, headers, HttpStatus.OK);
		case 201:
			return new ResponseEntity<Object>(response, headers, HttpStatus.CREATED);
		case 202:
			return new ResponseEntity<Object>(response, headers, HttpStatus.ACCEPTED);
		case 203:
			return new ResponseEntity<Object>(response, headers, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		case 204:
			return new ResponseEntity<Object>(response, headers, HttpStatus.NO_CONTENT);
		case 205:
			return new ResponseEntity<Object>(response, headers, HttpStatus.RESET_CONTENT);
		case 206:
			return new ResponseEntity<Object>(response, headers, HttpStatus.PARTIAL_CONTENT);
		case 207:
			return new ResponseEntity<Object>(response, headers, HttpStatus.MULTI_STATUS);
		case 208:
			return new ResponseEntity<Object>(response, headers, HttpStatus.ALREADY_REPORTED);
		case 226:
			return new ResponseEntity<Object>(response, headers, HttpStatus.IM_USED);
		case 300:
			return new ResponseEntity<Object>(response, headers, HttpStatus.MULTIPLE_CHOICES);
		case 301:
			return new ResponseEntity<Object>(response, headers, HttpStatus.MOVED_PERMANENTLY);
		case 302:
			return new ResponseEntity<Object>(response, headers, HttpStatus.FOUND);
		case 303:
			return new ResponseEntity<Object>(response, headers, HttpStatus.SEE_OTHER);
		case 304:
			return new ResponseEntity<Object>(response, headers, HttpStatus.NOT_MODIFIED);
		case 307:
			return new ResponseEntity<Object>(response, headers, HttpStatus.TEMPORARY_REDIRECT);
		case 308:
			return new ResponseEntity<Object>(response, headers, HttpStatus.PERMANENT_REDIRECT);
		case 400:
			return new ResponseEntity<Object>(response, headers, HttpStatus.BAD_REQUEST);
		case 401:
			return new ResponseEntity<Object>(response, headers, HttpStatus.UNAUTHORIZED);
		case 402:
			return new ResponseEntity<Object>(response, headers, HttpStatus.PAYMENT_REQUIRED);
		case 403:
			return new ResponseEntity<Object>(response, headers, HttpStatus.FORBIDDEN);
		case 404:
			return new ResponseEntity<Object>(response, headers, HttpStatus.NOT_FOUND);
		case 405:
			return new ResponseEntity<Object>(response, headers, HttpStatus.METHOD_NOT_ALLOWED);
		case 406:
			return new ResponseEntity<Object>(response, headers, HttpStatus.NOT_ACCEPTABLE);
		default:
			return new ResponseEntity<Object>(response, headers, HttpStatus.OK);
		}
		
	}

}
